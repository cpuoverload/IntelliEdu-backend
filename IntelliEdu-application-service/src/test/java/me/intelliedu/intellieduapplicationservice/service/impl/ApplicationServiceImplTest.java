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
import me.intelliedu.intellieduapplicationservice.mapper.ApplicationMapper;
import me.intelliedu.intellieducommon.utils.BusinessException;
import me.intelliedu.intellieducommon.utils.Err;
import me.intelliedu.intellieducommon.utils.IdRequest;
import me.intelliedu.intellieducommon.utils.TableRequest;
import me.intelliedu.intelliedumodel.dto.application.ListAppRequest;
import me.intelliedu.intelliedumodel.dto.application.ListMyAppRequest;
import me.intelliedu.intelliedumodel.dto.application.ListPublicAppRequest;
import me.intelliedu.intelliedumodel.entity.Application;
import me.intelliedu.intelliedumodel.vo.ApplicationVo;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
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

@ContextConfiguration(classes = {ApplicationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ApplicationServiceImplTest {
    @MockBean
    private ApplicationMapper applicationMapper;

    @Autowired
    private ApplicationServiceImpl applicationServiceImpl;

    @MockBean
    private UserClient userClient;

    /**
     * Test {@link ApplicationServiceImpl#validate(Application)}.
     * <ul>
     *   <li>Given empty string.</li>
     *   <li>When {@link Application} {@link Application#getAppName()} return empty
     * string.</li>
     * </ul>
     * <p>
     * Method under test: {@link ApplicationServiceImpl#validate(Application)}
     */
    @Test
    @DisplayName("Test validate(Application); given empty string; when Application getAppName() return empty string")
    void testValidate_givenEmptyString_whenApplicationGetAppNameReturnEmptyString() {
        // Arrange
        Application application = mock(Application.class);
        when(application.getStrategy()).thenReturn(1);
        when(application.getType()).thenReturn(1);
        when(application.getAppName()).thenReturn("");
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

        // Act and Assert
        assertThrows(BusinessException.class, () -> applicationServiceImpl.validate(application));
        verify(application).getAppName();
        verify(application).getStrategy();
        verify(application).getType();
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
     * Test {@link ApplicationServiceImpl#validate(Application)}.
     * <ul>
     *   <li>Given minus one.</li>
     *   <li>When {@link Application} {@link Application#getStrategy()} return minus
     * one.</li>
     * </ul>
     * <p>
     * Method under test: {@link ApplicationServiceImpl#validate(Application)}
     */
    @Test
    @DisplayName("Test validate(Application); given minus one; when Application getStrategy() return minus one")
    void testValidate_givenMinusOne_whenApplicationGetStrategyReturnMinusOne() {
        // Arrange
        Application application = mock(Application.class);
        when(application.getStrategy()).thenReturn(-1);
        when(application.getType()).thenReturn(1);
        when(application.getAppName()).thenReturn("App Name");
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

        // Act and Assert
        assertThrows(BusinessException.class, () -> applicationServiceImpl.validate(application));
        verify(application).getAppName();
        verify(application).getStrategy();
        verify(application).getType();
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
     * Test {@link ApplicationServiceImpl#validate(Application)}.
     * <ul>
     *   <li>Given minus one.</li>
     *   <li>When {@link Application} {@link Application#getType()} return minus
     * one.</li>
     * </ul>
     * <p>
     * Method under test: {@link ApplicationServiceImpl#validate(Application)}
     */
    @Test
    @DisplayName("Test validate(Application); given minus one; when Application getType() return minus one")
    void testValidate_givenMinusOne_whenApplicationGetTypeReturnMinusOne() {
        // Arrange
        Application application = mock(Application.class);
        when(application.getStrategy()).thenReturn(1);
        when(application.getType()).thenReturn(-1);
        when(application.getAppName()).thenReturn("App Name");
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

        // Act and Assert
        assertThrows(BusinessException.class, () -> applicationServiceImpl.validate(application));
        verify(application).getAppName();
        verify(application).getStrategy();
        verify(application).getType();
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
     * Test {@link ApplicationServiceImpl#entityToVo(Application)}.
     * <p>
     * Method under test: {@link ApplicationServiceImpl#entityToVo(Application)}
     */
    @Test
    @DisplayName("Test entityToVo(Application)")
    void testEntityToVo() {
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

        // Act
        ApplicationVo actualEntityToVoResult = applicationServiceImpl.entityToVo(application);

        // Assert
        assertEquals("App Name", actualEntityToVoResult.getAppName());
        assertEquals("Audit Message", actualEntityToVoResult.getAuditMessage());
        assertEquals("The characteristics of someone or something", actualEntityToVoResult.getDescription());
        assertEquals("https://example.org/example", actualEntityToVoResult.getImageUrl());
        assertNull(actualEntityToVoResult.getUserVo());
        assertEquals(1, actualEntityToVoResult.getAuditStatus().intValue());
        assertEquals(1, actualEntityToVoResult.getStrategy().intValue());
        assertEquals(1, actualEntityToVoResult.getType().intValue());
        assertEquals(1L, actualEntityToVoResult.getAuditorId().longValue());
        assertEquals(1L, actualEntityToVoResult.getId().longValue());
        assertEquals(1L, actualEntityToVoResult.getUserId().longValue());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#listPublicApplication(ListPublicAppRequest)}.
     * <ul>
     *   <li>Given empty string.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listPublicApplication(ListPublicAppRequest)}
     */
    @Test
    @DisplayName("Test listPublicApplication(ListPublicAppRequest); given empty string")
    void testListPublicApplication_givenEmptyString() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);
        ListPublicAppRequest listPublicAppRequest = mock(ListPublicAppRequest.class);
        when(listPublicAppRequest.getIsAscend()).thenReturn(true);
        when(listPublicAppRequest.getCurrent()).thenReturn(1L);
        when(listPublicAppRequest.getPageSize()).thenReturn(3L);
        when(listPublicAppRequest.getSortField()).thenReturn("Sort Field");
        when(listPublicAppRequest.getAppName()).thenReturn("");
        doNothing().when(listPublicAppRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listPublicAppRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listPublicAppRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listPublicAppRequest).setSortField(Mockito.<String>any());
        doNothing().when(listPublicAppRequest).setAppName(Mockito.<String>any());
        listPublicAppRequest.setAppName("App Name");
        listPublicAppRequest.setCurrent(1L);
        listPublicAppRequest.setIsAscend(true);
        listPublicAppRequest.setPageSize(3L);
        listPublicAppRequest.setSortField("Sort Field");

        // Act
        Page<ApplicationVo> actualListPublicApplicationResult = applicationServiceImpl
                .listPublicApplication(listPublicAppRequest);

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listPublicAppRequest).getCurrent();
        verify(listPublicAppRequest).getIsAscend();
        verify(listPublicAppRequest).getPageSize();
        verify(listPublicAppRequest, atLeast(1)).getSortField();
        verify(listPublicAppRequest).setCurrent(eq(1L));
        verify(listPublicAppRequest).setIsAscend(eq(true));
        verify(listPublicAppRequest).setPageSize(eq(3L));
        verify(listPublicAppRequest).setSortField(eq("Sort Field"));
        verify(listPublicAppRequest, atLeast(1)).getAppName();
        verify(listPublicAppRequest).setAppName(eq("App Name"));
        assertEquals(1L, actualListPublicApplicationResult.getCurrent());
        assertEquals(1L, actualListPublicApplicationResult.getPages());
        assertEquals(1L, actualListPublicApplicationResult.getTotal());
        assertFalse(actualListPublicApplicationResult.hasPrevious());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#listPublicApplication(ListPublicAppRequest)}.
     * <ul>
     *   <li>Given {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listPublicApplication(ListPublicAppRequest)}
     */
    @Test
    @DisplayName("Test listPublicApplication(ListPublicAppRequest); given 'false'")
    void testListPublicApplication_givenFalse() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);
        ListPublicAppRequest listPublicAppRequest = mock(ListPublicAppRequest.class);
        when(listPublicAppRequest.getIsAscend()).thenReturn(false);
        when(listPublicAppRequest.getCurrent()).thenReturn(1L);
        when(listPublicAppRequest.getPageSize()).thenReturn(3L);
        when(listPublicAppRequest.getSortField()).thenReturn("Sort Field");
        when(listPublicAppRequest.getAppName()).thenReturn("App Name");
        doNothing().when(listPublicAppRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listPublicAppRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listPublicAppRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listPublicAppRequest).setSortField(Mockito.<String>any());
        doNothing().when(listPublicAppRequest).setAppName(Mockito.<String>any());
        listPublicAppRequest.setAppName("App Name");
        listPublicAppRequest.setCurrent(1L);
        listPublicAppRequest.setIsAscend(true);
        listPublicAppRequest.setPageSize(3L);
        listPublicAppRequest.setSortField("Sort Field");

        // Act
        Page<ApplicationVo> actualListPublicApplicationResult = applicationServiceImpl
                .listPublicApplication(listPublicAppRequest);

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listPublicAppRequest).getCurrent();
        verify(listPublicAppRequest).getIsAscend();
        verify(listPublicAppRequest).getPageSize();
        verify(listPublicAppRequest, atLeast(1)).getSortField();
        verify(listPublicAppRequest).setCurrent(eq(1L));
        verify(listPublicAppRequest).setIsAscend(eq(true));
        verify(listPublicAppRequest).setPageSize(eq(3L));
        verify(listPublicAppRequest).setSortField(eq("Sort Field"));
        verify(listPublicAppRequest, atLeast(1)).getAppName();
        verify(listPublicAppRequest).setAppName(eq("App Name"));
        assertEquals(1L, actualListPublicApplicationResult.getCurrent());
        assertEquals(1L, actualListPublicApplicationResult.getPages());
        assertEquals(1L, actualListPublicApplicationResult.getTotal());
        assertFalse(actualListPublicApplicationResult.hasPrevious());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#listPublicApplication(ListPublicAppRequest)}.
     * <ul>
     *   <li>Given twenty.</li>
     *   <li>Then return Current is twenty.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listPublicApplication(ListPublicAppRequest)}
     */
    @Test
    @DisplayName("Test listPublicApplication(ListPublicAppRequest); given twenty; then return Current is twenty")
    void testListPublicApplication_givenTwenty_thenReturnCurrentIsTwenty() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);
        ListPublicAppRequest listPublicAppRequest = mock(ListPublicAppRequest.class);
        when(listPublicAppRequest.getIsAscend()).thenReturn(true);
        when(listPublicAppRequest.getCurrent()).thenReturn(20L);
        when(listPublicAppRequest.getPageSize()).thenReturn(3L);
        when(listPublicAppRequest.getSortField()).thenReturn("Sort Field");
        when(listPublicAppRequest.getAppName()).thenReturn("App Name");
        doNothing().when(listPublicAppRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listPublicAppRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listPublicAppRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listPublicAppRequest).setSortField(Mockito.<String>any());
        doNothing().when(listPublicAppRequest).setAppName(Mockito.<String>any());
        listPublicAppRequest.setAppName("App Name");
        listPublicAppRequest.setCurrent(1L);
        listPublicAppRequest.setIsAscend(true);
        listPublicAppRequest.setPageSize(3L);
        listPublicAppRequest.setSortField("Sort Field");

        // Act
        Page<ApplicationVo> actualListPublicApplicationResult = applicationServiceImpl
                .listPublicApplication(listPublicAppRequest);

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listPublicAppRequest).getCurrent();
        verify(listPublicAppRequest).getIsAscend();
        verify(listPublicAppRequest).getPageSize();
        verify(listPublicAppRequest, atLeast(1)).getSortField();
        verify(listPublicAppRequest).setCurrent(eq(1L));
        verify(listPublicAppRequest).setIsAscend(eq(true));
        verify(listPublicAppRequest).setPageSize(eq(3L));
        verify(listPublicAppRequest).setSortField(eq("Sort Field"));
        verify(listPublicAppRequest, atLeast(1)).getAppName();
        verify(listPublicAppRequest).setAppName(eq("App Name"));
        assertEquals(1L, actualListPublicApplicationResult.getPages());
        assertEquals(1L, actualListPublicApplicationResult.getTotal());
        assertEquals(20L, actualListPublicApplicationResult.getCurrent());
        assertTrue(actualListPublicApplicationResult.hasPrevious());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#listPublicApplication(ListPublicAppRequest)}.
     * <ul>
     *   <li>Then return Pages is zero.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listPublicApplication(ListPublicAppRequest)}
     */
    @Test
    @DisplayName("Test listPublicApplication(ListPublicAppRequest); then return Pages is zero")
    void testListPublicApplication_thenReturnPagesIsZero() {
        // Arrange
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(new Page<>());

        ListPublicAppRequest listPublicAppRequest = new ListPublicAppRequest();
        listPublicAppRequest.setAppName("App Name");
        listPublicAppRequest.setCurrent(1L);
        listPublicAppRequest.setIsAscend(true);
        listPublicAppRequest.setPageSize(3L);
        listPublicAppRequest.setSortField("Sort Field");

        // Act
        Page<ApplicationVo> actualListPublicApplicationResult = applicationServiceImpl
                .listPublicApplication(listPublicAppRequest);

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        assertEquals(0L, actualListPublicApplicationResult.getPages());
        assertEquals(0L, actualListPublicApplicationResult.getTotal());
        assertEquals(1L, actualListPublicApplicationResult.getCurrent());
        assertFalse(actualListPublicApplicationResult.hasPrevious());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#listPublicApplication(ListPublicAppRequest)}.
     * <ul>
     *   <li>When {@link ListPublicAppRequest} (default constructor) AppName is
     * {@code App Name}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listPublicApplication(ListPublicAppRequest)}
     */
    @Test
    @DisplayName("Test listPublicApplication(ListPublicAppRequest); when ListPublicAppRequest (default constructor) AppName is 'App Name'")
    void testListPublicApplication_whenListPublicAppRequestAppNameIsAppName() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);

        ListPublicAppRequest listPublicAppRequest = new ListPublicAppRequest();
        listPublicAppRequest.setAppName("App Name");
        listPublicAppRequest.setCurrent(1L);
        listPublicAppRequest.setIsAscend(true);
        listPublicAppRequest.setPageSize(3L);
        listPublicAppRequest.setSortField("Sort Field");

        // Act
        Page<ApplicationVo> actualListPublicApplicationResult = applicationServiceImpl
                .listPublicApplication(listPublicAppRequest);

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        assertEquals(1L, actualListPublicApplicationResult.getCurrent());
        assertEquals(1L, actualListPublicApplicationResult.getPages());
        assertEquals(1L, actualListPublicApplicationResult.getTotal());
        assertFalse(actualListPublicApplicationResult.hasPrevious());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyApplication(Application, HttpServletRequest)")
    void testAddMyApplication() {
        // Arrange
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

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

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.addMyApplication(application, new MockHttpServletRequest()));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ApplicationMapper} {@link BaseMapper#insert(Object)} return
     * zero.</li>
     *   <li>Then calls {@link BaseMapper#insert(Object)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyApplication(Application, HttpServletRequest); given ApplicationMapper insert(Object) return zero; then calls insert(Object)")
    void testAddMyApplication_givenApplicationMapperInsertReturnZero_thenCallsInsert() {
        // Arrange
        when(applicationMapper.insert(Mockito.<Application>any())).thenReturn(0);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

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

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.addMyApplication(application, new MockHttpServletRequest()));
        verify(applicationMapper).insert(isA(Application.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Given empty string.</li>
     *   <li>When {@link Application} {@link Application#getAppName()} return empty
     * string.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyApplication(Application, HttpServletRequest); given empty string; when Application getAppName() return empty string")
    void testAddMyApplication_givenEmptyString_whenApplicationGetAppNameReturnEmptyString() {
        // Arrange
        Application application = mock(Application.class);
        when(application.getStrategy()).thenReturn(1);
        when(application.getType()).thenReturn(1);
        when(application.getAppName()).thenReturn("");
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

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.addMyApplication(application, new MockHttpServletRequest()));
        verify(application).getAppName();
        verify(application).getStrategy();
        verify(application).getType();
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
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Given minus one.</li>
     *   <li>When {@link Application} {@link Application#getStrategy()} return minus
     * one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyApplication(Application, HttpServletRequest); given minus one; when Application getStrategy() return minus one")
    void testAddMyApplication_givenMinusOne_whenApplicationGetStrategyReturnMinusOne() {
        // Arrange
        Application application = mock(Application.class);
        when(application.getStrategy()).thenReturn(-1);
        when(application.getType()).thenReturn(1);
        when(application.getAppName()).thenReturn("App Name");
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

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.addMyApplication(application, new MockHttpServletRequest()));
        verify(application).getAppName();
        verify(application).getStrategy();
        verify(application).getType();
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
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Given minus one.</li>
     *   <li>When {@link Application} {@link Application#getType()} return minus
     * one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyApplication(Application, HttpServletRequest); given minus one; when Application getType() return minus one")
    void testAddMyApplication_givenMinusOne_whenApplicationGetTypeReturnMinusOne() {
        // Arrange
        Application application = mock(Application.class);
        when(application.getStrategy()).thenReturn(1);
        when(application.getType()).thenReturn(-1);
        when(application.getAppName()).thenReturn("App Name");
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

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.addMyApplication(application, new MockHttpServletRequest()));
        verify(application).getAppName();
        verify(application).getStrategy();
        verify(application).getType();
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
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Then return longValue is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#addMyApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyApplication(Application, HttpServletRequest); then return longValue is one")
    void testAddMyApplication_thenReturnLongValueIsOne() {
        // Arrange
        when(applicationMapper.insert(Mockito.<Application>any())).thenReturn(1);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

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

        // Act
        Long actualAddMyApplicationResult = applicationServiceImpl.addMyApplication(application,
                new MockHttpServletRequest());

        // Assert
        verify(applicationMapper).insert(isA(Application.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertEquals(1L, actualAddMyApplicationResult.longValue());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#listMyApplication(ListMyAppRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@code false}.</li>
     *   <li>When {@link ListMyAppRequest} {@link TableRequest#getIsAscend()} return
     * {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listMyApplication(ListMyAppRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyApplication(ListMyAppRequest, HttpServletRequest); given 'false'; when ListMyAppRequest getIsAscend() return 'false'")
    void testListMyApplication_givenFalse_whenListMyAppRequestGetIsAscendReturnFalse() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);
        ListMyAppRequest listMyAppRequest = mock(ListMyAppRequest.class);
        when(listMyAppRequest.getIsAscend()).thenReturn(false);
        when(listMyAppRequest.getCurrent()).thenReturn(1L);
        when(listMyAppRequest.getPageSize()).thenReturn(3L);
        when(listMyAppRequest.getId()).thenReturn(1L);
        when(listMyAppRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listMyAppRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listMyAppRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listMyAppRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listMyAppRequest).setSortField(Mockito.<String>any());
        doNothing().when(listMyAppRequest).setId(Mockito.<Long>any());
        listMyAppRequest.setCurrent(1L);
        listMyAppRequest.setId(1L);
        listMyAppRequest.setIsAscend(true);
        listMyAppRequest.setPageSize(3L);
        listMyAppRequest.setSortField("Sort Field");

        // Act
        Page<ApplicationVo> actualListMyApplicationResult = applicationServiceImpl.listMyApplication(listMyAppRequest,
                new MockHttpServletRequest());

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(listMyAppRequest).getCurrent();
        verify(listMyAppRequest).getIsAscend();
        verify(listMyAppRequest).getPageSize();
        verify(listMyAppRequest, atLeast(1)).getSortField();
        verify(listMyAppRequest).setCurrent(eq(1L));
        verify(listMyAppRequest).setIsAscend(eq(true));
        verify(listMyAppRequest).setPageSize(eq(3L));
        verify(listMyAppRequest).setSortField(eq("Sort Field"));
        verify(listMyAppRequest, atLeast(1)).getId();
        verify(listMyAppRequest).setId(eq(1L));
        assertEquals(1L, actualListMyApplicationResult.getCurrent());
        assertEquals(1L, actualListMyApplicationResult.getPages());
        assertEquals(1L, actualListMyApplicationResult.getTotal());
        assertFalse(actualListMyApplicationResult.hasPrevious());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#listMyApplication(ListMyAppRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link IPage} {@link IPage#getRecords()} return
     * {@link ArrayList#ArrayList()}.</li>
     *   <li>Then return Current is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listMyApplication(ListMyAppRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyApplication(ListMyAppRequest, HttpServletRequest); given IPage getRecords() return ArrayList(); then return Current is one")
    void testListMyApplication_givenIPageGetRecordsReturnArrayList_thenReturnCurrentIsOne() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        ListMyAppRequest listMyAppRequest = new ListMyAppRequest();
        listMyAppRequest.setCurrent(1L);
        listMyAppRequest.setId(1L);
        listMyAppRequest.setIsAscend(true);
        listMyAppRequest.setPageSize(3L);
        listMyAppRequest.setSortField("Sort Field");

        // Act
        Page<ApplicationVo> actualListMyApplicationResult = applicationServiceImpl.listMyApplication(listMyAppRequest,
                new MockHttpServletRequest());

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertEquals(1L, actualListMyApplicationResult.getCurrent());
        assertEquals(1L, actualListMyApplicationResult.getPages());
        assertEquals(1L, actualListMyApplicationResult.getTotal());
        assertFalse(actualListMyApplicationResult.hasPrevious());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#listMyApplication(ListMyAppRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given twenty.</li>
     *   <li>Then return Current is twenty.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listMyApplication(ListMyAppRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyApplication(ListMyAppRequest, HttpServletRequest); given twenty; then return Current is twenty")
    void testListMyApplication_givenTwenty_thenReturnCurrentIsTwenty() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);
        ListMyAppRequest listMyAppRequest = mock(ListMyAppRequest.class);
        when(listMyAppRequest.getIsAscend()).thenReturn(true);
        when(listMyAppRequest.getCurrent()).thenReturn(20L);
        when(listMyAppRequest.getPageSize()).thenReturn(3L);
        when(listMyAppRequest.getId()).thenReturn(1L);
        when(listMyAppRequest.getSortField()).thenReturn("Sort Field");
        doNothing().when(listMyAppRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listMyAppRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listMyAppRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listMyAppRequest).setSortField(Mockito.<String>any());
        doNothing().when(listMyAppRequest).setId(Mockito.<Long>any());
        listMyAppRequest.setCurrent(1L);
        listMyAppRequest.setId(1L);
        listMyAppRequest.setIsAscend(true);
        listMyAppRequest.setPageSize(3L);
        listMyAppRequest.setSortField("Sort Field");

        // Act
        Page<ApplicationVo> actualListMyApplicationResult = applicationServiceImpl.listMyApplication(listMyAppRequest,
                new MockHttpServletRequest());

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        verify(listMyAppRequest).getCurrent();
        verify(listMyAppRequest).getIsAscend();
        verify(listMyAppRequest).getPageSize();
        verify(listMyAppRequest, atLeast(1)).getSortField();
        verify(listMyAppRequest).setCurrent(eq(1L));
        verify(listMyAppRequest).setIsAscend(eq(true));
        verify(listMyAppRequest).setPageSize(eq(3L));
        verify(listMyAppRequest).setSortField(eq("Sort Field"));
        verify(listMyAppRequest, atLeast(1)).getId();
        verify(listMyAppRequest).setId(eq(1L));
        assertEquals(1L, actualListMyApplicationResult.getPages());
        assertEquals(1L, actualListMyApplicationResult.getTotal());
        assertEquals(20L, actualListMyApplicationResult.getCurrent());
        assertTrue(actualListMyApplicationResult.hasPrevious());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#listMyApplication(ListMyAppRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Then return Pages is zero.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listMyApplication(ListMyAppRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyApplication(ListMyAppRequest, HttpServletRequest); then return Pages is zero")
    void testListMyApplication_thenReturnPagesIsZero() {
        // Arrange
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(new Page<>());
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        ListMyAppRequest listMyAppRequest = new ListMyAppRequest();
        listMyAppRequest.setCurrent(1L);
        listMyAppRequest.setId(1L);
        listMyAppRequest.setIsAscend(true);
        listMyAppRequest.setPageSize(3L);
        listMyAppRequest.setSortField("Sort Field");

        // Act
        Page<ApplicationVo> actualListMyApplicationResult = applicationServiceImpl.listMyApplication(listMyAppRequest,
                new MockHttpServletRequest());

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertEquals(0L, actualListMyApplicationResult.getPages());
        assertEquals(0L, actualListMyApplicationResult.getTotal());
        assertEquals(1L, actualListMyApplicationResult.getCurrent());
        assertFalse(actualListMyApplicationResult.hasPrevious());
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#listMyApplication(ListMyAppRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listMyApplication(ListMyAppRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyApplication(ListMyAppRequest, HttpServletRequest); then throw BusinessException")
    void testListMyApplication_thenThrowBusinessException() {
        // Arrange
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        ListMyAppRequest listMyAppRequest = new ListMyAppRequest();
        listMyAppRequest.setCurrent(1L);
        listMyAppRequest.setId(1L);
        listMyAppRequest.setIsAscend(true);
        listMyAppRequest.setPageSize(3L);
        listMyAppRequest.setSortField("Sort Field");

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.listMyApplication(listMyAppRequest, new MockHttpServletRequest()));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#updateMyApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ApplicationMapper} {@link BaseMapper#updateById(Object)}
     * return one.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#updateMyApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyApplication(Application, HttpServletRequest); given ApplicationMapper updateById(Object) return one; then return 'true'")
    void testUpdateMyApplication_givenApplicationMapperUpdateByIdReturnOne_thenReturnTrue() {
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
        when(applicationMapper.updateById(Mockito.<Application>any())).thenReturn(1);
        when(applicationMapper.selectOne(Mockito.<Wrapper<Application>>any(), anyBoolean())).thenReturn(application);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        Application application2 = new Application();
        application2.setAppName("App Name");
        application2.setAuditMessage("Audit Message");
        application2.setAuditStatus(1);
        application2.setAuditTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setAuditorId(1L);
        application2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setDeleted(1);
        application2.setDescription("The characteristics of someone or something");
        application2.setId(1L);
        application2.setImageUrl("https://example.org/example");
        application2.setStrategy(1);
        application2.setType(1);
        application2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setUserId(1L);

        // Act
        Boolean actualUpdateMyApplicationResult = applicationServiceImpl.updateMyApplication(application2,
                new MockHttpServletRequest());

        // Assert
        verify(applicationMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(applicationMapper).updateById(isA(Application.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertTrue(actualUpdateMyApplicationResult);
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#updateMyApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#updateMyApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyApplication(Application, HttpServletRequest); then return 'false'")
    void testUpdateMyApplication_thenReturnFalse() {
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
        when(applicationMapper.updateById(Mockito.<Application>any())).thenReturn(0);
        when(applicationMapper.selectOne(Mockito.<Wrapper<Application>>any(), anyBoolean())).thenReturn(application);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        Application application2 = new Application();
        application2.setAppName("App Name");
        application2.setAuditMessage("Audit Message");
        application2.setAuditStatus(1);
        application2.setAuditTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setAuditorId(1L);
        application2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setDeleted(1);
        application2.setDescription("The characteristics of someone or something");
        application2.setId(1L);
        application2.setImageUrl("https://example.org/example");
        application2.setStrategy(1);
        application2.setType(1);
        application2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setUserId(1L);

        // Act
        Boolean actualUpdateMyApplicationResult = applicationServiceImpl.updateMyApplication(application2,
                new MockHttpServletRequest());

        // Assert
        verify(applicationMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(applicationMapper).updateById(isA(Application.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertFalse(actualUpdateMyApplicationResult);
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#updateMyApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#updateMyApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyApplication(Application, HttpServletRequest); then throw BusinessException")
    void testUpdateMyApplication_thenThrowBusinessException() {
        // Arrange
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

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

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.updateMyApplication(application, new MockHttpServletRequest()));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#deleteMyApplication(IdRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link IdRequest} (default constructor) Id is {@code null}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#deleteMyApplication(IdRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test deleteMyApplication(IdRequest, HttpServletRequest); given 'null'; when IdRequest (default constructor) Id is 'null'")
    void testDeleteMyApplication_givenNull_whenIdRequestIdIsNull() {
        // Arrange
        IdRequest idRequest = new IdRequest();
        idRequest.setId(null);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.deleteMyApplication(idRequest, new MockHttpServletRequest()));
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#deleteMyApplication(IdRequest, HttpServletRequest)}.
     * <ul>
     *   <li>Then calls {@link UserClient#getLoginUserId(HttpServletRequest)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#deleteMyApplication(IdRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test deleteMyApplication(IdRequest, HttpServletRequest); then calls getLoginUserId(HttpServletRequest)")
    void testDeleteMyApplication_thenCallsGetLoginUserId() {
        // Arrange
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.deleteMyApplication(idRequest, new MockHttpServletRequest()));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test {@link ApplicationServiceImpl#listApplication(ListAppRequest)}.
     * <ul>
     *   <li>Given empty string.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listApplication(ListAppRequest)}
     */
    @Test
    @DisplayName("Test listApplication(ListAppRequest); given empty string")
    void testListApplication_givenEmptyString() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);
        ListAppRequest listAppRequest = mock(ListAppRequest.class);
        when(listAppRequest.getIsAscend()).thenReturn(true);
        when(listAppRequest.getAuditStatus()).thenReturn(1);
        when(listAppRequest.getStrategy()).thenReturn(1);
        when(listAppRequest.getType()).thenReturn(1);
        when(listAppRequest.getCurrent()).thenReturn(1L);
        when(listAppRequest.getPageSize()).thenReturn(3L);
        when(listAppRequest.getAuditorId()).thenReturn(1L);
        when(listAppRequest.getId()).thenReturn(1L);
        when(listAppRequest.getUserId()).thenReturn(1L);
        when(listAppRequest.getSortField()).thenReturn("Sort Field");
        when(listAppRequest.getAppName()).thenReturn("");
        doNothing().when(listAppRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listAppRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listAppRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listAppRequest).setSortField(Mockito.<String>any());
        doNothing().when(listAppRequest).setAppName(Mockito.<String>any());
        doNothing().when(listAppRequest).setAuditStatus(Mockito.<Integer>any());
        doNothing().when(listAppRequest).setAuditorId(Mockito.<Long>any());
        doNothing().when(listAppRequest).setId(Mockito.<Long>any());
        doNothing().when(listAppRequest).setStrategy(Mockito.<Integer>any());
        doNothing().when(listAppRequest).setType(Mockito.<Integer>any());
        doNothing().when(listAppRequest).setUserId(Mockito.<Long>any());
        listAppRequest.setAppName("App Name");
        listAppRequest.setAuditStatus(1);
        listAppRequest.setAuditorId(1L);
        listAppRequest.setCurrent(1L);
        listAppRequest.setId(1L);
        listAppRequest.setIsAscend(true);
        listAppRequest.setPageSize(3L);
        listAppRequest.setSortField("Sort Field");
        listAppRequest.setStrategy(1);
        listAppRequest.setType(1);
        listAppRequest.setUserId(1L);

        // Act
        Page<ApplicationVo> actualListApplicationResult = applicationServiceImpl.listApplication(listAppRequest);

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listAppRequest).getCurrent();
        verify(listAppRequest).getIsAscend();
        verify(listAppRequest).getPageSize();
        verify(listAppRequest, atLeast(1)).getSortField();
        verify(listAppRequest).setCurrent(eq(1L));
        verify(listAppRequest).setIsAscend(eq(true));
        verify(listAppRequest).setPageSize(eq(3L));
        verify(listAppRequest).setSortField(eq("Sort Field"));
        verify(listAppRequest, atLeast(1)).getAppName();
        verify(listAppRequest, atLeast(1)).getAuditStatus();
        verify(listAppRequest, atLeast(1)).getAuditorId();
        verify(listAppRequest, atLeast(1)).getId();
        verify(listAppRequest, atLeast(1)).getStrategy();
        verify(listAppRequest, atLeast(1)).getType();
        verify(listAppRequest, atLeast(1)).getUserId();
        verify(listAppRequest).setAppName(eq("App Name"));
        verify(listAppRequest).setAuditStatus(eq(1));
        verify(listAppRequest).setAuditorId(eq(1L));
        verify(listAppRequest).setId(eq(1L));
        verify(listAppRequest).setStrategy(eq(1));
        verify(listAppRequest).setType(eq(1));
        verify(listAppRequest).setUserId(eq(1L));
        assertEquals(1L, actualListApplicationResult.getCurrent());
        assertEquals(1L, actualListApplicationResult.getPages());
        assertEquals(1L, actualListApplicationResult.getTotal());
        assertFalse(actualListApplicationResult.hasPrevious());
    }

    /**
     * Test {@link ApplicationServiceImpl#listApplication(ListAppRequest)}.
     * <ul>
     *   <li>Given {@code false}.</li>
     *   <li>When {@link ListAppRequest} {@link TableRequest#getIsAscend()} return
     * {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listApplication(ListAppRequest)}
     */
    @Test
    @DisplayName("Test listApplication(ListAppRequest); given 'false'; when ListAppRequest getIsAscend() return 'false'")
    void testListApplication_givenFalse_whenListAppRequestGetIsAscendReturnFalse() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);
        ListAppRequest listAppRequest = mock(ListAppRequest.class);
        when(listAppRequest.getIsAscend()).thenReturn(false);
        when(listAppRequest.getAuditStatus()).thenReturn(1);
        when(listAppRequest.getStrategy()).thenReturn(1);
        when(listAppRequest.getType()).thenReturn(1);
        when(listAppRequest.getCurrent()).thenReturn(1L);
        when(listAppRequest.getPageSize()).thenReturn(3L);
        when(listAppRequest.getAuditorId()).thenReturn(1L);
        when(listAppRequest.getId()).thenReturn(1L);
        when(listAppRequest.getUserId()).thenReturn(1L);
        when(listAppRequest.getSortField()).thenReturn("Sort Field");
        when(listAppRequest.getAppName()).thenReturn("App Name");
        doNothing().when(listAppRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listAppRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listAppRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listAppRequest).setSortField(Mockito.<String>any());
        doNothing().when(listAppRequest).setAppName(Mockito.<String>any());
        doNothing().when(listAppRequest).setAuditStatus(Mockito.<Integer>any());
        doNothing().when(listAppRequest).setAuditorId(Mockito.<Long>any());
        doNothing().when(listAppRequest).setId(Mockito.<Long>any());
        doNothing().when(listAppRequest).setStrategy(Mockito.<Integer>any());
        doNothing().when(listAppRequest).setType(Mockito.<Integer>any());
        doNothing().when(listAppRequest).setUserId(Mockito.<Long>any());
        listAppRequest.setAppName("App Name");
        listAppRequest.setAuditStatus(1);
        listAppRequest.setAuditorId(1L);
        listAppRequest.setCurrent(1L);
        listAppRequest.setId(1L);
        listAppRequest.setIsAscend(true);
        listAppRequest.setPageSize(3L);
        listAppRequest.setSortField("Sort Field");
        listAppRequest.setStrategy(1);
        listAppRequest.setType(1);
        listAppRequest.setUserId(1L);

        // Act
        Page<ApplicationVo> actualListApplicationResult = applicationServiceImpl.listApplication(listAppRequest);

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listAppRequest).getCurrent();
        verify(listAppRequest).getIsAscend();
        verify(listAppRequest).getPageSize();
        verify(listAppRequest, atLeast(1)).getSortField();
        verify(listAppRequest).setCurrent(eq(1L));
        verify(listAppRequest).setIsAscend(eq(true));
        verify(listAppRequest).setPageSize(eq(3L));
        verify(listAppRequest).setSortField(eq("Sort Field"));
        verify(listAppRequest, atLeast(1)).getAppName();
        verify(listAppRequest, atLeast(1)).getAuditStatus();
        verify(listAppRequest, atLeast(1)).getAuditorId();
        verify(listAppRequest, atLeast(1)).getId();
        verify(listAppRequest, atLeast(1)).getStrategy();
        verify(listAppRequest, atLeast(1)).getType();
        verify(listAppRequest, atLeast(1)).getUserId();
        verify(listAppRequest).setAppName(eq("App Name"));
        verify(listAppRequest).setAuditStatus(eq(1));
        verify(listAppRequest).setAuditorId(eq(1L));
        verify(listAppRequest).setId(eq(1L));
        verify(listAppRequest).setStrategy(eq(1));
        verify(listAppRequest).setType(eq(1));
        verify(listAppRequest).setUserId(eq(1L));
        assertEquals(1L, actualListApplicationResult.getCurrent());
        assertEquals(1L, actualListApplicationResult.getPages());
        assertEquals(1L, actualListApplicationResult.getTotal());
        assertFalse(actualListApplicationResult.hasPrevious());
    }

    /**
     * Test {@link ApplicationServiceImpl#listApplication(ListAppRequest)}.
     * <ul>
     *   <li>Given twenty.</li>
     *   <li>Then return Current is twenty.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listApplication(ListAppRequest)}
     */
    @Test
    @DisplayName("Test listApplication(ListAppRequest); given twenty; then return Current is twenty")
    void testListApplication_givenTwenty_thenReturnCurrentIsTwenty() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);
        ListAppRequest listAppRequest = mock(ListAppRequest.class);
        when(listAppRequest.getIsAscend()).thenReturn(true);
        when(listAppRequest.getAuditStatus()).thenReturn(1);
        when(listAppRequest.getStrategy()).thenReturn(1);
        when(listAppRequest.getType()).thenReturn(1);
        when(listAppRequest.getCurrent()).thenReturn(20L);
        when(listAppRequest.getPageSize()).thenReturn(3L);
        when(listAppRequest.getAuditorId()).thenReturn(1L);
        when(listAppRequest.getId()).thenReturn(1L);
        when(listAppRequest.getUserId()).thenReturn(1L);
        when(listAppRequest.getSortField()).thenReturn("Sort Field");
        when(listAppRequest.getAppName()).thenReturn("App Name");
        doNothing().when(listAppRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listAppRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listAppRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listAppRequest).setSortField(Mockito.<String>any());
        doNothing().when(listAppRequest).setAppName(Mockito.<String>any());
        doNothing().when(listAppRequest).setAuditStatus(Mockito.<Integer>any());
        doNothing().when(listAppRequest).setAuditorId(Mockito.<Long>any());
        doNothing().when(listAppRequest).setId(Mockito.<Long>any());
        doNothing().when(listAppRequest).setStrategy(Mockito.<Integer>any());
        doNothing().when(listAppRequest).setType(Mockito.<Integer>any());
        doNothing().when(listAppRequest).setUserId(Mockito.<Long>any());
        listAppRequest.setAppName("App Name");
        listAppRequest.setAuditStatus(1);
        listAppRequest.setAuditorId(1L);
        listAppRequest.setCurrent(1L);
        listAppRequest.setId(1L);
        listAppRequest.setIsAscend(true);
        listAppRequest.setPageSize(3L);
        listAppRequest.setSortField("Sort Field");
        listAppRequest.setStrategy(1);
        listAppRequest.setType(1);
        listAppRequest.setUserId(1L);

        // Act
        Page<ApplicationVo> actualListApplicationResult = applicationServiceImpl.listApplication(listAppRequest);

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listAppRequest).getCurrent();
        verify(listAppRequest).getIsAscend();
        verify(listAppRequest).getPageSize();
        verify(listAppRequest, atLeast(1)).getSortField();
        verify(listAppRequest).setCurrent(eq(1L));
        verify(listAppRequest).setIsAscend(eq(true));
        verify(listAppRequest).setPageSize(eq(3L));
        verify(listAppRequest).setSortField(eq("Sort Field"));
        verify(listAppRequest, atLeast(1)).getAppName();
        verify(listAppRequest, atLeast(1)).getAuditStatus();
        verify(listAppRequest, atLeast(1)).getAuditorId();
        verify(listAppRequest, atLeast(1)).getId();
        verify(listAppRequest, atLeast(1)).getStrategy();
        verify(listAppRequest, atLeast(1)).getType();
        verify(listAppRequest, atLeast(1)).getUserId();
        verify(listAppRequest).setAppName(eq("App Name"));
        verify(listAppRequest).setAuditStatus(eq(1));
        verify(listAppRequest).setAuditorId(eq(1L));
        verify(listAppRequest).setId(eq(1L));
        verify(listAppRequest).setStrategy(eq(1));
        verify(listAppRequest).setType(eq(1));
        verify(listAppRequest).setUserId(eq(1L));
        assertEquals(1L, actualListApplicationResult.getPages());
        assertEquals(1L, actualListApplicationResult.getTotal());
        assertEquals(20L, actualListApplicationResult.getCurrent());
        assertTrue(actualListApplicationResult.hasPrevious());
    }

    /**
     * Test {@link ApplicationServiceImpl#listApplication(ListAppRequest)}.
     * <ul>
     *   <li>Then return Pages is zero.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listApplication(ListAppRequest)}
     */
    @Test
    @DisplayName("Test listApplication(ListAppRequest); then return Pages is zero")
    void testListApplication_thenReturnPagesIsZero() {
        // Arrange
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(new Page<>());

        ListAppRequest listAppRequest = new ListAppRequest();
        listAppRequest.setAppName("App Name");
        listAppRequest.setAuditStatus(1);
        listAppRequest.setAuditorId(1L);
        listAppRequest.setCurrent(1L);
        listAppRequest.setId(1L);
        listAppRequest.setIsAscend(true);
        listAppRequest.setPageSize(3L);
        listAppRequest.setSortField("Sort Field");
        listAppRequest.setStrategy(1);
        listAppRequest.setType(1);
        listAppRequest.setUserId(1L);

        // Act
        Page<ApplicationVo> actualListApplicationResult = applicationServiceImpl.listApplication(listAppRequest);

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        assertEquals(0L, actualListApplicationResult.getPages());
        assertEquals(0L, actualListApplicationResult.getTotal());
        assertEquals(1L, actualListApplicationResult.getCurrent());
        assertFalse(actualListApplicationResult.hasPrevious());
    }

    /**
     * Test {@link ApplicationServiceImpl#listApplication(ListAppRequest)}.
     * <ul>
     *   <li>When {@link ListAppRequest} (default constructor) AppName is
     * {@code App Name}.</li>
     *   <li>Then return Current is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#listApplication(ListAppRequest)}
     */
    @Test
    @DisplayName("Test listApplication(ListAppRequest); when ListAppRequest (default constructor) AppName is 'App Name'; then return Current is one")
    void testListApplication_whenListAppRequestAppNameIsAppName_thenReturnCurrentIsOne() {
        // Arrange
        IPage<Application> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(applicationMapper.selectPage(Mockito.<IPage<Application>>any(), Mockito.<Wrapper<Application>>any()))
                .thenReturn(iPage);

        ListAppRequest listAppRequest = new ListAppRequest();
        listAppRequest.setAppName("App Name");
        listAppRequest.setAuditStatus(1);
        listAppRequest.setAuditorId(1L);
        listAppRequest.setCurrent(1L);
        listAppRequest.setId(1L);
        listAppRequest.setIsAscend(true);
        listAppRequest.setPageSize(3L);
        listAppRequest.setSortField("Sort Field");
        listAppRequest.setStrategy(1);
        listAppRequest.setType(1);
        listAppRequest.setUserId(1L);

        // Act
        Page<ApplicationVo> actualListApplicationResult = applicationServiceImpl.listApplication(listAppRequest);

        // Assert
        verify(applicationMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        assertEquals(1L, actualListApplicationResult.getCurrent());
        assertEquals(1L, actualListApplicationResult.getPages());
        assertEquals(1L, actualListApplicationResult.getTotal());
        assertFalse(actualListApplicationResult.hasPrevious());
    }

    /**
     * Test {@link ApplicationServiceImpl#updateApplication(Application)}.
     * <ul>
     *   <li>Given {@link ApplicationMapper} {@link BaseMapper#updateById(Object)}
     * return one.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#updateApplication(Application)}
     */
    @Test
    @DisplayName("Test updateApplication(Application); given ApplicationMapper updateById(Object) return one; then return 'true'")
    void testUpdateApplication_givenApplicationMapperUpdateByIdReturnOne_thenReturnTrue() {
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
        when(applicationMapper.updateById(Mockito.<Application>any())).thenReturn(1);
        when(applicationMapper.selectById(Mockito.<Serializable>any())).thenReturn(application);

        Application application2 = new Application();
        application2.setAppName("App Name");
        application2.setAuditMessage("Audit Message");
        application2.setAuditStatus(1);
        application2.setAuditTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setAuditorId(1L);
        application2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setDeleted(1);
        application2.setDescription("The characteristics of someone or something");
        application2.setId(1L);
        application2.setImageUrl("https://example.org/example");
        application2.setStrategy(1);
        application2.setType(1);
        application2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setUserId(1L);

        // Act
        Boolean actualUpdateApplicationResult = applicationServiceImpl.updateApplication(application2);

        // Assert
        verify(applicationMapper).selectById(isA(Serializable.class));
        verify(applicationMapper).updateById(isA(Application.class));
        assertTrue(actualUpdateApplicationResult);
    }

    /**
     * Test {@link ApplicationServiceImpl#updateApplication(Application)}.
     * <ul>
     *   <li>Given {@link ApplicationMapper} {@link BaseMapper#updateById(Object)}
     * return zero.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#updateApplication(Application)}
     */
    @Test
    @DisplayName("Test updateApplication(Application); given ApplicationMapper updateById(Object) return zero; then return 'false'")
    void testUpdateApplication_givenApplicationMapperUpdateByIdReturnZero_thenReturnFalse() {
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
        when(applicationMapper.updateById(Mockito.<Application>any())).thenReturn(0);
        when(applicationMapper.selectById(Mockito.<Serializable>any())).thenReturn(application);

        Application application2 = new Application();
        application2.setAppName("App Name");
        application2.setAuditMessage("Audit Message");
        application2.setAuditStatus(1);
        application2.setAuditTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setAuditorId(1L);
        application2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setDeleted(1);
        application2.setDescription("The characteristics of someone or something");
        application2.setId(1L);
        application2.setImageUrl("https://example.org/example");
        application2.setStrategy(1);
        application2.setType(1);
        application2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setUserId(1L);

        // Act
        Boolean actualUpdateApplicationResult = applicationServiceImpl.updateApplication(application2);

        // Assert
        verify(applicationMapper).selectById(isA(Serializable.class));
        verify(applicationMapper).updateById(isA(Application.class));
        assertFalse(actualUpdateApplicationResult);
    }

    /**
     * Test {@link ApplicationServiceImpl#deleteApplication(IdRequest)}.
     * <ul>
     *   <li>Given {@link Application} (default constructor) AppName is
     * {@code App Name}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#deleteApplication(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteApplication(IdRequest); given Application (default constructor) AppName is 'App Name'")
    @Disabled("TODO: Complete this test")
    void testDeleteApplication_givenApplicationAppNameIsAppName() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.removeById(ServiceImpl.java:290)
        //       at impl.service.me.intelliedu.intellieduapplicationservice.ApplicationServiceImpl.deleteApplication(ApplicationServiceImpl.java:248)
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(applicationMapper.selectById(Mockito.<Serializable>any())).thenReturn(application);

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);

        // Act
        applicationServiceImpl.deleteApplication(idRequest);
    }

    /**
     * Test {@link ApplicationServiceImpl#deleteApplication(IdRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link IdRequest} (default constructor) Id is {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#deleteApplication(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteApplication(IdRequest); given 'null'; when IdRequest (default constructor) Id is 'null'; then throw BusinessException")
    void testDeleteApplication_givenNull_whenIdRequestIdIsNull_thenThrowBusinessException() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ApplicationServiceImpl applicationServiceImpl = new ApplicationServiceImpl();

        IdRequest idRequest = new IdRequest();
        idRequest.setId(null);

        // Act and Assert
        assertThrows(BusinessException.class, () -> applicationServiceImpl.deleteApplication(idRequest));
    }

    /**
     * Test {@link ApplicationServiceImpl#deleteApplication(IdRequest)}.
     * <ul>
     *   <li>Then calls {@link IdRequest#getId()}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#deleteApplication(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteApplication(IdRequest); then calls getId()")
    void testDeleteApplication_thenCallsGetId() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ApplicationServiceImpl applicationServiceImpl = new ApplicationServiceImpl();
        IdRequest idRequest = mock(IdRequest.class);
        when(idRequest.getId()).thenThrow(new BusinessException(Err.PARAMS_ERROR));

        // Act and Assert
        assertThrows(BusinessException.class, () -> applicationServiceImpl.deleteApplication(idRequest));
        verify(idRequest).getId();
    }

    /**
     * Test {@link ApplicationServiceImpl#deleteApplication(IdRequest)}.
     * <ul>
     *   <li>When {@code null}.</li>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#deleteApplication(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteApplication(IdRequest); when 'null'; then throw BusinessException")
    void testDeleteApplication_whenNull_thenThrowBusinessException() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertThrows(BusinessException.class, () -> (new ApplicationServiceImpl()).deleteApplication(null));
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#auditApplication(Application, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#auditApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test auditApplication(Application, HttpServletRequest)")
    void testAuditApplication() {
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
        when(applicationMapper.selectById(Mockito.<Serializable>any())).thenReturn(application);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any()))
                .thenThrow(new BusinessException(Err.PARAMS_ERROR));

        Application application2 = new Application();
        application2.setAppName("App Name");
        application2.setAuditMessage("Audit Message");
        application2.setAuditStatus(1);
        application2.setAuditTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setAuditorId(1L);
        application2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setDeleted(1);
        application2.setDescription("The characteristics of someone or something");
        application2.setId(1L);
        application2.setImageUrl("https://example.org/example");
        application2.setStrategy(1);
        application2.setType(1);
        application2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.auditApplication(application2, new MockHttpServletRequest()));
        verify(applicationMapper).selectById(isA(Serializable.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#auditApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ApplicationMapper} {@link BaseMapper#updateById(Object)}
     * return one.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#auditApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test auditApplication(Application, HttpServletRequest); given ApplicationMapper updateById(Object) return one; then return 'true'")
    void testAuditApplication_givenApplicationMapperUpdateByIdReturnOne_thenReturnTrue() {
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
        when(applicationMapper.updateById(Mockito.<Application>any())).thenReturn(1);
        when(applicationMapper.selectById(Mockito.<Serializable>any())).thenReturn(application);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        Application application2 = new Application();
        application2.setAppName("App Name");
        application2.setAuditMessage("Audit Message");
        application2.setAuditStatus(1);
        application2.setAuditTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setAuditorId(1L);
        application2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setDeleted(1);
        application2.setDescription("The characteristics of someone or something");
        application2.setId(1L);
        application2.setImageUrl("https://example.org/example");
        application2.setStrategy(1);
        application2.setType(1);
        application2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setUserId(1L);

        // Act
        Boolean actualAuditApplicationResult = applicationServiceImpl.auditApplication(application2,
                new MockHttpServletRequest());

        // Assert
        verify(applicationMapper).selectById(isA(Serializable.class));
        verify(applicationMapper).updateById(isA(Application.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertTrue(actualAuditApplicationResult);
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#auditApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link ApplicationMapper} {@link BaseMapper#updateById(Object)}
     * return zero.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#auditApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test auditApplication(Application, HttpServletRequest); given ApplicationMapper updateById(Object) return zero; then return 'false'")
    void testAuditApplication_givenApplicationMapperUpdateByIdReturnZero_thenReturnFalse() {
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
        when(applicationMapper.updateById(Mockito.<Application>any())).thenReturn(0);
        when(applicationMapper.selectById(Mockito.<Serializable>any())).thenReturn(application);
        when(userClient.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        Application application2 = new Application();
        application2.setAppName("App Name");
        application2.setAuditMessage("Audit Message");
        application2.setAuditStatus(1);
        application2.setAuditTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setAuditorId(1L);
        application2.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setDeleted(1);
        application2.setDescription("The characteristics of someone or something");
        application2.setId(1L);
        application2.setImageUrl("https://example.org/example");
        application2.setStrategy(1);
        application2.setType(1);
        application2.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application2.setUserId(1L);

        // Act
        Boolean actualAuditApplicationResult = applicationServiceImpl.auditApplication(application2,
                new MockHttpServletRequest());

        // Assert
        verify(applicationMapper).selectById(isA(Serializable.class));
        verify(applicationMapper).updateById(isA(Application.class));
        verify(userClient).getLoginUserId(isA(HttpServletRequest.class));
        assertFalse(actualAuditApplicationResult);
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#auditApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link Application} (default constructor) Id is {@code null}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#auditApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test auditApplication(Application, HttpServletRequest); given 'null'; when Application (default constructor) Id is 'null'")
    void testAuditApplication_givenNull_whenApplicationIdIsNull() {
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
        application.setId(null);
        application.setImageUrl("https://example.org/example");
        application.setStrategy(1);
        application.setType(1);
        application.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        application.setUserId(1L);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.auditApplication(application, new MockHttpServletRequest()));
    }

    /**
     * Test
     * {@link ApplicationServiceImpl#auditApplication(Application, HttpServletRequest)}.
     * <ul>
     *   <li>Given three.</li>
     *   <li>When {@link Application} (default constructor) AuditStatus is three.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ApplicationServiceImpl#auditApplication(Application, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test auditApplication(Application, HttpServletRequest); given three; when Application (default constructor) AuditStatus is three")
    void testAuditApplication_givenThree_whenApplicationAuditStatusIsThree() {
        // Arrange
        Application application = new Application();
        application.setAppName("App Name");
        application.setAuditMessage("Audit Message");
        application.setAuditStatus(3);
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

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> applicationServiceImpl.auditApplication(application, new MockHttpServletRequest()));
    }

    /**
     * Test {@link ApplicationServiceImpl#getApplicationById(Long)}.
     * <ul>
     *   <li>Given {@link Application} (default constructor) AppName is
     * {@code App Name}.</li>
     *   <li>Then return {@link Application} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test: {@link ApplicationServiceImpl#getApplicationById(Long)}
     */
    @Test
    @DisplayName("Test getApplicationById(Long); given Application (default constructor) AppName is 'App Name'; then return Application (default constructor)")
    void testGetApplicationById_givenApplicationAppNameIsAppName_thenReturnApplication() {
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
        when(applicationMapper.selectById(Mockito.<Serializable>any())).thenReturn(application);

        // Act
        Application actualApplicationById = applicationServiceImpl.getApplicationById(1L);

        // Assert
        verify(applicationMapper).selectById(isA(Serializable.class));
        assertSame(application, actualApplicationById);
    }

    /**
     * Test {@link ApplicationServiceImpl#getApplicationById(Long)}.
     * <ul>
     *   <li>Then throw {@link BusinessException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ApplicationServiceImpl#getApplicationById(Long)}
     */
    @Test
    @DisplayName("Test getApplicationById(Long); then throw BusinessException")
    void testGetApplicationById_thenThrowBusinessException() {
        // Arrange
        when(applicationMapper.selectById(Mockito.<Serializable>any())).thenThrow(new BusinessException(Err.PARAMS_ERROR));

        // Act and Assert
        assertThrows(BusinessException.class, () -> applicationServiceImpl.getApplicationById(1L));
        verify(applicationMapper).selectById(isA(Serializable.class));
    }

    /**
     * Test {@link ApplicationServiceImpl#getApplicationById(Long)}.
     * <ul>
     *   <li>When {@code null}.</li>
     *   <li>Then return {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ApplicationServiceImpl#getApplicationById(Long)}
     */
    @Test
    @DisplayName("Test getApplicationById(Long); when 'null'; then return 'null'")
    void testGetApplicationById_whenNull_thenReturnNull() {
        // Arrange, Act and Assert
        assertNull(applicationServiceImpl.getApplicationById(null));
    }

    /**
     * Test {@link ApplicationServiceImpl#getApplicationById(Long)}.
     * <ul>
     *   <li>When zero.</li>
     *   <li>Then return {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ApplicationServiceImpl#getApplicationById(Long)}
     */
    @Test
    @DisplayName("Test getApplicationById(Long); when zero; then return 'null'")
    void testGetApplicationById_whenZero_thenReturnNull() {
        // Arrange, Act and Assert
        assertNull(applicationServiceImpl.getApplicationById(0L));
    }
}
