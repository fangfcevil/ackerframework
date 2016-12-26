package com.ackerframework.admin.center.base.controller;

import com.ackerframework.base.controller.BaseController;
import com.ackerframework.utils.Constant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/admin/center/base/home")
public class HomeController extends BaseController {

    @RequestMapping(value = Constant.FORMLIST, method = RequestMethod.GET)
    public ModelAndView init(ModelAndView MV) {
        MV.setViewName("base/home");
        return MV;
    }
}
