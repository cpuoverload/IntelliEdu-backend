package com.team6.intelliedumodel.vo;

import com.team6.intelliedumodel.dto.question.QuestionContent;
import com.team6.intelliedumodel.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class QuestionVo implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * Question List (JSON)
     */
    private List<QuestionContent> questions;

    /**
     * Application ID
     */
    private Long appId;

    /**
     * Creator User ID
     */
    private Long userId;

    /**
     * Creation Time
     */
    private Date createTime;

    /**
     * Update Time
     */
    private Date updateTime;

    public static QuestionVo objToVo(Question question){
        if (question == null) {
            return null;
        }
        QuestionVo questionVo = new QuestionVo();
        BeanUtils.copyProperties(question, questionVo);
        return questionVo;
    }

    private static final long serialVersionUID = -2294546556817718253L;
}
