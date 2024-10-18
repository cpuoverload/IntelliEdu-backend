package com.team6.intellieduapplicationservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team6.intellieducommon.utils.IdRequest;
import com.team6.intelliedumodel.dto.application.ListAppRequest;
import com.team6.intelliedumodel.dto.application.ListMyAppRequest;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.vo.ApplicationVo;

import javax.servlet.http.HttpServletRequest;

public interface ApplicationService extends IService<Application> {

    Boolean addMyApplication(Application application, HttpServletRequest request);

    Page<ApplicationVo> listMyApplication(ListMyAppRequest listMyAppRequest, HttpServletRequest request);

    Boolean updateMyApplication(Application application, HttpServletRequest request);

    Boolean deleteMyApplication(IdRequest idRequest, HttpServletRequest request);

    Page<ApplicationVo> listApplication(ListAppRequest listAppRequest);

    Boolean updateApplication(Application application);

    Boolean deleteApplication(IdRequest idRequest);

    Boolean auditApplication(Application application, HttpServletRequest request);
}
