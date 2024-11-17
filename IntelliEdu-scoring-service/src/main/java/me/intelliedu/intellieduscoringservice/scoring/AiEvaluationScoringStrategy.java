package me.intelliedu.intellieduscoringservice.scoring;

import cn.hutool.json.JSONUtil;
import me.intelliedu.intellieduapi.client.ApplicationClient;
import me.intelliedu.intellieducommon.ai.AiManager;
import me.intelliedu.intelliedumodel.dto.question.QuestionContent;
import me.intelliedu.intelliedumodel.dto.scoring.AiDoScoreRequest;
import me.intelliedu.intelliedumodel.entity.AnswerRecord;
import me.intelliedu.intelliedumodel.entity.Application;
import me.intelliedu.intelliedumodel.entity.Question;
import me.intelliedu.intelliedumodel.vo.QuestionVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static me.intelliedu.intellieducommon.constant.Constant.AI_EVALUATION_SCORING_SYSTEM_MESSAGE;

/**
 * AI 测评类应用评分策略
 */
@ScoringStrategyConfig(appType = 1, scoringStrategy = 1)
public class AiEvaluationScoringStrategy implements ScoringStrategy {

    @Resource
    private ApplicationClient applicationClient;

    @Resource
    private AiManager aiManager;


    private String getAiEvaluationScoringUserMessage(Application application, List<QuestionContent> questionContentList, List<String> answerList) {
        StringBuilder userMessage = new StringBuilder();
        userMessage.append("Application name: ").append(application.getAppName()).append("\n");
        userMessage.append("Application description: ").append(application.getDescription()).append("\n");
        List<AiDoScoreRequest> aiDoScoreRequestList = new ArrayList<>();
        for (int i = 0; i < questionContentList.size(); i++) {
            AiDoScoreRequest aiDoScoreRequest = new AiDoScoreRequest();
            aiDoScoreRequest.setTitle(questionContentList.get(i).getTitle());
            aiDoScoreRequest.setUserAnswer(answerList.get(i));
            aiDoScoreRequestList.add(aiDoScoreRequest);
        }
        userMessage.append("List of questions and user answers: ").append(JSONUtil.toJsonStr(aiDoScoreRequestList));
        return userMessage.toString();
    }


    @Override
    public AnswerRecord doScore(List<String> answerList, Application application) throws Exception {
        Long appId = application.getId();
        // 1. 根据 appId 查询到对应题目
        Question question = applicationClient.getQuestionByAppId(appId).getData();
        QuestionVo questionVo = QuestionVo.objToVo(question);
        List<QuestionContent> questionContent = questionVo.getQuestions();
        // 2. 调用 AI 获取结果
        // 封装 Prompt
        String userMessage = getAiEvaluationScoringUserMessage(application, questionContent, answerList);
        // AI 生成
        String result = aiManager.doRequest(AI_EVALUATION_SCORING_SYSTEM_MESSAGE, userMessage, 1);
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
