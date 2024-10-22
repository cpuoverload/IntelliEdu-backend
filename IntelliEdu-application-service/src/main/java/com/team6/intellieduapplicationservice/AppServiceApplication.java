package com.team6.intellieduapplicationservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.team6.intellieduapplicationservice", "com.team6.intellieducommon"})
@MapperScan("com.team6.intellieduapplicationservice.mapper")
@EnableFeignClients(basePackages = "com.team6.intellieduapi.client")
public class AppServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppServiceApplication.class, args);
    }

}
