package com.ackerframework.admin.center.rights.controller;

import com.ackerframework.admin.center.rights.entity.EgridUsers;
import com.ackerframework.admin.center.rights.entity.User;
import com.ackerframework.admin.center.rights.params.UserParam;
import com.ackerframework.admin.center.rights.service.UserService;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.base.entity.EasyPage;
import com.ackerframework.base.entity.Result;
import com.ackerframework.utils.Constant;
import com.ackerframework.utils.GlobalUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/center/rights/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    private static final String VIEW_LIST_CODE = "user_list";
    private static final String VIEW_ITEM_CODE = "user_item";

    @RequestMapping(value = Constant.FORMLIST)
    public ModelAndView formlist() {
        ModelAndView MV = new ModelAndView("/rights/userlist");
        return MV;
    }

    @RequestMapping(value = Constant.FORMITEM)
    public ModelAndView formitem(@RequestParam(value = Constant.VIEWSTATE) String viewState,
                                 @RequestParam(value = Constant.ID, required = false) Integer id) {
        ModelAndView MV = new ModelAndView("/rights/useritem");
        User user = null;
        switch (viewState) {
            case Constant.LOOK:
            case Constant.UPDATE:
                user = userService.get(id);
                break;
            case Constant.INSERT:
                user = new User();
                break;
        }
        MV.addObject("detail", user);
        return MV;
    }

    @RequiresAuthentication
    @RequestMapping(value = Constant.GRIDLIST, method = RequestMethod.GET)
    public Result gridList(HttpServletRequest request) {
        EasyPage easyPage = new EasyPage();
        List<EgridUsers> users = userService.getGridList(new UserParam(request));
        Integer count = userService.listCount(new UserParam(request));
        easyPage.setTotal(count);
        easyPage.setRows(users);
        return new Result(easyPage);
    }

    @ResponseBody
    @RequestMapping(value = Constant.GET, method = RequestMethod.GET)
    public Result get(@RequestParam(value = Constant.ID) Integer id) {
        return new Result(userService.get(id), "查询成功");
    }

    @ResponseBody
    @RequestMapping(value = Constant.INSERT, method = RequestMethod.POST)
    //   @RequiresPermissions("sys:user:insert")
    public Result insert(@RequestBody User user) {
//        String enPassWord = new Md5Hash(GlobalUtils.getConfig("user.default.pwd"), Constant.pwd_salt).toString();
//        user.setPassWord(enPassWord);
        return userService.insert(user);
    }

    @ResponseBody
    @RequestMapping(value = Constant.UPDATE, method = RequestMethod.POST)
    public Result update(@RequestBody User user) {
        return userService.update(user);
    }

    @ResponseBody
    @RequestMapping(value = Constant.DELETE, method = RequestMethod.GET)
    public Result delete(@RequestParam(value = Constant.ID) Integer id) {
        return userService.delete(id);
    }
}
