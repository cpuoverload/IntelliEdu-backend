package com.team6.intellieducommon.utils;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private int code;
    private T data;
    private String message;

    // Feign 在处理序列化与反序列化时，会使用 Jackson 来处理 JSON 数据
    // Jackson 会尝试通过默认构造器（无参构造器）来创建 ApiResponse 的实例，若不添加，会报错
    public ApiResponse() {
    }

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
