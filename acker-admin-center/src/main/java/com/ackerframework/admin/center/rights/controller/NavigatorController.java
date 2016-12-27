package com.ackerframework.admin.center.rights.controller;

import com.ackerframework.admin.center.rights.entity.EgridNavigator;
import com.ackerframework.admin.center.rights.entity.EtreeNavigator;
import com.ackerframework.admin.center.rights.entity.Navigator;
import com.ackerframework.admin.center.rights.params.NavigatorParam;
import com.ackerframework.admin.center.rights.service.NavigatorService;
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
@RequestMapping(value = "/admin/center/rights/navigator")
public class NavigatorController extends BaseController {

    @Autowired
    private NavigatorService navigatorService;

    private static final String VIEW_LIST_CODE = "user_list";
    private static final String VIEW_ITEM_CODE = "user_item";

    @RequestMapping(value = Constant.FORMLIST)
    public ModelAndView formlist(ModelAndView MV) {
        MV.setViewName("/rights/navigatorlist");
        return MV;
    }

    @RequestMapping(value = Constant.FORMITEM)
    public ModelAndView formitem(ModelAndView MV,
                                 @RequestParam(value = Constant.VIEWSTATE) String viewState,
                                 @RequestParam(value = Constant.ID, required = false) Integer id) {
        MV.setViewName("/rights/navigatoritem");
        Navigator navigator = null;
        switch (viewState) {
            case Constant.LOOK:
            case Constant.UPDATE:
                navigator = navigatorService.get(id);
                break;
            case Constant.INSERT:
                navigator = new Navigator();
                break;
        }
        MV.addObject("detail", navigator);
        return MV;
    }

    @RequiresAuthentication
    @RequestMapping(value = Constant.GRIDLIST, method = RequestMethod.GET)
    public Result gridList(HttpServletRequest request) {
        EasyPage easyPage = new EasyPage();
        List<EgridNavigator> navigators = navigatorService.getGridList(new NavigatorParam(request));
        Integer count = navigatorService.listCount(new NavigatorParam(request));
        easyPage.setTotal(count);
        easyPage.setRows(navigators);
        return new Result(easyPage);
    }

    @ResponseBody
    @RequestMapping(value = Constant.GET, method = RequestMethod.GET)
    public Result get(@RequestParam(value = Constant.ID) Integer id) {
        return new Result(navigatorService.get(id));
    }

    @ResponseBody
    @RequestMapping(value = Constant.INSERT, method = RequestMethod.POST)
    //   @RequiresPermissions("sys:user:insert")
    public Result insert(@RequestBody Navigator navigator) {
        return navigatorService.insert(navigator);
    }

    @ResponseBody
    @RequestMapping(value = Constant.UPDATE, method = RequestMethod.POST)
    public Result update(@RequestBody Navigator navigator) {
        return navigatorService.update(navigator);
    }

    @ResponseBody
    @RequestMapping(value = Constant.DELETE, method = RequestMethod.GET)
    public Result delete(@RequestParam(value = Constant.ID) Integer id) {
        return navigatorService.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/authingnav", method = RequestMethod.GET)
    public Result authingNav(@RequestParam(value = "id", defaultValue = "0") Integer pid,
                             @RequestParam(value = "roleId") Integer roleId) {
        return navigatorService.authingNav(pid, roleId);
    }

    @ResponseBody
    @RequestMapping(value = "/authingbutton", method = RequestMethod.GET)
    public Result authingButton(@RequestParam(value = "id", defaultValue = "0") Integer pid,
                             @RequestParam(value = "roleId") Integer roleId) {
        EasyPage easyPage = new EasyPage();
        easyPage.setRows(navigatorService.authingButton(pid, roleId));
        return new Result(easyPage);
    }


}
