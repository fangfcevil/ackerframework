package com.ackerframework.admin.center.rights.controller;

import com.ackerframework.admin.center.rights.entity.EgridUsers;
import com.ackerframework.admin.center.rights.entity.Navigator;
import com.ackerframework.admin.center.rights.entity.User;
import com.ackerframework.admin.center.rights.params.UserParam;
import com.ackerframework.admin.center.rights.service.NavigatorService;
import com.ackerframework.admin.center.rights.service.UserService;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.base.entity.EasyPage;
import com.ackerframework.base.entity.Result;
import com.ackerframework.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/center/rights/navigator")
public class NavigatorController extends BaseController {

    @Autowired
    private NavigatorService navigatorService;

}
