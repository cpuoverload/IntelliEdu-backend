package com.team6.intellieduscoringservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team6.intelliedumodel.dto.scoring.ListMyScoringRequest;
import com.team6.intelliedumodel.dto.scoring.ListScoringRequest;
import com.team6.intelliedumodel.entity.Scoring;
import com.team6.intelliedumodel.vo.ScoringVo;

import javax.servlet.http.HttpServletRequest;

/**
* @author passion
* @description 针对表【scoring(Scoring)】的数据库操作Service
* @createDate 2024-10-15 16:38:23
*/
public interface ScoringService extends IService<Scoring> {

    /**
     * 校验数据
     * @param scoring
     * @param isAdd 是否是新增
     */
    void validScoring(Scoring scoring, boolean isAdd);

    /**
     * 获取查询条件
     *
     * @param
     * @return
     */
    QueryWrapper<Scoring> getQueryWrapper(ListMyScoringRequest listMyScoringRequest);

    QueryWrapper<Scoring> getQueryWrapperAdmin(ListScoringRequest listScoringRequest);


    /**
     * 获取打分结果封装
     *
     * @param scoringResult
     * @param request
     * @return
     */
    ScoringVo getScoringVo(Scoring scoringResult, HttpServletRequest request);

    /**
     * 分页获取打分结果封装
     *
     * @param scoringResultPage
     * @param request
     * @return
     */
    Page<ScoringVo> getScoringVoPage(Page<Scoring> scoringResultPage, HttpServletRequest request);

}
