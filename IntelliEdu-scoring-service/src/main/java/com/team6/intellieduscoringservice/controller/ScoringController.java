package com.team6.intellieduscoringservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieduapi.client.ApplicationClient;
import com.team6.intellieduapi.client.UserClient;
import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.scoring.*;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.entity.Scoring;
import com.team6.intelliedumodel.vo.ApplicationVo;
import com.team6.intelliedumodel.vo.ScoringVo;
import com.team6.intellieduscoringservice.service.ScoringService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class ScoringController {

    @Resource
    private ScoringService scoringService;

    @Resource
    private UserClient userClient;

    @Resource
    private ApplicationClient applicationClient;

    @GetMapping("test/getAppById")
    public Application getAppByIdTest(@RequestParam Long id) {
        return applicationClient.getApplicationById(id).getData();
    }


    // 普通用户添加评分规则
    @PostMapping("/add/me")
    public ApiResponse<Boolean> addMyScoring(@RequestBody AddMyScoringRequest addMyScoringRequest, HttpServletRequest request) {
        if (addMyScoringRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        if (addMyScoringRequest.getAppId() == null || addMyScoringRequest.getResultName() == null || addMyScoringRequest.getResultAttributes() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        Scoring scoring = new Scoring();
        BeanUtils.copyProperties(addMyScoringRequest, scoring);
        Boolean success = scoringService.addMyScoring(scoring, request);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);

    }

    // 普通用户查看评分规则
    @PostMapping("/list/me")
    public ApiResponse<Page<ScoringVo>> listMyScoring(@RequestBody ListMyScoringRequest listMyScoringRequest, HttpServletRequest request) {
        if (listMyScoringRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        Page<ScoringVo> scorigVoPage = scoringService.listMyScoring(listMyScoringRequest, request);
        return ApiResponse.success(scorigVoPage);
    }

    // 普通用户更新评分规则
    @PostMapping("/update/me")
    public ApiResponse<Boolean> updateMyScoring(@RequestBody UpdateMyScoringRequest updateMyScoringRequest, HttpServletRequest request) {
        if (updateMyScoringRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Scoring scoring = new Scoring();
        BeanUtils.copyProperties(updateMyScoringRequest, scoring);
        Boolean success = scoringService.updateMyScoring(scoring, request);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }

    // 普通用户删除评分规则
    @PostMapping("/delete/me")
    public ApiResponse<Boolean> deleteMyScoring(@RequestBody IdRequest idRequest, HttpServletRequest request) {
        if (idRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Boolean success = scoringService.deleteMyScoring(idRequest, request);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }

    // 管理员添加评分规则
    @PostMapping("/add")
    public ApiResponse<Boolean> addScoring(@RequestBody AddScoringRequest addScoringRequest, HttpServletRequest request) {
        if (addScoringRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        if (addScoringRequest.getAppId() == null || addScoringRequest.getResultName() == null || addScoringRequest.getResultAttributes() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        Scoring scoring = new Scoring();
        BeanUtils.copyProperties(addScoringRequest, scoring);
        Boolean success = scoringService.addScoring(scoring, request);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }

    // todo 和普通用户有何区别？
    // 管理员查看评分规则
    @PostMapping("/list")
    public ApiResponse<Page<ScoringVo>> listScoring(@RequestBody ListScoringRequest listScoringRequest, HttpServletRequest request) {
        if (listScoringRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Page<ScoringVo> scoringVoPage = scoringService.listScoring(listScoringRequest);
        return ApiResponse.success(scoringVoPage);

    }

    // 管理员更新评分规则
    @PostMapping("/update")
    public ApiResponse<Boolean> updateScoring(@RequestBody UpdateScoringRequest updateScoringRequest) {
        if (updateScoringRequest == null || updateScoringRequest.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Scoring scoring = new Scoring();
        BeanUtils.copyProperties(updateScoringRequest, scoring);
        Boolean success = scoringService.updateScoring(scoring);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }

    // 管理员删除评分规则
    @PostMapping("/delete")
    public ApiResponse<Boolean> deleteScoring(@RequestBody IdRequest idRequest, HttpServletRequest request) {
        if (idRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Boolean success = scoringService.deleteScoring(idRequest);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }
        return ApiResponse.success(true);
    }
}
