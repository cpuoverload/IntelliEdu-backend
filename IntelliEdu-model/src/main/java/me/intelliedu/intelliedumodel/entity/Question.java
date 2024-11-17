package me.intelliedu.intelliedumodel.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import me.intelliedu.intelliedumodel.dto.question.QuestionContent;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Question
 * @TableName question
 */
@TableName(value ="question", autoResultMap = true)
@Data
public class Question implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Question List (JSON)
     * <a href="https://baomidou.com/guides/type-handler/">JSON 字符串与 Java 对象相互转换</a>
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
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

    /**
     * Is Deleted
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}