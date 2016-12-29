package com.ackerframework.admin.center.rights.controller;

import com.ackerframework.admin.center.rights.entity.Org;
import com.ackerframework.admin.center.rights.service.OrgService;
import com.ackerframework.admin.center.sys.controller.ViewBaseController;
import com.ackerframework.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView formitem(ModelAndView MV,
                                 @RequestParam(value = Constant.VIEWSTATE) String viewState,
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
                break;
        }
        MV.addObject("comboIsUse", initCombo("is_use"));
        MV.addObject("detail", org);
        return MV;
    }
}
