package me.intelliedu.intellieduanswerrecordservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@MapperScan("me.intelliedu.intellieduanswerrecordservice.mapper")
@EnableFeignClients(basePackages = "me.intelliedu.intellieduapi.client")
public class AnswerRecordServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnswerRecordServiceApplication.class, args);
    }

}
