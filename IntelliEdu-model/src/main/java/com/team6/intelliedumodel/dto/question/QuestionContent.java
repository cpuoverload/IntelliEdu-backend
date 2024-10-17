package com.team6.intelliedumodel.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionContent {
    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目选项列表
     */
    private List<Option> options;

    /**
     * 题目选项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Option {
        /**
         * 选项的字母（A、B、C、D）
         */
        private String key;

        /**
         * 选项内容
         */
        private String value;

        /**
         * 选项对应分值（打分类应用使用）
         */
        private int grade;

        /**
         * 选项对应的测评结果（测评类应用使用）
         */
        private String evaluation;
    }
}