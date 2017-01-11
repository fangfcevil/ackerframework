package com.ackerframework.admin.filem.base.controller;

import com.ackerframework.admin.filem.base.service.DirectorService;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.base.entity.APIResult;
import com.ackerframework.base.entity.Result;
import com.ackerframework.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/base/director")
public class DirectorController extends BaseController {
    @Autowired
    private DirectorService directorService;

    @ResponseBody
    @RequestMapping(value = Constant.GET, method = RequestMethod.GET)
    public APIResult get(@RequestParam(value = Constant.ID) Integer id) {
        return new APIResult(directorService.get(id));
    }
}
