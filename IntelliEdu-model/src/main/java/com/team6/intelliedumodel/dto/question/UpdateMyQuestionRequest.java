package com.team6.intelliedumodel.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateMyQuestionRequest implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * Question List (JSON)
     */
    private List<QuestionContent> questions;

    private static final long serialVersionUID = -8662446364556125167L;
}
