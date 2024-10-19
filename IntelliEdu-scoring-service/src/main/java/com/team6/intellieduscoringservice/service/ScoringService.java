package com.team6.intellieduscoringservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.scoring.ListMyScoringRequest;
import com.team6.intelliedumodel.dto.scoring.ListScoringRequest;
import com.team6.intelliedumodel.entity.Scoring;
import com.team6.intelliedumodel.vo.ApplicationVo;
import com.team6.intelliedumodel.vo.ScoringVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author passion
 * @description 针对表【scoring(Scoring)】的数据库操作Service
 * @createDate 2024-10-15 16:38:23
 */
public interface ScoringService extends IService<Scoring> {


    Boolean addMyScoring(Scoring scoring, HttpServletRequest request);

    Boolean deleteMyScoring(IdRequest idRequest, HttpServletRequest request);

    Boolean updateMyScoring(Scoring scoring, HttpServletRequest request);

    Page<ScoringVo> listMyScoring(ListMyScoringRequest listMyScoringRequest, HttpServletRequest request);

    Boolean addScoring(Scoring scoring, HttpServletRequest request);

    Boolean deleteScoring(IdRequest idRequest);

    Boolean updateScoring(Scoring scoring);

    Page<ScoringVo> listScoring(ListScoringRequest listScoringRequest);

}
