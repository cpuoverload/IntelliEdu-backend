package com.team6.intelliedumodel.vo;

import com.team6.intelliedumodel.dto.question.QuestionContent;
import lombok.Data;

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

    private static final long serialVersionUID = -2294546556817718253L;
}
