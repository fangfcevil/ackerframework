package com.ackerframework.admin.center.sys.controller;

import com.ackerframework.admin.center.rights.entity.Navigator;
import com.ackerframework.base.entity.LoginUser;
import com.ackerframework.admin.center.sys.service.SysService;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class FrameController extends BaseController {

    @Autowired
    private SysService sysService;

    @RequestMapping(value = "/frame", method = RequestMethod.GET)
    public ModelAndView init(ModelAndView MV) {
        MV.setViewName(GlobalUtils.getConfig("frameTemplet"));
        LoginUser loginUser = GlobalUtils.getLoginUser();
        if (loginUser == null) {
            MV.setViewName(GlobalUtils.getConfig("loginTemplet"));
            return MV;
        }
        MV.addObject("loginUser", loginUser);
        return MV;
    }

    @ResponseBody
    @RequestMapping(value = "/frame/navs", method = RequestMethod.GET)
    public List<Navigator> getRightsNavigators(@RequestParam(value = "id", defaultValue = "0") Integer pid) {
        LoginUser loginUser = GlobalUtils.getLoginUser();
        return sysService.getRightsNavigators(loginUser.getId(), loginUser.getRoleId(), pid);
    }

}
