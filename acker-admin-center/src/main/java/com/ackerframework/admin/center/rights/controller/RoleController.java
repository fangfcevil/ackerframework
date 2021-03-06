package com.ackerframework.admin.center.rights.controller;

import com.ackerframework.admin.center.rights.entity.RoleGrid;
import com.ackerframework.admin.center.rights.entity.Role;
import com.ackerframework.admin.center.rights.params.RoleParam;
import com.ackerframework.admin.center.rights.service.RoleService;
import com.ackerframework.admin.center.sys.controller.ViewBaseController;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.base.entity.EasyPage;
import com.ackerframework.base.entity.Result;
import com.ackerframework.utils.Constant;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/center/rights/role")
public class RoleController extends ViewBaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = Constant.FORMLIST)
    public ModelAndView formlist() {
        ModelAndView MV = new ModelAndView("/rights/rolelist");
        return MV;
    }

    @RequestMapping(value = Constant.FORMITEM)
    public ModelAndView formitem(@RequestParam(value = Constant.VIEWSTATE) String viewState,
                                 @RequestParam(value = Constant.ID, required = false) Integer id) {
        ModelAndView MV = new ModelAndView("/rights/roleitem");
        Role role = null;
        switch (viewState) {
            case Constant.LOOK:
            case Constant.UPDATE:
                role = roleService.get(id);
                break;
            case Constant.INSERT:
                role = new Role();
                break;
        }
        MV.addObject("comboCanUse", initCombo("can_use"));
        MV.addObject("comboYesNo", initCombo("yes_no"));
        MV.addObject(Constant.DETAIL, role);
        return MV;
    }

    @RequestMapping(value = "/auth")
    public ModelAndView authRole(ModelAndView MV,
                                 @RequestParam(value = Constant.ID) Integer id) {
        MV.setViewName("rights/roleauth");
        MV.addObject("role", roleService.get(id));
        return MV;
    }


    @RequiresAuthentication
    @RequestMapping(value = Constant.GRIDLIST, method = RequestMethod.GET)
    public Result gridList(HttpServletRequest request) {
        EasyPage easyPage = new EasyPage();
        List<RoleGrid> roles = roleService.getGridList(new RoleParam(request));
        Integer count = roleService.listCount(new RoleParam(request));
        easyPage.setTotal(count);
        easyPage.setRows(roles);
        return new Result(easyPage);
    }

    @ResponseBody
    @RequestMapping(value = Constant.GET, method = RequestMethod.GET)
    public Result get(@RequestParam(value = Constant.ID) Integer id) {
        return new Result(roleService.get(id));
    }

    @ResponseBody
    @RequestMapping(value = Constant.INSERT, method = RequestMethod.POST)
    //   @RequiresPermissions("sys:user:insert")
    public Result insert(@RequestBody Role user) {
        return roleService.insert(user);
    }

    @ResponseBody
    @RequestMapping(value = Constant.UPDATE, method = RequestMethod.POST)
    public Result update(@RequestBody Role user) {
        return roleService.update(user);
    }

    @ResponseBody
    @RequestMapping(value = Constant.DELETE, method = RequestMethod.GET)
    public Result delete(@RequestParam(value = Constant.ID) Integer id) {
        return roleService.delete(id);
    }

}
