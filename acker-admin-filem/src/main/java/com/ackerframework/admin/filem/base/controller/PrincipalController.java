package com.ackerframework.admin.filem.base.controller;

import com.ackerframework.admin.filem.base.service.DirectorService;
import com.ackerframework.admin.filem.base.service.PerformerService;
import com.ackerframework.admin.filem.base.service.PrincipalService;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.base.entity.APIResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@RestController
@RequestMapping(value = "/admin/base/princ")
public class PrincipalController extends BaseController {

    @Autowired
    private PrincipalService principalService;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private PerformerService performerService;

    //    获取首页展示的列表信息
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResult getMain() {
        JSONArray filems = principalService.getMainFilemList();
        Iterator<Object> filem = filems.iterator();
        while (filem.hasNext()) {
            JSONObject filemObj = (JSONObject) filem.next();
            filemObj.put("directors", directorService.getFilemDirectors(filemObj.getInt("id")));
            filemObj.put("performers", performerService.getFilemPerformer(filemObj.getInt("id")));
        }
        return new APIResult(filems);
    }
}
