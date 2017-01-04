package com.ackerframework.admin.center.rights.controller;

import com.ackerframework.admin.center.rights.entity.Org;
import com.ackerframework.admin.center.rights.entity.OrgTreeGrid;
import com.ackerframework.admin.center.rights.params.OrgParam;
import com.ackerframework.admin.center.rights.service.OrgService;
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
@RequestMapping(value = "/admin/center/rights/org")
public class OrgController extends ViewBaseController {
    @Autowired
    private OrgService orgService;

    @RequestMapping(value = Constant.FORMLIST)
    public ModelAndView formlist(ModelAndView MV) {
        MV.setViewName("/rights/orglist");
        return MV;
    }

    @RequestMapping(value = Constant.FORMITEM)
    public ModelAndView formitem(ModelAndView MV, @RequestParam(value = Constant.VIEWSTATE) String viewState,
                                 @RequestParam(value = Constant.ID, required = false) Integer id) {
        MV.setViewName("/rights/orgitem");
        Org org = null;
        switch (viewState) {
            case Constant.LOOK:
            case Constant.UPDATE:
                org = orgService.get(id);
                break;
            case Constant.INSERT:
                org = new Org();
                org.setCanUse(true);
                break;
        }
        MV.addObject("comboCanUse", initCombo("can_use"));
        MV.addObject("comboOrgType", initCombo("org_type"));
        MV.addObject("detail", org);
        return MV;
    }

    @RequestMapping(value = Constant.GRIDLIST, method = RequestMethod.GET)
    public Result gridList(HttpServletRequest request) {
        EasyPage easyPage = new EasyPage();
        List<OrgTreeGrid> orgs = orgService.getGridList(new OrgParam(request));
        Integer count = orgService.listCount(new OrgParam(request));
        easyPage.setTotal(count);
        easyPage.setRows(orgs);
        return new Result(easyPage);
    }

    @ResponseBody
    @RequestMapping(value = Constant.INSERT, method = RequestMethod.POST)
    public Result insert(@RequestBody Org org) {
        return orgService.insert(org);
    }

    @ResponseBody
    @RequestMapping(value = Constant.UPDATE, method = RequestMethod.POST)
    public Result update(@RequestBody Org org) {
        return orgService.update(org);
    }

    @ResponseBody
    @RequestMapping(value = Constant.DELETE, method = RequestMethod.GET)
    public Result delete(@RequestParam(value = Constant.ID) Integer id) {
        return orgService.delete(id);
    }


    @ResponseBody
    @RequestMapping(value = "/initparent", method = RequestMethod.GET)
    public Result initComboParentTree(@RequestParam(value = "id") Integer id) {
        return new Result(orgService.initComboParentTree(id));
    }


}
