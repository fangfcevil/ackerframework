package com.ackerframework.admin.center.rights.controller;

import com.ackerframework.admin.center.rights.entity.OrgConfRightsGrid;
import com.ackerframework.admin.center.rights.entity.Rights;
import com.ackerframework.admin.center.rights.entity.RightsGrid;
import com.ackerframework.admin.center.rights.params.RightsParam;
import com.ackerframework.admin.center.rights.service.OrgService;
import com.ackerframework.admin.center.rights.service.RightsServive;
import com.ackerframework.admin.center.sys.controller.ViewBaseController;
import com.ackerframework.base.entity.EasyPage;
import com.ackerframework.base.entity.Result;
import com.ackerframework.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/center/rights/rightsconf")
public class RightsController extends ViewBaseController {
    @Autowired
    private OrgService orgService;

    @Autowired
    private RightsServive rightsServive;


    @RequestMapping(value = Constant.FORMLIST)
    public ModelAndView formlist(ModelAndView MV) {
        MV.setViewName("/rights/rightsconf");
        return MV;
    }

    @ResponseBody
    @RequestMapping(value = "orggridlist", method = RequestMethod.GET)
    public Result orgConfRightsGridList(HttpServletRequest request) {
        EasyPage easyPage = new EasyPage();
        List<OrgConfRightsGrid> orgs = orgService.getConfRightsGridList();
        easyPage.setRows(orgs);
        return new Result(easyPage);
    }

    @ResponseBody
    @RequestMapping(value = "usergridlist", method = RequestMethod.GET)
    public Result getUserGridList(HttpServletRequest request) {
        EasyPage easyPage = new EasyPage();
        List<RightsGrid> rightses = rightsServive.getGridList(new RightsParam(request));
        easyPage.setRows(rightses);
        return new Result(easyPage);
    }

    @ResponseBody
    @RequestMapping(value = Constant.DELETE, method = RequestMethod.GET)
    public Result delete(@RequestParam(value = "orgId") Integer orgId, @RequestParam(value = "userId") Integer userId,
                         @RequestParam(value = "roleId", required = false) Integer roleId) {
        return rightsServive.deleteRights(orgId, userId, roleId);
    }

}


