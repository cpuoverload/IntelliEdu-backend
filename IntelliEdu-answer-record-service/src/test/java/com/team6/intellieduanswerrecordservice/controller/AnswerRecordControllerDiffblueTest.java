package com.team6.intellieduanswerrecordservice.controller;

import static org.mockito.Mockito.when;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team6.intellieduanswerrecordservice.service.AnswerRecordService;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.answerrecord.AddMyAnswerRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListAnswerRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListMyAnswerRequest;
import com.team6.intelliedumodel.entity.AnswerRecord;
import com.team6.intelliedumodel.vo.AnswerRecordVo;

import java.time.LocalDate;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Date;
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

@ContextConfiguration(classes = {AnswerRecordController.class})
@ExtendWith(SpringExtension.class)
class AnswerRecordControllerDiffblueTest {
    @Autowired
    private AnswerRecordController answerRecordController;

    @MockBean
    private AnswerRecordService answerRecordService;

    /**
     * Test
     * {@link AnswerRecordController#addMyAnswerRecord(AddMyAnswerRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link AnswerRecordController#addMyAnswerRecord(AddMyAnswerRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyAnswerRecord(AddMyAnswerRequest, HttpServletRequest)")
    void testAddMyAnswerRecord() throws Exception {
        // Arrange
        when(answerRecordService.addMyAnswerRecord(Mockito.<AnswerRecord>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(1L);

        AddMyAnswerRequest addMyAnswerRequest = new AddMyAnswerRequest();
        addMyAnswerRequest.setAnswers(new ArrayList<>());
        addMyAnswerRequest.setAppId(1L);
        String content = (new ObjectMapper()).writeValueAsString(addMyAnswerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(answerRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":1,\"message\":\"success\"}"));
    }

    /**
     * Test
     * {@link AnswerRecordController#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link AnswerRecordController#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)")
    void testListMyAnswerRecord() throws Exception {
        // Arrange
        when(answerRecordService.listMyAnswerRecord(Mockito.<ListMyAnswerRequest>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(new Page<>());

        ListMyAnswerRequest listMyAnswerRequest = new ListMyAnswerRequest();
        listMyAnswerRequest.setCurrent(1L);
        listMyAnswerRequest.setIsAscend(true);
        listMyAnswerRequest.setPageSize(3L);
        listMyAnswerRequest.setSortField("Sort Field");
        String content = (new ObjectMapper()).writeValueAsString(listMyAnswerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/list/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(answerRecordController)
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
     * Test {@link AnswerRecordController#listAnswerRecord(ListAnswerRequest)}.
     * <p>
     * Method under test:
     * {@link AnswerRecordController#listAnswerRecord(ListAnswerRequest)}
     */
    @Test
    @DisplayName("Test listAnswerRecord(ListAnswerRequest)")
    void testListAnswerRecord() throws Exception {
        // Arrange
        when(answerRecordService.listAnswerRecord(Mockito.<ListAnswerRequest>any())).thenReturn(new Page<>());

        ListAnswerRequest listAnswerRequest = new ListAnswerRequest();
        listAnswerRequest.setAppId(1L);
        listAnswerRequest.setAppType(1);
        listAnswerRequest.setCurrent(1L);
        listAnswerRequest.setId(1L);
        listAnswerRequest.setIsAscend(true);
        listAnswerRequest.setPageSize(3L);
        listAnswerRequest.setResultGrade(1);
        listAnswerRequest.setResultId(1L);
        listAnswerRequest.setSortField("Sort Field");
        listAnswerRequest.setStrategy(1);
        listAnswerRequest.setUserId(1L);
        String content = (new ObjectMapper()).writeValueAsString(listAnswerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(answerRecordController)
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
     * Test {@link AnswerRecordController#getAnswerRecordById(Long)}.
     * <p>
     * Method under test: {@link AnswerRecordController#getAnswerRecordById(Long)}
     */
    @Test
    @DisplayName("Test getAnswerRecordById(Long)")
    void testGetAnswerRecordById() throws Exception {
        // Arrange
        AnswerRecordVo answerRecordVo = new AnswerRecordVo();
        answerRecordVo.setAnswers(new ArrayList<>());
        answerRecordVo.setAppId(1L);
        answerRecordVo.setAppType(1);
        answerRecordVo.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecordVo.setId(1L);
        answerRecordVo.setResultDetail("Result Detail");
        answerRecordVo.setResultGrade(1);
        answerRecordVo.setResultId(1L);
        answerRecordVo.setResultImageUrl("https://example.org/example");
        answerRecordVo.setResultName("Result Name");
        answerRecordVo.setStrategy(1);
        answerRecordVo.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecordVo.setUserId(1L);
        when(answerRecordService.getAnswerRecord(Mockito.<Long>any())).thenReturn(answerRecordVo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get/42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(answerRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"userId\":1,\"appId\":1,\"appType\":1,\"strategy\":1,\"answers\":[],\"resultId\":1,"
                                        + "\"resultName\":\"Result Name\",\"resultDetail\":\"Result Detail\",\"resultImageUrl\":\"https://example.org/example"
                                        + "\",\"resultGrade\":1,\"createTime\":0,\"updateTime\":0},\"message\":\"success\"}"));
    }

    /**
     * Test {@link AnswerRecordController#deleteAnswerRecord(IdRequest)}.
     * <p>
     * Method under test:
     * {@link AnswerRecordController#deleteAnswerRecord(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteAnswerRecord(IdRequest)")
    void testDeleteAnswerRecord() throws Exception {
        // Arrange
        when(answerRecordService.deleteAnswerRecord(Mockito.<IdRequest>any())).thenReturn(true);

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(idRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(answerRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }
}
