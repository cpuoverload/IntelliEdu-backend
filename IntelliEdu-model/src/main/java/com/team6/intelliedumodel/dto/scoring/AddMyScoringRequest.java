package com.team6.intelliedumodel.dto.scoring;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddMyScoringRequest implements Serializable {
    private static final long serialVersionUID = -587351637253379511L;

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
}
