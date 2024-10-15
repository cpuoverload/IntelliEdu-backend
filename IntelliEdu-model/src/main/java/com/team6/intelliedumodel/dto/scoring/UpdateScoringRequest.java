package com.team6.intelliedumodel.dto.scoring;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateScoringRequest implements Serializable {
    private static final long serialVersionUID = -5956785089437906956L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
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
}
