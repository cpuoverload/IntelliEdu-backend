package com.team6.intellieduapplicationservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieduapplicationservice.service.QuestionService;
import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.question.*;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.entity.Question;
import com.team6.intelliedumodel.vo.QuestionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

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

    @GetMapping("/get/{id}")
    ApiResponse<Question> getQuestionById(@PathVariable Long id){
        return ApiResponse.success(questionService.getQuestionById(id));
    }
}
