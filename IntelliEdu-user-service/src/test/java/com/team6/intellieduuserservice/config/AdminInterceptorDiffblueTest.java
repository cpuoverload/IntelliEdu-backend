package com.team6.intellieduuserservice.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.team6.intellieduuserservice.service.UserService;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.method.HandlerMethod;

@ContextConfiguration(classes = {AdminInterceptor.class})
@ExtendWith(SpringExtension.class)
class AdminInterceptorDiffblueTest {
    @Autowired
    private AdminInterceptor adminInterceptor;

    @MockBean
    private UserService userService;

    /**
     * Test
     * {@link AdminInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}.
     * <ul>
     *   <li>Then {@link MockHttpServletResponse} (default constructor) HeaderNames
     * size is one.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AdminInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    @DisplayName("Test preHandle(HttpServletRequest, HttpServletResponse, Object); then MockHttpServletResponse (default constructor) HeaderNames size is one")
    void testPreHandle_thenMockHttpServletResponseHeaderNamesSizeIsOne() throws Exception {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerMethod handlerMethod = mock(HandlerMethod.class);
        when(handlerMethod.getMethodAnnotation(Mockito.<Class<RequiresAdmin>>any())).thenReturn(mock(RequiresAdmin.class));

        // Act
        boolean actualPreHandleResult = adminInterceptor.preHandle(request, response, handlerMethod);

        // Assert
        verify(handlerMethod).getMethodAnnotation(isA(Class.class));
        Collection<String> headerNames = response.getHeaderNames();
        assertEquals(1, headerNames.size());
        assertTrue(headerNames instanceof Set);
        assertEquals("/login", response.getRedirectedUrl());
        assertEquals(302, response.getStatus());
        assertFalse(actualPreHandleResult);
        assertTrue(headerNames.contains("Location"));
        assertTrue(response.isCommitted());
    }

    /**
     * Test
     * {@link AdminInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}.
     * <ul>
     *   <li>When {@code Handler}.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link AdminInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    @DisplayName("Test preHandle(HttpServletRequest, HttpServletResponse, Object); when 'Handler'; then return 'true'")
    void testPreHandle_whenHandler_thenReturnTrue() throws Exception {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act and Assert
        assertTrue(adminInterceptor.preHandle(request, new Response(), "Handler"));
    }
}
