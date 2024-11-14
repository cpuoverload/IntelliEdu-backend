package com.team6.intellieduapplicationservice.controller;

import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team6.intellieduapplicationservice.service.ApplicationService;
import com.team6.intellieduapplicationservice.service.QuestionService;
import com.team6.intellieducommon.ai.AiManager;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.question.AddMyQuestionRequest;
import com.team6.intelliedumodel.dto.question.GetMyQuestionRequest;
import com.team6.intelliedumodel.dto.question.GetPublicQuestionRequest;
import com.team6.intelliedumodel.dto.question.ListQuestionRequest;
import com.team6.intelliedumodel.dto.question.UpdateMyQuestionRequest;
import com.team6.intelliedumodel.dto.question.UpdateQuestionRequest;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.entity.Question;
import com.team6.intelliedumodel.vo.QuestionVo;
import dev.ai4j.openai4j.chat.ChatCompletionRequest;
import dev.ai4j.openai4j.chat.FunctionCall;
import dev.ai4j.openai4j.chat.ResponseFormat;
import dev.ai4j.openai4j.chat.ResponseFormatType;
import dev.ai4j.openai4j.chat.ToolChoiceMode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@ContextConfiguration(classes = {QuestionController.class})
@ExtendWith(SpringExtension.class)
class QuestionControllerTest {
    @MockBean
    private AiManager aiManager;

    @MockBean
    private ApplicationService applicationService;

    @Autowired
    private QuestionController questionController;

    @MockBean
    private QuestionService questionService;

    /**
     * Test
     * {@link QuestionController#getPublicQuestionOfOneApp(GetPublicQuestionRequest)}.
     * <p>
     * Method under test:
     * {@link QuestionController#getPublicQuestionOfOneApp(GetPublicQuestionRequest)}
     */
    @Test
    @DisplayName("Test getPublicQuestionOfOneApp(GetPublicQuestionRequest)")
    void testGetPublicQuestionOfOneApp() throws Exception {
        // Arrange
        QuestionVo questionVo = new QuestionVo();
        questionVo.setAppId(1L);
        questionVo.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        questionVo.setId(1L);
        questionVo.setQuestions(new ArrayList<>());
        questionVo.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        questionVo.setUserId(1L);
        when(questionService.getPublicQuestion(Mockito.<GetPublicQuestionRequest>any())).thenReturn(questionVo);

        GetPublicQuestionRequest getPublicQuestionRequest = new GetPublicQuestionRequest();
        getPublicQuestionRequest.setAppId(1L);
        String content = (new ObjectMapper()).writeValueAsString(getPublicQuestionRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/question/get/public")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(questionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"questions\":[],\"appId\":1,\"userId\":1,\"createTime\":0,\"updateTime\":0},\"message"
                                        + "\":\"success\"}"));
    }

    /**
     * Test
     * {@link QuestionController#addMyQuestion(AddMyQuestionRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link QuestionController#addMyQuestion(AddMyQuestionRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyQuestion(AddMyQuestionRequest, HttpServletRequest)")
    void testAddMyQuestion() throws Exception {
        // Arrange
        when(questionService.addMyQuestion(Mockito.<Question>any(), Mockito.<HttpServletRequest>any())).thenReturn(true);

        AddMyQuestionRequest addMyQuestionRequest = new AddMyQuestionRequest();
        addMyQuestionRequest.setAppId(1L);
        addMyQuestionRequest.setQuestions(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(addMyQuestionRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/question/add/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(questionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test
     * {@link QuestionController#updateMyQuestion(UpdateMyQuestionRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link QuestionController#updateMyQuestion(UpdateMyQuestionRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyQuestion(UpdateMyQuestionRequest, HttpServletRequest)")
    void testUpdateMyQuestion() throws Exception {
        // Arrange
        when(questionService.updateMyQuestion(Mockito.<Question>any(), Mockito.<HttpServletRequest>any())).thenReturn(true);

        UpdateMyQuestionRequest updateMyQuestionRequest = new UpdateMyQuestionRequest();
        updateMyQuestionRequest.setId(1L);
        updateMyQuestionRequest.setQuestions(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(updateMyQuestionRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/question/update/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(questionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test
     * {@link QuestionController#getMyQuestionOfOneApp(GetMyQuestionRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link QuestionController#getMyQuestionOfOneApp(GetMyQuestionRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test getMyQuestionOfOneApp(GetMyQuestionRequest, HttpServletRequest)")
    void testGetMyQuestionOfOneApp() throws Exception {
        // Arrange
        QuestionVo questionVo = new QuestionVo();
        questionVo.setAppId(1L);
        questionVo.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        questionVo.setId(1L);
        questionVo.setQuestions(new ArrayList<>());
        questionVo.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        questionVo.setUserId(1L);
        when(questionService.getMyQuestion(Mockito.<GetMyQuestionRequest>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(questionVo);

        GetMyQuestionRequest getMyQuestionRequest = new GetMyQuestionRequest();
        getMyQuestionRequest.setAppId(1L);
        String content = (new ObjectMapper()).writeValueAsString(getMyQuestionRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/question/get/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(questionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"questions\":[],\"appId\":1,\"userId\":1,\"createTime\":0,\"updateTime\":0},\"message"
                                        + "\":\"success\"}"));
    }

    /**
     * Test
     * {@link QuestionController#deleteMyQuestion(IdRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link QuestionController#deleteMyQuestion(IdRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test deleteMyQuestion(IdRequest, HttpServletRequest)")
    void testDeleteMyQuestion() throws Exception {
        // Arrange
        when(questionService.deleteMyQuestion(Mockito.<IdRequest>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(true);

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(idRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/question/delete/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(questionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test {@link QuestionController#listQuestion(ListQuestionRequest)}.
     * <p>
     * Method under test:
     * {@link QuestionController#listQuestion(ListQuestionRequest)}
     */
    @Test
    @DisplayName("Test listQuestion(ListQuestionRequest)")
    void testListQuestion() throws Exception {
        // Arrange
        when(questionService.listQuestion(Mockito.<ListQuestionRequest>any())).thenReturn(new Page<>());

        ListQuestionRequest listQuestionRequest = new ListQuestionRequest();
        listQuestionRequest.setAppId(1L);
        listQuestionRequest.setCurrent(1L);
        listQuestionRequest.setId(1L);
        listQuestionRequest.setIsAscend(true);
        listQuestionRequest.setPageSize(3L);
        listQuestionRequest.setSortField("Sort Field");
        listQuestionRequest.setUserId(1L);
        String content = (new ObjectMapper()).writeValueAsString(listQuestionRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/question/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(questionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"records\":[],\"total\":0,\"size\":10,\"current\":1,\"orders\":[],\"optimizeCountSql\":true,"
                                        + "\"searchCount\":true,\"maxLimit\":null,\"countId\":null,\"pages\":0},\"message\":\"success\"}"));
    }

    /**
     * Test {@link QuestionController#updateQuestion(UpdateQuestionRequest)}.
     * <p>
     * Method under test:
     * {@link QuestionController#updateQuestion(UpdateQuestionRequest)}
     */
    @Test
    @DisplayName("Test updateQuestion(UpdateQuestionRequest)")
    void testUpdateQuestion() throws Exception {
        // Arrange
        when(questionService.updateQuestion(Mockito.<Question>any())).thenReturn(true);

        UpdateQuestionRequest updateQuestionRequest = new UpdateQuestionRequest();
        updateQuestionRequest.setId(1L);
        updateQuestionRequest.setQuestions(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(updateQuestionRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/question/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(questionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test {@link QuestionController#deleteQuestion(IdRequest)}.
     * <p>
     * Method under test: {@link QuestionController#deleteQuestion(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteQuestion(IdRequest)")
    void testDeleteQuestion() throws Exception {
        // Arrange
        when(questionService.deleteQuestion(Mockito.<IdRequest>any())).thenReturn(true);

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(idRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/question/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(questionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test {@link QuestionController#getQuestionByAppId(Long)}.
     * <p>
     * Method under test: {@link QuestionController#getQuestionByAppId(Long)}
     */
    @Test
    @DisplayName("Test getQuestionByAppId(Long)")
    void testGetQuestionByAppId() throws Exception {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);
        when(questionService.getQuestionByAppId(Mockito.<Long>any())).thenReturn(question);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/question/get/42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(questionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"questions\":[],\"appId\":1,\"userId\":1,\"createTime\":0,\"updateTime\":0,\"deleted\""
                                        + ":1},\"message\":\"success\"}"));
    }

    /**
     * Test
     * {@link QuestionController#aiGenerateQuestionSse(Long, Integer, Integer)}.
     * <p>
     * Method under test:
     * {@link QuestionController#aiGenerateQuestionSse(Long, Integer, Integer)}
     */
    @Test
    @DisplayName("Test aiGenerateQuestionSse(Long, Integer, Integer)")
    void testAiGenerateQuestionSse() throws Exception {
        // Arrange
        ChatCompletionRequest.Builder frequencyPenaltyResult = ChatCompletionRequest.builder().frequencyPenalty(10.0d);
        FunctionCall functionCall = FunctionCall.builder().arguments("Arguments").name("Name").build();
        ChatCompletionRequest.Builder functionCallResult = frequencyPenaltyResult.functionCall(functionCall);
        ChatCompletionRequest.Builder functionsResult = functionCallResult.functions(new ArrayList<>());
        ChatCompletionRequest.Builder maxTokensResult = functionsResult.logitBias(new HashMap<>()).maxTokens(3);
        ChatCompletionRequest.Builder presencePenaltyResult = maxTokensResult.messages(new ArrayList<>())
                .model("Model")
                .n(1)
                .presencePenalty(10.0d);
        ChatCompletionRequest.Builder seedResult = presencePenaltyResult
                .responseFormat(new ResponseFormat(ResponseFormatType.TEXT))
                .seed(42);
        ChatCompletionRequest.Builder toolChoiceResult = seedResult.stop(new ArrayList<>())
                .stream(true)
                .temperature(10.0d)
                .toolChoice(ToolChoiceMode.NONE);
        ChatCompletionRequest buildResult = toolChoiceResult.tools(new ArrayList<>()).topP(10.0d).user("User").build();
        when(aiManager.generalStreamRequest(Mockito.<String>any(), Mockito.<String>any(), anyDouble()))
                .thenReturn(buildResult);
        doNothing().when(aiManager)
                .executeChatCompletion(Mockito.<ChatCompletionRequest>any(), Mockito.<SseEmitter>any(),
                        Mockito.<CompletableFuture<String>>any());

        Application application = new Application();
        application.setAppName("App Name");
        application.setAuditMessage("Audit Message");
        application.setAuditStatus(1);
        application.setAuditTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application.setAuditorId(1L);
        application.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application.setDeleted(1);
        application.setDescription("The characteristics of someone or something");
        application.setId(1L);
        application.setImageUrl("https://example.org/example");
        application.setStrategy(1);
        application.setType(1);
        application.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application.setUserId(1L);
        when(applicationService.getById(Mockito.<Serializable>any())).thenReturn(application);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/question/ai_generate/sse");
        MockHttpServletRequestBuilder paramResult = getResult.param("appId", String.valueOf(1L));
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("optionNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("questionNumber", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(questionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
