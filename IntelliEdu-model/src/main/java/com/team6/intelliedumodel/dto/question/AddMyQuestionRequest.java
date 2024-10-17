package com.team6.intelliedumodel.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddMyQuestionRequest implements Serializable {
    /**
     * Question List (JSON)
     */
    private List<QuestionContent> questions;

    /**
     * Application ID
     */
    private Long appId;

    private static final long serialVersionUID = -6287971457354284030L;
}
