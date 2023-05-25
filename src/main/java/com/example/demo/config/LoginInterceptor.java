package com.example.demo.config;

import com.example.demo.common.AppVariable;
import com.example.demo.entity.Userinfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * true -> 用户已登录
     * false -> 用户未登录
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(AppVariable.USER_SESSION_KEY) != null) {
            // 用户已登陆
            System.out.println("当前登录用户为：" +
                    ((Userinfo) session.getAttribute(AppVariable.USER_SESSION_KEY)).getUsername());
            return true;
        }
        // 调整到登录页面
        response.sendRedirect("/login.html");
        return false;
    }
}
