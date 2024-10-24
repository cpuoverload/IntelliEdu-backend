package com.team6.intellieduscoringservice.scoring;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.team6.intellieduapi.client.ApplicationClient;
import com.team6.intelliedumodel.dto.question.QuestionContent;
import com.team6.intelliedumodel.entity.AnswerRecord;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.entity.Question;
import com.team6.intelliedumodel.entity.Scoring;
import com.team6.intelliedumodel.vo.QuestionVo;
import com.team6.intellieduscoringservice.service.ScoringService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义测评类应用评分策略
 */
@ScoringStrategyConfig(appType = 1, scoringStrategy = 0)
public class CustomEvaluationScoringStrategy implements ScoringStrategy {

    @Resource
    private ApplicationClient applicationClient;

    @Resource
    private ScoringService scoringService;

    @Override
    public AnswerRecord doScore(List<String> answerList, Application application) throws Exception {
        Long appId = application.getId();
        // 1. 根据 appId 查询到题目和题目结果信息
        Question question = applicationClient.getQuestionByAppId(appId).getData();
        List<Scoring> scoringList = scoringService.list(
                Wrappers.lambdaQuery(Scoring.class)
                        .eq(Scoring::getAppId, appId)
        );

        // 2. 统计用户每个选择对应的属性个数，如 I = 10 个，E = 5 个
        // 初始化一个Map，用于存储每个选项的计数
        Map<String, Integer> optionCountMap = new HashMap<>();

        QuestionVo questionVo = QuestionVo.objToVo(question);
        List<QuestionContent> questionContent = questionVo.getQuestions();

        for (int i = 0; i < questionContent.size(); i++) {
            QuestionContent questionContentDTO = questionContent.get(i);
            String answer = answerList.get(i);  // 从 answerList 中拿到对应题目的答案

            // 遍历题目中的选项
            for (QuestionContent.Option option : questionContentDTO.getOptions()) {
                // 如果答案和选项的 key 匹配
                if (option.getKey().equals(answer)) {
                    String result = option.getEvaluation();

                    // 统计 result
                    optionCountMap.put(result, optionCountMap.getOrDefault(result, 0) + 1);
                    break;  // 跳出循环，避免重复统计
                }
            }
        }


        // 3. 遍历每种评分结果，计算哪个结果的得分更高
        // 初始化最高分数和最高分数对应的评分结果
        int maxScore = 0;
        Scoring maxScoringResult = scoringList.get(0);

        // 遍历评分结果列表
        for (Scoring scoringResult : scoringList) {

            // 计算当前评分结果的分数，[I, E] => [1, 5] => 6
            int score = scoringResult.getResultAttributes().stream()
                    .mapToInt(prop -> optionCountMap.getOrDefault(prop, 0))
                    .sum();

            // 如果分数高于当前最高分数，更新最高分数和最高分数对应的评分结果
            if (score > maxScore) {
                maxScore = score;
                maxScoringResult = scoringResult;
            }
        }

        // 4. 构造返回值，填充答案对象的属性
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAppId(appId);
        answerRecord.setAppType(application.getType());
        answerRecord.setStrategy(application.getStrategy());
        answerRecord.setAnswers(answerList);
        answerRecord.setResultId(maxScoringResult.getId());
        answerRecord.setResultName(maxScoringResult.getResultName());
        answerRecord.setResultDetail(maxScoringResult.getResultDetail());
        answerRecord.setResultImageUrl(maxScoringResult.getResultImageUrl());
        return answerRecord;
    }
}
