package com.team6.intellieduuserservice.utils;

import lombok.Getter;

@Getter
public enum Err {

    PARAMS_ERROR(10001, "请求参数错误"),
    UNLOGIN_ERROR(10002, "未登录"),
    FORBIDDEN_ERROR(10003, "无权限"),
    SYSTEM_ERROR(10004, "系统异常"),
    DUPLICATED_USERNAME_ERROR(10005, "用户名重复"),
    USER_NOT_FOUND(10006, "用户不存在"),
    PASSWORD_ERROR(10007, "密码错误"),
    DELETE_ERROR(10008, "删除失败"),
    UPDATE_ERROR(10009, "更新失败");

    private final int code;
    private final String message;

    Err(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
