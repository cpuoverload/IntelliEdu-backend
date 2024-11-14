package com.team6.intellieduanswerrecordservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieduanswerrecordservice.mapper.AnswerRecordMapper;
import com.team6.intellieduapi.client.ApplicationClient;
import com.team6.intellieduapi.client.ScoringClient;
import com.team6.intellieduapi.client.UserClient;
import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intellieducommon.utils.TableRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListAnswerRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListMyAnswerRequest;
import com.team6.intelliedumodel.dto.scoring.DoScoreRequest;
import com.team6.intelliedumodel.entity.AnswerRecord;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.vo.AnswerRecordVo;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AnswerRecordServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AnswerRecordServiceImplDiffblueTest {
    @MockBean
    private AnswerRecordMapper answerRecordMapper;

    @Autowired
    private AnswerRecordServiceImpl answerRecordServiceImpl;

    @MockBean
    private ApplicationClient applicationClient;

    @MockBean
    private ScoringClient scoringClient;

    @MockBean
    private UserClient userClient;

    /**
     * Test {@link AnswerRecordServiceImpl#validate(AnswerRecord)}.
     * <ul>
     *   <li>Given {@link Application} (default constructor) AppName is
     * {@code App Name}.</li>
     *   <li>Then calls {@link ApiResponse#getData()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link AnswerRecordServiceImpl#validate(AnswerRecord)}
     */
    @Test
    @DisplayName("Test validate(AnswerRecord); given Application (default constructor) AppName is 'App Name'; then calls getData()")
    void testValidate_givenApplicationAppNameIsAppName_thenCallsGetData() {
        // Arrange
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
        ApiResponse<Application> apiResponse = mock(ApiResponse.class);
        when(apiResponse.getData()).thenReturn(application);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(apiResponse);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        AnswerRecord answerRecord = mock(AnswerRecord.class);
        when(answerRecord.getAppId()).thenReturn(1L);
        when(answerRecord.getAnswers()).thenReturn(stringList);
        doNothing().when(answerRecord).setAnswers(Mockito.<List<String>>any());
        doNothing().when(answerRecord).setAppId(Mockito.<Long>any());
        doNothing().when(answerRecord).setAppType(Mockito.<Integer>any());
        doNothing().when(answerRecord).setCreateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setDeleted(Mockito.<Integer>any());
        doNothing().when(answerRecord).setId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultDetail(Mockito.<String>any());
        doNothing().when(answerRecord).setResultGrade(Mockito.<Integer>any());
        doNothing().when(answerRecord).setResultId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultImageUrl(Mockito.<String>any());
        doNothing().when(answerRecord).setResultName(Mockito.<String>any());
        doNothing().when(answerRecord).setStrategy(Mockito.<Integer>any());
        doNothing().when(answerRecord).setUpdateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setUserId(Mockito.<Long>any());
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act
        answerRecordServiceImpl.validate(answerRecord);

        // Assert that nothing has changed
        verify(applicationClient).getApplicationById(eq(1L));
        verify(apiResponse).getData();
        verify(answerRecord).getAnswers();
        verify(answerRecord).getAppId();
        verify(answerRecord).setAnswers(isA(List.class));
        verify(answerRecord).setAppId(eq(1L));
        verify(answerRecord).setAppType(eq(1));
        verify(answerRecord).setCreateTime(isA(Date.class));
        verify(answerRecord).setDeleted(eq(1));
        verify(answerRecord).setId(eq(1L));
        verify(answerRecord).setResultDetail(eq("Result Detail"));
        verify(answerRecord).setResultGrade(eq(1));
        verify(answerRecord).setResultId(eq(1L));
        verify(answerRecord).setResultImageUrl(eq("https://example.org/example"));
        verify(answerRecord).setResultName(eq("Result Name"));
        verify(answerRecord).setStrategy(eq(1));
        verify(answerRecord).setUpdateTime(isA(Date.class));
        verify(answerRecord).setUserId(eq(1L));
    }

    /**
     * Test {@link AnswerRecordServiceImpl#validate(AnswerRecord)}.
     * <ul>
     *   <li>Given {@link ApplicationClient}
     * {@link ApplicationClient#getApplicationById(Long)} return fail
     * {@code PARAMS_ERROR}.</li>
     * </ul>
     * <p>
     * Method under test: {@link AnswerRecordServiceImpl#validate(AnswerRecord)}
     */
    @Test
    @DisplayName("Test validate(AnswerRecord); given ApplicationClient getApplicationById(Long) return fail 'PARAMS_ERROR'")
    void testValidate_givenApplicationClientGetApplicationByIdReturnFailParamsError() {
        // Arrange
        ApiResponse<Application> failResult = ApiResponse.fail(Err.PARAMS_ERROR);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(failResult);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        AnswerRecord answerRecord = mock(AnswerRecord.class);
        when(answerRecord.getAppId()).thenReturn(1L);
        when(answerRecord.getAnswers()).thenReturn(stringList);
        doNothing().when(answerRecord).setAnswers(Mockito.<List<String>>any());
        doNothing().when(answerRecord).setAppId(Mockito.<Long>any());
        doNothing().when(answerRecord).setAppType(Mockito.<Integer>any());
        doNothing().when(answerRecord).setCreateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setDeleted(Mockito.<Integer>any());
        doNothing().when(answerRecord).setId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultDetail(Mockito.<String>any());
        doNothing().when(answerRecord).setResultGrade(Mockito.<Integer>any());
        doNothing().when(answerRecord).setResultId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultImageUrl(Mockito.<String>any());
        doNothing().when(answerRecord).setResultName(Mockito.<String>any());
        doNothing().when(answerRecord).setStrategy(Mockito.<Integer>any());
        doNothing().when(answerRecord).setUpdateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setUserId(Mockito.<Long>any());
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> answerRecordServiceImpl.validate(answerRecord));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(answerRecord).getAnswers();
        verify(answerRecord).getAppId();
        verify(answerRecord).setAnswers(isA(List.class));
        verify(answerRecord).setAppId(eq(1L));
        verify(answerRecord).setAppType(eq(1));
        verify(answerRecord).setCreateTime(isA(Date.class));
        verify(answerRecord).setDeleted(eq(1));
        verify(answerRecord).setId(eq(1L));
        verify(answerRecord).setResultDetail(eq("Result Detail"));
        verify(answerRecord).setResultGrade(eq(1));
        verify(answerRecord).setResultId(eq(1L));
        verify(answerRecord).setResultImageUrl(eq("https://example.org/example"));
        verify(answerRecord).setResultName(eq("Result Name"));
        verify(answerRecord).setStrategy(eq(1));
        verify(answerRecord).setUpdateTime(isA(Date.class));
        verify(answerRecord).setUserId(eq(1L));
    }

    /**
     * Test {@link AnswerRecordServiceImpl#validate(AnswerRecord)}.
     * <ul>
     *   <li>Given {@link ApplicationClient}.</li>
     *   <li>When {@link AnswerRecord} (default constructor) Answers is
     * {@link ArrayList#ArrayList()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link AnswerRecordServiceImpl#validate(AnswerRecord)}
     */
    @Test
    @DisplayName("Test validate(AnswerRecord); given ApplicationClient; when AnswerRecord (default constructor) Answers is ArrayList()")
    void testValidate_givenApplicationClient_whenAnswerRecordAnswersIsArrayList() {
        // Arrange
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> answerRecordServiceImpl.validate(answerRecord));
    }

    /**
     * Test {@link AnswerRecordServiceImpl#validate(AnswerRecord)}.
     * <ul>
     *   <li>Given minus one.</li>
     *   <li>When {@link AnswerRecord} {@link AnswerRecord#getAppId()} return minus
     * one.</li>
     * </ul>
     * <p>
     * Method under test: {@link AnswerRecordServiceImpl#validate(AnswerRecord)}
     */
    @Test
    @DisplayName("Test validate(AnswerRecord); given minus one; when AnswerRecord getAppId() return minus one")
    void testValidate_givenMinusOne_whenAnswerRecordGetAppIdReturnMinusOne() {
        // Arrange
        AnswerRecord answerRecord = mock(AnswerRecord.class);
        when(answerRecord.getAppId()).thenReturn(-1L);
        when(answerRecord.getAnswers()).thenReturn(new ArrayList<>());
        doNothing().when(answerRecord).setAnswers(Mockito.<List<String>>any());
        doNothing().when(answerRecord).setAppId(Mockito.<Long>any());
        doNothing().when(answerRecord).setAppType(Mockito.<Integer>any());
        doNothing().when(answerRecord).setCreateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setDeleted(Mockito.<Integer>any());
        doNothing().when(answerRecord).setId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultDetail(Mockito.<String>any());
        doNothing().when(answerRecord).setResultGrade(Mockito.<Integer>any());
        doNothing().when(answerRecord).setResultId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultImageUrl(Mockito.<String>any());
        doNothing().when(answerRecord).setResultName(Mockito.<String>any());
        doNothing().when(answerRecord).setStrategy(Mockito.<Integer>any());
        doNothing().when(answerRecord).setUpdateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setUserId(Mockito.<Long>any());
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> answerRecordServiceImpl.validate(answerRecord));
        verify(answerRecord).getAnswers();
        verify(answerRecord).getAppId();
        verify(answerRecord).setAnswers(isA(List.class));
        verify(answerRecord).setAppId(eq(1L));
        verify(answerRecord).setAppType(eq(1));
        verify(answerRecord).setCreateTime(isA(Date.class));
        verify(answerRecord).setDeleted(eq(1));
        verify(answerRecord).setId(eq(1L));
        verify(answerRecord).setResultDetail(eq("Result Detail"));
        verify(answerRecord).setResultGrade(eq(1));
        verify(answerRecord).setResultId(eq(1L));
        verify(answerRecord).setResultImageUrl(eq("https://example.org/example"));
        verify(answerRecord).setResultName(eq("Result Name"));
        verify(answerRecord).setStrategy(eq(1));
        verify(answerRecord).setUpdateTime(isA(Date.class));
        verify(answerRecord).setUserId(eq(1L));
    }

    /**
     * Test {@link AnswerRecordServiceImpl#validate(AnswerRecord)}.
     * <ul>
     *   <li>Then calls {@link Application#getAuditStatus()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link AnswerRecordServiceImpl#validate(AnswerRecord)}
     */
    @Test
    @DisplayName("Test validate(AnswerRecord); then calls getAuditStatus()")
    void testValidate_thenCallsGetAuditStatus() {
        // Arrange
        Application application = mock(Application.class);
        when(application.getAuditStatus()).thenReturn(-1);
        doNothing().when(application).setAppName(Mockito.<String>any());
        doNothing().when(application).setAuditMessage(Mockito.<String>any());
        doNothing().when(application).setAuditStatus(Mockito.<Integer>any());
        doNothing().when(application).setAuditTime(Mockito.<Date>any());
        doNothing().when(application).setAuditorId(Mockito.<Long>any());
        doNothing().when(application).setCreateTime(Mockito.<Date>any());
        doNothing().when(application).setDeleted(Mockito.<Integer>any());
        doNothing().when(application).setDescription(Mockito.<String>any());
        doNothing().when(application).setId(Mockito.<Long>any());
        doNothing().when(application).setImageUrl(Mockito.<String>any());
        doNothing().when(application).setStrategy(Mockito.<Integer>any());
        doNothing().when(application).setType(Mockito.<Integer>any());
        doNothing().when(application).setUpdateTime(Mockito.<Date>any());
        doNothing().when(application).setUserId(Mockito.<Long>any());
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
        ApiResponse<Application> apiResponse = mock(ApiResponse.class);
        when(apiResponse.getData()).thenReturn(application);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(apiResponse);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        AnswerRecord answerRecord = mock(AnswerRecord.class);
        when(answerRecord.getAppId()).thenReturn(1L);
        when(answerRecord.getAnswers()).thenReturn(stringList);
        doNothing().when(answerRecord).setAnswers(Mockito.<List<String>>any());
        doNothing().when(answerRecord).setAppId(Mockito.<Long>any());
        doNothing().when(answerRecord).setAppType(Mockito.<Integer>any());
        doNothing().when(answerRecord).setCreateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setDeleted(Mockito.<Integer>any());
        doNothing().when(answerRecord).setId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultDetail(Mockito.<String>any());
        doNothing().when(answerRecord).setResultGrade(Mockito.<Integer>any());
        doNothing().when(answerRecord).setResultId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultImageUrl(Mockito.<String>any());
        doNothing().when(answerRecord).setResultName(Mockito.<String>any());
        doNothing().when(answerRecord).setStrategy(Mockito.<Integer>any());
        doNothing().when(answerRecord).setUpdateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setUserId(Mockito.<Long>any());
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> answerRecordServiceImpl.validate(answerRecord));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(apiResponse).getData();
        verify(answerRecord).getAnswers();
        verify(answerRecord).getAppId();
        verify(answerRecord).setAnswers(isA(List.class));
        verify(answerRecord).setAppId(eq(1L));
        verify(answerRecord).setAppType(eq(1));
        verify(answerRecord).setCreateTime(isA(Date.class));
        verify(answerRecord).setDeleted(eq(1));
        verify(answerRecord).setId(eq(1L));
        verify(answerRecord).setResultDetail(eq("Result Detail"));
        verify(answerRecord).setResultGrade(eq(1));
        verify(answerRecord).setResultId(eq(1L));
        verify(answerRecord).setResultImageUrl(eq("https://example.org/example"));
        verify(answerRecord).setResultName(eq("Result Name"));
        verify(answerRecord).setStrategy(eq(1));
        verify(answerRecord).setUpdateTime(isA(Date.class));
        verify(answerRecord).setUserId(eq(1L));
        verify(application).getAuditStatus();
        verify(application).setAppName(eq("App Name"));
        verify(application).setAuditMessage(eq("Audit Message"));
        verify(application).setAuditStatus(eq(1));
        verify(application).setAuditTime(isA(Date.class));
        verify(application).setAuditorId(eq(1L));
        verify(application).setCreateTime(isA(Date.class));
        verify(application).setDeleted(eq(1));
        verify(application).setDescription(eq("The characteristics of someone or something"));
        verify(application).setId(eq(1L));
        verify(application).setImageUrl(eq("https://example.org/example"));
        verify(application).setStrategy(eq(1));
        verify(application).setType(eq(1));
        verify(application).setUpdateTime(isA(Date.class));
        verify(application).setUserId(eq(1L));
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyAnswerRecord(AnswerRecord, HttpServletRequest)")
    void testAddMyAnswerRecord() {
        // Arrange
        ApiResponse<Application> failResult = ApiResponse.fail(Err.PARAMS_ERROR);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(failResult);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        AnswerRecord answerRecord = mock(AnswerRecord.class);
        when(answerRecord.getAppId()).thenReturn(1L);
        when(answerRecord.getAnswers()).thenReturn(stringList);
        doNothing().when(answerRecord).setAnswers(Mockito.<List<String>>any());
        doNothing().when(answerRecord).setAppId(Mockito.<Long>any());
        doNothing().when(answerRecord).setAppType(Mockito.<Integer>any());
        doNothing().when(answerRecord).setCreateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setDeleted(Mockito.<Integer>any());
        doNothing().when(answerRecord).setId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultDetail(Mockito.<String>any());
        doNothing().when(answerRecord).setResultGrade(Mockito.<Integer>any());
        doNothing().when(answerRecord).setResultId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultImageUrl(Mockito.<String>any());
        doNothing().when(answerRecord).setResultName(Mockito.<String>any());
        doNothing().when(answerRecord).setStrategy(Mockito.<Integer>any());
        doNothing().when(answerRecord).setUpdateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setUserId(Mockito.<Long>any());
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> answerRecordServiceImpl.addMyAnswerRecord(answerRecord, new MockHttpServletRequest()));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(answerRecord).getAnswers();
        verify(answerRecord).getAppId();
        verify(answerRecord).setAnswers(isA(List.class));
        verify(answerRecord).setAppId(eq(1L));
        verify(answerRecord).setAppType(eq(1));
        verify(answerRecord).setCreateTime(isA(Date.class));
        verify(answerRecord).setDeleted(eq(1));
        verify(answerRecord).setId(eq(1L));
        verify(answerRecord).setResultDetail(eq("Result Detail"));
        verify(answerRecord).setResultGrade(eq(1));
        verify(answerRecord).setResultId(eq(1L));
        verify(answerRecord).setResultImageUrl(eq("https://example.org/example"));
        verify(answerRecord).setResultName(eq("Result Name"));
        verify(answerRecord).setStrategy(eq(1));
        verify(answerRecord).setUpdateTime(isA(Date.class));
        verify(answerRecord).setUserId(eq(1L));
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link AnswerRecordMapper} {@link BaseMapper#insert(Object)} return
     * zero.</li>
     *   <li>Then calls {@link BaseMapper#insert(Object)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyAnswerRecord(AnswerRecord, HttpServletRequest); given AnswerRecordMapper insert(Object) return zero; then calls insert(Object)")
    void testAddMyAnswerRecord_givenAnswerRecordMapperInsertReturnZero_thenCallsInsert() {
        // Arrange
        when(answerRecordMapper.insert(Mockito.<AnswerRecord>any())).thenReturn(0);

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
        ApiResponse<Application> apiResponse = mock(ApiResponse.class);
        when(apiResponse.getData()).thenReturn(application);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(apiResponse);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        AnswerRecord answerRecord = mock(AnswerRecord.class);
        when(answerRecord.getAppId()).thenReturn(1L);
        when(answerRecord.getAnswers()).thenReturn(stringList);
        doNothing().when(answerRecord).setAnswers(Mockito.<List<String>>any());
        doNothing().when(answerRecord).setAppId(Mockito.<Long>any());
        doNothing().when(answerRecord).setAppType(Mockito.<Integer>any());
        doNothing().when(answerRecord).setCreateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setDeleted(Mockito.<Integer>any());
        doNothing().when(answerRecord).setId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultDetail(Mockito.<String>any());
        doNothing().when(answerRecord).setResultGrade(Mockito.<Integer>any());
        doNothing().when(answerRecord).setResultId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultImageUrl(Mockito.<String>any());
        doNothing().when(answerRecord).setResultName(Mockito.<String>any());
        doNothing().when(answerRecord).setStrategy(Mockito.<Integer>any());
        doNothing().when(answerRecord).setUpdateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setUserId(Mockito.<Long>any());
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> answerRecordServiceImpl.addMyAnswerRecord(answerRecord, new MockHttpServletRequest()));
        verify(answerRecordMapper).insert(isA(AnswerRecord.class));
        verify(applicationClient, atLeast(1)).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse, atLeast(1)).getData();
        verify(answerRecord).getAnswers();
        verify(answerRecord, atLeast(1)).getAppId();
        verify(answerRecord).setAnswers(isA(List.class));
        verify(answerRecord).setAppId(eq(1L));
        verify(answerRecord).setAppType(eq(1));
        verify(answerRecord).setCreateTime(isA(Date.class));
        verify(answerRecord).setDeleted(eq(1));
        verify(answerRecord).setId(eq(1L));
        verify(answerRecord).setResultDetail(eq("Result Detail"));
        verify(answerRecord).setResultGrade(eq(1));
        verify(answerRecord).setResultId(eq(1L));
        verify(answerRecord).setResultImageUrl(eq("https://example.org/example"));
        verify(answerRecord).setResultName(eq("Result Name"));
        verify(answerRecord).setStrategy(eq(1));
        verify(answerRecord).setUpdateTime(isA(Date.class));
        verify(answerRecord, atLeast(1)).setUserId(eq(1L));
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ApplicationClient}.</li>
     *   <li>When {@link AnswerRecord} (default constructor) Answers is
     * {@link ArrayList#ArrayList()}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyAnswerRecord(AnswerRecord, HttpServletRequest); given ApplicationClient; when AnswerRecord (default constructor) Answers is ArrayList()")
    void testAddMyAnswerRecord_givenApplicationClient_whenAnswerRecordAnswersIsArrayList() {
        // Arrange
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> answerRecordServiceImpl.addMyAnswerRecord(answerRecord, new MockHttpServletRequest()));
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link BusinessException#BusinessException(Err)} with error is
     * {@code PARAMS_ERROR}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyAnswerRecord(AnswerRecord, HttpServletRequest); given BusinessException(Err) with error is 'PARAMS_ERROR'")
    void testAddMyAnswerRecord_givenBusinessExceptionWithErrorIsParamsError() {
        // Arrange
        when(answerRecordMapper.insert(Mockito.<AnswerRecord>any())).thenReturn(1);

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
        ApiResponse<Application> apiResponse = mock(ApiResponse.class);
        when(apiResponse.getData()).thenReturn(application);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(apiResponse);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        AnswerRecord answerRecord = mock(AnswerRecord.class);
        when(answerRecord.getId()).thenThrow(new BusinessException(Err.PARAMS_ERROR));
        when(answerRecord.getAppId()).thenReturn(1L);
        when(answerRecord.getAnswers()).thenReturn(stringList);
        doNothing().when(answerRecord).setAnswers(Mockito.<List<String>>any());
        doNothing().when(answerRecord).setAppId(Mockito.<Long>any());
        doNothing().when(answerRecord).setAppType(Mockito.<Integer>any());
        doNothing().when(answerRecord).setCreateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setDeleted(Mockito.<Integer>any());
        doNothing().when(answerRecord).setId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultDetail(Mockito.<String>any());
        doNothing().when(answerRecord).setResultGrade(Mockito.<Integer>any());
        doNothing().when(answerRecord).setResultId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultImageUrl(Mockito.<String>any());
        doNothing().when(answerRecord).setResultName(Mockito.<String>any());
        doNothing().when(answerRecord).setStrategy(Mockito.<Integer>any());
        doNothing().when(answerRecord).setUpdateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setUserId(Mockito.<Long>any());
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> answerRecordServiceImpl.addMyAnswerRecord(answerRecord, new MockHttpServletRequest()));
        verify(answerRecordMapper).insert(isA(AnswerRecord.class));
        verify(applicationClient, atLeast(1)).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse, atLeast(1)).getData();
        verify(answerRecord).getAnswers();
        verify(answerRecord, atLeast(1)).getAppId();
        verify(answerRecord).getId();
        verify(answerRecord).setAnswers(isA(List.class));
        verify(answerRecord).setAppId(eq(1L));
        verify(answerRecord).setAppType(eq(1));
        verify(answerRecord).setCreateTime(isA(Date.class));
        verify(answerRecord).setDeleted(eq(1));
        verify(answerRecord).setId(eq(1L));
        verify(answerRecord).setResultDetail(eq("Result Detail"));
        verify(answerRecord).setResultGrade(eq(1));
        verify(answerRecord).setResultId(eq(1L));
        verify(answerRecord).setResultImageUrl(eq("https://example.org/example"));
        verify(answerRecord).setResultName(eq("Result Name"));
        verify(answerRecord).setStrategy(eq(1));
        verify(answerRecord).setUpdateTime(isA(Date.class));
        verify(answerRecord, atLeast(1)).setUserId(eq(1L));
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}.
     * <ul>
     *   <li>Given minus one.</li>
     *   <li>When {@link AnswerRecord} {@link AnswerRecord#getAppId()} return minus
     * one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyAnswerRecord(AnswerRecord, HttpServletRequest); given minus one; when AnswerRecord getAppId() return minus one")
    void testAddMyAnswerRecord_givenMinusOne_whenAnswerRecordGetAppIdReturnMinusOne() {
        // Arrange
        AnswerRecord answerRecord = mock(AnswerRecord.class);
        when(answerRecord.getAppId()).thenReturn(-1L);
        when(answerRecord.getAnswers()).thenReturn(new ArrayList<>());
        doNothing().when(answerRecord).setAnswers(Mockito.<List<String>>any());
        doNothing().when(answerRecord).setAppId(Mockito.<Long>any());
        doNothing().when(answerRecord).setAppType(Mockito.<Integer>any());
        doNothing().when(answerRecord).setCreateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setDeleted(Mockito.<Integer>any());
        doNothing().when(answerRecord).setId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultDetail(Mockito.<String>any());
        doNothing().when(answerRecord).setResultGrade(Mockito.<Integer>any());
        doNothing().when(answerRecord).setResultId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultImageUrl(Mockito.<String>any());
        doNothing().when(answerRecord).setResultName(Mockito.<String>any());
        doNothing().when(answerRecord).setStrategy(Mockito.<Integer>any());
        doNothing().when(answerRecord).setUpdateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setUserId(Mockito.<Long>any());
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> answerRecordServiceImpl.addMyAnswerRecord(answerRecord, new MockHttpServletRequest()));
        verify(answerRecord).getAnswers();
        verify(answerRecord).getAppId();
        verify(answerRecord).setAnswers(isA(List.class));
        verify(answerRecord).setAppId(eq(1L));
        verify(answerRecord).setAppType(eq(1));
        verify(answerRecord).setCreateTime(isA(Date.class));
        verify(answerRecord).setDeleted(eq(1));
        verify(answerRecord).setId(eq(1L));
        verify(answerRecord).setResultDetail(eq("Result Detail"));
        verify(answerRecord).setResultGrade(eq(1));
        verify(answerRecord).setResultId(eq(1L));
        verify(answerRecord).setResultImageUrl(eq("https://example.org/example"));
        verify(answerRecord).setResultName(eq("Result Name"));
        verify(answerRecord).setStrategy(eq(1));
        verify(answerRecord).setUpdateTime(isA(Date.class));
        verify(answerRecord).setUserId(eq(1L));
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}.
     * <ul>
     *   <li>Then calls {@link Application#getAuditStatus()}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyAnswerRecord(AnswerRecord, HttpServletRequest); then calls getAuditStatus()")
    void testAddMyAnswerRecord_thenCallsGetAuditStatus() {
        // Arrange
        Application application = mock(Application.class);
        when(application.getAuditStatus()).thenReturn(-1);
        doNothing().when(application).setAppName(Mockito.<String>any());
        doNothing().when(application).setAuditMessage(Mockito.<String>any());
        doNothing().when(application).setAuditStatus(Mockito.<Integer>any());
        doNothing().when(application).setAuditTime(Mockito.<Date>any());
        doNothing().when(application).setAuditorId(Mockito.<Long>any());
        doNothing().when(application).setCreateTime(Mockito.<Date>any());
        doNothing().when(application).setDeleted(Mockito.<Integer>any());
        doNothing().when(application).setDescription(Mockito.<String>any());
        doNothing().when(application).setId(Mockito.<Long>any());
        doNothing().when(application).setImageUrl(Mockito.<String>any());
        doNothing().when(application).setStrategy(Mockito.<Integer>any());
        doNothing().when(application).setType(Mockito.<Integer>any());
        doNothing().when(application).setUpdateTime(Mockito.<Date>any());
        doNothing().when(application).setUserId(Mockito.<Long>any());
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
        ApiResponse<Application> apiResponse = mock(ApiResponse.class);
        when(apiResponse.getData()).thenReturn(application);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(apiResponse);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        AnswerRecord answerRecord = mock(AnswerRecord.class);
        when(answerRecord.getAppId()).thenReturn(1L);
        when(answerRecord.getAnswers()).thenReturn(stringList);
        doNothing().when(answerRecord).setAnswers(Mockito.<List<String>>any());
        doNothing().when(answerRecord).setAppId(Mockito.<Long>any());
        doNothing().when(answerRecord).setAppType(Mockito.<Integer>any());
        doNothing().when(answerRecord).setCreateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setDeleted(Mockito.<Integer>any());
        doNothing().when(answerRecord).setId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultDetail(Mockito.<String>any());
        doNothing().when(answerRecord).setResultGrade(Mockito.<Integer>any());
        doNothing().when(answerRecord).setResultId(Mockito.<Long>any());
        doNothing().when(answerRecord).setResultImageUrl(Mockito.<String>any());
        doNothing().when(answerRecord).setResultName(Mockito.<String>any());
        doNothing().when(answerRecord).setStrategy(Mockito.<Integer>any());
        doNothing().when(answerRecord).setUpdateTime(Mockito.<Date>any());
        doNothing().when(answerRecord).setUserId(Mockito.<Long>any());
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> answerRecordServiceImpl.addMyAnswerRecord(answerRecord, new MockHttpServletRequest()));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(apiResponse).getData();
        verify(answerRecord).getAnswers();
        verify(answerRecord).getAppId();
        verify(answerRecord).setAnswers(isA(List.class));
        verify(answerRecord).setAppId(eq(1L));
        verify(answerRecord).setAppType(eq(1));
        verify(answerRecord).setCreateTime(isA(Date.class));
        verify(answerRecord).setDeleted(eq(1));
        verify(answerRecord).setId(eq(1L));
        verify(answerRecord).setResultDetail(eq("Result Detail"));
        verify(answerRecord).setResultGrade(eq(1));
        verify(answerRecord).setResultId(eq(1L));
        verify(answerRecord).setResultImageUrl(eq("https://example.org/example"));
        verify(answerRecord).setResultName(eq("Result Name"));
        verify(answerRecord).setStrategy(eq(1));
        verify(answerRecord).setUpdateTime(isA(Date.class));
        verify(answerRecord).setUserId(eq(1L));
        verify(application).getAuditStatus();
        verify(application).setAppName(eq("App Name"));
        verify(application).setAuditMessage(eq("Audit Message"));
        verify(application).setAuditStatus(eq(1));
        verify(application).setAuditTime(isA(Date.class));
        verify(application).setAuditorId(eq(1L));
        verify(application).setCreateTime(isA(Date.class));
        verify(application).setDeleted(eq(1));
        verify(application).setDescription(eq("The characteristics of someone or something"));
        verify(application).setId(eq(1L));
        verify(application).setImageUrl(eq("https://example.org/example"));
        verify(application).setStrategy(eq(1));
        verify(application).setType(eq(1));
        verify(application).setUpdateTime(isA(Date.class));
        verify(application).setUserId(eq(1L));
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}.
     * <ul>
     *   <li>Then return longValue is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#addMyAnswerRecord(AnswerRecord, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyAnswerRecord(AnswerRecord, HttpServletRequest); then return longValue is one")
    void testAddMyAnswerRecord_thenReturnLongValueIsOne() {
        // Arrange
        when(answerRecordMapper.updateById(Mockito.<AnswerRecord>any())).thenReturn(1);
        when(answerRecordMapper.insert(Mockito.<AnswerRecord>any())).thenReturn(1);

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
        ApiResponse<Application> apiResponse = mock(ApiResponse.class);
        when(apiResponse.getData()).thenReturn(application);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(apiResponse);

        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);
        when(scoringClient.doScore(Mockito.<DoScoreRequest>any())).thenReturn(answerRecord);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        AnswerRecord answerRecord2 = mock(AnswerRecord.class);
        when(answerRecord2.getId()).thenReturn(1L);
        when(answerRecord2.getAppId()).thenReturn(1L);
        when(answerRecord2.getAnswers()).thenReturn(stringList);
        doNothing().when(answerRecord2).setAnswers(Mockito.<List<String>>any());
        doNothing().when(answerRecord2).setAppId(Mockito.<Long>any());
        doNothing().when(answerRecord2).setAppType(Mockito.<Integer>any());
        doNothing().when(answerRecord2).setCreateTime(Mockito.<Date>any());
        doNothing().when(answerRecord2).setDeleted(Mockito.<Integer>any());
        doNothing().when(answerRecord2).setId(Mockito.<Long>any());
        doNothing().when(answerRecord2).setResultDetail(Mockito.<String>any());
        doNothing().when(answerRecord2).setResultGrade(Mockito.<Integer>any());
        doNothing().when(answerRecord2).setResultId(Mockito.<Long>any());
        doNothing().when(answerRecord2).setResultImageUrl(Mockito.<String>any());
        doNothing().when(answerRecord2).setResultName(Mockito.<String>any());
        doNothing().when(answerRecord2).setStrategy(Mockito.<Integer>any());
        doNothing().when(answerRecord2).setUpdateTime(Mockito.<Date>any());
        doNothing().when(answerRecord2).setUserId(Mockito.<Long>any());
        answerRecord2.setAnswers(new ArrayList<>());
        answerRecord2.setAppId(1L);
        answerRecord2.setAppType(1);
        answerRecord2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord2.setDeleted(1);
        answerRecord2.setId(1L);
        answerRecord2.setResultDetail("Result Detail");
        answerRecord2.setResultGrade(1);
        answerRecord2.setResultId(1L);
        answerRecord2.setResultImageUrl("https://example.org/example");
        answerRecord2.setResultName("Result Name");
        answerRecord2.setStrategy(1);
        answerRecord2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord2.setUserId(1L);

        // Act
        Long actualAddMyAnswerRecordResult = answerRecordServiceImpl.addMyAnswerRecord(answerRecord2,
                new MockHttpServletRequest());

        // Assert
        verify(answerRecordMapper).insert(isA(AnswerRecord.class));
        verify(answerRecordMapper).updateById(isA(AnswerRecord.class));
        verify(applicationClient, atLeast(1)).getApplicationById(eq(1L));
        verify(scoringClient).doScore(isA(DoScoreRequest.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse, atLeast(1)).getData();
        verify(answerRecord2, atLeast(1)).getAnswers();
        verify(answerRecord2, atLeast(1)).getAppId();
        verify(answerRecord2).getId();
        verify(answerRecord2).setAnswers(isA(List.class));
        verify(answerRecord2).setAppId(eq(1L));
        verify(answerRecord2).setAppType(eq(1));
        verify(answerRecord2).setCreateTime(isA(Date.class));
        verify(answerRecord2).setDeleted(eq(1));
        verify(answerRecord2).setId(eq(1L));
        verify(answerRecord2).setResultDetail(eq("Result Detail"));
        verify(answerRecord2).setResultGrade(eq(1));
        verify(answerRecord2).setResultId(eq(1L));
        verify(answerRecord2).setResultImageUrl(eq("https://example.org/example"));
        verify(answerRecord2).setResultName(eq("Result Name"));
        verify(answerRecord2).setStrategy(eq(1));
        verify(answerRecord2).setUpdateTime(isA(Date.class));
        verify(answerRecord2, atLeast(1)).setUserId(eq(1L));
        assertEquals(1L, actualAddMyAnswerRecordResult.longValue());
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@code false}.</li>
     *   <li>When {@link ListMyAnswerRequest} {@link TableRequest#getIsAscend()}
     * return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest); given 'false'; when ListMyAnswerRequest getIsAscend() return 'false'")
    void testListMyAnswerRecord_givenFalse_whenListMyAnswerRequestGetIsAscendReturnFalse() {
        // Arrange
        IPage<AnswerRecord> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(answerRecordMapper.selectPage(Mockito.<IPage<AnswerRecord>>any(), Mockito.<Wrapper<AnswerRecord>>any()))
                .thenReturn(iPage);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);
        ListMyAnswerRequest listMyAnswerRequest = mock(ListMyAnswerRequest.class);
        when(listMyAnswerRequest.getIsAscend()).thenReturn(false);
        when(listMyAnswerRequest.getCurrent()).thenReturn(1L);
        when(listMyAnswerRequest.getPageSize()).thenReturn(3L);
        when(listMyAnswerRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listMyAnswerRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listMyAnswerRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listMyAnswerRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listMyAnswerRequest).setSortField(Mockito.<String>any());
        listMyAnswerRequest.setCurrent(1L);
        listMyAnswerRequest.setIsAscend(true);
        listMyAnswerRequest.setPageSize(3L);
        listMyAnswerRequest.setSortField("Sort Field");

        // Act
        Page<AnswerRecordVo> actualListMyAnswerRecordResult = answerRecordServiceImpl
                .listMyAnswerRecord(listMyAnswerRequest, new MockHttpServletRequest());

        // Assert
        verify(answerRecordMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(listMyAnswerRequest).getCurrent();
        verify(listMyAnswerRequest).getIsAscend();
        verify(listMyAnswerRequest).getPageSize();
        verify(listMyAnswerRequest, atLeast(1)).getSortField();
        verify(listMyAnswerRequest).setCurrent(eq(1L));
        verify(listMyAnswerRequest).setIsAscend(eq(true));
        verify(listMyAnswerRequest).setPageSize(eq(3L));
        verify(listMyAnswerRequest).setSortField(eq("Sort Field"));
        assertEquals(1L, actualListMyAnswerRecordResult.getCurrent());
        assertEquals(1L, actualListMyAnswerRecordResult.getPages());
        assertEquals(1L, actualListMyAnswerRecordResult.getTotal());
        assertFalse(actualListMyAnswerRecordResult.hasPrevious());
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given twenty.</li>
     *   <li>Then return Current is twenty.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest); given twenty; then return Current is twenty")
    void testListMyAnswerRecord_givenTwenty_thenReturnCurrentIsTwenty() {
        // Arrange
        IPage<AnswerRecord> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(answerRecordMapper.selectPage(Mockito.<IPage<AnswerRecord>>any(), Mockito.<Wrapper<AnswerRecord>>any()))
                .thenReturn(iPage);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);
        ListMyAnswerRequest listMyAnswerRequest = mock(ListMyAnswerRequest.class);
        when(listMyAnswerRequest.getIsAscend()).thenReturn(true);
        when(listMyAnswerRequest.getCurrent()).thenReturn(20L);
        when(listMyAnswerRequest.getPageSize()).thenReturn(3L);
        when(listMyAnswerRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listMyAnswerRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listMyAnswerRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listMyAnswerRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listMyAnswerRequest).setSortField(Mockito.<String>any());
        listMyAnswerRequest.setCurrent(1L);
        listMyAnswerRequest.setIsAscend(true);
        listMyAnswerRequest.setPageSize(3L);
        listMyAnswerRequest.setSortField("Sort Field");

        // Act
        Page<AnswerRecordVo> actualListMyAnswerRecordResult = answerRecordServiceImpl
                .listMyAnswerRecord(listMyAnswerRequest, new MockHttpServletRequest());

        // Assert
        verify(answerRecordMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(listMyAnswerRequest).getCurrent();
        verify(listMyAnswerRequest).getIsAscend();
        verify(listMyAnswerRequest).getPageSize();
        verify(listMyAnswerRequest, atLeast(1)).getSortField();
        verify(listMyAnswerRequest).setCurrent(eq(1L));
        verify(listMyAnswerRequest).setIsAscend(eq(true));
        verify(listMyAnswerRequest).setPageSize(eq(3L));
        verify(listMyAnswerRequest).setSortField(eq("Sort Field"));
        assertEquals(1L, actualListMyAnswerRecordResult.getPages());
        assertEquals(1L, actualListMyAnswerRecordResult.getTotal());
        assertEquals(20L, actualListMyAnswerRecordResult.getCurrent());
        assertTrue(actualListMyAnswerRecordResult.hasPrevious());
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Then return Current is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest); then return Current is one")
    void testListMyAnswerRecord_thenReturnCurrentIsOne() {
        // Arrange
        IPage<AnswerRecord> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(answerRecordMapper.selectPage(Mockito.<IPage<AnswerRecord>>any(), Mockito.<Wrapper<AnswerRecord>>any()))
                .thenReturn(iPage);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        ListMyAnswerRequest listMyAnswerRequest = new ListMyAnswerRequest();
        listMyAnswerRequest.setCurrent(1L);
        listMyAnswerRequest.setIsAscend(true);
        listMyAnswerRequest.setPageSize(3L);
        listMyAnswerRequest.setSortField("Sort Field");

        // Act
        Page<AnswerRecordVo> actualListMyAnswerRecordResult = answerRecordServiceImpl
                .listMyAnswerRecord(listMyAnswerRequest, new MockHttpServletRequest());

        // Assert
        verify(answerRecordMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertEquals(1L, actualListMyAnswerRecordResult.getCurrent());
        assertEquals(1L, actualListMyAnswerRecordResult.getPages());
        assertEquals(1L, actualListMyAnswerRecordResult.getTotal());
        assertFalse(actualListMyAnswerRecordResult.hasPrevious());
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Then return Pages is zero.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest); then return Pages is zero")
    void testListMyAnswerRecord_thenReturnPagesIsZero() {
        // Arrange
        when(answerRecordMapper.selectPage(Mockito.<IPage<AnswerRecord>>any(), Mockito.<Wrapper<AnswerRecord>>any()))
                .thenReturn(new Page<>());
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        ListMyAnswerRequest listMyAnswerRequest = new ListMyAnswerRequest();
        listMyAnswerRequest.setCurrent(1L);
        listMyAnswerRequest.setIsAscend(true);
        listMyAnswerRequest.setPageSize(3L);
        listMyAnswerRequest.setSortField("Sort Field");

        // Act
        Page<AnswerRecordVo> actualListMyAnswerRecordResult = answerRecordServiceImpl
                .listMyAnswerRecord(listMyAnswerRequest, new MockHttpServletRequest());

        // Assert
        verify(answerRecordMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertEquals(0L, actualListMyAnswerRecordResult.getPages());
        assertEquals(0L, actualListMyAnswerRecordResult.getTotal());
        assertEquals(1L, actualListMyAnswerRecordResult.getCurrent());
        assertFalse(actualListMyAnswerRecordResult.hasPrevious());
    }

    /**
     * Test
     * {@link AnswerRecordServiceImpl#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyAnswerRecord(ListMyAnswerRequest, HttpServletRequest); then throw BusinessException")
    void testListMyAnswerRecord_thenThrowBusinessException() {
        // Arrange
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        ListMyAnswerRequest listMyAnswerRequest = new ListMyAnswerRequest();
        listMyAnswerRequest.setCurrent(1L);
        listMyAnswerRequest.setIsAscend(true);
        listMyAnswerRequest.setPageSize(3L);
        listMyAnswerRequest.setSortField("Sort Field");

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> answerRecordServiceImpl.listMyAnswerRecord(listMyAnswerRequest, new MockHttpServletRequest()));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test {@link AnswerRecordServiceImpl#getAnswerRecord(Long)}.
     * <ul>
     *   <li>Given {@link AnswerRecord} (default constructor) Answers is
     * {@link ArrayList#ArrayList()}.</li>
     *   <li>Then return {@code Result Detail}.</li>
     * </ul>
     * <p>
     * Method under test: {@link AnswerRecordServiceImpl#getAnswerRecord(Long)}
     */
    @Test
    @DisplayName("Test getAnswerRecord(Long); given AnswerRecord (default constructor) Answers is ArrayList(); then return 'Result Detail'")
    void testGetAnswerRecord_givenAnswerRecordAnswersIsArrayList_thenReturnResultDetail() {
        // Arrange
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);
        when(answerRecordMapper.selectById(Mockito.<Serializable>any())).thenReturn(answerRecord);

        // Act
        AnswerRecordVo actualAnswerRecord = answerRecordServiceImpl.getAnswerRecord(1L);

        // Assert
        verify(answerRecordMapper).selectById(isA(Serializable.class));
        assertEquals("Result Detail", actualAnswerRecord.getResultDetail());
        assertEquals("Result Name", actualAnswerRecord.getResultName());
        assertEquals("https://example.org/example", actualAnswerRecord.getResultImageUrl());
        assertEquals(1, actualAnswerRecord.getAppType().intValue());
        assertEquals(1, actualAnswerRecord.getResultGrade().intValue());
        assertEquals(1, actualAnswerRecord.getStrategy().intValue());
        assertEquals(1L, actualAnswerRecord.getAppId().longValue());
        assertEquals(1L, actualAnswerRecord.getId().longValue());
        assertEquals(1L, actualAnswerRecord.getResultId().longValue());
        assertEquals(1L, actualAnswerRecord.getUserId().longValue());
        assertTrue(actualAnswerRecord.getAnswers().isEmpty());
    }

    /**
     * Test {@link AnswerRecordServiceImpl#getAnswerRecord(Long)}.
     * <ul>
     *   <li>When {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link AnswerRecordServiceImpl#getAnswerRecord(Long)}
     */
    @Test
    @DisplayName("Test getAnswerRecord(Long); when 'null'; then throw BusinessException")
    void testGetAnswerRecord_whenNull_thenThrowBusinessException() {
        // Arrange, Act and Assert
        assertThrows(BusinessException.class, () -> answerRecordServiceImpl.getAnswerRecord(null));
    }

    /**
     * Test {@link AnswerRecordServiceImpl#getAnswerRecord(Long)}.
     * <ul>
     *   <li>When zero.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link AnswerRecordServiceImpl#getAnswerRecord(Long)}
     */
    @Test
    @DisplayName("Test getAnswerRecord(Long); when zero; then throw BusinessException")
    void testGetAnswerRecord_whenZero_thenThrowBusinessException() {
        // Arrange, Act and Assert
        assertThrows(BusinessException.class, () -> answerRecordServiceImpl.getAnswerRecord(0L));
    }

    /**
     * Test {@link AnswerRecordServiceImpl#entityToVo(AnswerRecord)}.
     * <p>
     * Method under test: {@link AnswerRecordServiceImpl#entityToVo(AnswerRecord)}
     */
    @Test
    @DisplayName("Test entityToVo(AnswerRecord)")
    void testEntityToVo() {
        // Arrange
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);

        // Act
        AnswerRecordVo actualEntityToVoResult = answerRecordServiceImpl.entityToVo(answerRecord);

        // Assert
        assertEquals("Result Detail", actualEntityToVoResult.getResultDetail());
        assertEquals("Result Name", actualEntityToVoResult.getResultName());
        assertEquals("https://example.org/example", actualEntityToVoResult.getResultImageUrl());
        assertEquals(1, actualEntityToVoResult.getAppType().intValue());
        assertEquals(1, actualEntityToVoResult.getResultGrade().intValue());
        assertEquals(1, actualEntityToVoResult.getStrategy().intValue());
        assertEquals(1L, actualEntityToVoResult.getAppId().longValue());
        assertEquals(1L, actualEntityToVoResult.getId().longValue());
        assertEquals(1L, actualEntityToVoResult.getResultId().longValue());
        assertEquals(1L, actualEntityToVoResult.getUserId().longValue());
        assertTrue(actualEntityToVoResult.getAnswers().isEmpty());
    }

    /**
     * Test {@link AnswerRecordServiceImpl#listAnswerRecord(ListAnswerRequest)}.
     * <ul>
     *   <li>Given {@code false}.</li>
     *   <li>When {@link ListAnswerRequest} {@link TableRequest#getIsAscend()} return
     * {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#listAnswerRecord(ListAnswerRequest)}
     */
    @Test
    @DisplayName("Test listAnswerRecord(ListAnswerRequest); given 'false'; when ListAnswerRequest getIsAscend() return 'false'")
    void testListAnswerRecord_givenFalse_whenListAnswerRequestGetIsAscendReturnFalse() {
        // Arrange
        IPage<AnswerRecord> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(answerRecordMapper.selectPage(Mockito.<IPage<AnswerRecord>>any(), Mockito.<Wrapper<AnswerRecord>>any()))
                .thenReturn(iPage);
        ListAnswerRequest listAnswerRequest = mock(ListAnswerRequest.class);
        when(listAnswerRequest.getIsAscend()).thenReturn(false);
        when(listAnswerRequest.getAppType()).thenReturn(1);
        when(listAnswerRequest.getResultGrade()).thenReturn(1);
        when(listAnswerRequest.getStrategy()).thenReturn(1);
        when(listAnswerRequest.getCurrent()).thenReturn(1L);
        when(listAnswerRequest.getPageSize()).thenReturn(3L);
        when(listAnswerRequest.getAppId()).thenReturn(1L);
        when(listAnswerRequest.getId()).thenReturn(1L);
        when(listAnswerRequest.getResultId()).thenReturn(1L);
        when(listAnswerRequest.getUserId()).thenReturn(1L);
        when(listAnswerRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listAnswerRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listAnswerRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listAnswerRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listAnswerRequest).setSortField(Mockito.<String>any());
        doNothing().when(listAnswerRequest).setAppId(Mockito.<Long>any());
        doNothing().when(listAnswerRequest).setAppType(Mockito.<Integer>any());
        doNothing().when(listAnswerRequest).setId(Mockito.<Long>any());
        doNothing().when(listAnswerRequest).setResultGrade(Mockito.<Integer>any());
        doNothing().when(listAnswerRequest).setResultId(Mockito.<Long>any());
        doNothing().when(listAnswerRequest).setStrategy(Mockito.<Integer>any());
        doNothing().when(listAnswerRequest).setUserId(Mockito.<Long>any());
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

        // Act
        Page<AnswerRecordVo> actualListAnswerRecordResult = answerRecordServiceImpl.listAnswerRecord(listAnswerRequest);

        // Assert
        verify(answerRecordMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listAnswerRequest).getCurrent();
        verify(listAnswerRequest).getIsAscend();
        verify(listAnswerRequest).getPageSize();
        verify(listAnswerRequest, atLeast(1)).getSortField();
        verify(listAnswerRequest).setCurrent(eq(1L));
        verify(listAnswerRequest).setIsAscend(eq(true));
        verify(listAnswerRequest).setPageSize(eq(3L));
        verify(listAnswerRequest).setSortField(eq("Sort Field"));
        verify(listAnswerRequest, atLeast(1)).getAppId();
        verify(listAnswerRequest, atLeast(1)).getAppType();
        verify(listAnswerRequest, atLeast(1)).getId();
        verify(listAnswerRequest, atLeast(1)).getResultGrade();
        verify(listAnswerRequest, atLeast(1)).getResultId();
        verify(listAnswerRequest, atLeast(1)).getStrategy();
        verify(listAnswerRequest, atLeast(1)).getUserId();
        verify(listAnswerRequest).setAppId(eq(1L));
        verify(listAnswerRequest).setAppType(eq(1));
        verify(listAnswerRequest).setId(eq(1L));
        verify(listAnswerRequest).setResultGrade(eq(1));
        verify(listAnswerRequest).setResultId(eq(1L));
        verify(listAnswerRequest).setStrategy(eq(1));
        verify(listAnswerRequest).setUserId(eq(1L));
        assertEquals(1L, actualListAnswerRecordResult.getCurrent());
        assertEquals(1L, actualListAnswerRecordResult.getPages());
        assertEquals(1L, actualListAnswerRecordResult.getTotal());
        assertFalse(actualListAnswerRecordResult.hasPrevious());
    }

    /**
     * Test {@link AnswerRecordServiceImpl#listAnswerRecord(ListAnswerRequest)}.
     * <ul>
     *   <li>Given {@link IPage} {@link IPage#getRecords()} return
     * {@link ArrayList#ArrayList()}.</li>
     *   <li>Then return Current is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#listAnswerRecord(ListAnswerRequest)}
     */
    @Test
    @DisplayName("Test listAnswerRecord(ListAnswerRequest); given IPage getRecords() return ArrayList(); then return Current is one")
    void testListAnswerRecord_givenIPageGetRecordsReturnArrayList_thenReturnCurrentIsOne() {
        // Arrange
        IPage<AnswerRecord> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(answerRecordMapper.selectPage(Mockito.<IPage<AnswerRecord>>any(), Mockito.<Wrapper<AnswerRecord>>any()))
                .thenReturn(iPage);

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

        // Act
        Page<AnswerRecordVo> actualListAnswerRecordResult = answerRecordServiceImpl.listAnswerRecord(listAnswerRequest);

        // Assert
        verify(answerRecordMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        assertEquals(1L, actualListAnswerRecordResult.getCurrent());
        assertEquals(1L, actualListAnswerRecordResult.getPages());
        assertEquals(1L, actualListAnswerRecordResult.getTotal());
        assertFalse(actualListAnswerRecordResult.hasPrevious());
    }

    /**
     * Test {@link AnswerRecordServiceImpl#listAnswerRecord(ListAnswerRequest)}.
     * <ul>
     *   <li>Given twenty.</li>
     *   <li>Then return Current is twenty.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#listAnswerRecord(ListAnswerRequest)}
     */
    @Test
    @DisplayName("Test listAnswerRecord(ListAnswerRequest); given twenty; then return Current is twenty")
    void testListAnswerRecord_givenTwenty_thenReturnCurrentIsTwenty() {
        // Arrange
        IPage<AnswerRecord> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(answerRecordMapper.selectPage(Mockito.<IPage<AnswerRecord>>any(), Mockito.<Wrapper<AnswerRecord>>any()))
                .thenReturn(iPage);
        ListAnswerRequest listAnswerRequest = mock(ListAnswerRequest.class);
        when(listAnswerRequest.getIsAscend()).thenReturn(true);
        when(listAnswerRequest.getAppType()).thenReturn(1);
        when(listAnswerRequest.getResultGrade()).thenReturn(1);
        when(listAnswerRequest.getStrategy()).thenReturn(1);
        when(listAnswerRequest.getCurrent()).thenReturn(20L);
        when(listAnswerRequest.getPageSize()).thenReturn(3L);
        when(listAnswerRequest.getAppId()).thenReturn(1L);
        when(listAnswerRequest.getId()).thenReturn(1L);
        when(listAnswerRequest.getResultId()).thenReturn(1L);
        when(listAnswerRequest.getUserId()).thenReturn(1L);
        when(listAnswerRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listAnswerRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listAnswerRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listAnswerRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listAnswerRequest).setSortField(Mockito.<String>any());
        doNothing().when(listAnswerRequest).setAppId(Mockito.<Long>any());
        doNothing().when(listAnswerRequest).setAppType(Mockito.<Integer>any());
        doNothing().when(listAnswerRequest).setId(Mockito.<Long>any());
        doNothing().when(listAnswerRequest).setResultGrade(Mockito.<Integer>any());
        doNothing().when(listAnswerRequest).setResultId(Mockito.<Long>any());
        doNothing().when(listAnswerRequest).setStrategy(Mockito.<Integer>any());
        doNothing().when(listAnswerRequest).setUserId(Mockito.<Long>any());
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

        // Act
        Page<AnswerRecordVo> actualListAnswerRecordResult = answerRecordServiceImpl.listAnswerRecord(listAnswerRequest);

        // Assert
        verify(answerRecordMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listAnswerRequest).getCurrent();
        verify(listAnswerRequest).getIsAscend();
        verify(listAnswerRequest).getPageSize();
        verify(listAnswerRequest, atLeast(1)).getSortField();
        verify(listAnswerRequest).setCurrent(eq(1L));
        verify(listAnswerRequest).setIsAscend(eq(true));
        verify(listAnswerRequest).setPageSize(eq(3L));
        verify(listAnswerRequest).setSortField(eq("Sort Field"));
        verify(listAnswerRequest, atLeast(1)).getAppId();
        verify(listAnswerRequest, atLeast(1)).getAppType();
        verify(listAnswerRequest, atLeast(1)).getId();
        verify(listAnswerRequest, atLeast(1)).getResultGrade();
        verify(listAnswerRequest, atLeast(1)).getResultId();
        verify(listAnswerRequest, atLeast(1)).getStrategy();
        verify(listAnswerRequest, atLeast(1)).getUserId();
        verify(listAnswerRequest).setAppId(eq(1L));
        verify(listAnswerRequest).setAppType(eq(1));
        verify(listAnswerRequest).setId(eq(1L));
        verify(listAnswerRequest).setResultGrade(eq(1));
        verify(listAnswerRequest).setResultId(eq(1L));
        verify(listAnswerRequest).setStrategy(eq(1));
        verify(listAnswerRequest).setUserId(eq(1L));
        assertEquals(1L, actualListAnswerRecordResult.getPages());
        assertEquals(1L, actualListAnswerRecordResult.getTotal());
        assertEquals(20L, actualListAnswerRecordResult.getCurrent());
        assertTrue(actualListAnswerRecordResult.hasPrevious());
    }

    /**
     * Test {@link AnswerRecordServiceImpl#listAnswerRecord(ListAnswerRequest)}.
     * <ul>
     *   <li>Then return Pages is zero.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#listAnswerRecord(ListAnswerRequest)}
     */
    @Test
    @DisplayName("Test listAnswerRecord(ListAnswerRequest); then return Pages is zero")
    void testListAnswerRecord_thenReturnPagesIsZero() {
        // Arrange
        when(answerRecordMapper.selectPage(Mockito.<IPage<AnswerRecord>>any(), Mockito.<Wrapper<AnswerRecord>>any()))
                .thenReturn(new Page<>());

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

        // Act
        Page<AnswerRecordVo> actualListAnswerRecordResult = answerRecordServiceImpl.listAnswerRecord(listAnswerRequest);

        // Assert
        verify(answerRecordMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        assertEquals(0L, actualListAnswerRecordResult.getPages());
        assertEquals(0L, actualListAnswerRecordResult.getTotal());
        assertEquals(1L, actualListAnswerRecordResult.getCurrent());
        assertFalse(actualListAnswerRecordResult.hasPrevious());
    }

    /**
     * Test {@link AnswerRecordServiceImpl#deleteAnswerRecord(IdRequest)}.
     * <ul>
     *   <li>Given {@link AnswerRecord} (default constructor) Answers is
     * {@link ArrayList#ArrayList()}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#deleteAnswerRecord(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteAnswerRecord(IdRequest); given AnswerRecord (default constructor) Answers is ArrayList()")
    @Disabled("TODO: Complete this test")
    void testDeleteAnswerRecord_givenAnswerRecordAnswersIsArrayList() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.removeById(ServiceImpl.java:290)
        //       at com.team6.intellieduanswerrecordservice.service.impl.AnswerRecordServiceImpl.deleteAnswerRecord(AnswerRecordServiceImpl.java:204)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAnswers(new ArrayList<>());
        answerRecord.setAppId(1L);
        answerRecord.setAppType(1);
        answerRecord.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setDeleted(1);
        answerRecord.setId(1L);
        answerRecord.setResultDetail("Result Detail");
        answerRecord.setResultGrade(1);
        answerRecord.setResultId(1L);
        answerRecord.setResultImageUrl("https://example.org/example");
        answerRecord.setResultName("Result Name");
        answerRecord.setStrategy(1);
        answerRecord.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        answerRecord.setUserId(1L);
        when(answerRecordMapper.selectById(Mockito.<Serializable>any())).thenReturn(answerRecord);

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);

        // Act
        answerRecordServiceImpl.deleteAnswerRecord(idRequest);
    }

    /**
     * Test {@link AnswerRecordServiceImpl#deleteAnswerRecord(IdRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link IdRequest} (default constructor) Id is {@code null}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#deleteAnswerRecord(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteAnswerRecord(IdRequest); given 'null'; when IdRequest (default constructor) Id is 'null'")
    void testDeleteAnswerRecord_givenNull_whenIdRequestIdIsNull() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AnswerRecordServiceImpl answerRecordServiceImpl = new AnswerRecordServiceImpl();

        IdRequest idRequest = new IdRequest();
        idRequest.setId(null);

        // Act and Assert
        assertThrows(BusinessException.class, () -> answerRecordServiceImpl.deleteAnswerRecord(idRequest));
    }

    /**
     * Test {@link AnswerRecordServiceImpl#deleteAnswerRecord(IdRequest)}.
     * <ul>
     *   <li>Then calls {@link IdRequest#getId()}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#deleteAnswerRecord(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteAnswerRecord(IdRequest); then calls getId()")
    void testDeleteAnswerRecord_thenCallsGetId() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AnswerRecordServiceImpl answerRecordServiceImpl = new AnswerRecordServiceImpl();
        IdRequest idRequest = mock(IdRequest.class);
        when(idRequest.getId()).thenThrow(new BusinessException(Err.PARAMS_ERROR));

        // Act and Assert
        assertThrows(BusinessException.class, () -> answerRecordServiceImpl.deleteAnswerRecord(idRequest));
        verify(idRequest).getId();
    }

    /**
     * Test {@link AnswerRecordServiceImpl#deleteAnswerRecord(IdRequest)}.
     * <ul>
     *   <li>When {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AnswerRecordServiceImpl#deleteAnswerRecord(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteAnswerRecord(IdRequest); when 'null'; then throw BusinessException")
    void testDeleteAnswerRecord_whenNull_thenThrowBusinessException() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertThrows(BusinessException.class, () -> (new AnswerRecordServiceImpl()).deleteAnswerRecord(null));
    }
}
