package com.team6.intellieduapplicationservice.service.impl;

import com.team6.intellieduapplicationservice.service.ApplicationService;
import com.team6.intelliedumodel.entity.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName: ApplicationServiceImplTest
 * Package: com.team6.intellieduapplicationservice.service.impl
 * Description:
 *
 * @Author CBX
 * @Create 19/10/24 01:02
 * @Version 1.0
 */
@SpringBootTest
class ApplicationServiceImplTest {
    @Resource
    ApplicationService applicationService;

    @Test
    void getApplicationById() {
        Application application = applicationService.getApplicationById(1L);
        assertTrue(application != null);
    }
}