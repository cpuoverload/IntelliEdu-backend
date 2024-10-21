package com.team6.intellieduanswerrecordservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team6.intellieduanswerrecordservice.mapper.AnswerRecordMapper;
import com.team6.intellieduanswerrecordservice.service.AnswerRecordService;
import com.team6.intellieduapi.client.ApplicationClient;
import com.team6.intellieduapi.client.ScoringClient;
import com.team6.intellieduapi.client.UserClient;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListAnswerRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListMyAnswerRequest;
import com.team6.intelliedumodel.dto.scoring.DoScoreRequest;
import com.team6.intelliedumodel.entity.AnswerRecord;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.vo.AnswerRecordVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author passion
 * @description 针对表【answer_record(Answer Record)】的数据库操作Service实现
 * @createDate 2024-10-15 16:41:48
 */
@Service
public class AnswerRecordServiceImpl extends ServiceImpl<AnswerRecordMapper, AnswerRecord>
        implements AnswerRecordService {

    @Resource
    UserClient userClient;

    @Resource
    ApplicationClient applicationClient;

    @Resource
    ScoringClient scoringClient;

    @Resource
    AnswerRecordMapper answerRecordMapper;


    public void validate(AnswerRecord answerRecord) {
        if (answerRecord == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Long appId = answerRecord.getAppId();
        List<String> answers = answerRecord.getAnswers();

        if (appId != null && appId <= 0) {
            throw new BusinessException(Err.PARAMS_ERROR, "appId is invalid");
        }
        if (answers == null || answers.isEmpty()) {
            throw new BusinessException(Err.PARAMS_ERROR, "answers is empty");
        }

        Application application = applicationClient.getApplicationById(appId).getData();

        if (application == null) {
            throw new BusinessException(Err.PARAMS_ERROR, "application not found");
        }

        if (application.getAuditStatus() != 1) {
            throw new BusinessException(Err.PARAMS_ERROR, "application not audited");
        }


    }

    @Override
    public Boolean addMyAnswerRecord(AnswerRecord answerRecord, HttpServletRequest request) {
        // 1. validation
        validate(answerRecord);

        // 2. set not null fields
        Long userId = userClient.getLoginUserId(request);
        answerRecord.setUserId(userId);
        Application application = applicationClient.getApplicationById(answerRecord.getAppId()).getData();

        int saveResult = answerRecordMapper.insert(answerRecord);
        if (saveResult <= 0) {
            throw new BusinessException(Err.SYSTEM_ERROR, "save answer record failed");
        }
        Long answerRecordId = answerRecord.getId();

        // 3. do score
        try {
            DoScoreRequest doScoreRequest = new DoScoreRequest();
            doScoreRequest.setAnswerList(answerRecord.getAnswers());
            doScoreRequest.setApplication(application);
            AnswerRecord userAnswerWithResult = scoringClient.doScore(doScoreRequest);
            // 这里为什么还要 setid？
            // 评分结果也是UserAnswer类，先前的 UserAnswer 已经保存到数据库中，评分是更新那条记录的过程，因此需要保持一致的 ID
            userAnswerWithResult.setId(answerRecordId);
            answerRecordMapper.updateById(userAnswerWithResult);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(Err.SYSTEM_ERROR, "do score failed");
        }


        // 3. add application
        return true;
    }

    @Override
    public Page<AnswerRecordVo> listMyAnswerRecord(ListMyAnswerRequest listMyAnswerRequest, HttpServletRequest request) {
        return null;
    }

    @Override
    public Page<AnswerRecordVo> listAnswerRecord(ListAnswerRequest listAnswerRequest) {
        return null;
    }

    @Override
    public Boolean deleteAnswerRecord(IdRequest idRequest) {
        return null;
    }
}




