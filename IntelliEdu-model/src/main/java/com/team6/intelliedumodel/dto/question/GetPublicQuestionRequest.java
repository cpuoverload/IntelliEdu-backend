package com.team6.intelliedumodel.dto.question;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetPublicQuestionRequest implements Serializable {
    /**
     * Application ID
     */
    private Long appId;

    private static final long serialVersionUID = 7906514929475469665L;
}
