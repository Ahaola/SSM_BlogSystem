package com.example.demo.controller;

import com.example.demo.common.AjaxResult;
import com.example.demo.common.AppVariable;
import com.example.demo.common.PasswordUtils;
import com.example.demo.common.UserSessionUtils;
import com.example.demo.entity.Userinfo;
import com.example.demo.entity.vo.UserinfoVO;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/reg")
    public AjaxResult reg(Userinfo userinfo) {
        // 非空效验和参数有效性效验
        if (userinfo == null || !StringUtils.hasLength(userinfo.getUsername()) ||
                !StringUtils.hasLength(userinfo.getPassword())) {
            return AjaxResult.fail(-1, "非法参数");
        }
        // 密码加盐处理
        userinfo.setPassword(PasswordUtils.encrypt(userinfo.getPassword()));
        return AjaxResult.success(userService.reg(userinfo));
    }

    @RequestMapping("/login")
    public AjaxResult login(HttpServletRequest request, String username, String password) {
        // 1.非空效验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return AjaxResult.fail(-1, "非法请求");
        }
        // 2.查询数据库
        Userinfo userinfo = userService.getUserByName(username);
        if (userinfo != null && userinfo.getId() > 0) {   // 有效的用户
            // 两个密码是否相同
            if (PasswordUtils.check(password, userinfo.getPassword())) {
                // 登录成功
                // 将用户存储到 session
                HttpSession session = request.getSession();
                session.setAttribute(AppVariable.USER_SESSION_KEY, userinfo);
                userinfo.setPassword(""); // 返回前端之前，隐藏敏感（密码）信息
                return AjaxResult.success(userinfo);
            }
        }
        return AjaxResult.success(0, null);
    }

    @RequestMapping("/showinfo")
    public AjaxResult showInfo(HttpServletRequest request) {
        UserinfoVO userinfoVO = new UserinfoVO();
        // 1.得到当前登录用户（从 session 中获取）
        Userinfo userinfo = UserSessionUtils.getUser(request);
        if (userinfo == null) {
            return AjaxResult.fail(-1, "非法请求");
        }
        // Spring 提供的深克隆方法
        BeanUtils.copyProperties(userinfo, userinfoVO);
        // 2.得到用户发表文章的总数
        userinfoVO.setArtCount(articleService.getArtCountByUid(userinfo.getId()));
        return AjaxResult.success(userinfoVO);
    }

    /**
     * 注销（退出登录）
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public AjaxResult logout(HttpSession session) {
        session.removeAttribute(AppVariable.USER_SESSION_KEY);
        return AjaxResult.success(1);
    }

    @RequestMapping("/getuserbyid")
    public AjaxResult getUserById(Integer id) {
        if (id == null || id <= 0) {
            // 无效参数
            return AjaxResult.fail(-1, "非法参数");
        }
        Userinfo userinfo = userService.getUserById(id);
        if (userinfo == null || userinfo.getId() <= 0) {
            // 无效参数
            return AjaxResult.fail(-1, "非法参数");
        }
        // 去除 userinfo 中的敏感数据，ex：密码
        userinfo.setPassword("");
        UserinfoVO userinfoVO = new UserinfoVO();
        BeanUtils.copyProperties(userinfo, userinfoVO);
        // 查询当前用户发表的文章数
        userinfoVO.setArtCount(articleService.getArtCountByUid(id));
        return AjaxResult.success(userinfoVO);
    }

}
