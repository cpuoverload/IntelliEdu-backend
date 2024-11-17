package me.intelliedu.intelliedumodel.dto.scoring;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateMyScoringRequest implements Serializable {
    private static final long serialVersionUID = -3520641049172938943L;

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