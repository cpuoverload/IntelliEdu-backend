package com.team6.intellieduscoringservice.scoring;


import com.team6.intelliedumodel.entity.AnswerRecord;
import com.team6.intelliedumodel.entity.Application;

import java.util.List;

/**
 * 评分策略
 */
public interface ScoringStrategy {

    /**
     * 执行评分
     *
     * @param answers
     * @param application
     * @return
     * @throws Exception
     */
    AnswerRecord doScore(List<String> answers, Application application) throws Exception;
}