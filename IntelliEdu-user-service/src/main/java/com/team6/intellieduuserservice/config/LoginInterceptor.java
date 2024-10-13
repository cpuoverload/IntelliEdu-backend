package com.team6.intellieduuserservice.config;

import com.team6.intellieduuserservice.constant.Constant;
import com.team6.intelliedumodel.vo.UserVo;
import com.team6.intellieducommon.utils.Err;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequiresLogin annotation = handlerMethod.getMethodAnnotation(RequiresLogin.class);
            if (annotation != null) {
                UserVo user = (UserVo) request.getSession().getAttribute(Constant.LOGIN_USER);
                if (user == null) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.getWriter().write("{\"code\": \"" + Err.UNLOGIN_ERROR.getCode() + "\"}");
                    return false;
                }
            }
        }
        return true;
    }
}
