package com.team6.intellieduscoringservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team6.intellieduapi.client.ApplicationClient;
import com.team6.intellieduapi.client.UserClient;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.scoring.ListMyScoringRequest;
import com.team6.intelliedumodel.dto.scoring.ListScoringRequest;
import com.team6.intelliedumodel.entity.Scoring;
import com.team6.intelliedumodel.vo.ScoringVo;
import com.team6.intellieduscoringservice.mapper.ScoringMapper;
import com.team6.intellieduscoringservice.service.ScoringService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author passion
 * @description 针对表【scoring(Scoring)】的数据库操作Service实现
 * @createDate 2024-10-15 16:38:23
 */
@Service
public class ScoringServiceImpl extends ServiceImpl<ScoringMapper, Scoring>
        implements ScoringService {

    @Resource
    UserClient userClient;

    @Resource
    ApplicationClient applicationClient;

    public void validate(Scoring scoring) {
        if (scoring == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Long appId = scoring.getAppId();
        String resultName = scoring.getResultName();

        if (appId != null && appId <= 0) {
            throw new BusinessException(Err.PARAMS_ERROR, "Application ID is illegal");
        }
        if (resultName != null && StringUtils.isBlank(resultName)) {
            throw new BusinessException(Err.PARAMS_ERROR, "Result name is blank");
        }
        if (applicationClient.getApplicationById(appId).getData() == null) {
            throw new BusinessException(Err.PARAMS_ERROR, "Application does not exist");
        }

    }

    public ScoringVo entityToVo(Scoring scoring) {
        ScoringVo scoringVo = new ScoringVo();
        BeanUtils.copyProperties(scoring, scoringVo);
        return scoringVo;
    }

    @Override
    public Boolean addMyScoring(Scoring scoring, HttpServletRequest request) {
        // 1. validation
        validate(scoring);

        // 2. set user id
        Long userId = userClient.getLoginUserId(request);
        scoring.setUserId(userId);

        // 3. add application
        return save(scoring);
    }

    @Override
    public Boolean deleteMyScoring(IdRequest idRequest, HttpServletRequest request) {
        // 1. validation
        if (idRequest == null || idRequest.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // 2. check if the Scoring exists
        Long userId = userClient.getLoginUserId(request);
        LambdaQueryWrapper<Scoring> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Scoring::getId, idRequest.getId())
                .eq(Scoring::getUserId, userId);
        Scoring scoring = getOne(queryWrapper);
        if (scoring == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. delete scoring
        return removeById(idRequest.getId());
    }

    @Override
    public Boolean updateMyScoring(Scoring scoring, HttpServletRequest request) {
        // 1. validation
        if (scoring.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        validate(scoring);

        // 2. check if the scoring exists
        Long userId = userClient.getLoginUserId(request);
        LambdaQueryWrapper<Scoring> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Scoring::getId, scoring.getId())
                .eq(Scoring::getUserId, userId);
        Scoring oldScoring = getOne(queryWrapper);
        if (oldScoring == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. update application
        return updateById(scoring);
    }

    @Override
    public Page<ScoringVo> listMyScoring(ListMyScoringRequest listMyScoringRequest, HttpServletRequest request) {
        // 1. set page info
        Long current = listMyScoringRequest.getCurrent();
        Long pageSize = listMyScoringRequest.getPageSize();
        IPage<Scoring> page = new Page<>(current, pageSize);

        Long userId = userClient.getLoginUserId(request);

        // 2. paged query
        // 由于不是所有字段都是精确查询，有的字段需要模糊查询，有的字段需要排序，所以不能简单地写成 new QueryWrapper(entity)
        // sortField 是动态传入的列名，无法使用 LambdaQueryWrapper
        QueryWrapper<Scoring> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .orderBy(listMyScoringRequest.getSortField() != null, listMyScoringRequest.getIsAscend(), StrUtil.toUnderlineCase(listMyScoringRequest.getSortField()));
        IPage<Scoring> scoringPage = page(page, queryWrapper);

        // 3. convert entity to vo
        Page<ScoringVo> scoringVoPage = new Page<>(current, pageSize, scoringPage.getTotal());
        List<ScoringVo> voRecords = scoringPage.getRecords().stream().map(this::entityToVo).collect(Collectors.toList());
        scoringVoPage.setRecords(voRecords);

        return scoringVoPage;
    }

    @Override
    public Boolean addScoring(Scoring scoring, HttpServletRequest request) {
        // 1. validation
        validate(scoring);

        // 2. set user id
        Long userId = userClient.getLoginUserId(request);
        scoring.setUserId(userId);

        // 3. add application
        return save(scoring);
    }

    @Override
    public Page<ScoringVo> listScoring(ListScoringRequest listScoringRequest) {
        // 1. set page info
        Long current = listScoringRequest.getCurrent();
        Long pageSize = listScoringRequest.getPageSize();
        IPage<Scoring> page = new Page<>(current, pageSize);

        // 2. paged query
        QueryWrapper<Scoring> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(listScoringRequest.getAppId() != null, "app_id", listScoringRequest.getAppId())
                .orderBy(listScoringRequest.getSortField() != null, listScoringRequest.getIsAscend(), StrUtil.toUnderlineCase(listScoringRequest.getSortField()));
        IPage<Scoring> scoringPage = page(page, queryWrapper);

        // 3. convert entity to vo
        Page<ScoringVo> scoringVoPage = new Page<>(current, pageSize, scoringPage.getTotal());
        List<ScoringVo> voRecords = scoringPage.getRecords().stream().map(this::entityToVo).collect(Collectors.toList());
        scoringVoPage.setRecords(voRecords);

        return scoringVoPage;
    }

    @Override
    public Boolean updateScoring(Scoring scoring) {
        // 1. validation
        if (scoring.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        validate(scoring);

        // 2. check if the scoring exists
        Scoring oldScoring = getById(scoring.getId());
        if (oldScoring == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. update scoring
        return updateById(scoring);
    }

    @Override
    public Boolean deleteScoring(IdRequest idRequest) {
        // 1. validation
        if (idRequest == null || idRequest.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // 2. check if the scoring exists
        Scoring scoring = getById(idRequest.getId());
        if (scoring == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. delete scoring
        return removeById(idRequest.getId());
    }

}




