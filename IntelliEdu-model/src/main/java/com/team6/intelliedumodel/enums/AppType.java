package com.team6.intelliedumodel.enums;

import lombok.Getter;

@Getter
public enum AppType {
    GRADE(0, "Grade"),
    EVALUATION(1, "Evaluation");

    private final int code;
    private final String description;

    AppType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static AppType fromCode(int code) {
        for (AppType appType : AppType.values()) {
            if (appType.code == code) {
                return appType;
            }
        }
        return null;
    }
}
