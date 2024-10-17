package com.team6.intelliedumodel.dto.answerrecord;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddMyAnswerRequest implements Serializable {
    private static final long serialVersionUID = 3363245248748215134L;

    /**
     * Application ID
     */
    private Long appId;

    /**
     * User Answer List (JSON)
     */
    private Object answers;
}