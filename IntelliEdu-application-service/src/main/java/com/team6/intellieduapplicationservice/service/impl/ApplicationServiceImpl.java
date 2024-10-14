package com.team6.intellieduapplicationservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team6.intellieduapplicationservice.mapper.ApplicationMapper;
import com.team6.intellieduapplicationservice.service.ApplicationService;
import com.team6.intelliedumodel.entity.Application;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application>
        implements ApplicationService {

}
