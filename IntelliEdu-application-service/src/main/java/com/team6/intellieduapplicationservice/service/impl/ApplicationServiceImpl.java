package com.team6.intellieduapplicationservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team6.intellieduapi.client.UserClient;
import com.team6.intellieduapplicationservice.mapper.ApplicationMapper;
import com.team6.intellieduapplicationservice.service.ApplicationService;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.application.ListAppRequest;
import com.team6.intelliedumodel.dto.application.ListMyAppRequest;
import com.team6.intelliedumodel.dto.application.ListPublicAppRequest;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.enums.AppType;
import com.team6.intelliedumodel.enums.AuditStatus;
import com.team6.intelliedumodel.enums.ScoringStrategy;
import com.team6.intelliedumodel.vo.ApplicationVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application>
        implements ApplicationService {

    @Resource
    private UserClient userClient;

    public void validate(Application application) {
        if (application == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        String appName = application.getAppName();
        Integer type = application.getType();
        Integer strategy = application.getStrategy();

        if (appName != null && StringUtils.isBlank(appName)) {
            throw new BusinessException(Err.PARAMS_ERROR, "Application name is empty");
        }
        if (type != null && AppType.fromCode(type) == null) {
            throw new BusinessException(Err.PARAMS_ERROR, "Application type is invalid");
        }
        if (strategy != null && ScoringStrategy.fromCode(strategy) == null) {
            throw new BusinessException(Err.PARAMS_ERROR, "Application strategy is invalid");
        }
    }

    /**
     * convert entity to vo
     * @param application
     * @return
     */
    public ApplicationVo entityToVo(Application application) {
        ApplicationVo applicationVo = new ApplicationVo();
        BeanUtils.copyProperties(application, applicationVo);
        return applicationVo;
    }

    @Override
    public Page<ApplicationVo> listPublicApplication(ListPublicAppRequest listPublicAppRequest) {
        // 1. set page info
        Long current = listPublicAppRequest.getCurrent();
        Long pageSize = listPublicAppRequest.getPageSize();
        IPage<Application> page = new Page<>(current, pageSize);

        // 2. paged query
        QueryWrapper<Application> queryWrapper = new QueryWrapper<>();
        // only can see approved applications
        queryWrapper
                .like(StringUtils.isNotBlank(listPublicAppRequest.getAppName()), "app_name", listPublicAppRequest.getAppName())
                .eq("audit_status", AuditStatus.APPROVED.getCode())
                .orderBy(listPublicAppRequest.getSortField() != null, listPublicAppRequest.getIsAscend(), StrUtil.toUnderlineCase(listPublicAppRequest.getSortField()));
        IPage<Application> applicationPage = page(page, queryWrapper);

        // 3. convert entity to vo
        Page<ApplicationVo> applicationVoPage = new Page<>(current, pageSize, applicationPage.getTotal());
        List<ApplicationVo> voRecords = applicationPage.getRecords().stream().map(this::entityToVo).collect(Collectors.toList());
        applicationVoPage.setRecords(voRecords);

        return applicationVoPage;
    }

    @Override
    public Long addMyApplication(Application application, HttpServletRequest request) {
        // 1. validation
        validate(application);

        // 2. set user id
        Long userId = userClient.getLoginUserId(request);
        application.setUserId(userId);

        // 3. add application
        boolean success = save(application);
        if (!success) {
            throw new BusinessException(Err.SYSTEM_ERROR);
        }

        // 4. return application id
        return application.getId();
    }

    @Override
    public Page<ApplicationVo> listMyApplication(ListMyAppRequest listMyAppRequest, HttpServletRequest request) {
        // 1. set page info
        Long current = listMyAppRequest.getCurrent();
        Long pageSize = listMyAppRequest.getPageSize();
        IPage<Application> page = new Page<>(current, pageSize);

        Long userId = userClient.getLoginUserId(request);

        // 2. paged query
        // 由于不是所有字段都是精确查询，有的字段需要模糊查询，有的字段需要排序，所以不能简单地写成 new QueryWrapper(entity)
        // sortField 是动态传入的列名，无法使用 LambdaQueryWrapper
        QueryWrapper<Application> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .orderBy(listMyAppRequest.getSortField() != null, listMyAppRequest.getIsAscend(), StrUtil.toUnderlineCase(listMyAppRequest.getSortField()));
        IPage<Application> applicationPage = page(page, queryWrapper);

        // 3. convert entity to vo
        Page<ApplicationVo> applicationVoPage = new Page<>(current, pageSize, applicationPage.getTotal());
        List<ApplicationVo> voRecords = applicationPage.getRecords().stream().map(this::entityToVo).collect(Collectors.toList());
        applicationVoPage.setRecords(voRecords);

        return applicationVoPage;
    }

    @Override
    public Boolean updateMyApplication(Application application, HttpServletRequest request) {
        // 1. validation
        if (application.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        validate(application);

        // 2. check if the application exists
        Long userId = userClient.getLoginUserId(request);
        LambdaQueryWrapper<Application> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Application::getId, application.getId())
                .eq(Application::getUserId, userId);
        Application oldApplication = getOne(queryWrapper);
        if (oldApplication == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. update application
        return updateById(application);
    }

    @Override
    public Boolean deleteMyApplication(IdRequest idRequest, HttpServletRequest request) {
        // 1. validation
        if (idRequest == null || idRequest.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // 2. check if the application exists
        Long userId = userClient.getLoginUserId(request);
        LambdaQueryWrapper<Application> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Application::getId, idRequest.getId())
                .eq(Application::getUserId, userId);
        Application application = getOne(queryWrapper);
        if (application == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. delete application
        return removeById(idRequest.getId());
    }

    @Override
    public Page<ApplicationVo> listApplication(ListAppRequest listAppRequest) {
        // 1. set page info
        Long current = listAppRequest.getCurrent();
        Long pageSize = listAppRequest.getPageSize();
        IPage<Application> page = new Page<>(current, pageSize);

        // 2. paged query
        QueryWrapper<Application> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(listAppRequest.getId() != null, "id", listAppRequest.getId())
                .like(StringUtils.isNotBlank(listAppRequest.getAppName()), "app_name", listAppRequest.getAppName())
                .eq(listAppRequest.getType() != null, "type", listAppRequest.getType())
                .eq(listAppRequest.getStrategy() != null, "strategy", listAppRequest.getStrategy())
                .eq(listAppRequest.getUserId() != null, "user_id", listAppRequest.getUserId())
                .eq(listAppRequest.getAuditStatus() != null, "audit_status", listAppRequest.getAuditStatus())
                .eq(listAppRequest.getAuditorId() != null, "auditor_id", listAppRequest.getAuditorId())
                .orderBy(listAppRequest.getSortField() != null, listAppRequest.getIsAscend(), StrUtil.toUnderlineCase(listAppRequest.getSortField()));
        IPage<Application> applicationPage = page(page, queryWrapper);

        // 3. convert entity to vo
        Page<ApplicationVo> applicationVoPage = new Page<>(current, pageSize, applicationPage.getTotal());
        List<ApplicationVo> voRecords = applicationPage.getRecords().stream().map(this::entityToVo).collect(Collectors.toList());
        applicationVoPage.setRecords(voRecords);

        return applicationVoPage;
    }

    @Override
    public Boolean updateApplication(Application application) {
        // 1. validation
        if (application.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        validate(application);

        // 2. check if the application exists
        Application oldApplication = getById(application.getId());
        if (oldApplication == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. update application
        return updateById(application);
    }

    @Override
    public Boolean deleteApplication(IdRequest idRequest) {
        // 1. validation
        if (idRequest == null || idRequest.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }

        // check if the application exists
        Application application = getById(idRequest.getId());
        if (application == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. delete application
        return removeById(idRequest.getId());
    }

    @Override
    public Boolean auditApplication(Application application, HttpServletRequest request) {
        // 1. validation
        if (application.getId() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        if (AuditStatus.fromCode(application.getAuditStatus()) == null) {
            throw new BusinessException(Err.PARAMS_ERROR, "Audit status is invalid");
        }

        // 2. check if the application exists
        Application oldApplication = getById(application.getId());
        if (oldApplication == null) {
            throw new BusinessException(Err.NOT_FOUND_ERROR);
        }

        // 3. set auditor user id and audit time
        Long userId = userClient.getLoginUserId(request);
        application.setAuditorId(userId);
        application.setAuditTime(new Date());

        // 4. update application
        return updateById(application);
    }


}
