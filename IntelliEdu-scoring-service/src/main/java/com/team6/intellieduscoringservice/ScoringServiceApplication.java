package com.team6.intellieduscoringservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.team6.intellieduscoringservice", "com.team6.intellieducommon"})
@MapperScan("com.team6.intellieduscoringservice.mapper")
@EnableFeignClients(basePackages = "com.team6.intellieduapi.client")
@EnableDiscoveryClient
public class ScoringServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoringServiceApplication.class, args);
    }

}
