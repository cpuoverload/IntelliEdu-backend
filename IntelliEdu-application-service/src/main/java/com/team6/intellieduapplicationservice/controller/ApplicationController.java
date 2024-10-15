package com.team6.intellieduapplicationservice.controller;

import com.team6.intellieduapi.client.UserClient;
import com.team6.intellieducommon.utils.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class ApplicationController {

    @Resource
    private UserClient userClient;

    @GetMapping("/test")
    public ApiResponse<String> test() {
        ApiResponse<String> response = userClient.testFegin();
        return response;
    }
}
