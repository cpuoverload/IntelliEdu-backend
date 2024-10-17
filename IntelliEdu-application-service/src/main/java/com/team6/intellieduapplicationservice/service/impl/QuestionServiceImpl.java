package com.team6.intellieduapplicationservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team6.intellieduapplicationservice.mapper.QuestionMapper;
import com.team6.intellieduapplicationservice.service.QuestionService;
import com.team6.intelliedumodel.entity.Question;
import org.springframework.stereotype.Service;

/**
* @author passion
* @description 针对表【question(Question)】的数据库操作Service实现
* @createDate 2024-10-17 15:51:37
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService {

}




