package com.ackerframework.admin.filem.sys.controller;

import com.ackerframework.base.controller.BaseController;
import com.ackerframework.base.entity.APIResult;
import com.ackerframework.base.entity.LoginUser;
import com.ackerframework.core.security.UsernamePasswordToken;
import com.ackerframework.utils.GlobalUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public APIResult login(@RequestParam String username, @RequestParam String password,
                           @RequestParam(required = false, defaultValue = "false") boolean rememberMe) {

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            try {
                subject.login(token);

                return new APIResult(subject.getSession().getId());
            } catch (UnknownAccountException ex) {
                return new APIResult(1000, "账号错误");
            } catch (IncorrectCredentialsException ex) {
                return new APIResult(1001, "密码错误");
            } catch (LockedAccountException ex) {
                return new APIResult(1002, "账号已被锁定");
            } catch (AuthenticationException ex) {
                return new APIResult(1003, "您没有授权");
            }
        } else {
            return new APIResult(1004, "已登录");
        }
    }
}
