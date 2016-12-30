package com.ackerframework.admin.center.sys.controller;

import com.ackerframework.base.entity.LoginUser;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.utils.GlobalUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController extends BaseController {

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(ModelAndView MV) {
        LoginUser loginUser = GlobalUtils.getLoginUser();
        // 如果已经登录，则跳转到管理首页
        if (loginUser != null) {
            MV.setViewName("redirect:" + GlobalUtils.getConfig("frameTemplet"));
            return MV;
        }
        return new ModelAndView(GlobalUtils.getConfig("loginTemplet"));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginFail(HttpServletRequest request, ModelAndView MV) {

        LoginUser loginUser = GlobalUtils.getLoginUser();

        // 如果已经登录，则跳转到管理首页
        if (loginUser != null) {
            MV.setViewName("redirect:" + GlobalUtils.getConfig("frameTemplet"));
            return MV;
        } else {
            MV.setViewName(GlobalUtils.getConfig("loginTemplet"));
            String message = (String) request.getAttribute("message");
            MV.addObject("userName", request.getParameter("username"));
            MV.addObject("errorMessage", message);
            return MV;
        }
    }
}
