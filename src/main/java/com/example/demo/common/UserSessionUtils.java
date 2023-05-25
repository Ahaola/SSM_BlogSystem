package com.example.demo.common;

import com.example.demo.entity.Userinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 当前登录用户相关的操作
 */
public class UserSessionUtils {

    /**
     * 得到当前的登录用户
     */
    public static Userinfo getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null &&
                session.getAttribute(AppVariable.USER_SESSION_KEY) != null) {
            // 说明用户已经正常登录
            return (Userinfo) session.getAttribute(AppVariable.USER_SESSION_KEY);
        }
        return null;
    }
}