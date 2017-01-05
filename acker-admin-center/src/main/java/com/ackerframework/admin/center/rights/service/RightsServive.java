package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.RightsDao;
import com.ackerframework.admin.center.rights.entity.Rights;
import com.ackerframework.base.entity.Result;
import com.ackerframework.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RightsServive extends BaseService<RightsDao, Rights> {
@Autowired
private RightsDao rightsDao;

    @Transactional(readOnly = false)
    public Result deleteRights(Integer orgId,Integer userId,Integer roleId) {
        return new Result(rightsDao.deleteRights(orgId,userId,roleId));
    }
}
