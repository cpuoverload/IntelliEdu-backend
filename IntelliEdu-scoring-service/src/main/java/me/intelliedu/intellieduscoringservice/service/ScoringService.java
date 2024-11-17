package me.intelliedu.intellieduscoringservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.intelliedu.intellieducommon.utils.IdRequest;
import me.intelliedu.intelliedumodel.dto.scoring.ListMyScoringRequest;
import me.intelliedu.intelliedumodel.dto.scoring.ListScoringRequest;
import me.intelliedu.intelliedumodel.entity.Scoring;
import me.intelliedu.intelliedumodel.vo.ScoringVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author passion
 * @description 针对表【scoring(Scoring)】的数据库操作Service
 * @createDate 2024-10-15 16:38:23
 */
public interface ScoringService extends IService<Scoring> {


    Boolean addMyScoring(Scoring scoring, HttpServletRequest request);

    Boolean addMyScoringBatch(List<Scoring> scoringList, HttpServletRequest request);

    Boolean deleteMyScoring(IdRequest idRequest, HttpServletRequest request);

    Boolean updateMyScoring(Scoring scoring, HttpServletRequest request);

    Page<ScoringVo> listMyScoring(ListMyScoringRequest listMyScoringRequest, HttpServletRequest request);

    Boolean addScoring(Scoring scoring, HttpServletRequest request);

    Boolean deleteScoring(IdRequest idRequest);

    Boolean updateScoring(Scoring scoring);

    Page<ScoringVo> listScoring(ListScoringRequest listScoringRequest);

}
