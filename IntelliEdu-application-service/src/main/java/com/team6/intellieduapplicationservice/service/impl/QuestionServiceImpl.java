package com.team6.intellieduapplicationservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team6.intellieduapi.client.UserClient;
import com.team6.intellieduapplicationservice.mapper.QuestionMapper;
import com.team6.intellieduapplicationservice.service.QuestionService;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.question.GetMyQuestionRequest;
import com.team6.intelliedumodel.dto.question.GetPublicQuestionRequest;
import com.team6.intelliedumodel.dto.question.ListQuestionRequest;
import com.team6.intelliedumodel.dto.question.QuestionContent;
import com.team6.intelliedumodel.entity.Question;
import com.team6.intelliedumodel.vo.QuestionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author passion
* @description 针对表【question(Question)】的数据库操作Service实现
* @createDate 2024-10-17 15:51:37
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService {

    @Resource
    private UserClient userClient;

    public void validate(Question question) {
        if (question == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Long appId = question.getAppId();
        List<QuestionContent> questions = question.getQuestions();
        if (appId == null || appId <= 0) {
            throw new BusinessException(Err.PARAMS_ERROR, "Application id is invalid");
        }
        if (questions == null || questions.isEmpty()) {
            throw new BusinessException(Err.PARAMS_ERROR, "Question list is empty");
        }
    }

    /**
     * convert entity to vo
     * @param question
     * @return
     */
    public QuestionVo entityToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVo questionVo = new QuestionVo();
        BeanUtils.copyProperties(question, questionVo);
        return questionVo;
    }

    @Override
    public QuestionVo getPublicQuestion(GetPublicQuestionRequest getPublicQuestionRequest) {
        // 1. validation
        if (getPublicQuestionRequest == null || getPublicQuestionRequest.getAppId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // 2. query
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("app_id", getPublicQuestionRequest.getAppId());
        Question question = getOne(queryWrapper);

        // 3. convert entity to vo
        return entityToVo(question);
    }

    @Override
    public Boolean addMyQuestion(Question question, HttpServletRequest request) {
        // 1. validation
        validate(question);

        // 2. set user id
        Long userId = userClient.getLoginUserId(request);
        question.setUserId(userId);

        // 3. add question
        return save(question);
    }

    @Override
    public QuestionVo getMyQuestion(GetMyQuestionRequest getMyQuestionRequest, HttpServletRequest request) {
        // 1. validation
        if (getMyQuestionRequest == null || getMyQuestionRequest.getAppId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // 2. query
        Long userId = userClient.getLoginUserId(request);
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("app_id", getMyQuestionRequest.getAppId())
                .eq("user_id", userId);
        Question question = getOne(queryWrapper);

        // 3. convert entity to vo
        return entityToVo(question);
    }

    @Override
    public Boolean updateMyQuestion(Question question, HttpServletRequest request) {
        // 1. validation
        if (question == null || question.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // 2. check if the question exists
        Long userId = userClient.getLoginUserId(request);
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Question::getId, question.getId())
                .eq(Question::getUserId, userId);
        Question oldQuestion = getOne(queryWrapper);
        if (oldQuestion == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. update question
        return updateById(question);
    }

    @Override
    public Boolean deleteMyQuestion(IdRequest idRequest, HttpServletRequest request) {
        // 1. validation
        if (idRequest == null || idRequest.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // 2. check if the question exists
        Long userId = userClient.getLoginUserId(request);
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Question::getId, idRequest.getId())
                .eq(Question::getUserId, userId);
        Question question = getOne(queryWrapper);
        if (question == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. delete question
        return removeById(idRequest.getId());
    }

    @Override
    public Page<QuestionVo> listQuestion(ListQuestionRequest listQuestionRequest) {
        // 1. set page info
        Long current = listQuestionRequest.getCurrent();
        Long pageSize = listQuestionRequest.getPageSize();
        IPage<Question> page = new Page<>(current, pageSize);

        // 2. paged query
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(listQuestionRequest.getId() != null, "id", listQuestionRequest.getId())
                .eq(listQuestionRequest.getAppId() != null, "app_id", listQuestionRequest.getAppId())
                .eq(listQuestionRequest.getUserId() != null, "user_id", listQuestionRequest.getUserId())
                .orderBy(listQuestionRequest.getSortField() != null, listQuestionRequest.getIsAscend(), StrUtil.toUnderlineCase(listQuestionRequest.getSortField()));
        IPage<Question> questionPage = page(page, queryWrapper);

        // 3. convert entity to vo
        Page<QuestionVo> questionVoPage = new Page<>(current, pageSize, questionPage.getTotal());
        List<QuestionVo> voRecords = questionPage.getRecords().stream().map(this::entityToVo).collect(Collectors.toList());
        questionVoPage.setRecords(voRecords);

        return questionVoPage;
    }

    @Override
    public Boolean updateQuestion(Question question) {
        // 1. validation
        if (question == null || question.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // 2. check if the question exists
        Question oldQuestion = getById(question.getId());
        if (oldQuestion == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. update question
        return updateById(question);
    }

    @Override
    public Boolean deleteQuestion(IdRequest idRequest) {
        // 1. validation
        if (idRequest == null || idRequest.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // check if the question exists
        Question question = getById(idRequest.getId());
        if (question == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. delete question
        return removeById(idRequest.getId());
    }
}




