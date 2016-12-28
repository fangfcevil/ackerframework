package com.ackerframework.admin.center.sys.controller;

import com.ackerframework.admin.center.base.entity.Combo;
import com.ackerframework.admin.center.base.service.BaseDataService;
import com.ackerframework.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class ViewBaseController extends BaseController {
    @Autowired
    public BaseDataService baseDataService;

    public List<Combo> initCombo(String navCode) {
        return baseDataService.initComboList(navCode);
    }
}
