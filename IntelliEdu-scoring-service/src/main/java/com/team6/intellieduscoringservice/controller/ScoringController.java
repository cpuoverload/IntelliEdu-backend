package com.team6.intellieduscoringservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.scoring.*;
import com.team6.intelliedumodel.vo.ScoringVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ScoringController {
    // 普通用户添加评分规则
    @PostMapping("/add/me")
    public ApiResponse<Boolean> addMyScoring(@RequestBody AddMyScoringRequest addMyScoringRequest) {
        return ApiResponse.success(true);
    }

    // 普通用户查看评分规则
    @PostMapping("/list/me")
    public ApiResponse<Page<ScoringVo>> listMyScoring(@RequestBody ListMyScoringRequest listMyScoringRequest) {
        return ApiResponse.success(new Page<>());
    }

    // 普通用户更新评分规则
    @PostMapping("/update/me")
    public ApiResponse<Boolean> updateMyScoring(@RequestBody UpdateMyScoringRequest updateMyScoringRequest) {
        return ApiResponse.success(true);
    }

    // 普通用户删除评分规则
    @PostMapping("/delete/me")
    public ApiResponse<Boolean> deleteMyScoring(@RequestBody IdRequest idRequest) {
        return ApiResponse.success(true);
    }

    // 管理员添加评分规则
    @PostMapping("/add")
    public ApiResponse<Boolean> addScoring(@RequestBody AddScoringRequest addScoringRequest) {
        return ApiResponse.success(true);
    }

    // 管理员查看评分规则
    @PostMapping("/list")
    public ApiResponse<Page<ScoringVo>> listScoring(@RequestBody ListScoringRequest listScoringRequest) {
        return ApiResponse.success(new Page<>());
    }

    // 管理员更新评分规则
    @PostMapping("/update")
    public ApiResponse<Boolean> updateScoring(@RequestBody UpdateScoringRequest updateScoringRequest) {
        return ApiResponse.success(true);
    }

    // 管理员删除评分规则
    @PostMapping("/delete")
    public ApiResponse<Boolean> deleteScoring(@RequestBody IdRequest idRequest) {
        return ApiResponse.success(true);
    }
}
