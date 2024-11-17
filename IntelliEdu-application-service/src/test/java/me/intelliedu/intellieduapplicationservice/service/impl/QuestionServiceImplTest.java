package me.intelliedu.intellieduapplicationservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import me.intelliedu.intellieduapi.client.UserClient;
import me.intelliedu.intellieduapplicationservice.mapper.QuestionMapper;
import me.intelliedu.intellieducommon.utils.BusinessException;
import me.intelliedu.intellieducommon.utils.Err;
import me.intelliedu.intellieducommon.utils.IdRequest;
import me.intelliedu.intellieducommon.utils.TableRequest;
import me.intelliedu.intelliedumodel.dto.question.GetMyQuestionRequest;
import me.intelliedu.intelliedumodel.dto.question.GetPublicQuestionRequest;
import me.intelliedu.intelliedumodel.dto.question.ListQuestionRequest;
import me.intelliedu.intelliedumodel.dto.question.QuestionContent;
import me.intelliedu.intelliedumodel.entity.Question;
import me.intelliedu.intelliedumodel.vo.QuestionVo;

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

@ContextConfiguration(classes = {QuestionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class QuestionServiceImplTest {
    @MockBean
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @MockBean
    private UserClient userClient;

    /**
     * Test {@link QuestionServiceImpl#validate(Question)}.
     * <ul>
     *   <li>Given {@link ArrayList#ArrayList()} add
     * {@link QuestionContent#QuestionContent()}.</li>
     *   <li>When {@link Question} {@link Question#getAppId()} return one.</li>
     * </ul>
     * <p>
     * Method under test: {@link QuestionServiceImpl#validate(Question)}
     */
    @Test
    @DisplayName("Test validate(Question); given ArrayList() add QuestionContent(); when Question getAppId() return one")
    void testValidate_givenArrayListAddQuestionContent_whenQuestionGetAppIdReturnOne() {
        // Arrange
        ArrayList<QuestionContent> questionContentList = new ArrayList<>();
        questionContentList.add(new QuestionContent());
        Question question = mock(Question.class);
        when(question.getAppId()).thenReturn(1L);
        when(question.getQuestions()).thenReturn(questionContentList);
        doNothing().when(question).setAppId(Mockito.<Long>any());
        doNothing().when(question).setCreateTime(Mockito.<Date>any());
        doNothing().when(question).setDeleted(Mockito.<Integer>any());
        doNothing().when(question).setId(Mockito.<Long>any());
        doNothing().when(question).setQuestions(Mockito.<List<QuestionContent>>any());
        doNothing().when(question).setUpdateTime(Mockito.<Date>any());
        doNothing().when(question).setUserId(Mockito.<Long>any());
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);

        // Act
        questionServiceImpl.validate(question);

        // Assert that nothing has changed
        verify(question).getAppId();
        verify(question).getQuestions();
        verify(question).setAppId(eq(1L));
        verify(question).setCreateTime(isA(Date.class));
        verify(question).setDeleted(eq(1));
        verify(question).setId(eq(1L));
        verify(question).setQuestions(isA(List.class));
        verify(question).setUpdateTime(isA(Date.class));
        verify(question).setUserId(eq(1L));
    }

    /**
     * Test {@link QuestionServiceImpl#validate(Question)}.
     * <ul>
     *   <li>Given minus one.</li>
     *   <li>When {@link Question} {@link Question#getAppId()} return minus one.</li>
     * </ul>
     * <p>
     * Method under test: {@link QuestionServiceImpl#validate(Question)}
     */
    @Test
    @DisplayName("Test validate(Question); given minus one; when Question getAppId() return minus one")
    void testValidate_givenMinusOne_whenQuestionGetAppIdReturnMinusOne() {
        // Arrange
        Question question = mock(Question.class);
        when(question.getAppId()).thenReturn(-1L);
        when(question.getQuestions()).thenReturn(new ArrayList<>());
        doNothing().when(question).setAppId(Mockito.<Long>any());
        doNothing().when(question).setCreateTime(Mockito.<Date>any());
        doNothing().when(question).setDeleted(Mockito.<Integer>any());
        doNothing().when(question).setId(Mockito.<Long>any());
        doNothing().when(question).setQuestions(Mockito.<List<QuestionContent>>any());
        doNothing().when(question).setUpdateTime(Mockito.<Date>any());
        doNothing().when(question).setUserId(Mockito.<Long>any());
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> questionServiceImpl.validate(question));
        verify(question).getAppId();
        verify(question).getQuestions();
        verify(question).setAppId(eq(1L));
        verify(question).setCreateTime(isA(Date.class));
        verify(question).setDeleted(eq(1));
        verify(question).setId(eq(1L));
        verify(question).setQuestions(isA(List.class));
        verify(question).setUpdateTime(isA(Date.class));
        verify(question).setUserId(eq(1L));
    }

    /**
     * Test {@link QuestionServiceImpl#validate(Question)}.
     * <ul>
     *   <li>When {@link Question} (default constructor) AppId is one.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link QuestionServiceImpl#validate(Question)}
     */
    @Test
    @DisplayName("Test validate(Question); when Question (default constructor) AppId is one; then throw BusinessException")
    void testValidate_whenQuestionAppIdIsOne_thenThrowBusinessException() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> questionServiceImpl.validate(question));
    }

    /**
     * Test {@link QuestionServiceImpl#entityToVo(Question)}.
     * <p>
     * Method under test: {@link QuestionServiceImpl#entityToVo(Question)}
     */
    @Test
    @DisplayName("Test entityToVo(Question)")
    void testEntityToVo() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);

        // Act
        QuestionVo actualEntityToVoResult = questionServiceImpl.entityToVo(question);

        // Assert
        assertEquals(1L, actualEntityToVoResult.getAppId().longValue());
        assertEquals(1L, actualEntityToVoResult.getId().longValue());
        assertEquals(1L, actualEntityToVoResult.getUserId().longValue());
        assertTrue(actualEntityToVoResult.getQuestions().isEmpty());
    }

    /**
     * Test {@link QuestionServiceImpl#getPublicQuestion(GetPublicQuestionRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#getPublicQuestion(GetPublicQuestionRequest)}
     */
    @Test
    @DisplayName("Test getPublicQuestion(GetPublicQuestionRequest); given 'null'; then throw BusinessException")
    void testGetPublicQuestion_givenNull_thenThrowBusinessException() {
        // Arrange
        GetPublicQuestionRequest getPublicQuestionRequest = new GetPublicQuestionRequest();
        getPublicQuestionRequest.setAppId(null);

        // Act and Assert
        assertThrows(BusinessException.class, () -> questionServiceImpl.getPublicQuestion(getPublicQuestionRequest));
    }

    /**
     * Test {@link QuestionServiceImpl#getPublicQuestion(GetPublicQuestionRequest)}.
     * <ul>
     *   <li>Given {@link Question} (default constructor) AppId is one.</li>
     *   <li>Then return AppId longValue is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#getPublicQuestion(GetPublicQuestionRequest)}
     */
    @Test
    @DisplayName("Test getPublicQuestion(GetPublicQuestionRequest); given Question (default constructor) AppId is one; then return AppId longValue is one")
    void testGetPublicQuestion_givenQuestionAppIdIsOne_thenReturnAppIdLongValueIsOne() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);
        when(questionMapper.selectOne(Mockito.<Wrapper<Question>>any(), anyBoolean())).thenReturn(question);

        GetPublicQuestionRequest getPublicQuestionRequest = new GetPublicQuestionRequest();
        getPublicQuestionRequest.setAppId(1L);

        // Act
        QuestionVo actualPublicQuestion = questionServiceImpl.getPublicQuestion(getPublicQuestionRequest);

        // Assert
        verify(questionMapper).selectOne(isA(Wrapper.class), eq(true));
        assertEquals(1L, actualPublicQuestion.getAppId().longValue());
        assertEquals(1L, actualPublicQuestion.getId().longValue());
        assertEquals(1L, actualPublicQuestion.getUserId().longValue());
        assertTrue(actualPublicQuestion.getQuestions().isEmpty());
    }

    /**
     * Test {@link QuestionServiceImpl#addMyQuestion(Question, HttpServletRequest)}.
     * <ul>
     *   <li>Given minus one.</li>
     *   <li>When {@link Question} {@link Question#getAppId()} return minus one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#addMyQuestion(Question, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyQuestion(Question, HttpServletRequest); given minus one; when Question getAppId() return minus one")
    void testAddMyQuestion_givenMinusOne_whenQuestionGetAppIdReturnMinusOne() {
        // Arrange
        Question question = mock(Question.class);
        when(question.getAppId()).thenReturn(-1L);
        when(question.getQuestions()).thenReturn(new ArrayList<>());
        doNothing().when(question).setAppId(Mockito.<Long>any());
        doNothing().when(question).setCreateTime(Mockito.<Date>any());
        doNothing().when(question).setDeleted(Mockito.<Integer>any());
        doNothing().when(question).setId(Mockito.<Long>any());
        doNothing().when(question).setQuestions(Mockito.<List<QuestionContent>>any());
        doNothing().when(question).setUpdateTime(Mockito.<Date>any());
        doNothing().when(question).setUserId(Mockito.<Long>any());
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> questionServiceImpl.addMyQuestion(question, new MockHttpServletRequest()));
        verify(question).getAppId();
        verify(question).getQuestions();
        verify(question).setAppId(eq(1L));
        verify(question).setCreateTime(isA(Date.class));
        verify(question).setDeleted(eq(1));
        verify(question).setId(eq(1L));
        verify(question).setQuestions(isA(List.class));
        verify(question).setUpdateTime(isA(Date.class));
        verify(question).setUserId(eq(1L));
    }

    /**
     * Test {@link QuestionServiceImpl#addMyQuestion(Question, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link Question} {@link Question#getAppId()} return
     * {@code null}.</li>
     *   <li>Then calls {@link Question#getAppId()}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#addMyQuestion(Question, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyQuestion(Question, HttpServletRequest); given 'null'; when Question getAppId() return 'null'; then calls getAppId()")
    void testAddMyQuestion_givenNull_whenQuestionGetAppIdReturnNull_thenCallsGetAppId() {
        // Arrange
        ArrayList<QuestionContent> questionContentList = new ArrayList<>();
        questionContentList.add(new QuestionContent());
        Question question = mock(Question.class);
        when(question.getAppId()).thenReturn(null);
        when(question.getQuestions()).thenReturn(questionContentList);
        doNothing().when(question).setAppId(Mockito.<Long>any());
        doNothing().when(question).setCreateTime(Mockito.<Date>any());
        doNothing().when(question).setDeleted(Mockito.<Integer>any());
        doNothing().when(question).setId(Mockito.<Long>any());
        doNothing().when(question).setQuestions(Mockito.<List<QuestionContent>>any());
        doNothing().when(question).setUpdateTime(Mockito.<Date>any());
        doNothing().when(question).setUserId(Mockito.<Long>any());
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> questionServiceImpl.addMyQuestion(question, new MockHttpServletRequest()));
        verify(question).getAppId();
        verify(question).getQuestions();
        verify(question).setAppId(eq(1L));
        verify(question).setCreateTime(isA(Date.class));
        verify(question).setDeleted(eq(1));
        verify(question).setId(eq(1L));
        verify(question).setQuestions(isA(List.class));
        verify(question).setUpdateTime(isA(Date.class));
        verify(question).setUserId(eq(1L));
    }

    /**
     * Test {@link QuestionServiceImpl#addMyQuestion(Question, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link Question} (default constructor) AppId is one.</li>
     *   <li>Then calls {@link BaseMapper#selectOne(Wrapper, boolean)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#addMyQuestion(Question, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyQuestion(Question, HttpServletRequest); given Question (default constructor) AppId is one; then calls selectOne(Wrapper, boolean)")
    void testAddMyQuestion_givenQuestionAppIdIsOne_thenCallsSelectOne() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);
        when(questionMapper.selectOne(Mockito.<Wrapper<Question>>any(), anyBoolean())).thenReturn(question);

        ArrayList<QuestionContent> questionContentList = new ArrayList<>();
        questionContentList.add(new QuestionContent());
        Question question2 = mock(Question.class);
        when(question2.getAppId()).thenReturn(1L);
        when(question2.getQuestions()).thenReturn(questionContentList);
        doNothing().when(question2).setAppId(Mockito.<Long>any());
        doNothing().when(question2).setCreateTime(Mockito.<Date>any());
        doNothing().when(question2).setDeleted(Mockito.<Integer>any());
        doNothing().when(question2).setId(Mockito.<Long>any());
        doNothing().when(question2).setQuestions(Mockito.<List<QuestionContent>>any());
        doNothing().when(question2).setUpdateTime(Mockito.<Date>any());
        doNothing().when(question2).setUserId(Mockito.<Long>any());
        question2.setAppId(1L);
        question2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question2.setDeleted(1);
        question2.setId(1L);
        question2.setQuestions(new ArrayList<>());
        question2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question2.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> questionServiceImpl.addMyQuestion(question2, new MockHttpServletRequest()));
        verify(questionMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(question2, atLeast(1)).getAppId();
        verify(question2).getQuestions();
        verify(question2).setAppId(eq(1L));
        verify(question2).setCreateTime(isA(Date.class));
        verify(question2).setDeleted(eq(1));
        verify(question2).setId(eq(1L));
        verify(question2).setQuestions(isA(List.class));
        verify(question2).setUpdateTime(isA(Date.class));
        verify(question2).setUserId(eq(1L));
    }

    /**
     * Test {@link QuestionServiceImpl#addMyQuestion(Question, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link QuestionMapper}.</li>
     *   <li>When {@link Question} (default constructor) AppId is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#addMyQuestion(Question, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyQuestion(Question, HttpServletRequest); given QuestionMapper; when Question (default constructor) AppId is one")
    void testAddMyQuestion_givenQuestionMapper_whenQuestionAppIdIsOne() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> questionServiceImpl.addMyQuestion(question, new MockHttpServletRequest()));
    }

    /**
     * Test
     * {@link QuestionServiceImpl#getMyQuestion(GetMyQuestionRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#getMyQuestion(GetMyQuestionRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test getMyQuestion(GetMyQuestionRequest, HttpServletRequest)")
    void testGetMyQuestion() {
        // Arrange
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        GetMyQuestionRequest getMyQuestionRequest = new GetMyQuestionRequest();
        getMyQuestionRequest.setAppId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> questionServiceImpl.getMyQuestion(getMyQuestionRequest, new MockHttpServletRequest()));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test
     * {@link QuestionServiceImpl#getMyQuestion(GetMyQuestionRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link GetMyQuestionRequest} (default constructor) AppId is
     * {@code null}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#getMyQuestion(GetMyQuestionRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test getMyQuestion(GetMyQuestionRequest, HttpServletRequest); given 'null'; when GetMyQuestionRequest (default constructor) AppId is 'null'")
    void testGetMyQuestion_givenNull_whenGetMyQuestionRequestAppIdIsNull() {
        // Arrange
        GetMyQuestionRequest getMyQuestionRequest = new GetMyQuestionRequest();
        getMyQuestionRequest.setAppId(null);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> questionServiceImpl.getMyQuestion(getMyQuestionRequest, new MockHttpServletRequest()));
    }

    /**
     * Test
     * {@link QuestionServiceImpl#getMyQuestion(GetMyQuestionRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link Question} (default constructor) AppId is one.</li>
     *   <li>Then return AppId longValue is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#getMyQuestion(GetMyQuestionRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test getMyQuestion(GetMyQuestionRequest, HttpServletRequest); given Question (default constructor) AppId is one; then return AppId longValue is one")
    void testGetMyQuestion_givenQuestionAppIdIsOne_thenReturnAppIdLongValueIsOne() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);
        when(questionMapper.selectOne(Mockito.<Wrapper<Question>>any(), anyBoolean())).thenReturn(question);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        GetMyQuestionRequest getMyQuestionRequest = new GetMyQuestionRequest();
        getMyQuestionRequest.setAppId(1L);

        // Act
        QuestionVo actualMyQuestion = questionServiceImpl.getMyQuestion(getMyQuestionRequest, new MockHttpServletRequest());

        // Assert
        verify(questionMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertEquals(1L, actualMyQuestion.getAppId().longValue());
        assertEquals(1L, actualMyQuestion.getId().longValue());
        assertEquals(1L, actualMyQuestion.getUserId().longValue());
        assertTrue(actualMyQuestion.getQuestions().isEmpty());
    }

    /**
     * Test
     * {@link QuestionServiceImpl#updateMyQuestion(Question, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#updateMyQuestion(Question, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyQuestion(Question, HttpServletRequest)")
    void testUpdateMyQuestion() {
        // Arrange
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> questionServiceImpl.updateMyQuestion(question, new MockHttpServletRequest()));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test
     * {@link QuestionServiceImpl#updateMyQuestion(Question, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link Question} (default constructor) Id is {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#updateMyQuestion(Question, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyQuestion(Question, HttpServletRequest); given 'null'; when Question (default constructor) Id is 'null'; then throw BusinessException")
    void testUpdateMyQuestion_givenNull_whenQuestionIdIsNull_thenThrowBusinessException() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(null);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> questionServiceImpl.updateMyQuestion(question, new MockHttpServletRequest()));
    }

    /**
     * Test
     * {@link QuestionServiceImpl#updateMyQuestion(Question, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link QuestionMapper} {@link BaseMapper#updateById(Object)} return
     * one.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#updateMyQuestion(Question, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyQuestion(Question, HttpServletRequest); given QuestionMapper updateById(Object) return one; then return 'true'")
    void testUpdateMyQuestion_givenQuestionMapperUpdateByIdReturnOne_thenReturnTrue() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);
        when(questionMapper.updateById(Mockito.<Question>any())).thenReturn(1);
        when(questionMapper.selectOne(Mockito.<Wrapper<Question>>any(), anyBoolean())).thenReturn(question);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        Question question2 = new Question();
        question2.setAppId(1L);
        question2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question2.setDeleted(1);
        question2.setId(1L);
        question2.setQuestions(new ArrayList<>());
        question2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question2.setUserId(1L);

        // Act
        Boolean actualUpdateMyQuestionResult = questionServiceImpl.updateMyQuestion(question2,
                new MockHttpServletRequest());

        // Assert
        verify(questionMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(questionMapper).updateById(isA(Question.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertTrue(actualUpdateMyQuestionResult);
    }

    /**
     * Test
     * {@link QuestionServiceImpl#updateMyQuestion(Question, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link QuestionMapper} {@link BaseMapper#updateById(Object)} return
     * zero.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#updateMyQuestion(Question, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyQuestion(Question, HttpServletRequest); given QuestionMapper updateById(Object) return zero; then return 'false'")
    void testUpdateMyQuestion_givenQuestionMapperUpdateByIdReturnZero_thenReturnFalse() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);
        when(questionMapper.updateById(Mockito.<Question>any())).thenReturn(0);
        when(questionMapper.selectOne(Mockito.<Wrapper<Question>>any(), anyBoolean())).thenReturn(question);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        Question question2 = new Question();
        question2.setAppId(1L);
        question2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question2.setDeleted(1);
        question2.setId(1L);
        question2.setQuestions(new ArrayList<>());
        question2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question2.setUserId(1L);

        // Act
        Boolean actualUpdateMyQuestionResult = questionServiceImpl.updateMyQuestion(question2,
                new MockHttpServletRequest());

        // Assert
        verify(questionMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(questionMapper).updateById(isA(Question.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertFalse(actualUpdateMyQuestionResult);
    }

    /**
     * Test
     * {@link QuestionServiceImpl#deleteMyQuestion(IdRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link IdRequest} (default constructor) Id is {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#deleteMyQuestion(IdRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test deleteMyQuestion(IdRequest, HttpServletRequest); given 'null'; when IdRequest (default constructor) Id is 'null'; then throw BusinessException")
    void testDeleteMyQuestion_givenNull_whenIdRequestIdIsNull_thenThrowBusinessException() {
        // Arrange
        IdRequest idRequest = new IdRequest();
        idRequest.setId(null);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> questionServiceImpl.deleteMyQuestion(idRequest, new MockHttpServletRequest()));
    }

    /**
     * Test
     * {@link QuestionServiceImpl#deleteMyQuestion(IdRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Then calls {@link UserClient#getLoginUserId(HttpServletRequest)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#deleteMyQuestion(IdRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test deleteMyQuestion(IdRequest, HttpServletRequest); then calls getLoginUserId(HttpServletRequest)")
    void testDeleteMyQuestion_thenCallsGetLoginUserId() {
        // Arrange
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> questionServiceImpl.deleteMyQuestion(idRequest, new MockHttpServletRequest()));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test {@link QuestionServiceImpl#listQuestion(ListQuestionRequest)}.
     * <ul>
     *   <li>Given {@code false}.</li>
     *   <li>When {@link ListQuestionRequest} {@link TableRequest#getIsAscend()}
     * return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#listQuestion(ListQuestionRequest)}
     */
    @Test
    @DisplayName("Test listQuestion(ListQuestionRequest); given 'false'; when ListQuestionRequest getIsAscend() return 'false'")
    void testListQuestion_givenFalse_whenListQuestionRequestGetIsAscendReturnFalse() {
        // Arrange
        IPage<Question> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(questionMapper.selectPage(Mockito.<IPage<Question>>any(), Mockito.<Wrapper<Question>>any())).thenReturn(iPage);
        ListQuestionRequest listQuestionRequest = mock(ListQuestionRequest.class);
        when(listQuestionRequest.getIsAscend()).thenReturn(false);
        when(listQuestionRequest.getCurrent()).thenReturn(1L);
        when(listQuestionRequest.getPageSize()).thenReturn(3L);
        when(listQuestionRequest.getAppId()).thenReturn(1L);
        when(listQuestionRequest.getId()).thenReturn(1L);
        when(listQuestionRequest.getUserId()).thenReturn(1L);
        when(listQuestionRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listQuestionRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listQuestionRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listQuestionRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listQuestionRequest).setSortField(Mockito.<String>any());
        doNothing().when(listQuestionRequest).setAppId(Mockito.<Long>any());
        doNothing().when(listQuestionRequest).setId(Mockito.<Long>any());
        doNothing().when(listQuestionRequest).setUserId(Mockito.<Long>any());
        listQuestionRequest.setAppId(1L);
        listQuestionRequest.setCurrent(1L);
        listQuestionRequest.setId(1L);
        listQuestionRequest.setIsAscend(true);
        listQuestionRequest.setPageSize(3L);
        listQuestionRequest.setSortField("Sort Field");
        listQuestionRequest.setUserId(1L);

        // Act
        Page<QuestionVo> actualListQuestionResult = questionServiceImpl.listQuestion(listQuestionRequest);

        // Assert
        verify(questionMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listQuestionRequest).getCurrent();
        verify(listQuestionRequest).getIsAscend();
        verify(listQuestionRequest).getPageSize();
        verify(listQuestionRequest, atLeast(1)).getSortField();
        verify(listQuestionRequest).setCurrent(eq(1L));
        verify(listQuestionRequest).setIsAscend(eq(true));
        verify(listQuestionRequest).setPageSize(eq(3L));
        verify(listQuestionRequest).setSortField(eq("Sort Field"));
        verify(listQuestionRequest, atLeast(1)).getAppId();
        verify(listQuestionRequest, atLeast(1)).getId();
        verify(listQuestionRequest, atLeast(1)).getUserId();
        verify(listQuestionRequest).setAppId(eq(1L));
        verify(listQuestionRequest).setId(eq(1L));
        verify(listQuestionRequest).setUserId(eq(1L));
        assertEquals(1L, actualListQuestionResult.getCurrent());
        assertEquals(1L, actualListQuestionResult.getPages());
        assertEquals(1L, actualListQuestionResult.getTotal());
        assertFalse(actualListQuestionResult.hasPrevious());
    }

    /**
     * Test {@link QuestionServiceImpl#listQuestion(ListQuestionRequest)}.
     * <ul>
     *   <li>Given {@link IPage} {@link IPage#getRecords()} return
     * {@link ArrayList#ArrayList()}.</li>
     *   <li>Then return Current is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#listQuestion(ListQuestionRequest)}
     */
    @Test
    @DisplayName("Test listQuestion(ListQuestionRequest); given IPage getRecords() return ArrayList(); then return Current is one")
    void testListQuestion_givenIPageGetRecordsReturnArrayList_thenReturnCurrentIsOne() {
        // Arrange
        IPage<Question> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(questionMapper.selectPage(Mockito.<IPage<Question>>any(), Mockito.<Wrapper<Question>>any())).thenReturn(iPage);

        ListQuestionRequest listQuestionRequest = new ListQuestionRequest();
        listQuestionRequest.setAppId(1L);
        listQuestionRequest.setCurrent(1L);
        listQuestionRequest.setId(1L);
        listQuestionRequest.setIsAscend(true);
        listQuestionRequest.setPageSize(3L);
        listQuestionRequest.setSortField("Sort Field");
        listQuestionRequest.setUserId(1L);

        // Act
        Page<QuestionVo> actualListQuestionResult = questionServiceImpl.listQuestion(listQuestionRequest);

        // Assert
        verify(questionMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        assertEquals(1L, actualListQuestionResult.getCurrent());
        assertEquals(1L, actualListQuestionResult.getPages());
        assertEquals(1L, actualListQuestionResult.getTotal());
        assertFalse(actualListQuestionResult.hasPrevious());
    }

    /**
     * Test {@link QuestionServiceImpl#listQuestion(ListQuestionRequest)}.
     * <ul>
     *   <li>Given {@link QuestionMapper}
     * {@link BaseMapper#selectPage(IPage, Wrapper)} return
     * {@link Page#Page()}.</li>
     *   <li>Then return Pages is zero.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#listQuestion(ListQuestionRequest)}
     */
    @Test
    @DisplayName("Test listQuestion(ListQuestionRequest); given QuestionMapper selectPage(IPage, Wrapper) return Page(); then return Pages is zero")
    void testListQuestion_givenQuestionMapperSelectPageReturnPage_thenReturnPagesIsZero() {
        // Arrange
        when(questionMapper.selectPage(Mockito.<IPage<Question>>any(), Mockito.<Wrapper<Question>>any()))
                .thenReturn(new Page<>());

        ListQuestionRequest listQuestionRequest = new ListQuestionRequest();
        listQuestionRequest.setAppId(1L);
        listQuestionRequest.setCurrent(1L);
        listQuestionRequest.setId(1L);
        listQuestionRequest.setIsAscend(true);
        listQuestionRequest.setPageSize(3L);
        listQuestionRequest.setSortField("Sort Field");
        listQuestionRequest.setUserId(1L);

        // Act
        Page<QuestionVo> actualListQuestionResult = questionServiceImpl.listQuestion(listQuestionRequest);

        // Assert
        verify(questionMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        assertEquals(0L, actualListQuestionResult.getPages());
        assertEquals(0L, actualListQuestionResult.getTotal());
        assertEquals(1L, actualListQuestionResult.getCurrent());
        assertFalse(actualListQuestionResult.hasPrevious());
    }

    /**
     * Test {@link QuestionServiceImpl#listQuestion(ListQuestionRequest)}.
     * <ul>
     *   <li>Given twenty.</li>
     *   <li>Then return Current is twenty.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link QuestionServiceImpl#listQuestion(ListQuestionRequest)}
     */
    @Test
    @DisplayName("Test listQuestion(ListQuestionRequest); given twenty; then return Current is twenty")
    void testListQuestion_givenTwenty_thenReturnCurrentIsTwenty() {
        // Arrange
        IPage<Question> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(questionMapper.selectPage(Mockito.<IPage<Question>>any(), Mockito.<Wrapper<Question>>any())).thenReturn(iPage);
        ListQuestionRequest listQuestionRequest = mock(ListQuestionRequest.class);
        when(listQuestionRequest.getIsAscend()).thenReturn(true);
        when(listQuestionRequest.getCurrent()).thenReturn(20L);
        when(listQuestionRequest.getPageSize()).thenReturn(3L);
        when(listQuestionRequest.getAppId()).thenReturn(1L);
        when(listQuestionRequest.getId()).thenReturn(1L);
        when(listQuestionRequest.getUserId()).thenReturn(1L);
        when(listQuestionRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listQuestionRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listQuestionRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listQuestionRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listQuestionRequest).setSortField(Mockito.<String>any());
        doNothing().when(listQuestionRequest).setAppId(Mockito.<Long>any());
        doNothing().when(listQuestionRequest).setId(Mockito.<Long>any());
        doNothing().when(listQuestionRequest).setUserId(Mockito.<Long>any());
        listQuestionRequest.setAppId(1L);
        listQuestionRequest.setCurrent(1L);
        listQuestionRequest.setId(1L);
        listQuestionRequest.setIsAscend(true);
        listQuestionRequest.setPageSize(3L);
        listQuestionRequest.setSortField("Sort Field");
        listQuestionRequest.setUserId(1L);

        // Act
        Page<QuestionVo> actualListQuestionResult = questionServiceImpl.listQuestion(listQuestionRequest);

        // Assert
        verify(questionMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listQuestionRequest).getCurrent();
        verify(listQuestionRequest).getIsAscend();
        verify(listQuestionRequest).getPageSize();
        verify(listQuestionRequest, atLeast(1)).getSortField();
        verify(listQuestionRequest).setCurrent(eq(1L));
        verify(listQuestionRequest).setIsAscend(eq(true));
        verify(listQuestionRequest).setPageSize(eq(3L));
        verify(listQuestionRequest).setSortField(eq("Sort Field"));
        verify(listQuestionRequest, atLeast(1)).getAppId();
        verify(listQuestionRequest, atLeast(1)).getId();
        verify(listQuestionRequest, atLeast(1)).getUserId();
        verify(listQuestionRequest).setAppId(eq(1L));
        verify(listQuestionRequest).setId(eq(1L));
        verify(listQuestionRequest).setUserId(eq(1L));
        assertEquals(1L, actualListQuestionResult.getPages());
        assertEquals(1L, actualListQuestionResult.getTotal());
        assertEquals(20L, actualListQuestionResult.getCurrent());
        assertTrue(actualListQuestionResult.hasPrevious());
    }

    /**
     * Test {@link QuestionServiceImpl#updateQuestion(Question)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link Question} (default constructor) Id is {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link QuestionServiceImpl#updateQuestion(Question)}
     */
    @Test
    @DisplayName("Test updateQuestion(Question); given 'null'; when Question (default constructor) Id is 'null'; then throw BusinessException")
    void testUpdateQuestion_givenNull_whenQuestionIdIsNull_thenThrowBusinessException() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(null);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class, () -> questionServiceImpl.updateQuestion(question));
    }

    /**
     * Test {@link QuestionServiceImpl#updateQuestion(Question)}.
     * <ul>
     *   <li>Given {@link QuestionMapper} {@link BaseMapper#updateById(Object)} return
     * one.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link QuestionServiceImpl#updateQuestion(Question)}
     */
    @Test
    @DisplayName("Test updateQuestion(Question); given QuestionMapper updateById(Object) return one; then return 'true'")
    void testUpdateQuestion_givenQuestionMapperUpdateByIdReturnOne_thenReturnTrue() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);
        when(questionMapper.updateById(Mockito.<Question>any())).thenReturn(1);
        when(questionMapper.selectById(Mockito.<Serializable>any())).thenReturn(question);

        Question question2 = new Question();
        question2.setAppId(1L);
        question2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question2.setDeleted(1);
        question2.setId(1L);
        question2.setQuestions(new ArrayList<>());
        question2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question2.setUserId(1L);

        // Act
        Boolean actualUpdateQuestionResult = questionServiceImpl.updateQuestion(question2);

        // Assert
        verify(questionMapper).selectById(isA(Serializable.class));
        verify(questionMapper).updateById(isA(Question.class));
        assertTrue(actualUpdateQuestionResult);
    }

    /**
     * Test {@link QuestionServiceImpl#updateQuestion(Question)}.
     * <ul>
     *   <li>Given {@link QuestionMapper} {@link BaseMapper#updateById(Object)} return
     * zero.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link QuestionServiceImpl#updateQuestion(Question)}
     */
    @Test
    @DisplayName("Test updateQuestion(Question); given QuestionMapper updateById(Object) return zero; then return 'false'")
    void testUpdateQuestion_givenQuestionMapperUpdateByIdReturnZero_thenReturnFalse() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);
        when(questionMapper.updateById(Mockito.<Question>any())).thenReturn(0);
        when(questionMapper.selectById(Mockito.<Serializable>any())).thenReturn(question);

        Question question2 = new Question();
        question2.setAppId(1L);
        question2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question2.setDeleted(1);
        question2.setId(1L);
        question2.setQuestions(new ArrayList<>());
        question2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question2.setUserId(1L);

        // Act
        Boolean actualUpdateQuestionResult = questionServiceImpl.updateQuestion(question2);

        // Assert
        verify(questionMapper).selectById(isA(Serializable.class));
        verify(questionMapper).updateById(isA(Question.class));
        assertFalse(actualUpdateQuestionResult);
    }

    /**
     * Test {@link QuestionServiceImpl#deleteQuestion(IdRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link IdRequest} (default constructor) Id is {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link QuestionServiceImpl#deleteQuestion(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteQuestion(IdRequest); given 'null'; when IdRequest (default constructor) Id is 'null'; then throw BusinessException")
    void testDeleteQuestion_givenNull_whenIdRequestIdIsNull_thenThrowBusinessException() {
        // Arrange
        IdRequest idRequest = new IdRequest();
        idRequest.setId(null);

        // Act and Assert
        assertThrows(BusinessException.class, () -> questionServiceImpl.deleteQuestion(idRequest));
    }

    /**
     * Test {@link QuestionServiceImpl#getQuestionByAppId(Long)}.
     * <ul>
     *   <li>Given {@link Question} (default constructor) AppId is one.</li>
     *   <li>When one.</li>
     *   <li>Then return {@link Question} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test: {@link QuestionServiceImpl#getQuestionByAppId(Long)}
     */
    @Test
    @DisplayName("Test getQuestionByAppId(Long); given Question (default constructor) AppId is one; when one; then return Question (default constructor)")
    void testGetQuestionByAppId_givenQuestionAppIdIsOne_whenOne_thenReturnQuestion() {
        // Arrange
        Question question = new Question();
        question.setAppId(1L);
        question.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setDeleted(1);
        question.setId(1L);
        question.setQuestions(new ArrayList<>());
        question.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        question.setUserId(1L);
        when(questionMapper.selectOne(Mockito.<Wrapper<Question>>any())).thenReturn(question);

        // Act
        Question actualQuestionByAppId = questionServiceImpl.getQuestionByAppId(1L);

        // Assert
        verify(questionMapper).selectOne(isA(Wrapper.class));
        assertSame(question, actualQuestionByAppId);
    }

    /**
     * Test {@link QuestionServiceImpl#getQuestionByAppId(Long)}.
     * <ul>
     *   <li>When {@code null}.</li>
     *   <li>Then return {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link QuestionServiceImpl#getQuestionByAppId(Long)}
     */
    @Test
    @DisplayName("Test getQuestionByAppId(Long); when 'null'; then return 'null'")
    void testGetQuestionByAppId_whenNull_thenReturnNull() {
        // Arrange, Act and Assert
        assertNull(questionServiceImpl.getQuestionByAppId(null));
    }

    /**
     * Test {@link QuestionServiceImpl#getQuestionByAppId(Long)}.
     * <ul>
     *   <li>When zero.</li>
     *   <li>Then return {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link QuestionServiceImpl#getQuestionByAppId(Long)}
     */
    @Test
    @DisplayName("Test getQuestionByAppId(Long); when zero; then return 'null'")
    void testGetQuestionByAppId_whenZero_thenReturnNull() {
        // Arrange, Act and Assert
        assertNull(questionServiceImpl.getQuestionByAppId(0L));
    }
}
