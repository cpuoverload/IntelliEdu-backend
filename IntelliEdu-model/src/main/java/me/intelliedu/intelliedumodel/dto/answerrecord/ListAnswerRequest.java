package me.intelliedu.intelliedumodel.dto.answerrecord;

import me.intelliedu.intellieducommon.utils.TableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListAnswerRequest extends TableRequest implements Serializable {
    private static final long serialVersionUID = -3846615005554985063L;

    /**
     * ID
     */
    private Long id;

    /**
     * Creator User ID
     */
    private Long userId;

    /**
     * Application ID
     */
    private Long appId;

    /**
     * Application Type (0 - Grade, 1 - Evaluation)
     */
    private Integer appType;

    /**
     * Scoring Strategy (0 - Custom, 1 - AI)
     */
    private Integer strategy;

    /**
     * Result ID
     */
    private Long resultId;

    /**
     * Result Grade, Intended For Grade-Type Applications
     */
    private Integer resultGrade;

}
