package me.intelliedu.intelliedumodel.enums;

import lombok.Getter;

@Getter
public enum ScoringStrategy {
    CUSTOM(0, "Custom"),
    AI(1, "AI");

    private final int code;
    private final String description;

    ScoringStrategy(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ScoringStrategy fromCode(int code) {
        for (ScoringStrategy strategy : ScoringStrategy.values()) {
            if (strategy.code == code) {
                return strategy;
            }
        }
        return null;
    }
}
