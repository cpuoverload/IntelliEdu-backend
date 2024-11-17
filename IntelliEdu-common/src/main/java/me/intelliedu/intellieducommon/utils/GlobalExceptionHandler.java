package me.intelliedu.intellieducommon.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;

@ConditionalOnClass(DispatcherServlet.class)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return ApiResponse.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ApiResponse.fail(Err.SYSTEM_ERROR);
    }
}
