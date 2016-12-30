package com.ackerframework.admin.center.sys.controller;

import com.ackerframework.base.entity.LoginUser;
import com.ackerframework.admin.center.sys.entity.UserRights;
import com.ackerframework.admin.center.sys.service.SysService;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ChosenController extends BaseController {

    @Autowired
    private SysService sysService;

    @RequestMapping(value = "/chosen", method = RequestMethod.GET)
    public ModelAndView init(ModelAndView MV) {
        MV.setViewName("chosen");
        LoginUser loginUser = GlobalUtils.getLoginUser();
        //初始化组织选择
        //根据登录用户查询当 所属组织
        List<UserRights> userRightses = sysService.getUserRightses(loginUser.getId());
        //未分配 权限
        if (userRightses.size() == 0) {
            MV.setViewName("redirect:/frame");
        }
        //如果只有一个，直接进入
        if (userRightses.size() == 1) {

            loginUser.setRoleId(userRightses.get(0).getRoleId());
            loginUser.setRoleCode(userRightses.get(0).getRoleCode());
            loginUser.setRoleName(userRightses.get(0).getRoleName());
            loginUser.setOrgId(userRightses.get(0).getOrgId());
            loginUser.setOrgCode(userRightses.get(0).getOrgCode());
            loginUser.setOrgName(userRightses.get(0).getOrgName());

            MV.setViewName("redirect:/frame");
        }
        MV.addObject("userRightses", userRightses);
        return MV;
    }

    @RequestMapping(value = "/redirectframe", method = RequestMethod.GET)
    public ModelAndView redirectFrame(ModelAndView MV,
                                      @RequestParam(value = "roleId") Integer roleId,
                                      @RequestParam(value = "orgId") Integer orgId) {
        LoginUser loginUser = GlobalUtils.getLoginUser();
        //..清空当前登录人的权限缓存

        //..赋值当前权限组织的信息
        List<UserRights> userRights = sysService.getUserRights(loginUser.getId(), roleId, orgId);
        if (userRights.size() == 1) {
            loginUser.setRoleId(userRights.get(0).getRoleId());
            loginUser.setRoleCode(userRights.get(0).getRoleCode());
            loginUser.setRoleName(userRights.get(0).getRoleName());
            loginUser.setOrgId(userRights.get(0).getOrgId());
            loginUser.setOrgCode(userRights.get(0).getOrgCode());
            loginUser.setOrgName(userRights.get(0).getOrgName());
            MV.setViewName("redirect:/frame");
            return MV;
        }
        //..到异常界面
        MV.setViewName(GlobalUtils.getConfig("loginTemplet"));
        return MV;
    }
}
