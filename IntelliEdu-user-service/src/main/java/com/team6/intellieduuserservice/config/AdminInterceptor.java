package com.team6.intellieduuserservice.config;

import com.team6.intellieduuserservice.constant.Constant;
import com.team6.intellieduuserservice.model.vo.UserVo;
import com.team6.intellieduuserservice.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequiresAdmin annotation = handlerMethod.getMethodAnnotation(RequiresAdmin.class);
            if (annotation != null) {
                UserVo user = (UserVo) request.getSession().getAttribute(Constant.LOGIN_USER);
                if (user == null) {
                    response.sendRedirect("/login"); // todo 修改重定向页面 path
                    return false;
                }
                UserVo userVo = userService.getMyInfo(request);
                if (!userVo.getRole().equals("admin")) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.getWriter().write("{\"error\": \"Forbidden\"}");
                    return false;
                }
                return true;
            }
        }
        return true;
    }
}

