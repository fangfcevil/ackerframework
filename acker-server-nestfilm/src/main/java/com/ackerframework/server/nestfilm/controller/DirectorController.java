package com.ackerframework.server.nestfilm.controller;

import com.ackerframework.base.controller.BaseController;
import com.ackerframework.base.entity.APIResult;
import com.ackerframework.server.nestfilm.entity.Director;
import com.ackerframework.server.nestfilm.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DirectorController extends BaseController {
    @Autowired
    private DirectorService directorService;

    @ResponseBody
    @RequestMapping(value = "/director/{id}", method = RequestMethod.GET)
    public APIResult get(@PathVariable("id") Integer id) {
        Director director = directorService.get(id);
        if (director == null) {
            return new APIResult(4000, "数据不存在");
        }
        return new APIResult(director);
    }

    @ResponseBody
    @RequestMapping(value = "/director", method = RequestMethod.POST)
    public APIResult insert(Director director) {
        return new APIResult(directorService.insert(director));
    }

    @ResponseBody
    @RequestMapping(value = "/director/list", method = RequestMethod.GET)
    public APIResult getList(@RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize) {
        return new APIResult();

    }
}
