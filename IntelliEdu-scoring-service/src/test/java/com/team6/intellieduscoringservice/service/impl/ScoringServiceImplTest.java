package com.team6.intellieduscoringservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieduapi.client.ApplicationClient;
import com.team6.intellieduapi.client.UserClient;
import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intellieducommon.utils.TableRequest;
import com.team6.intelliedumodel.dto.scoring.ListMyScoringRequest;
import com.team6.intelliedumodel.dto.scoring.ListScoringRequest;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.entity.Scoring;
import com.team6.intelliedumodel.vo.ScoringVo;
import com.team6.intellieduscoringservice.mapper.ScoringMapper;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ScoringServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ScoringServiceImplTest {
    @MockBean
    private ApplicationClient applicationClient;

    @MockBean
    private ScoringMapper scoringMapper;

    @Autowired
    private ScoringServiceImpl scoringServiceImpl;

    @MockBean
    private UserClient userClient;

    /**
     * Test {@link ScoringServiceImpl#validate(Scoring)}.
     * <ul>
     *   <li>Given {@link Application} (default constructor) AppName is
     * {@code App Name}.</li>
     *   <li>Then calls {@link ApiResponse#getData()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ScoringServiceImpl#validate(Scoring)}
     */
    @Test
    @DisplayName("Test validate(Scoring); given Application (default constructor) AppName is 'App Name'; then calls getData()")
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

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act
        scoringServiceImpl.validate(scoring);

        // Assert that nothing has changed
        verify(applicationClient).getApplicationById(eq(1L));
        verify(apiResponse).getData();
    }

    /**
     * Test {@link ScoringServiceImpl#validate(Scoring)}.
     * <ul>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ScoringServiceImpl#validate(Scoring)}
     */
    @Test
    @DisplayName("Test validate(Scoring); then throw BusinessException")
    void testValidate_thenThrowBusinessException() {
        // Arrange
        ApiResponse<Application> failResult = ApiResponse.fail(Err.PARAMS_ERROR);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(failResult);

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> scoringServiceImpl.validate(scoring));
        verify(applicationClient).getApplicationById(eq(1L));
    }

    /**
     * Test {@link ScoringServiceImpl#entityToVo(Scoring)}.
     * <p>
     * Method under test: {@link ScoringServiceImpl#entityToVo(Scoring)}
     */
    @Test
    @DisplayName("Test entityToVo(Scoring)")
    void testEntityToVo() {
        // Arrange
        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act
        ScoringVo actualEntityToVoResult = scoringServiceImpl.entityToVo(scoring);

        // Assert
        assertEquals("Result Detail", actualEntityToVoResult.getResultDetail());
        assertEquals("Result Name", actualEntityToVoResult.getResultName());
        assertEquals("https://example.org/example", actualEntityToVoResult.getResultImageUrl());
        assertEquals(1, actualEntityToVoResult.getResultThreshold().intValue());
        assertEquals(1L, actualEntityToVoResult.getAppId().longValue());
        assertEquals(1L, actualEntityToVoResult.getId().longValue());
        assertEquals(1L, actualEntityToVoResult.getUserId().longValue());
        assertTrue(actualEntityToVoResult.getResultAttributes().isEmpty());
    }

    /**
     * Test {@link ScoringServiceImpl#addMyScoring(Scoring, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#addMyScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyScoring(Scoring, HttpServletRequest)")
    void testAddMyScoring() {
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
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> scoringServiceImpl.addMyScoring(scoring, new MockHttpServletRequest()));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse).getData();
    }

    /**
     * Test {@link ScoringServiceImpl#addMyScoring(Scoring, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ApplicationClient}
     * {@link ApplicationClient#getApplicationById(Long)} return fail
     * {@code PARAMS_ERROR}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#addMyScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyScoring(Scoring, HttpServletRequest); given ApplicationClient getApplicationById(Long) return fail 'PARAMS_ERROR'")
    void testAddMyScoring_givenApplicationClientGetApplicationByIdReturnFailParamsError() {
        // Arrange
        ApiResponse<Application> failResult = ApiResponse.fail(Err.PARAMS_ERROR);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(failResult);

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> scoringServiceImpl.addMyScoring(scoring, new MockHttpServletRequest()));
        verify(applicationClient).getApplicationById(eq(1L));
    }

    /**
     * Test {@link ScoringServiceImpl#addMyScoring(Scoring, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ScoringMapper} {@link BaseMapper#insert(Object)} return
     * one.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#addMyScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyScoring(Scoring, HttpServletRequest); given ScoringMapper insert(Object) return one; then return 'true'")
    void testAddMyScoring_givenScoringMapperInsertReturnOne_thenReturnTrue() {
        // Arrange
        when(scoringMapper.insert(Mockito.<Scoring>any())).thenReturn(1);

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

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act
        Boolean actualAddMyScoringResult = scoringServiceImpl.addMyScoring(scoring, new MockHttpServletRequest());

        // Assert
        verify(scoringMapper).insert(isA(Scoring.class));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse).getData();
        assertTrue(actualAddMyScoringResult);
    }

    /**
     * Test {@link ScoringServiceImpl#addMyScoring(Scoring, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ScoringMapper} {@link BaseMapper#insert(Object)} return
     * zero.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#addMyScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyScoring(Scoring, HttpServletRequest); given ScoringMapper insert(Object) return zero; then return 'false'")
    void testAddMyScoring_givenScoringMapperInsertReturnZero_thenReturnFalse() {
        // Arrange
        when(scoringMapper.insert(Mockito.<Scoring>any())).thenReturn(0);

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

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act
        Boolean actualAddMyScoringResult = scoringServiceImpl.addMyScoring(scoring, new MockHttpServletRequest());

        // Assert
        verify(scoringMapper).insert(isA(Scoring.class));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse).getData();
        assertFalse(actualAddMyScoringResult);
    }

    /**
     * Test {@link ScoringServiceImpl#addMyScoringBatch(List, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#addMyScoringBatch(List, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyScoringBatch(List, HttpServletRequest)")
    void testAddMyScoringBatch() {
        // Arrange
        ApiResponse<Application> failResult = ApiResponse.fail(Err.PARAMS_ERROR);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(failResult);

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        ArrayList<Scoring> scoringList = new ArrayList<>();
        scoringList.add(scoring);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> scoringServiceImpl.addMyScoringBatch(scoringList, new MockHttpServletRequest()));
        verify(applicationClient).getApplicationById(eq(1L));
    }

    /**
     * Test {@link ScoringServiceImpl#addMyScoringBatch(List, HttpServletRequest)}.
     * <ul>
     *   <li>Then calls {@link UserClient#getLoginUserId(HttpServletRequest)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#addMyScoringBatch(List, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyScoringBatch(List, HttpServletRequest); then calls getLoginUserId(HttpServletRequest)")
    void testAddMyScoringBatch_thenCallsGetLoginUserId() {
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
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        ArrayList<Scoring> scoringList = new ArrayList<>();
        scoringList.add(scoring);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> scoringServiceImpl.addMyScoringBatch(scoringList, new MockHttpServletRequest()));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse).getData();
    }

    /**
     * Test
     * {@link ScoringServiceImpl#deleteMyScoring(IdRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link IdRequest} (default constructor) Id is {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#deleteMyScoring(IdRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test deleteMyScoring(IdRequest, HttpServletRequest); given 'null'; when IdRequest (default constructor) Id is 'null'; then throw BusinessException")
    void testDeleteMyScoring_givenNull_whenIdRequestIdIsNull_thenThrowBusinessException() {
        // Arrange
        IdRequest idRequest = new IdRequest();
        idRequest.setId(null);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> scoringServiceImpl.deleteMyScoring(idRequest, new MockHttpServletRequest()));
    }

    /**
     * Test
     * {@link ScoringServiceImpl#deleteMyScoring(IdRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Then calls {@link UserClient#getLoginUserId(HttpServletRequest)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#deleteMyScoring(IdRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test deleteMyScoring(IdRequest, HttpServletRequest); then calls getLoginUserId(HttpServletRequest)")
    void testDeleteMyScoring_thenCallsGetLoginUserId() {
        // Arrange
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> scoringServiceImpl.deleteMyScoring(idRequest, new MockHttpServletRequest()));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test {@link ScoringServiceImpl#updateMyScoring(Scoring, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#updateMyScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyScoring(Scoring, HttpServletRequest)")
    void testUpdateMyScoring() {
        // Arrange
        ApiResponse<Application> failResult = ApiResponse.fail(Err.PARAMS_ERROR);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(failResult);

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> scoringServiceImpl.updateMyScoring(scoring, new MockHttpServletRequest()));
        verify(applicationClient).getApplicationById(eq(1L));
    }

    /**
     * Test {@link ScoringServiceImpl#updateMyScoring(Scoring, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#updateMyScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyScoring(Scoring, HttpServletRequest)")
    void testUpdateMyScoring2() {
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
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> scoringServiceImpl.updateMyScoring(scoring, new MockHttpServletRequest()));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse).getData();
    }

    /**
     * Test {@link ScoringServiceImpl#updateMyScoring(Scoring, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ScoringMapper} {@link BaseMapper#updateById(Object)} return
     * one.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#updateMyScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyScoring(Scoring, HttpServletRequest); given ScoringMapper updateById(Object) return one; then return 'true'")
    void testUpdateMyScoring_givenScoringMapperUpdateByIdReturnOne_thenReturnTrue() {
        // Arrange
        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);
        when(scoringMapper.updateById(Mockito.<Scoring>any())).thenReturn(1);
        when(scoringMapper.selectOne(Mockito.<Wrapper<Scoring>>any(), anyBoolean())).thenReturn(scoring);

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

        Scoring scoring2 = new Scoring();
        scoring2.setAppId(1L);
        scoring2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring2.setDeleted(1);
        scoring2.setId(1L);
        scoring2.setResultAttributes(new ArrayList<>());
        scoring2.setResultDetail("Result Detail");
        scoring2.setResultImageUrl("https://example.org/example");
        scoring2.setResultName("Result Name");
        scoring2.setResultThreshold(1);
        scoring2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring2.setUserId(1L);

        // Act
        Boolean actualUpdateMyScoringResult = scoringServiceImpl.updateMyScoring(scoring2, new MockHttpServletRequest());

        // Assert
        verify(scoringMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(scoringMapper).updateById(isA(Scoring.class));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse).getData();
        assertTrue(actualUpdateMyScoringResult);
    }

    /**
     * Test {@link ScoringServiceImpl#updateMyScoring(Scoring, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ScoringMapper} {@link BaseMapper#updateById(Object)} return
     * zero.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#updateMyScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyScoring(Scoring, HttpServletRequest); given ScoringMapper updateById(Object) return zero; then return 'false'")
    void testUpdateMyScoring_givenScoringMapperUpdateByIdReturnZero_thenReturnFalse() {
        // Arrange
        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);
        when(scoringMapper.updateById(Mockito.<Scoring>any())).thenReturn(0);
        when(scoringMapper.selectOne(Mockito.<Wrapper<Scoring>>any(), anyBoolean())).thenReturn(scoring);

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

        Scoring scoring2 = new Scoring();
        scoring2.setAppId(1L);
        scoring2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring2.setDeleted(1);
        scoring2.setId(1L);
        scoring2.setResultAttributes(new ArrayList<>());
        scoring2.setResultDetail("Result Detail");
        scoring2.setResultImageUrl("https://example.org/example");
        scoring2.setResultName("Result Name");
        scoring2.setResultThreshold(1);
        scoring2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring2.setUserId(1L);

        // Act
        Boolean actualUpdateMyScoringResult = scoringServiceImpl.updateMyScoring(scoring2, new MockHttpServletRequest());

        // Assert
        verify(scoringMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(scoringMapper).updateById(isA(Scoring.class));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse).getData();
        assertFalse(actualUpdateMyScoringResult);
    }

    /**
     * Test
     * {@link ScoringServiceImpl#listMyScoring(ListMyScoringRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@code false}.</li>
     *   <li>When {@link ListMyScoringRequest} {@link TableRequest#getIsAscend()}
     * return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#listMyScoring(ListMyScoringRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyScoring(ListMyScoringRequest, HttpServletRequest); given 'false'; when ListMyScoringRequest getIsAscend() return 'false'")
    void testListMyScoring_givenFalse_whenListMyScoringRequestGetIsAscendReturnFalse() {
        // Arrange
        IPage<Scoring> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(scoringMapper.selectPage(Mockito.<IPage<Scoring>>any(), Mockito.<Wrapper<Scoring>>any())).thenReturn(iPage);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);
        ListMyScoringRequest listMyScoringRequest = mock(ListMyScoringRequest.class);
        when(listMyScoringRequest.getIsAscend()).thenReturn(false);
        when(listMyScoringRequest.getCurrent()).thenReturn(1L);
        when(listMyScoringRequest.getPageSize()).thenReturn(3L);
        when(listMyScoringRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listMyScoringRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listMyScoringRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listMyScoringRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listMyScoringRequest).setSortField(Mockito.<String>any());
        doNothing().when(listMyScoringRequest).setAppId(Mockito.<Long>any());
        listMyScoringRequest.setAppId(1L);
        listMyScoringRequest.setCurrent(1L);
        listMyScoringRequest.setIsAscend(true);
        listMyScoringRequest.setPageSize(3L);
        listMyScoringRequest.setSortField("Sort Field");

        // Act
        Page<ScoringVo> actualListMyScoringResult = scoringServiceImpl.listMyScoring(listMyScoringRequest,
                new MockHttpServletRequest());

        // Assert
        verify(scoringMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(listMyScoringRequest).getCurrent();
        verify(listMyScoringRequest).getIsAscend();
        verify(listMyScoringRequest).getPageSize();
        verify(listMyScoringRequest, atLeast(1)).getSortField();
        verify(listMyScoringRequest).setCurrent(eq(1L));
        verify(listMyScoringRequest).setIsAscend(eq(true));
        verify(listMyScoringRequest).setPageSize(eq(3L));
        verify(listMyScoringRequest).setSortField(eq("Sort Field"));
        verify(listMyScoringRequest).setAppId(eq(1L));
        assertEquals(1L, actualListMyScoringResult.getCurrent());
        assertEquals(1L, actualListMyScoringResult.getPages());
        assertEquals(1L, actualListMyScoringResult.getTotal());
        assertFalse(actualListMyScoringResult.hasPrevious());
    }

    /**
     * Test
     * {@link ScoringServiceImpl#listMyScoring(ListMyScoringRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link IPage} {@link IPage#getRecords()} return
     * {@link ArrayList#ArrayList()}.</li>
     *   <li>Then return Current is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#listMyScoring(ListMyScoringRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyScoring(ListMyScoringRequest, HttpServletRequest); given IPage getRecords() return ArrayList(); then return Current is one")
    void testListMyScoring_givenIPageGetRecordsReturnArrayList_thenReturnCurrentIsOne() {
        // Arrange
        IPage<Scoring> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(scoringMapper.selectPage(Mockito.<IPage<Scoring>>any(), Mockito.<Wrapper<Scoring>>any())).thenReturn(iPage);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        ListMyScoringRequest listMyScoringRequest = new ListMyScoringRequest();
        listMyScoringRequest.setAppId(1L);
        listMyScoringRequest.setCurrent(1L);
        listMyScoringRequest.setIsAscend(true);
        listMyScoringRequest.setPageSize(3L);
        listMyScoringRequest.setSortField("Sort Field");

        // Act
        Page<ScoringVo> actualListMyScoringResult = scoringServiceImpl.listMyScoring(listMyScoringRequest,
                new MockHttpServletRequest());

        // Assert
        verify(scoringMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertEquals(1L, actualListMyScoringResult.getCurrent());
        assertEquals(1L, actualListMyScoringResult.getPages());
        assertEquals(1L, actualListMyScoringResult.getTotal());
        assertFalse(actualListMyScoringResult.hasPrevious());
    }

    /**
     * Test
     * {@link ScoringServiceImpl#listMyScoring(ListMyScoringRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ScoringMapper} {@link BaseMapper#selectPage(IPage, Wrapper)}
     * return {@link Page#Page()}.</li>
     *   <li>Then return Pages is zero.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#listMyScoring(ListMyScoringRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyScoring(ListMyScoringRequest, HttpServletRequest); given ScoringMapper selectPage(IPage, Wrapper) return Page(); then return Pages is zero")
    void testListMyScoring_givenScoringMapperSelectPageReturnPage_thenReturnPagesIsZero() {
        // Arrange
        when(scoringMapper.selectPage(Mockito.<IPage<Scoring>>any(), Mockito.<Wrapper<Scoring>>any()))
                .thenReturn(new Page<>());
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        ListMyScoringRequest listMyScoringRequest = new ListMyScoringRequest();
        listMyScoringRequest.setAppId(1L);
        listMyScoringRequest.setCurrent(1L);
        listMyScoringRequest.setIsAscend(true);
        listMyScoringRequest.setPageSize(3L);
        listMyScoringRequest.setSortField("Sort Field");

        // Act
        Page<ScoringVo> actualListMyScoringResult = scoringServiceImpl.listMyScoring(listMyScoringRequest,
                new MockHttpServletRequest());

        // Assert
        verify(scoringMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertEquals(0L, actualListMyScoringResult.getPages());
        assertEquals(0L, actualListMyScoringResult.getTotal());
        assertEquals(1L, actualListMyScoringResult.getCurrent());
        assertFalse(actualListMyScoringResult.hasPrevious());
    }

    /**
     * Test
     * {@link ScoringServiceImpl#listMyScoring(ListMyScoringRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given twenty.</li>
     *   <li>Then return Current is twenty.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#listMyScoring(ListMyScoringRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyScoring(ListMyScoringRequest, HttpServletRequest); given twenty; then return Current is twenty")
    void testListMyScoring_givenTwenty_thenReturnCurrentIsTwenty() {
        // Arrange
        IPage<Scoring> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(scoringMapper.selectPage(Mockito.<IPage<Scoring>>any(), Mockito.<Wrapper<Scoring>>any())).thenReturn(iPage);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);
        ListMyScoringRequest listMyScoringRequest = mock(ListMyScoringRequest.class);
        when(listMyScoringRequest.getIsAscend()).thenReturn(true);
        when(listMyScoringRequest.getCurrent()).thenReturn(20L);
        when(listMyScoringRequest.getPageSize()).thenReturn(3L);
        when(listMyScoringRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listMyScoringRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listMyScoringRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listMyScoringRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listMyScoringRequest).setSortField(Mockito.<String>any());
        doNothing().when(listMyScoringRequest).setAppId(Mockito.<Long>any());
        listMyScoringRequest.setAppId(1L);
        listMyScoringRequest.setCurrent(1L);
        listMyScoringRequest.setIsAscend(true);
        listMyScoringRequest.setPageSize(3L);
        listMyScoringRequest.setSortField("Sort Field");

        // Act
        Page<ScoringVo> actualListMyScoringResult = scoringServiceImpl.listMyScoring(listMyScoringRequest,
                new MockHttpServletRequest());

        // Assert
        verify(scoringMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(listMyScoringRequest).getCurrent();
        verify(listMyScoringRequest).getIsAscend();
        verify(listMyScoringRequest).getPageSize();
        verify(listMyScoringRequest, atLeast(1)).getSortField();
        verify(listMyScoringRequest).setCurrent(eq(1L));
        verify(listMyScoringRequest).setIsAscend(eq(true));
        verify(listMyScoringRequest).setPageSize(eq(3L));
        verify(listMyScoringRequest).setSortField(eq("Sort Field"));
        verify(listMyScoringRequest).setAppId(eq(1L));
        assertEquals(1L, actualListMyScoringResult.getPages());
        assertEquals(1L, actualListMyScoringResult.getTotal());
        assertEquals(20L, actualListMyScoringResult.getCurrent());
        assertTrue(actualListMyScoringResult.hasPrevious());
    }

    /**
     * Test
     * {@link ScoringServiceImpl#listMyScoring(ListMyScoringRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#listMyScoring(ListMyScoringRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyScoring(ListMyScoringRequest, HttpServletRequest); then throw BusinessException")
    void testListMyScoring_thenThrowBusinessException() {
        // Arrange
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        ListMyScoringRequest listMyScoringRequest = new ListMyScoringRequest();
        listMyScoringRequest.setAppId(1L);
        listMyScoringRequest.setCurrent(1L);
        listMyScoringRequest.setIsAscend(true);
        listMyScoringRequest.setPageSize(3L);
        listMyScoringRequest.setSortField("Sort Field");

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> scoringServiceImpl.listMyScoring(listMyScoringRequest, new MockHttpServletRequest()));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test {@link ScoringServiceImpl#addScoring(Scoring, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#addScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addScoring(Scoring, HttpServletRequest)")
    void testAddScoring() {
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
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> scoringServiceImpl.addScoring(scoring, new MockHttpServletRequest()));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse).getData();
    }

    /**
     * Test {@link ScoringServiceImpl#addScoring(Scoring, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ApplicationClient}
     * {@link ApplicationClient#getApplicationById(Long)} return fail
     * {@code PARAMS_ERROR}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#addScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addScoring(Scoring, HttpServletRequest); given ApplicationClient getApplicationById(Long) return fail 'PARAMS_ERROR'")
    void testAddScoring_givenApplicationClientGetApplicationByIdReturnFailParamsError() {
        // Arrange
        ApiResponse<Application> failResult = ApiResponse.fail(Err.PARAMS_ERROR);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(failResult);

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> scoringServiceImpl.addScoring(scoring, new MockHttpServletRequest()));
        verify(applicationClient).getApplicationById(eq(1L));
    }

    /**
     * Test {@link ScoringServiceImpl#addScoring(Scoring, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ScoringMapper} {@link BaseMapper#insert(Object)} return
     * one.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#addScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addScoring(Scoring, HttpServletRequest); given ScoringMapper insert(Object) return one; then return 'true'")
    void testAddScoring_givenScoringMapperInsertReturnOne_thenReturnTrue() {
        // Arrange
        when(scoringMapper.insert(Mockito.<Scoring>any())).thenReturn(1);

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

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act
        Boolean actualAddScoringResult = scoringServiceImpl.addScoring(scoring, new MockHttpServletRequest());

        // Assert
        verify(scoringMapper).insert(isA(Scoring.class));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse).getData();
        assertTrue(actualAddScoringResult);
    }

    /**
     * Test {@link ScoringServiceImpl#addScoring(Scoring, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ScoringMapper} {@link BaseMapper#insert(Object)} return
     * zero.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ScoringServiceImpl#addScoring(Scoring, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addScoring(Scoring, HttpServletRequest); given ScoringMapper insert(Object) return zero; then return 'false'")
    void testAddScoring_givenScoringMapperInsertReturnZero_thenReturnFalse() {
        // Arrange
        when(scoringMapper.insert(Mockito.<Scoring>any())).thenReturn(0);

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

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act
        Boolean actualAddScoringResult = scoringServiceImpl.addScoring(scoring, new MockHttpServletRequest());

        // Assert
        verify(scoringMapper).insert(isA(Scoring.class));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(apiResponse).getData();
        assertFalse(actualAddScoringResult);
    }

    /**
     * Test {@link ScoringServiceImpl#listScoring(ListScoringRequest)}.
     * <ul>
     *   <li>Given {@code false}.</li>
     *   <li>When {@link ListScoringRequest} {@link TableRequest#getIsAscend()} return
     * {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ScoringServiceImpl#listScoring(ListScoringRequest)}
     */
    @Test
    @DisplayName("Test listScoring(ListScoringRequest); given 'false'; when ListScoringRequest getIsAscend() return 'false'")
    void testListScoring_givenFalse_whenListScoringRequestGetIsAscendReturnFalse() {
        // Arrange
        IPage<Scoring> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(scoringMapper.selectPage(Mockito.<IPage<Scoring>>any(), Mockito.<Wrapper<Scoring>>any())).thenReturn(iPage);
        ListScoringRequest listScoringRequest = mock(ListScoringRequest.class);
        when(listScoringRequest.getIsAscend()).thenReturn(false);
        when(listScoringRequest.getCurrent()).thenReturn(1L);
        when(listScoringRequest.getPageSize()).thenReturn(3L);
        when(listScoringRequest.getAppId()).thenReturn(1L);
        when(listScoringRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listScoringRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listScoringRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listScoringRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listScoringRequest).setSortField(Mockito.<String>any());
        doNothing().when(listScoringRequest).setAppId(Mockito.<Long>any());
        listScoringRequest.setAppId(1L);
        listScoringRequest.setCurrent(1L);
        listScoringRequest.setIsAscend(true);
        listScoringRequest.setPageSize(3L);
        listScoringRequest.setSortField("Sort Field");

        // Act
        Page<ScoringVo> actualListScoringResult = scoringServiceImpl.listScoring(listScoringRequest);

        // Assert
        verify(scoringMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listScoringRequest).getCurrent();
        verify(listScoringRequest).getIsAscend();
        verify(listScoringRequest).getPageSize();
        verify(listScoringRequest, atLeast(1)).getSortField();
        verify(listScoringRequest).setCurrent(eq(1L));
        verify(listScoringRequest).setIsAscend(eq(true));
        verify(listScoringRequest).setPageSize(eq(3L));
        verify(listScoringRequest).setSortField(eq("Sort Field"));
        verify(listScoringRequest, atLeast(1)).getAppId();
        verify(listScoringRequest).setAppId(eq(1L));
        assertEquals(1L, actualListScoringResult.getCurrent());
        assertEquals(1L, actualListScoringResult.getPages());
        assertEquals(1L, actualListScoringResult.getTotal());
        assertFalse(actualListScoringResult.hasPrevious());
    }

    /**
     * Test {@link ScoringServiceImpl#listScoring(ListScoringRequest)}.
     * <ul>
     *   <li>Given {@link IPage} {@link IPage#getRecords()} return
     * {@link ArrayList#ArrayList()}.</li>
     *   <li>Then return Current is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link ScoringServiceImpl#listScoring(ListScoringRequest)}
     */
    @Test
    @DisplayName("Test listScoring(ListScoringRequest); given IPage getRecords() return ArrayList(); then return Current is one")
    void testListScoring_givenIPageGetRecordsReturnArrayList_thenReturnCurrentIsOne() {
        // Arrange
        IPage<Scoring> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(scoringMapper.selectPage(Mockito.<IPage<Scoring>>any(), Mockito.<Wrapper<Scoring>>any())).thenReturn(iPage);

        ListScoringRequest listScoringRequest = new ListScoringRequest();
        listScoringRequest.setAppId(1L);
        listScoringRequest.setCurrent(1L);
        listScoringRequest.setIsAscend(true);
        listScoringRequest.setPageSize(3L);
        listScoringRequest.setSortField("Sort Field");

        // Act
        Page<ScoringVo> actualListScoringResult = scoringServiceImpl.listScoring(listScoringRequest);

        // Assert
        verify(scoringMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        assertEquals(1L, actualListScoringResult.getCurrent());
        assertEquals(1L, actualListScoringResult.getPages());
        assertEquals(1L, actualListScoringResult.getTotal());
        assertFalse(actualListScoringResult.hasPrevious());
    }

    /**
     * Test {@link ScoringServiceImpl#listScoring(ListScoringRequest)}.
     * <ul>
     *   <li>Given {@link ScoringMapper} {@link BaseMapper#selectPage(IPage, Wrapper)}
     * return {@link Page#Page()}.</li>
     *   <li>Then return Pages is zero.</li>
     * </ul>
     * <p>
     * Method under test: {@link ScoringServiceImpl#listScoring(ListScoringRequest)}
     */
    @Test
    @DisplayName("Test listScoring(ListScoringRequest); given ScoringMapper selectPage(IPage, Wrapper) return Page(); then return Pages is zero")
    void testListScoring_givenScoringMapperSelectPageReturnPage_thenReturnPagesIsZero() {
        // Arrange
        when(scoringMapper.selectPage(Mockito.<IPage<Scoring>>any(), Mockito.<Wrapper<Scoring>>any()))
                .thenReturn(new Page<>());

        ListScoringRequest listScoringRequest = new ListScoringRequest();
        listScoringRequest.setAppId(1L);
        listScoringRequest.setCurrent(1L);
        listScoringRequest.setIsAscend(true);
        listScoringRequest.setPageSize(3L);
        listScoringRequest.setSortField("Sort Field");

        // Act
        Page<ScoringVo> actualListScoringResult = scoringServiceImpl.listScoring(listScoringRequest);

        // Assert
        verify(scoringMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        assertEquals(0L, actualListScoringResult.getPages());
        assertEquals(0L, actualListScoringResult.getTotal());
        assertEquals(1L, actualListScoringResult.getCurrent());
        assertFalse(actualListScoringResult.hasPrevious());
    }

    /**
     * Test {@link ScoringServiceImpl#listScoring(ListScoringRequest)}.
     * <ul>
     *   <li>Given twenty.</li>
     *   <li>Then return Current is twenty.</li>
     * </ul>
     * <p>
     * Method under test: {@link ScoringServiceImpl#listScoring(ListScoringRequest)}
     */
    @Test
    @DisplayName("Test listScoring(ListScoringRequest); given twenty; then return Current is twenty")
    void testListScoring_givenTwenty_thenReturnCurrentIsTwenty() {
        // Arrange
        IPage<Scoring> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(scoringMapper.selectPage(Mockito.<IPage<Scoring>>any(), Mockito.<Wrapper<Scoring>>any())).thenReturn(iPage);
        ListScoringRequest listScoringRequest = mock(ListScoringRequest.class);
        when(listScoringRequest.getIsAscend()).thenReturn(true);
        when(listScoringRequest.getCurrent()).thenReturn(20L);
        when(listScoringRequest.getPageSize()).thenReturn(3L);
        when(listScoringRequest.getAppId()).thenReturn(1L);
        when(listScoringRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listScoringRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listScoringRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listScoringRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listScoringRequest).setSortField(Mockito.<String>any());
        doNothing().when(listScoringRequest).setAppId(Mockito.<Long>any());
        listScoringRequest.setAppId(1L);
        listScoringRequest.setCurrent(1L);
        listScoringRequest.setIsAscend(true);
        listScoringRequest.setPageSize(3L);
        listScoringRequest.setSortField("Sort Field");

        // Act
        Page<ScoringVo> actualListScoringResult = scoringServiceImpl.listScoring(listScoringRequest);

        // Assert
        verify(scoringMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listScoringRequest).getCurrent();
        verify(listScoringRequest).getIsAscend();
        verify(listScoringRequest).getPageSize();
        verify(listScoringRequest, atLeast(1)).getSortField();
        verify(listScoringRequest).setCurrent(eq(1L));
        verify(listScoringRequest).setIsAscend(eq(true));
        verify(listScoringRequest).setPageSize(eq(3L));
        verify(listScoringRequest).setSortField(eq("Sort Field"));
        verify(listScoringRequest, atLeast(1)).getAppId();
        verify(listScoringRequest).setAppId(eq(1L));
        assertEquals(1L, actualListScoringResult.getPages());
        assertEquals(1L, actualListScoringResult.getTotal());
        assertEquals(20L, actualListScoringResult.getCurrent());
        assertTrue(actualListScoringResult.hasPrevious());
    }

    /**
     * Test {@link ScoringServiceImpl#updateScoring(Scoring)}.
     * <ul>
     *   <li>Given {@link ScoringMapper} {@link BaseMapper#updateById(Object)} return
     * one.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ScoringServiceImpl#updateScoring(Scoring)}
     */
    @Test
    @DisplayName("Test updateScoring(Scoring); given ScoringMapper updateById(Object) return one; then return 'true'")
    void testUpdateScoring_givenScoringMapperUpdateByIdReturnOne_thenReturnTrue() {
        // Arrange
        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);
        when(scoringMapper.updateById(Mockito.<Scoring>any())).thenReturn(1);
        when(scoringMapper.selectById(Mockito.<Serializable>any())).thenReturn(scoring);

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

        Scoring scoring2 = new Scoring();
        scoring2.setAppId(1L);
        scoring2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring2.setDeleted(1);
        scoring2.setId(1L);
        scoring2.setResultAttributes(new ArrayList<>());
        scoring2.setResultDetail("Result Detail");
        scoring2.setResultImageUrl("https://example.org/example");
        scoring2.setResultName("Result Name");
        scoring2.setResultThreshold(1);
        scoring2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring2.setUserId(1L);

        // Act
        Boolean actualUpdateScoringResult = scoringServiceImpl.updateScoring(scoring2);

        // Assert
        verify(scoringMapper).selectById(isA(Serializable.class));
        verify(scoringMapper).updateById(isA(Scoring.class));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(apiResponse).getData();
        assertTrue(actualUpdateScoringResult);
    }

    /**
     * Test {@link ScoringServiceImpl#updateScoring(Scoring)}.
     * <ul>
     *   <li>Given {@link ScoringMapper} {@link BaseMapper#updateById(Object)} return
     * zero.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ScoringServiceImpl#updateScoring(Scoring)}
     */
    @Test
    @DisplayName("Test updateScoring(Scoring); given ScoringMapper updateById(Object) return zero; then return 'false'")
    void testUpdateScoring_givenScoringMapperUpdateByIdReturnZero_thenReturnFalse() {
        // Arrange
        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);
        when(scoringMapper.updateById(Mockito.<Scoring>any())).thenReturn(0);
        when(scoringMapper.selectById(Mockito.<Serializable>any())).thenReturn(scoring);

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

        Scoring scoring2 = new Scoring();
        scoring2.setAppId(1L);
        scoring2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring2.setDeleted(1);
        scoring2.setId(1L);
        scoring2.setResultAttributes(new ArrayList<>());
        scoring2.setResultDetail("Result Detail");
        scoring2.setResultImageUrl("https://example.org/example");
        scoring2.setResultName("Result Name");
        scoring2.setResultThreshold(1);
        scoring2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring2.setUserId(1L);

        // Act
        Boolean actualUpdateScoringResult = scoringServiceImpl.updateScoring(scoring2);

        // Assert
        verify(scoringMapper).selectById(isA(Serializable.class));
        verify(scoringMapper).updateById(isA(Scoring.class));
        verify(applicationClient).getApplicationById(eq(1L));
        verify(apiResponse).getData();
        assertFalse(actualUpdateScoringResult);
    }

    /**
     * Test {@link ScoringServiceImpl#updateScoring(Scoring)}.
     * <ul>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ScoringServiceImpl#updateScoring(Scoring)}
     */
    @Test
    @DisplayName("Test updateScoring(Scoring); then throw BusinessException")
    void testUpdateScoring_thenThrowBusinessException() {
        // Arrange
        ApiResponse<Application> failResult = ApiResponse.fail(Err.PARAMS_ERROR);
        when(applicationClient.getApplicationById(Mockito.<Long>any())).thenReturn(failResult);

        Scoring scoring = new Scoring();
        scoring.setAppId(1L);
        scoring.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setDeleted(1);
        scoring.setId(1L);
        scoring.setResultAttributes(new ArrayList<>());
        scoring.setResultDetail("Result Detail");
        scoring.setResultImageUrl("https://example.org/example");
        scoring.setResultName("Result Name");
        scoring.setResultThreshold(1);
        scoring.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scoring.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> scoringServiceImpl.updateScoring(scoring));
        verify(applicationClient).getApplicationById(eq(1L));
    }

    /**
     * Test {@link ScoringServiceImpl#deleteScoring(IdRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link IdRequest} (default constructor) Id is {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ScoringServiceImpl#deleteScoring(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteScoring(IdRequest); given 'null'; when IdRequest (default constructor) Id is 'null'; then throw BusinessException")
    void testDeleteScoring_givenNull_whenIdRequestIdIsNull_thenThrowBusinessException() {
        // Arrange
        IdRequest idRequest = new IdRequest();
        idRequest.setId(null);

        // Act and Assert
        assertThrows(BusinessException.class, () -> scoringServiceImpl.deleteScoring(idRequest));
    }
}
