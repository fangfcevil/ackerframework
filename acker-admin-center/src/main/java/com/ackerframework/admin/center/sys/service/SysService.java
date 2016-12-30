package com.ackerframework.admin.center.sys.service;

import com.ackerframework.admin.center.rights.dao.MenuDao;
import com.ackerframework.admin.center.rights.service.MenuService;
import com.ackerframework.admin.center.sys.dao.SysDao;
import com.ackerframework.admin.center.sys.entity.UserRights;
import com.ackerframework.base.entity.Result;
import com.ackerframework.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SysService {
    @Autowired
    protected SysDao sysDao;
    @Autowired
    protected MenuService menuService;

    public List<UserRights> getUserRightses(Integer userId) {
        return sysDao.getUserRightses(userId, null, null);
    }

    public List<UserRights> getUserRights(Integer userId, Integer roleId, Integer orgId) {
        return sysDao.getUserRightses(userId, roleId, orgId);
    }

    public Result getRightsNavigators(Integer pid) {
        return new Result(menuService.generateTree(pid));
    }

}
