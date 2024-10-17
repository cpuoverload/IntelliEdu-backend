package com.team6.intelliedumodel.dto.scoring;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddScoringRequest implements Serializable {
    private static final long serialVersionUID = 6504624271615045534L;

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
    private List<String> attributes;

    /**
     * Score Threshold For This Result, Intended For Grading-Type Applications
     */
    private Integer threshold;

    /**
     * Application ID
     */
    private Long appId;
}
