package com.ackerframework.admin.filem.controller;

import com.ackerframework.admin.filem.service.DirectorService;
import com.ackerframework.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

public class DirectorController extends BaseController {
    @Autowired
    private DirectorService directorService;
}
