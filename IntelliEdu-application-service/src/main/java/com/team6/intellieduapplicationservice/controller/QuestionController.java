package com.team6.intellieduapplicationservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.question.*;
import com.team6.intelliedumodel.vo.QuestionVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionController {

    // 普通用户创建题目
    @PostMapping("/add/me")
    public ApiResponse<Boolean> addMyQuestion(@RequestBody AddMyQuestionRequest addMyQuestionRequest) {
        return ApiResponse.success(true);
    }

    // 普通用户查看题目列表
    @PostMapping("/list/me")
    public ApiResponse<Page<QuestionVo>> listMyQuestion(@RequestBody ListMyQuestionRequest listMyQuestionRequest) {
        return ApiResponse.success(new Page<>());
    }

    // 普通用户更新题目
    @PostMapping("/update/me")
    public ApiResponse<Boolean> updateMyQuestion(@RequestBody UpdateMyQuestionRequest updateMyQuestionRequest) {
        return ApiResponse.success(true);
    }

    // 普通用户删除题目
    @PostMapping("/delete/me")
    public ApiResponse<Boolean> deleteMyQuestion(@RequestBody IdRequest idRequest) {
        return ApiResponse.success(true);
    }

    // 管理员查看题目列表
    @PostMapping("/list")
    public ApiResponse<Page<QuestionVo>> listQuestion(@RequestBody ListQuestionRequest listQuestionRequest) {
        return ApiResponse.success(new Page<>());
    }

    // 管理员更新题目
    @PostMapping("/update")
    public ApiResponse<Boolean> updateQuestion(@RequestBody UpdateQuestionRequest updateQuestionRequest) {
        return ApiResponse.success(true);
    }

    // 管理员删除题目
    @PostMapping("/delete")
    public ApiResponse<Boolean> deleteQuestion(@RequestBody IdRequest idRequest) {
        return ApiResponse.success(true);
    }
}
