package me.intelliedu.intellieduapplicationservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.intelliedu.intellieducommon.utils.IdRequest;
import me.intelliedu.intelliedumodel.dto.question.GetMyQuestionRequest;
import me.intelliedu.intelliedumodel.dto.question.GetPublicQuestionRequest;
import me.intelliedu.intelliedumodel.dto.question.ListQuestionRequest;
import me.intelliedu.intelliedumodel.entity.Question;
import me.intelliedu.intelliedumodel.vo.QuestionVo;

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

    Question getQuestionByAppId(Long id);
}
