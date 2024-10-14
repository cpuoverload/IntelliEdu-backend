package com.team6.intelliedumodel.dto.application;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddMyAppRequest implements Serializable {
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

    private static final long serialVersionUID = 1L;
}
