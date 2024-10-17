package com.team6.intelliedumodel.enums;

import lombok.Getter;

@Getter
public enum AuditStatus {
    PENDING(0, "Pending"),
    APPROVED(1, "Approved"),
    REJECTED(2, "Rejected");

    private final int code;
    private final String description;

    AuditStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public static AuditStatus fromCode(int code) {
        for (AuditStatus auditStatus : AuditStatus.values()) {
            if (auditStatus.code == code) {
                return auditStatus;
            }
        }
        return null;
    }
}
