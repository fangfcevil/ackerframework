package com.ackerframework.admin.filem.base.service;

import com.ackerframework.admin.filem.base.dao.PrincipalDao;
import com.ackerframework.admin.filem.base.entity.Principal;
import com.ackerframework.base.service.BaseService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService extends BaseService<PrincipalDao, Principal> {
    @Autowired
    private PrincipalDao principalDao;

    public JSONArray getMainFilemList() {
        return principalDao.getMainFilemList();
    }

}
