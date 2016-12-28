package com.ackerframework.admin.center.base.controller;

import com.ackerframework.admin.center.base.service.BaseDataService;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.base.entity.Result;
import com.ackerframework.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/center/base/basedata")
public class BaseDataController extends BaseController {
    @Autowired
    protected BaseDataService baseDataService;

    @ResponseBody
    @RequestMapping(value = "/combo/{classCode}", method = RequestMethod.GET)
    public Result initCombo(@PathVariable String classCode) {
        return baseDataService.initCombo(classCode);
    }
}
