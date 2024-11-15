package com.team6.intellieduapplicationservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieduapplicationservice.service.ApplicationService;
import com.team6.intellieduapplicationservice.service.QuestionService;
import com.team6.intellieducommon.ai.AiManager;
import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.question.*;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.entity.Question;
import com.team6.intelliedumodel.enums.AppType;
import com.team6.intelliedumodel.vo.QuestionVo;
import dev.ai4j.openai4j.chat.ChatCompletionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

import static com.team6.intellieducommon.constant.Constant.GENERATE_EVALUATION_QUESTION_SYSTEM_MESSAGE;
import static com.team6.intellieducommon.constant.Constant.GENERATE_GRADE_QUESTION_SYSTEM_MESSAGE;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @Resource
    private ApplicationService applicationService;


    @Resource
    AiManager aiManager;

    // 获取一个应用的题目列表（不要分页）
    @PostMapping("/get/public")
    public ApiResponse<QuestionVo> getPublicQuestionOfOneApp(@RequestBody GetPublicQuestionRequest getPublicQuestionRequest) {
        if (getPublicQuestionRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        QuestionVo questionVo = questionService.getPublicQuestion(getPublicQuestionRequest);
        return ApiResponse.success(questionVo);
    }

    // 普通用户创建题目
    @PostMapping("/add/me")
    public ApiResponse<Boolean> addMyQuestion(@RequestBody AddMyQuestionRequest addMyQuestionRequest, HttpServletRequest request) {
        if (addMyQuestionRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        if (addMyQuestionRequest.getQuestions() == null || addMyQuestionRequest.getAppId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Question question = new Question();
        BeanUtils.copyProperties(addMyQuestionRequest, question);
        Boolean success = questionService.addMyQuestion(question, request);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }

    // 普通用户查看自己的题目（只允许每次查询一个应用的题目，不要分页）
    @PostMapping("/get/me")
    public ApiResponse<QuestionVo> getMyQuestionOfOneApp(@RequestBody GetMyQuestionRequest getMyQuestionRequest, HttpServletRequest request) {
        if (getMyQuestionRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        QuestionVo questionVo = questionService.getMyQuestion(getMyQuestionRequest, request);
        return ApiResponse.success(questionVo);
    }

    // 普通用户更新题目
    @PostMapping("/update/me")
    public ApiResponse<Boolean> updateMyQuestion(@RequestBody UpdateMyQuestionRequest updateMyQuestionRequest, HttpServletRequest request) {
        if (updateMyQuestionRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Question question = new Question();
        BeanUtils.copyProperties(updateMyQuestionRequest, question);
        Boolean success = questionService.updateMyQuestion(question, request);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }

    // 普通用户删除题目
    @PostMapping("/delete/me")
    public ApiResponse<Boolean> deleteMyQuestion(@RequestBody IdRequest idRequest, HttpServletRequest request) {
        if (idRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Boolean success = questionService.deleteMyQuestion(idRequest, request);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }

    // 管理员查看题目列表
    @PostMapping("/list")
    public ApiResponse<Page<QuestionVo>> listQuestion(@RequestBody ListQuestionRequest listQuestionRequest) {
        if (listQuestionRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Page<QuestionVo> questionVoPage = questionService.listQuestion(listQuestionRequest);
        return ApiResponse.success(questionVoPage);
    }

    // 管理员更新题目
    @PostMapping("/update")
    public ApiResponse<Boolean> updateQuestion(@RequestBody UpdateQuestionRequest updateQuestionRequest) {
        if (updateQuestionRequest == null || updateQuestionRequest.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Question question = new Question();
        BeanUtils.copyProperties(updateQuestionRequest, question);
        Boolean success = questionService.updateQuestion(question);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }

    // 管理员删除题目
    @PostMapping("/delete")
    public ApiResponse<Boolean> deleteQuestion(@RequestBody IdRequest idRequest) {
        if (idRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Boolean success = questionService.deleteQuestion(idRequest);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }

    @GetMapping("/get/{appId}")
    public ApiResponse<Question> getQuestionByAppId(@PathVariable Long appId) {
        return ApiResponse.success(questionService.getQuestionByAppId(appId));
    }


    //region ai生成题目


    /**
     * 生成题目的用户消息
     *
     * @param application
     * @param questionNumber
     * @param optionNumber
     * @return
     */
    private String getGenerateQuestionUserMessage(Application application, int questionNumber, int optionNumber) {
        StringBuilder userMessage = new StringBuilder();
        userMessage.append("Application name: ").append(application.getAppName()).append("\n");
        userMessage.append("Application description: ").append(application.getDescription()).append("\n");
        userMessage.append("Application category: ").append(AppType.fromCode(application.getType()).getDescription() + " type").append("\n");
        userMessage.append("Number of questions to generate: ").append(questionNumber);
        userMessage.append("Number of options per question: ").append(optionNumber);
        return userMessage.toString();
    }


    @GetMapping("/ai_generate/sse")
    public SseEmitter aiGenerateQuestionSse(Long appId, Integer questionNumber, Integer optionNumber) {
        // 调用 AI 生成题目的请求是否为空
        if (appId == null || questionNumber == null || optionNumber == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // 获取应用信息
        Application application = applicationService.getById(appId);
        if (application == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 封装prompt
        String userMessage = getGenerateQuestionUserMessage(application, questionNumber, optionNumber);
        String systemMessage = null;

        //测评类应用系统消息
        if (application.getType() == AppType.EVALUATION.getCode()) {
            systemMessage = GENERATE_EVALUATION_QUESTION_SYSTEM_MESSAGE;
        }
        //得分类应用系统消息
        else if (application.getType() == AppType.GRADE.getCode()) {
            systemMessage = GENERATE_GRADE_QUESTION_SYSTEM_MESSAGE;
        }

        ChatCompletionRequest chatCompletionRequest = aiManager.generalStreamRequest(systemMessage, userMessage, 1);
        //建立 sse 连接对象，0表示永不超时
        SseEmitter emitter = new SseEmitter(0L);

        // 处理 AI 生成题目的请求
        CompletableFuture<String> future = new CompletableFuture<>();

        aiManager.executeChatCompletion(chatCompletionRequest, emitter, future);

        return emitter;


    }
    //endregion
}
