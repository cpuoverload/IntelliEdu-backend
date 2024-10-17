package com.team6.intellieduapplicationservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieduapi.client.UserClient;
import com.team6.intellieduapplicationservice.service.ApplicationService;
import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.application.*;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.vo.ApplicationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class ApplicationController {

    @Resource
    private UserClient userClient;

    @Resource
    private ApplicationService applicationService;

    // 测试 Feign
    // @GetMapping("/test")
    // public ApiResponse<String> test() {
    //     ApiResponse<String> response = userClient.testFegin();
    //     return response;
    // }

    // 普通用户创建应用
    @PostMapping("/add/me")
    public ApiResponse<Boolean> addMyApplication(@RequestBody AddMyAppRequest addMyAppRequest) {
        if (addMyAppRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        if (addMyAppRequest.getAppName() == null || addMyAppRequest.getType() == null || addMyAppRequest.getStrategy() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Application application = new Application();
        BeanUtils.copyProperties(addMyAppRequest, application);
        Boolean success = applicationService.addMyApplication(application);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }

    // 普通用户查看应用列表
    @PostMapping("/list/me")
    public ApiResponse<Page<ApplicationVo>> listMyApplication(@RequestBody ListMyAppRequest listMyAppRequest) {
        return ApiResponse.success(new Page<>());
    }

    // 普通用户更新应用
    @PostMapping("/update/me")
    public ApiResponse<Boolean> updateMyApplication(@RequestBody UpdateMyAppRequest updateMyAppRequest) {
        return ApiResponse.success(true);
    }

    // 普通用户删除应用
    @PostMapping("/delete/me")
    public ApiResponse<Boolean> deleteMyApplication(@RequestBody IdRequest idRequest) {
        return ApiResponse.success(true);
    }

    // 管理员查看应用列表
    @PostMapping("/list")
    public ApiResponse<Page<ApplicationVo>> listApplication(@RequestBody ListAppRequest listAppRequest) {
        return ApiResponse.success(new Page<>());
    }

    // 管理员更新应用
    @PostMapping("/update")
    public ApiResponse<Boolean> updateApplication(@RequestBody UpdateAppRequest updateAppRequest) {
        return ApiResponse.success(true);
    }

    // 管理员删除应用
    @PostMapping("/delete")
    public ApiResponse<Boolean> deleteApplication(@RequestBody IdRequest idRequest) {
        return ApiResponse.success(true);
    }

    // 管理员审核应用
    @PostMapping("/audit")
    public ApiResponse<Boolean> auditApplication(@RequestBody AuditAppRequest auditAppRequest) {
        return ApiResponse.success(true);
    }

}
