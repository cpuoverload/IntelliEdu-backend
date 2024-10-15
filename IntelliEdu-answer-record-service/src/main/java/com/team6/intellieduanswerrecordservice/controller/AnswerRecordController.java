package com.team6.intellieduanswerrecordservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.answerrecord.AddMyAnswerRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListAnswerRequest;
import com.team6.intelliedumodel.dto.answerrecord.ListMyAnswerRequest;
import com.team6.intelliedumodel.vo.AnswerRecordVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AnswerRecordController {
    // 普通用户添加答题记录
    @PostMapping("/add/me")
    public ApiResponse<Boolean> addMyAnswerRecord(@RequestBody AddMyAnswerRequest addMyAnswerRequest) {
        return ApiResponse.success(true);
    }

    // 普通用户查看答题记录
    @PostMapping("/list/me")
    public ApiResponse<Page<AnswerRecordVo>> listMyAnswerRecord(@RequestBody ListMyAnswerRequest listMyAnswerRequest) {
        return ApiResponse.success(new Page<>());
    }

    // 管理员查看答题记录
    @PostMapping("/list")
    public ApiResponse<Page<AnswerRecordVo>> listAnswerRecord(@RequestBody ListAnswerRequest listAnswerRequest) {
        return ApiResponse.success(new Page<>());
    }

    // 管理员删除答题记录
    @PostMapping("/delete")
    public ApiResponse<Boolean> deleteAnswerRecord(@RequestBody IdRequest idRequest) {
        return ApiResponse.success(true);
    }
}
