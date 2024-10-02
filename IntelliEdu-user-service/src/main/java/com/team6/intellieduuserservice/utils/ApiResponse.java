package com.team6.intellieduuserservice.utils;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private int code;
    private T data;
    private String message;

    public ApiResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(0, data, "success");
    }

    public static <T> ApiResponse<T> fail(int code, String message) {
        return new ApiResponse<>(code, null, message);
    }

    public static <T> ApiResponse<T> fail(Err error) {
        return new ApiResponse<>(error.getCode(), null, error.getMessage());
    }
}
