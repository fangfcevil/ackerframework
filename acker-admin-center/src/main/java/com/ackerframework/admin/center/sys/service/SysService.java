package com.ackerframework.admin.center.sys.service;

import com.ackerframework.admin.center.sys.dao.SysDao;
import com.ackerframework.admin.center.sys.entity.UserRights;
import com.ackerframework.base.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SysService {
    @Autowired
    protected SysDao sysDao;

    public List<UserRights> getUserRightses(Integer userId) {
        return sysDao.getUserRightses(userId);
    }

    public UserRights getUserRights(Integer userId, Integer roleId, Integer orgId) {
        return sysDao.getUserRights(userId, roleId, orgId);
    }

    public Result getRightsNavigators(Integer userId, Integer roleId, Integer pid) {
        return new Result(sysDao.getRightsNavigators(userId, roleId, pid));
    }
}
