package me.intelliedu.intellieduapi.client;

import me.intelliedu.intellieducommon.utils.ApiResponse;
import me.intelliedu.intelliedumodel.entity.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import me.intelliedu.intelliedumodel.entity.Application;


@FeignClient(value = "IntelliEdu-application-service", path = "/api/application")
public interface ApplicationClient {

    @GetMapping("/get/{id}")
    ApiResponse<Application> getApplicationById(@PathVariable("id") Long id);

    @GetMapping("question/get/{appId}")
    ApiResponse<Question> getQuestionByAppId(@PathVariable("appId") Long appId);






}
