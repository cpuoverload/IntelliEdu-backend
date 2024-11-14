package com.team6.intellieduuserservice.config;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@ContextConfiguration(classes = {AppConfig.class})
@ExtendWith(SpringExtension.class)
class AppConfigTest {
    @MockBean
    private AdminInterceptor adminInterceptor;

    @Autowired
    private AppConfig appConfig;

    @MockBean
    private LoginInterceptor loginInterceptor;

    /**
     * Test {@link AppConfig#addInterceptors(InterceptorRegistry)}.
     * <ul>
     *   <li>Then calls
     * {@link InterceptorRegistry#addInterceptor(HandlerInterceptor)}.</li>
     * </ul>
     * <p>
     * Method under test: {@link AppConfig#addInterceptors(InterceptorRegistry)}
     */
    @Test
    @DisplayName("Test addInterceptors(InterceptorRegistry); then calls addInterceptor(HandlerInterceptor)")
    void testAddInterceptors_thenCallsAddInterceptor() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AppConfig appConfig = new AppConfig();
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        when(registry.addInterceptor(Mockito.<HandlerInterceptor>any()))
                .thenReturn(new InterceptorRegistration(new AdminInterceptor()));

        // Act
        appConfig.addInterceptors(registry);

        // Assert
        verify(registry, atLeast(1)).addInterceptor(isNull());
    }

    /**
     * Test {@link AppConfig#addInterceptors(InterceptorRegistry)}.
     * <ul>
     *   <li>When {@link InterceptorRegistry} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test: {@link AppConfig#addInterceptors(InterceptorRegistry)}
     */
    @Test
    @DisplayName("Test addInterceptors(InterceptorRegistry); when InterceptorRegistry (default constructor)")
    void testAddInterceptors_whenInterceptorRegistry() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        appConfig.addInterceptors(new InterceptorRegistry());
    }

    /**
     * Test {@link AppConfig#addInterceptors(InterceptorRegistry)}.
     * <ul>
     *   <li>When {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link AppConfig#addInterceptors(InterceptorRegistry)}
     */
    @Test
    @DisplayName("Test addInterceptors(InterceptorRegistry); when 'null'")
    @Disabled("TODO: Complete this test")
    void testAddInterceptors_whenNull() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.team6.intellieduuserservice.config.AppConfig.addInterceptors(AppConfig.java:20)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        appConfig.addInterceptors(null);
    }
}
