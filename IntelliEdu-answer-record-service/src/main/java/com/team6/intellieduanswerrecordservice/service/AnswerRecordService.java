package com.team6.intellieduanswerrecordservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListAnswerRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListMyAnswerRequest;
import com.team6.intelliedumodel.entity.AnswerRecord;
import com.team6.intelliedumodel.vo.AnswerRecordVo;

import javax.servlet.http.HttpServletRequest;

/**
* @author passion
* @description 针对表【answer_record(Answer Record)】的数据库操作Service
* @createDate 2024-10-15 16:41:48
*/
public interface AnswerRecordService extends IService<AnswerRecord> {
    Long addMyAnswerRecord(AnswerRecord answerRecord, HttpServletRequest request);

    Page<AnswerRecordVo> listMyAnswerRecord(ListMyAnswerRequest listMyAnswerRequest, HttpServletRequest request);

    AnswerRecordVo getAnswerRecord(Long id);

    Page<AnswerRecordVo> listAnswerRecord(ListAnswerRequest listAnswerRequest);

    Boolean deleteAnswerRecord(IdRequest idRequest);
}
