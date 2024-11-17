package me.intelliedu.intelliedumodel.dto.question;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetMyQuestionRequest implements Serializable {
    /**
     * Application ID
     */
    private Long appId;

    private static final long serialVersionUID = 5900675948817710128L;
}
