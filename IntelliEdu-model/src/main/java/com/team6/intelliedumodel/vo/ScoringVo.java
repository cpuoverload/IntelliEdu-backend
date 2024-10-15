package com.team6.intelliedumodel.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ScoringVo implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * Result name
     */
    private String resultName;

    /**
     * Result Detail
     */
    private String detail;

    /**
     * Result Image URL
     */
    private String imageUrl;

    /**
     * Result Attribute Array, Intended For Evaluation-Type Applications
     */
    private Object attributes;

    /**
     * Score Threshold For This Result, Intended For Grading-Type Applications
     */
    private Integer threshold;

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

    private static final long serialVersionUID = 1L;
}
