package com.team6.intellieduscoringservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieduapi.client.UserClient;
import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.scoring.*;
import com.team6.intelliedumodel.entity.Scoring;
import com.team6.intelliedumodel.vo.ScoringVo;
import com.team6.intellieduscoringservice.service.ScoringService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class ScoringController {

    @Resource
    private ScoringService scoringService;

    @Resource
    private UserClient userService;

    // 普通用户添加评分规则
    @PostMapping("/add/me")
    public ApiResponse<Boolean> addMyScoring(@RequestBody AddMyScoringRequest addMyScoringRequest, HttpServletRequest request) {
        if (addMyScoringRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // entity -> dto
        Scoring scoring = new Scoring();
        BeanUtils.copyProperties(addMyScoringRequest, scoring);
        scoring.setResultAttributes(addMyScoringRequest.getResultAttributes());


        // 数据校验
        scoringService.validScoring(scoring, true);

        //  填充默认值
        scoring.setUserId(userService.getLoginUserId(request));
        // 写入数据库
        boolean result = scoringService.save(scoring);
        if (result == false) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }

        return ApiResponse.success(true);
    }

    // 查看当前登录用户的评分规则列表
    @PostMapping("/list/me")
    public ApiResponse<Page<ScoringVo>> listMyScoring(@RequestBody ListMyScoringRequest listMyScoringRequest, HttpServletRequest request) {
        if (listMyScoringRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // 只查询当前登录用户的数据
        listMyScoringRequest.setUserId(userService.getLoginUserId(request));
        long current = listMyScoringRequest.getCurrent();
        long size = listMyScoringRequest.getPageSize();

        // 查询数据库
        Page<Scoring> scoringPage = scoringService.page(new Page<>(current, size), scoringService.getQueryWrapper(listMyScoringRequest));

        return ApiResponse.success(scoringService.getScoringVoPage(scoringPage, request));
    }

    // 普通用户更新评分规则
    @PostMapping("/update/me")
    public ApiResponse<Boolean> updateMyScoring(@RequestBody UpdateMyScoringRequest updateMyScoringRequest) {
        if (updateMyScoringRequest == null || updateMyScoringRequest.getId() <= 0) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // dto -> entity
        Scoring scoring = new Scoring();
        BeanUtils.copyProperties(updateMyScoringRequest, scoring);

        // 数据校验
        scoringService.validScoring(scoring, false);
        // 判断是否存在
        long id = updateMyScoringRequest.getId();
        Scoring oldScoring = scoringService.getById(id);
        if (oldScoring == null) {
            throw new BusinessException(Err.SYSTEM_ERROR, "评分规则不存在");
        }
        // 操作数据库
        boolean result = scoringService.updateById(scoring);
        if (result == false) {
            throw new BusinessException(Err.SYSTEM_ERROR, "更新评分规则失败");
        }

        return ApiResponse.success(true);
    }

    // 普通用户删除评分规则
    @PostMapping("/delete/me")
    public ApiResponse<Boolean> deleteMyScoring(@RequestBody IdRequest idRequest, HttpServletRequest request) {

        if (idRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        long id = idRequest.getId();
        // 判断是否存在
        Scoring oldScoring = scoringService.getById(id);
        if (oldScoring == null) {
            throw new BusinessException(Err.SYSTEM_ERROR, "评分规则不存在");
        }
        // 仅本人可删除
        if (!oldScoring.getUserId().equals(userService.getLoginUserId(request))) {
            throw new BusinessException(Err.FORBIDDEN_ERROR, "不是本人无法删除");
        }
        // 操作数据库
        boolean result = scoringService.removeById(id);
        if (result == false) {
            throw new BusinessException(Err.SYSTEM_ERROR, "删除评分规则失败");
        }

        return ApiResponse.success(true);
    }

    // todo 这里和普通用户有何区别？
    // 管理员添加评分规则
    @PostMapping("/add")
    public ApiResponse<Boolean> addScoring(@RequestBody AddScoringRequest addScoringRequest, HttpServletRequest request) {

        if (addScoringRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // dto -> entity
        Scoring scoring = new Scoring();
        BeanUtils.copyProperties(addScoringRequest, scoring);

        // 数据校验
        scoringService.validScoring(scoring, true);

        //  填充默认值
        scoring.setUserId(userService.getLoginUserId(request));
        // 写入数据库
        boolean result = scoringService.save(scoring);
        if (result == false) {
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

        long current = listScoringRequest.getCurrent();
        long size = listScoringRequest.getPageSize();

        // 查询数据库
        Page<Scoring> scoringPage = scoringService.page(new Page<>(current, size), scoringService.getQueryWrapperAdmin(listScoringRequest));

        return ApiResponse.success(scoringService.getScoringVoPage(scoringPage, request));

    }

    // 管理员更新评分规则
    @PostMapping("/update")
    public ApiResponse<Boolean> updateScoring(@RequestBody UpdateScoringRequest updateScoringRequest) {
        if (updateScoringRequest == null || updateScoringRequest.getId() <= 0) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        //  dto->entity
        Scoring scoring = new Scoring();
        BeanUtils.copyProperties(updateScoringRequest, scoring);

        // 数据校验
        scoringService.validScoring(scoring, false);
        // 判断是否存在
        long id = updateScoringRequest.getId();
        Scoring oldScoringResult = scoringService.getById(id);
        if (oldScoringResult == null) {
            throw new BusinessException(Err.SYSTEM_ERROR, "评分规则不存在");
        }
        // 操作数据库
        boolean result = scoringService.updateById(scoring);
        if (result == false) {
            throw new BusinessException(Err.SYSTEM_ERROR, "更新评分规则失败");
        }
        return ApiResponse.success(true);
    }

    // 管理员删除评分规则
    @PostMapping("/delete")
    public ApiResponse<Boolean> deleteScoring(@RequestBody IdRequest idRequest, HttpServletRequest request) {
        long userId = userService.getLoginUserId(request);
        long id = idRequest.getId();

        if (idRequest == null || id <= 0) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        // 判断是否存在
        Scoring oldScoringResult = scoringService.getById(id);
        if (oldScoringResult == null) {
            throw new BusinessException(Err.SYSTEM_ERROR, "评分规则不存在");
        }
        // 仅管理员可删除
        String userRole = userService.getMyInfo(request).getData().getRole();
        if (!"admin".equals(userRole)) {
            throw new BusinessException(Err.FORBIDDEN_ERROR, "不是管理员无法删除");
        }
        // 操作数据库
        boolean result = scoringService.removeById(id);
        if (result == false) {
            throw new BusinessException(Err.SYSTEM_ERROR, "删除评分规则失败");
        }
        return ApiResponse.success(true);
    }
}
