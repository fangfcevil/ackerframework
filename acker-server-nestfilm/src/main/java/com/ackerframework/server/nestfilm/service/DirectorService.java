package com.ackerframework.server.nestfilm.service;

import com.ackerframework.base.service.BaseService;
import com.ackerframework.server.nestfilm.dao.DirectorDao;
import com.ackerframework.server.nestfilm.entity.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectorService {

    @Autowired
    private DirectorDao directorDao;

    public Director get(Integer id) {
        return directorDao.get(id);
    }

    @Transactional(readOnly = false)
    public Integer insert(Director director) {
        Integer no = directorDao.insert(director);
        if (no == 1) {
            return director.getId();
        } else {
            return 0;
        }
    }
}
