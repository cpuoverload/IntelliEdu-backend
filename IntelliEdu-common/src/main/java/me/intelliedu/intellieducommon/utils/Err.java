package me.intelliedu.intellieducommon.utils;

import lombok.Getter;

@Getter
public enum Err {

    PARAMS_ERROR(10001, "Request parameter error"),
    UNLOGIN_ERROR(10002, "Not logged in"),
    FORBIDDEN_ERROR(10003, "No permission"),
    SYSTEM_ERROR(10004, "System error"),
    DUPLICATED_USERNAME_ERROR(10005, "Duplicate username"),
    USER_NOT_FOUND(10006, "User not found"),
    PASSWORD_ERROR(10007, "Password error"),
    DELETE_ERROR(10008, "Delete failed"),
    UPDATE_ERROR(10009, "Update failed"),
    NOT_FOUND_ERROR(10010, "Not found"),
    EXISTED_ERROR(10011, "Already existed");

    private final int code;
    private final String message;

    Err(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
