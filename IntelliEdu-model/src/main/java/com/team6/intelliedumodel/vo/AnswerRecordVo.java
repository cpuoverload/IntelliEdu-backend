package com.team6.intelliedumodel.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class AnswerRecordVo implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * Creator User ID
     */
    private Long userId;

    /**
     * Application ID
     */
    private Long appId;

    /**
     * Application Type (0 - Grade, 1 - Evaluation)
     */
    private Integer appType;

    /**
     * Scoring Strategy (0 - Custom, 1 - AI)
     */
    private Integer strategy;

    /**
     * User Answer List (JSON)
     */
    private List<String> answers;

    /**
     * Result ID
     */
    private Long resultId;

    /**
     * Result name
     */
    private String resultName;

    /**
     * Result Detail
     */
    private String resultDetail;

    /**
     * Result Image URL
     */
    private String resultImageUrl;

    /**
     * Result Grade, Intended For Grade-Type Applications
     */
    private Integer resultGrade;

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
