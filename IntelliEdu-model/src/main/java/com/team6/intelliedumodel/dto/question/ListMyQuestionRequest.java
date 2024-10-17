package com.team6.intelliedumodel.dto.question;

import com.team6.intellieducommon.utils.TableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListMyQuestionRequest extends TableRequest implements Serializable {
    /**
     * Application ID
     */
    private Long appId;

    private static final long serialVersionUID = 5900675948817710128L;
}
