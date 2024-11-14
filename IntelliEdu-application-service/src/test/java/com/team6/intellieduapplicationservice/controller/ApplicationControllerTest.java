package com.team6.intellieduapplicationservice.controller;

import static org.mockito.Mockito.when;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team6.intellieduapplicationservice.service.ApplicationService;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.application.AddMyAppRequest;
import com.team6.intelliedumodel.dto.application.AuditAppRequest;
import com.team6.intelliedumodel.dto.application.ListAppRequest;
import com.team6.intelliedumodel.dto.application.ListMyAppRequest;
import com.team6.intelliedumodel.dto.application.ListPublicAppRequest;
import com.team6.intelliedumodel.dto.application.UpdateAppRequest;
import com.team6.intelliedumodel.dto.application.UpdateMyAppRequest;
import com.team6.intelliedumodel.entity.Application;

import java.time.LocalDate;
import java.time.ZoneOffset;
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

@ContextConfiguration(classes = {ApplicationController.class})
@ExtendWith(SpringExtension.class)
class ApplicationControllerTest {
    @Autowired
    private ApplicationController applicationController;

    @MockBean
    private ApplicationService applicationService;

    /**
     * Test
     * {@link ApplicationController#listPublicApplication(ListPublicAppRequest)}.
     * <p>
     * Method under test:
     * {@link ApplicationController#listPublicApplication(ListPublicAppRequest)}
     */
    @Test
    @DisplayName("Test listPublicApplication(ListPublicAppRequest)")
    void testListPublicApplication() throws Exception {
        // Arrange
        when(applicationService.listPublicApplication(Mockito.<ListPublicAppRequest>any())).thenReturn(new Page<>());

        ListPublicAppRequest listPublicAppRequest = new ListPublicAppRequest();
        listPublicAppRequest.setAppName("App Name");
        listPublicAppRequest.setCurrent(1L);
        listPublicAppRequest.setIsAscend(true);
        listPublicAppRequest.setPageSize(3L);
        listPublicAppRequest.setSortField("Sort Field");
        String content = (new ObjectMapper()).writeValueAsString(listPublicAppRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/list/public")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
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
     * Test
     * {@link ApplicationController#addMyApplication(AddMyAppRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ApplicationController#addMyApplication(AddMyAppRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test addMyApplication(AddMyAppRequest, HttpServletRequest)")
    void testAddMyApplication() throws Exception {
        // Arrange
        when(applicationService.addMyApplication(Mockito.<Application>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(1L);

        AddMyAppRequest addMyAppRequest = new AddMyAppRequest();
        addMyAppRequest.setAppName("App Name");
        addMyAppRequest.setDescription("The characteristics of someone or something");
        addMyAppRequest.setImageUrl("https://example.org/example");
        addMyAppRequest.setStrategy(1);
        addMyAppRequest.setType(1);
        String content = (new ObjectMapper()).writeValueAsString(addMyAppRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":1,\"message\":\"success\"}"));
    }

    /**
     * Test
     * {@link ApplicationController#listMyApplication(ListMyAppRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ApplicationController#listMyApplication(ListMyAppRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test listMyApplication(ListMyAppRequest, HttpServletRequest)")
    void testListMyApplication() throws Exception {
        // Arrange
        when(applicationService.listMyApplication(Mockito.<ListMyAppRequest>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(new Page<>());

        ListMyAppRequest listMyAppRequest = new ListMyAppRequest();
        listMyAppRequest.setCurrent(1L);
        listMyAppRequest.setId(1L);
        listMyAppRequest.setIsAscend(true);
        listMyAppRequest.setPageSize(3L);
        listMyAppRequest.setSortField("Sort Field");
        String content = (new ObjectMapper()).writeValueAsString(listMyAppRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/list/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
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
     * Test
     * {@link ApplicationController#updateMyApplication(UpdateMyAppRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ApplicationController#updateMyApplication(UpdateMyAppRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyApplication(UpdateMyAppRequest, HttpServletRequest)")
    void testUpdateMyApplication() throws Exception {
        // Arrange
        when(applicationService.updateMyApplication(Mockito.<Application>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(true);

        UpdateMyAppRequest updateMyAppRequest = new UpdateMyAppRequest();
        updateMyAppRequest.setAppName("App Name");
        updateMyAppRequest.setDescription("The characteristics of someone or something");
        updateMyAppRequest.setId(1L);
        updateMyAppRequest.setImageUrl("https://example.org/example");
        updateMyAppRequest.setStrategy(1);
        updateMyAppRequest.setType(1);
        String content = (new ObjectMapper()).writeValueAsString(updateMyAppRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/update/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test
     * {@link ApplicationController#deleteMyApplication(IdRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ApplicationController#deleteMyApplication(IdRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test deleteMyApplication(IdRequest, HttpServletRequest)")
    void testDeleteMyApplication() throws Exception {
        // Arrange
        when(applicationService.deleteMyApplication(Mockito.<IdRequest>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(true);

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(idRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/delete/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test {@link ApplicationController#listApplication(ListAppRequest)}.
     * <p>
     * Method under test:
     * {@link ApplicationController#listApplication(ListAppRequest)}
     */
    @Test
    @DisplayName("Test listApplication(ListAppRequest)")
    void testListApplication() throws Exception {
        // Arrange
        when(applicationService.listApplication(Mockito.<ListAppRequest>any())).thenReturn(new Page<>());

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
        String content = (new ObjectMapper()).writeValueAsString(listAppRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
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
     * Test {@link ApplicationController#updateApplication(UpdateAppRequest)}.
     * <p>
     * Method under test:
     * {@link ApplicationController#updateApplication(UpdateAppRequest)}
     */
    @Test
    @DisplayName("Test updateApplication(UpdateAppRequest)")
    void testUpdateApplication() throws Exception {
        // Arrange
        when(applicationService.updateApplication(Mockito.<Application>any())).thenReturn(true);

        UpdateAppRequest updateAppRequest = new UpdateAppRequest();
        updateAppRequest.setAppName("App Name");
        updateAppRequest.setDescription("The characteristics of someone or something");
        updateAppRequest.setId(1L);
        updateAppRequest.setImageUrl("https://example.org/example");
        String content = (new ObjectMapper()).writeValueAsString(updateAppRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test {@link ApplicationController#deleteApplication(IdRequest)}.
     * <p>
     * Method under test: {@link ApplicationController#deleteApplication(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteApplication(IdRequest)")
    void testDeleteApplication() throws Exception {
        // Arrange
        when(applicationService.deleteApplication(Mockito.<IdRequest>any())).thenReturn(true);

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(idRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test
     * {@link ApplicationController#auditApplication(AuditAppRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link ApplicationController#auditApplication(AuditAppRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test auditApplication(AuditAppRequest, HttpServletRequest)")
    void testAuditApplication() throws Exception {
        // Arrange
        when(applicationService.auditApplication(Mockito.<Application>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(true);

        AuditAppRequest auditAppRequest = new AuditAppRequest();
        auditAppRequest.setAuditMessage("Audit Message");
        auditAppRequest.setAuditStatus(1);
        auditAppRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(auditAppRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/audit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test {@link ApplicationController#getApplicationById(Long)}.
     * <p>
     * Method under test: {@link ApplicationController#getApplicationById(Long)}
     */
    @Test
    @DisplayName("Test getApplicationById(Long)")
    void testGetApplicationById() throws Exception {
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
        when(applicationService.getApplicationById(Mockito.<Long>any())).thenReturn(application);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get/42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"appName\":\"App Name\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"imageUrl\":\"https://example.org/example\",\"type\":1,\"strategy\":1,\"userId\":1,\"auditStatus\":1"
                                        + ",\"auditorId\":1,\"auditMessage\":\"Audit Message\",\"auditTime\":0,\"createTime\":0,\"updateTime\":0,\"deleted\":1"
                                        + "},\"message\":\"success\"}"));
    }
}
