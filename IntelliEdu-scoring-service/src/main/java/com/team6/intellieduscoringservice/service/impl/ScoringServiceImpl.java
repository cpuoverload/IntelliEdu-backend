package com.team6.intellieduscoringservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intelliedumodel.dto.scoring.ListMyScoringRequest;
import com.team6.intelliedumodel.dto.scoring.ListScoringRequest;
import com.team6.intelliedumodel.entity.Scoring;
import com.team6.intelliedumodel.vo.ScoringVo;
import com.team6.intellieduscoringservice.mapper.ScoringMapper;
import com.team6.intellieduscoringservice.service.ScoringService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author passion
 * @description 针对表【scoring(Scoring)】的数据库操作Service实现
 * @createDate 2024-10-15 16:38:23
 */
@Service
public class ScoringServiceImpl extends ServiceImpl<ScoringMapper, Scoring>
        implements ScoringService {

//    @Resource
//    UserService userService;

    @Override
    public void validScoring(Scoring scoring, boolean isAdd) {
        if (scoring == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        //  从对象中取值
        String resultName = scoring.getResultName();
        String detail = scoring.getResultDetail();
        Long appId = scoring.getAppId();

        // 创建数据时，参数不能为空
        if (isAdd) {
            //  补充校验规则
            if (StringUtils.isBlank(resultName)) {
                throw new BusinessException(Err.PARAMS_ERROR, "结果名称不能为空");
            }
            if (StringUtils.isBlank(detail)) {
                throw new BusinessException(Err.PARAMS_ERROR, "结果描述不能为空");
            }
            if (appId == null || appId <= 0) {
                throw new BusinessException(Err.PARAMS_ERROR, "appId非法");
            }
        }

        // 修改数据时，有参数则校验
        //  补充校验规则
        if (StringUtils.isNotBlank(resultName)) {
            if (resultName.length() > 128) {
                throw new BusinessException(Err.PARAMS_ERROR, "结果名称不能超过 128");
            }
        }
        // todo 应用表的增删改查还没写
//        if (appId != null) {
//            App app = appService.getById(appId);
//            ThrowUtils.throwIf(app == null, ErrorCode.PARAMS_ERROR, "应用不存在");
//        }

    }

    @Override
    public QueryWrapper<Scoring> getQueryWrapper(ListMyScoringRequest listMyScoringRequest) {
        QueryWrapper<Scoring> queryWrapper = new QueryWrapper<>();
        if (listMyScoringRequest == null) {
            return queryWrapper;
        }
//  从对象中取值
        Long id = listMyScoringRequest.getId();
        String resultName = listMyScoringRequest.getResultName();
        String detail = listMyScoringRequest.getResultDetail();
        String attributes = listMyScoringRequest.getResultAttributes().toString();
        Integer threshold = listMyScoringRequest.getResultThreshold();
        Long appId = listMyScoringRequest.getAppId();
        Long userId = listMyScoringRequest.getUserId();


        //  补充需要的查询条件

        // 模糊查询
        queryWrapper.like(StringUtils.isNotBlank(attributes), "result_attributes", attributes);
        queryWrapper.like(StringUtils.isNotBlank(resultName), "result_name", resultName);
        queryWrapper.like(StringUtils.isNotBlank(detail), "result_detail", detail);

        // 精确查询
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "user_id", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(appId), "app_id", appId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(threshold), "result_threshold", threshold);


        return queryWrapper;
    }

    @Override
    public QueryWrapper<Scoring> getQueryWrapperAdmin(ListScoringRequest listScoringRequest) {
        QueryWrapper<Scoring> queryWrapper = new QueryWrapper<>();
        if (listScoringRequest == null) {
            return queryWrapper;
        }
//  从对象中取值
        Long id = listScoringRequest.getId();
        String resultName = listScoringRequest.getResultName();
        String detail = listScoringRequest.getResultDetail();
        String attributes = listScoringRequest.getResultAttributes().toString();
        Integer threshold = listScoringRequest.getResultThreshold();
        Long appId = listScoringRequest.getAppId();
        Long userId = listScoringRequest.getUserId();


        //  补充需要的查询条件

        // 模糊查询
        queryWrapper.like(StringUtils.isNotBlank(attributes), "result_attributes", attributes);
        queryWrapper.like(StringUtils.isNotBlank(resultName), "result_name", resultName);
        queryWrapper.like(StringUtils.isNotBlank(detail), "result_detail", detail);

        // 精确查询
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "user_id", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(appId), "app_id", appId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(threshold), "result_threshold", threshold);


        return queryWrapper;

    }

    @Override
    public ScoringVo getScoringVo(Scoring scoring, HttpServletRequest request) {
        // 对象转封装类
        ScoringVo scoringVo = ScoringVo.objToVo(scoring);

        //  可以根据需要为封装对象补充值，不需要的内容可以删除
        // 1. 关联查询用户信息
        Long userId = scoring.getUserId();
        // todo scoring 和 scoringVo 的区别
//        User user = null;
//        if (userId != null && userId > 0) {
//            user = userService.getById(userId);
//        }
//        UserVo userVo = new UserVo();
//        BeanUtils.copyProperties(user, userVo);
        scoringVo.setUserId(userId);


        return scoringVo;
    }

    @Override
    public Page<ScoringVo> getScoringVoPage(Page<Scoring> scoringResultPage, HttpServletRequest request) {
        return null;
    }
}




