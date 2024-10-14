package com.team6.intelliedumodel.dto.application;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateRequest implements Serializable {
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

    private static final long serialVersionUID = 1L;
}
