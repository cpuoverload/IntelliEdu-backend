package com.team6.intellieduapplicationservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team6.intellieduapplicationservice.mapper.ApplicationMapper;
import com.team6.intellieduapplicationservice.service.ApplicationService;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import com.team6.intelliedumodel.entity.Application;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application>
        implements ApplicationService {

    public void validate(Application application, boolean isUpdate) {
        if (application == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        String appName = application.getAppName();
        String description = application.getDescription();
        String imageUrl = application.getImageUrl();
        Integer type = application.getType();
        Integer strategy = application.getStrategy();

        if (appName != null && StringUtils.isBlank(appName)) {
            throw new BusinessException(Err.PARAMS_ERROR, "Application name is empty");
        }

    }

    @Override
    public Boolean addMyApplication(Application application) {
        return true;
    }
}
