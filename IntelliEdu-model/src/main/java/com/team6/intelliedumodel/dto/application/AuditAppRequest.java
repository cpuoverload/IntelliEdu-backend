package com.team6.intelliedumodel.dto.application;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuditAppRequest implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * Audit Status: 0 - Pending, 1 - Approved, 2 - Rejected
     */
    private Integer auditStatus;

    /**
     * Audit Message
     */
    private String auditMessage;

    private static final long serialVersionUID = 1L;
}