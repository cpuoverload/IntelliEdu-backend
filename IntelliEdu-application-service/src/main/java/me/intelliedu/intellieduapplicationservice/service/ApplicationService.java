package me.intelliedu.intellieduapplicationservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.intelliedu.intellieducommon.utils.IdRequest;
import me.intelliedu.intelliedumodel.dto.application.ListAppRequest;
import me.intelliedu.intelliedumodel.dto.application.ListMyAppRequest;
import me.intelliedu.intelliedumodel.dto.application.ListPublicAppRequest;
import me.intelliedu.intelliedumodel.entity.Application;
import me.intelliedu.intelliedumodel.vo.ApplicationVo;

import javax.servlet.http.HttpServletRequest;

public interface ApplicationService extends IService<Application> {
    Page<ApplicationVo> listPublicApplication(ListPublicAppRequest listPublicAppRequest);

    Long addMyApplication(Application application, HttpServletRequest request);

    Page<ApplicationVo> listMyApplication(ListMyAppRequest listMyAppRequest, HttpServletRequest request);

    Boolean updateMyApplication(Application application, HttpServletRequest request);

    Boolean deleteMyApplication(IdRequest idRequest, HttpServletRequest request);

    Page<ApplicationVo> listApplication(ListAppRequest listAppRequest);

    Boolean updateApplication(Application application);

    Boolean deleteApplication(IdRequest idRequest);

    Boolean auditApplication(Application application, HttpServletRequest request);

    Application getApplicationById(Long id);
}
