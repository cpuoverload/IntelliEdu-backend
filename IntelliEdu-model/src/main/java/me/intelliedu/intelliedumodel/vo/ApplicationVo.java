package me.intelliedu.intelliedumodel.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApplicationVo implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * Application Name
     */
    private String appName;

    /**
     * Application Description
     */
    private String description;

    /**
     * Application Image URL
     */
    private String imageUrl;

    /**
     * Application Type (0 - Grade, 1 - Evaluation)
     */
    private Integer type;

    /**
     * Scoring Strategy (0 - Custom, 1 - AI)
     */
    private Integer strategy;

    /**
     * Creator User ID
     */
    private Long userId;

    /**
     * Audit Status: 0 - Pending, 1 - Approved, 2 - Rejected
     */
    private Integer auditStatus;

    /**
     * Auditor User ID
     */
    private Long auditorId;

    /**
     * Audit Message
     */
    private String auditMessage;

    /**
     * Audit Time
     */
    private Date auditTime;

    /**
     * Creation Time
     */
    private Date createTime;

    /**
     * Update Time
     */
    private Date updateTime;

    private UserVo userVo;

    private static final long serialVersionUID = 1L;
}
