package com.team6.intellieduapi.client;

import com.team6.intellieducommon.utils.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "IntelliEdu-user-service", path = "/api/user")
public interface UserClient {
    @GetMapping("/test/fegin")
    ApiResponse<String> testFegin();
}
