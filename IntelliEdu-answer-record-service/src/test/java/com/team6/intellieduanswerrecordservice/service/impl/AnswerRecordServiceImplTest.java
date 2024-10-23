package com.team6.intellieduanswerrecordservice.service.impl;

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
import com.team6.intelliedumodel.dto.answerrecord.ListAnswerRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListMyAnswerRequest;
import com.team6.intelliedumodel.dto.scoring.DoScoreRequest;
import com.team6.intelliedumodel.entity.AnswerRecord;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.vo.AnswerRecordVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * ClassName: AnswerRecordServiceImplTest
 * Package: com.team6.intellieduanswerrecordservice.service.impl
 * Description:
 *
 * @Author CBX
 * @Create 21/10/24 15:04
 * @Version 1.0
 */
@SpringBootTest
class AnswerRecordServiceImplTest {
    @InjectMocks
    private AnswerRecordServiceImpl answerRecordService;

    @Mock
    private UserClient userClient;

    @Mock
    private ApplicationClient applicationClient;

    @Mock
    private ScoringClient scoringClient;

    @Mock
    private AnswerRecordMapper answerRecordMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set the baseMapper field
        ReflectionTestUtils.setField(answerRecordService, "baseMapper", answerRecordMapper);

    }


    /**
     * validate
     */
    @Test
    void testValidate_NullAnswerRecord() {
        BusinessException exception = assertThrows(BusinessException.class, () -> answerRecordService.validate(null));
        assertEquals(Err.PARAMS_ERROR.getCode(), exception.getCode());
    }

    @Test
    void testValidate_InvalidAppId() {
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAppId(-1L);
        answerRecord.setAnswers(Collections.singletonList("answer"));

        BusinessException exception = assertThrows(BusinessException.class, () -> answerRecordService.validate(answerRecord));
        assertEquals("appId is invalid", exception.getMessage());
    }

    @Test
    void testValidate_EmptyAnswers() {
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAppId(1L);
        answerRecord.setAnswers(Collections.emptyList());

        BusinessException exception = assertThrows(BusinessException.class, () -> answerRecordService.validate(answerRecord));
        assertEquals("answers is empty", exception.getMessage());
    }

    @Test
    void testValidate_ApplicationNotFound() {
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAppId(1L);
        answerRecord.setAnswers(Collections.singletonList("answer"));

        when(applicationClient.getApplicationById(anyLong())).thenReturn(ApiResponse.fail(Err.NOT_FOUND_ERROR));

        BusinessException exception = assertThrows(BusinessException.class, () -> answerRecordService.validate(answerRecord));
        assertEquals("application not found", exception.getMessage());
    }

    @Test
    void testValidate_ApplicationNotAudited() {
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAppId(1L);
        answerRecord.setAnswers(Collections.singletonList("answer"));

        Application application = new Application();
        application.setAuditStatus(0);

        when(applicationClient.getApplicationById(anyLong())).thenReturn(ApiResponse.success(application));

        BusinessException exception = assertThrows(BusinessException.class, () -> answerRecordService.validate(answerRecord));
        assertEquals("application not audited", exception.getMessage());
    }


    /**
     * AddMyAnswerRecord
     */

    @Test
    void testAddMyAnswerRecord_Success() {
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAppId(1L);
        answerRecord.setAnswers(Collections.singletonList("answer"));

        Application application = new Application();
        application.setAuditStatus(1);

        when(userClient.getLoginUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(applicationClient.getApplicationById(anyLong())).thenReturn(ApiResponse.success(application));
        when(answerRecordMapper.insert(any(AnswerRecord.class))).thenReturn(1);

        DoScoreRequest doScoreRequest = new DoScoreRequest();
        doScoreRequest.setAnswerList(answerRecord.getAnswers());
        doScoreRequest.setApplication(application);

        AnswerRecord scoredAnswerRecord = new AnswerRecord();
        scoredAnswerRecord.setId(1L);

        when(scoringClient.doScore(any(DoScoreRequest.class))).thenReturn(scoredAnswerRecord);

        // Long id = answerRecordService.addMyAnswerRecord(answerRecord, request);
        // verify(answerRecordMapper, times(1)).updateById(scoredAnswerRecord);
    }

    @Test
    void testAddMyAnswerRecord_SaveError() {
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAppId(1L);
        answerRecord.setAnswers(Collections.singletonList("answer"));

        Application application = new Application();
        application.setAuditStatus(1);

        when(userClient.getLoginUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(applicationClient.getApplicationById(anyLong())).thenReturn(ApiResponse.success(application));
        when(answerRecordMapper.insert(any(AnswerRecord.class))).thenReturn(0);

        BusinessException exception = assertThrows(BusinessException.class, () -> answerRecordService.addMyAnswerRecord(answerRecord, request));
        assertEquals("save answer record failed", exception.getMessage());
    }


    /**
     * ListMyAnswerRecord
     */
    @Test
    void testListMyAnswerRecord_Success() {
        ListMyAnswerRequest listMyAnswerRequest = new ListMyAnswerRequest();
        listMyAnswerRequest.setCurrent(1L);
        listMyAnswerRequest.setPageSize(10L);
        listMyAnswerRequest.setSortField("id");
        listMyAnswerRequest.setIsAscend(true);

        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setId(1L);

        IPage<AnswerRecord> page = new Page<>(1L, 10L);
        page.setRecords(Collections.singletonList(answerRecord));
        page.setTotal(1);

        when(userClient.getLoginUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(answerRecordMapper.selectPage(any(), any())).thenReturn(page);

        Page<AnswerRecordVo> result = answerRecordService.listMyAnswerRecord(listMyAnswerRequest, request);

        assertNotNull(result);
        assertEquals(1, result.getTotal());
        assertEquals(1L, result.getRecords().get(0).getId());
    }


    /**
     * ListAnswerRecord
     */
    @Test
    void testListAnswerRecord_Success() {
        ListAnswerRequest listAnswerRequest = new ListAnswerRequest();
        listAnswerRequest.setCurrent(1L);
        listAnswerRequest.setPageSize(10L);
        listAnswerRequest.setSortField("id");
        listAnswerRequest.setIsAscend(true);

        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setId(1L);

        IPage<AnswerRecord> page = new Page<>(1L, 10L);
        page.setRecords(Collections.singletonList(answerRecord));
        page.setTotal(1);

        when(answerRecordMapper.selectPage(any(), any())).thenReturn(page);

        Page<AnswerRecordVo> result = answerRecordService.listAnswerRecord(listAnswerRequest);

        assertNotNull(result);
        assertEquals(1, result.getTotal());
        assertEquals(1L, result.getRecords().get(0).getId());
    }

    /**
     * DeleteAnswerRecord
     */
    @Test
    void testDeleteAnswerRecord_Success() {
        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);

        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setId(1L);

        when(answerRecordMapper.selectById(anyLong())).thenReturn(answerRecord);
        when(answerRecordMapper.deleteById(anyLong())).thenReturn(1);

        Boolean result = answerRecordService.deleteAnswerRecord(idRequest);

        assertTrue(result);
    }

    @Test
    void testDeleteAnswerRecord_NotFound() {
        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);

        when(answerRecordMapper.selectById(anyLong())).thenReturn(null);

        BusinessException exception = assertThrows(BusinessException.class, () -> answerRecordService.deleteAnswerRecord(idRequest));
        assertEquals(Err.NOT_FOUND_ERROR.getCode(), exception.getCode());
    }

}