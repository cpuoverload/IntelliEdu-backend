package me.intelliedu.intellieduapplicationservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"me.intelliedu.intellieduapplicationservice", "me.intelliedu.intellieducommon"})
@MapperScan("me.intelliedu.intellieduapplicationservice.mapper")
@EnableFeignClients(basePackages = "me.intelliedu.intellieduapi.client")
public class AppServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppServiceApplication.class, args);
    }

}
