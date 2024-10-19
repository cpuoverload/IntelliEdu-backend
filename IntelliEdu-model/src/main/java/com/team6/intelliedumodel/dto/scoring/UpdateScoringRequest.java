package com.team6.intelliedumodel.dto.scoring;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateScoringRequest implements Serializable {
    private static final long serialVersionUID = -5956785089437906956L;

    /**
     * ID
     */
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
     */
    private List<String> resultAttributes;
}
