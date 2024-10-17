package com.team6.intelliedumodel.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Scoring
 * @TableName scoring
 */
@TableName(value ="scoring", autoResultMap = true)
@Data
public class Scoring implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Application ID
     */
    private Long appId;

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
     * Score Threshold For This Result, Intended For Grade-Type Applications
     */
    private Integer resultThreshold;

    /**
     * Result Attribute Array (JSON), Intended For Evaluation-Type Applications
     * <a href="https://baomidou.com/guides/type-handler/">JSON 字符串与 Java 对象相互转换</a>
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> resultAttributes;

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

    /**
     * Is Deleted
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}