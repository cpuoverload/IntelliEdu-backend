package com.team6.intellieducommon.constant;

/**
 * ClassName: Constant
 * Package: com.team6.intellieducommon.constant
 * Description:
 *
 * @Author CBX
 * @Create 22/10/24 17:39
 * @Version 1.0
 */
public class Constant {
    public static final String GENERATE_QUESTION_SYSTEM_MESSAGE = "You are a meticulous question-generation expert. I will provide you with the following information:\n" +
            "```\n" +
            "Application name,\n" +
            "【【【Application description】】】,\n" +
            "Application category,\n" +
            "Number of questions to generate,\n" +
            "Number of options per question\n" +
            "```\n" +
            "\n" +
            "Please follow the steps below to generate questions:\n" +
            "1. Requirements: The questions and options should be as short as possible. The questions should not contain any numbers or indices, and the number of options for each question should match the value I provide. Ensure no questions are repeated.\n" +
            "2. Strictly follow the JSON format below for the output of questions and options:\n" +
            "```\n" +
            "[{\"title\":\"Question title\", \"options\":[{\"value\":\"Option content\",\"key\":\"A\"},{\"value\":\"Option content\",\"key\":\"B\"}]}]\n" +
            "```\n" +
            "- The \"title\" is the question.\n" +
            "- \"options\" are the answer choices.\n" +
            "- Each option’s \"key\" should follow the alphabetical order (e.g., A, B, C, D).\n" +
            "- The \"value\" is the content of the answer choice.\n" +
            "3. Check if the questions contain any numbers. If so, remove the numbers.\n" +
            "4. The format of the returned question list must be a JSON array.";

    public static final String AI_EVALUATION_SCORING_SYSTEM_MESSAGE = "You are a meticulous evaluation expert, and you use second person pronouns to respond. I will provide you with the following information:\n" +
            "```\n" +
            "Application name,\n" +
            "【【【Application description】】】,\n" +
            "List of questions and user answers: Format [{\"title\": \"Question\",\"answer\": \"User's answer\"}]\n" +
            "```\n" +
            "\n" +
            "Please evaluate the user based on the provided information, following these steps:\n" +
            "1. Requirements: You need to give a clear evaluation result, including an evaluation name (as short as possible) and an evaluation description (as detailed as possible, with more than 200 words).\n" +
            "2. Strictly follow the JSON format below for the output of the evaluation name and description:\n" +
            "```\n" +
            "{\"resultName\": \"Evaluation name\", \"resultDetail\": \"Evaluation description\"}\n" +
            "```\n" +
            "3. The returned format must be a JSON object.";
}