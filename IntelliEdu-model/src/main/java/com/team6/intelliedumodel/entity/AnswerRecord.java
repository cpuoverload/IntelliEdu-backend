package com.team6.intelliedumodel.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Answer Record
 * @TableName answer_record
 */
@TableName(value ="answer_record", autoResultMap = true)
@Data
public class AnswerRecord implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
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
     * <a href="https://baomidou.com/guides/type-handler/">JSON 字符串与 Java 对象相互转换</a>
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
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

    /**
     * Is Deleted
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}