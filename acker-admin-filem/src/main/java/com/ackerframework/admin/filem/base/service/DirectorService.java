package com.ackerframework.admin.filem.base.service;

import com.ackerframework.admin.filem.base.dao.DirectorDao;
import com.ackerframework.admin.filem.base.entity.Director;
import com.ackerframework.base.service.BaseService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorService extends BaseService<DirectorDao, Director> {
    @Autowired
    private DirectorDao directorDao;

    public JSONArray getFilemDirectors(Integer filemId) {
        return directorDao.getFilemDirectors(filemId);
    }

}
