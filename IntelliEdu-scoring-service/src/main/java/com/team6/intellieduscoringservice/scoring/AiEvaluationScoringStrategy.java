package com.team6.intellieduscoringservice.scoring;

import cn.hutool.json.JSONUtil;
import com.team6.intellieduapi.client.ApplicationClient;
import com.team6.intelliedumodel.dto.question.QuestionContent;
import com.team6.intelliedumodel.dto.scoring.AiDoScoreRequest;
import com.team6.intelliedumodel.entity.AnswerRecord;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.entity.Question;
import com.team6.intelliedumodel.vo.QuestionVo;
import com.team6.intellieduscoringservice.ai.AiManager;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * AI 测评类应用评分策略
 */
@ScoringStrategyConfig(appType = 1, scoringStrategy = 1)
public class AiEvaluationScoringStrategy implements ScoringStrategy {

    @Resource
    private ApplicationClient applicationClient;

    @Resource
    private AiManager aiManager;

    private static final String AI_EVALUATION_SCORING_SYSTEM_MESSAGE = "You are a meticulous evaluation expert. I will provide you with the following information:\n" +
            "```\n" +
            "Application name,\n" +
            "【【【Application description】】】,\n" +
            "List of questions and user answers: Format [{\"title\": \"Question\",\"answer\": \"User's answer\"}]\n" +
            "```\n" +
            "\n" +
            "Please evaluate the user based on the provided information, following these steps:\n" +
            "1. Requirements: You need to give a clear evaluation result, including an evaluation name (as short as possible) and an evaluation description (as detailed as possible, with more than 200 words).\n" +
            "2. Strictly follow the JSON format below for the output of the evaluation name and description:\n" +
            "```\n" +
            "{\"resultName\": \"Evaluation name\", \"resultDetail\": \"Evaluation description\"}\n" +
            "```\n" +
            "3. The returned format must be a JSON object.";

    private String getAiEvaluationScoringUserMessage(Application application, List<QuestionContent> questionContentList, List<String> answerList) {
        StringBuilder userMessage = new StringBuilder();
        userMessage.append(application.getAppName()).append("\n");
        userMessage.append(application.getDescription()).append("\n");
        List<AiDoScoreRequest> aiDoScoreRequestList = new ArrayList<>();
        for (int i = 0; i < questionContentList.size(); i++) {
            AiDoScoreRequest aiDoScoreRequest = new AiDoScoreRequest();
            aiDoScoreRequest.setTitle(questionContentList.get(i).getTitle());
            aiDoScoreRequest.setUserAnswer(answerList.get(i));
            aiDoScoreRequestList.add(aiDoScoreRequest);
        }
        userMessage.append(JSONUtil.toJsonStr(aiDoScoreRequestList));
        return userMessage.toString();
    }


    @Override
    public AnswerRecord doScore(List<String> answerList, Application application) throws Exception {
        Long appId = application.getId();
        // 1. 根据 appId 查询到对应题目
        Question question = applicationClient.getQuestionById(appId).getData();
        QuestionVo questionVo = QuestionVo.objToVo(question);
        List<QuestionContent> questionContent = questionVo.getQuestions();
        // 2. 调用 AI 获取结果
        // 封装 Prompt
        String userMessage = getAiEvaluationScoringUserMessage(application, questionContent, answerList);
        // AI 生成
        String result = aiManager.doRequest(AI_EVALUATION_SCORING_SYSTEM_MESSAGE, userMessage,0.5);
        // 结果处理
        int start = result.indexOf("{");
        int end = result.lastIndexOf("}");
        String json = result.substring(start, end + 1);
        // 3. 构造返回值，填充答案对象的属性
        AnswerRecord answerRecord = JSONUtil.toBean(json, AnswerRecord.class);
        answerRecord.setAppId(appId);
        answerRecord.setAppType(application.getType());
        answerRecord.setStrategy(application.getStrategy());
        answerRecord.setAnswers(answerList);
        return answerRecord;
    }

}
