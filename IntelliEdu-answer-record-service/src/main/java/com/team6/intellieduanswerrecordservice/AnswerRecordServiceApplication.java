package com.team6.intellieduanswerrecordservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@MapperScan("com.team6.intellieduanswerrecordservice.mapper")
@EnableFeignClients(basePackages = "com.team6.intellieduapi.client")
public class AnswerRecordServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnswerRecordServiceApplication.class, args);
    }

}
