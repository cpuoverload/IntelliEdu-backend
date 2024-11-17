package me.intelliedu.intellieduscoringservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"me.intelliedu.intellieduscoringservice", "me.intelliedu.intellieducommon"})
@MapperScan("me.intelliedu.intellieduscoringservice.mapper")
@EnableFeignClients(basePackages = "me.intelliedu.intellieduapi.client")
@EnableDiscoveryClient
public class ScoringServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoringServiceApplication.class, args);
    }

}
