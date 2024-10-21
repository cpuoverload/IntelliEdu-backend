package com.team6.intellieduapplicationservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.question.GetMyQuestionRequest;
import com.team6.intelliedumodel.dto.question.GetPublicQuestionRequest;
import com.team6.intelliedumodel.dto.question.ListQuestionRequest;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.entity.Question;
import com.team6.intelliedumodel.vo.QuestionVo;

import javax.servlet.http.HttpServletRequest;

/**
* @author passion
* @description 针对表【question(Question)】的数据库操作Service
* @createDate 2024-10-17 15:51:37
*/
public interface QuestionService extends IService<Question> {
    QuestionVo getPublicQuestion(GetPublicQuestionRequest getPublicQuestionRequest);

    Boolean addMyQuestion(Question question, HttpServletRequest request);

    QuestionVo getMyQuestion(GetMyQuestionRequest getMyQuestionRequest, HttpServletRequest request);

    Boolean updateMyQuestion(Question question, HttpServletRequest request);

    Boolean deleteMyQuestion(IdRequest idRequest, HttpServletRequest request);

    Page<QuestionVo> listQuestion(ListQuestionRequest listQuestionRequest);

    Boolean updateQuestion(Question question);

    Boolean deleteQuestion(IdRequest idRequest);

    Question getQuestionById(Long id);
}
