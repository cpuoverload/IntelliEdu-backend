package com.team6.intellieduapi.client;

import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intelliedumodel.entity.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.team6.intelliedumodel.entity.Application;


@FeignClient(value = "IntelliEdu-application-service", path = "/api/application")
public interface ApplicationClient {

    @GetMapping("/get/{id}")
    ApiResponse<Application> getApplicationById(@PathVariable("id") Long id);

    @GetMapping("question/get/{id}")
    ApiResponse<Question> getQuestionById(@PathVariable("id") Long id);






}
