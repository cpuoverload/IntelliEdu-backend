package com.team6.intellieduapi.client;

import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intelliedumodel.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "IntelliEdu-user-service", path = "/api/user")
public interface UserClient {
    @GetMapping("/test/fegin")
    ApiResponse<String> testFegin();


    default Long getLoginUserId(HttpServletRequest request) {
        UserVo userVo = (UserVo) request.getSession().getAttribute("loginUser");
        return userVo.getId();
    }

    default ApiResponse<UserVo> getMyInfo(HttpServletRequest request) {
        UserVo userVo = (UserVo) request.getSession().getAttribute("loginUser");
        return ApiResponse.success(userVo);
    }

}
