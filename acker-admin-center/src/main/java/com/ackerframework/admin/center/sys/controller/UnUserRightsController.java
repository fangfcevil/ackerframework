package com.ackerframework.admin.center.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UnUserRightsController {
    @RequestMapping(value = "/unuserrights")
    public ModelAndView init(ModelAndView MV) {
        MV.setViewName("unuserrights");
        return MV;
    }
}
