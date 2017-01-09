package com.ackerframework.admin.filem.service;

import com.ackerframework.admin.filem.dao.DirectorDao;
import com.ackerframework.admin.filem.entity.Director;
import com.ackerframework.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class DirectorService extends BaseService<DirectorDao, Director> {
    @Autowired
    private DirectorDao directorDao;
}
