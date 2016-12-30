package com.ackerframework.admin.center.rights.controller;

import com.ackerframework.admin.center.rights.entity.MenuTreeGrid;
import com.ackerframework.admin.center.rights.entity.Menu;
import com.ackerframework.admin.center.rights.params.MenuParam;
import com.ackerframework.admin.center.rights.service.MenuService;
import com.ackerframework.admin.center.sys.controller.ViewBaseController;
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
@RequestMapping(value = "/admin/center/rights/menu")
public class MenuController extends ViewBaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = Constant.FORMLIST)
    public ModelAndView formlist(ModelAndView MV) {
        MV.setViewName("rights/menulist");
        return MV;
    }

    @RequestMapping(value = Constant.FORMITEM)
    public ModelAndView formitem(ModelAndView MV,
                                 @RequestParam(value = Constant.VIEWSTATE) String viewState,
                                 @RequestParam(value = Constant.ID, required = false) Integer id) {
        MV.setViewName("rights/menuitem");
        Menu menu = null;
        switch (viewState) {
            case Constant.LOOK:
            case Constant.UPDATE:
                menu = menuService.get(id);
                break;
            case Constant.INSERT:
                menu = new Menu();
                menu.setCanUse(true);
                break;
        }
        MV.addObject("comboCanUse", initCombo("yes_no"));
        MV.addObject("comboNavType", initCombo("nav_type"));
        MV.addObject(Constant.DETAIL, menu);
        return MV;
    }

    @RequiresAuthentication
    @RequestMapping(value = Constant.GRIDLIST, method = RequestMethod.GET)
    public Result gridList(HttpServletRequest request) {
        EasyPage easyPage = new EasyPage();
        List<MenuTreeGrid> navigators = menuService.getGridList(new MenuParam(request));
        Integer count = menuService.listCount(new MenuParam(request));
        easyPage.setTotal(count);
        easyPage.setRows(navigators);
        return new Result(easyPage);
    }

    @ResponseBody
    @RequestMapping(value = Constant.GET, method = RequestMethod.GET)
    public Result get(@RequestParam(value = Constant.ID) Integer id) {
        return new Result(menuService.get(id));
    }

    @ResponseBody
    @RequestMapping(value = Constant.INSERT, method = RequestMethod.POST)
    //   @RequiresPermissions("sys:user:insert")
    public Result insert(@RequestBody Menu menu) {
        return menuService.insert(menu);
    }

    @ResponseBody
    @RequestMapping(value = Constant.UPDATE, method = RequestMethod.POST)
    public Result update(@RequestBody Menu menu) {
        return menuService.update(menu);
    }

    @ResponseBody
    @RequestMapping(value = Constant.DELETE, method = RequestMethod.GET)
    public Result delete(@RequestParam(value = Constant.ID) Integer id) {
        return menuService.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/authingnav", method = RequestMethod.GET)
    public Result authingNav(@RequestParam(value = "id", defaultValue = "0") Integer pid,
                             @RequestParam(value = "roleId") Integer roleId) {
        return menuService.authingNav(pid, roleId);
    }

    @ResponseBody
    @RequestMapping(value = "/authingbutton", method = RequestMethod.GET)
    public Result authingButton(@RequestParam(value = "id", defaultValue = "0") Integer pid,
                                @RequestParam(value = "roleId") Integer roleId) {
        EasyPage easyPage = new EasyPage();
        easyPage.setRows(menuService.authingButton(pid, roleId));
        return new Result(easyPage);
    }

    @ResponseBody
    @RequestMapping(value = "/initparent", method = RequestMethod.GET)
    public Result initComboParentTree(@RequestParam(value = "id") Integer id) {
        return new Result(menuService.initComboParentTree(id));
    }

}
