package com.team6.intelliedumodel.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateMyQuestionRequest implements Serializable {
    /**
     * Question List (JSON)
     */
    private List<QuestionContent> questions;

    /**
     * Application ID
     */
    private Long appId;

    private static final long serialVersionUID = -8662446364556125167L;
}
