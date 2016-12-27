package com.ackerframework.admin.center.sys.service;

import com.ackerframework.admin.center.rights.entity.EtreeNavigator;
import com.ackerframework.admin.center.rights.entity.Navigator;
import com.ackerframework.admin.center.sys.dao.SysDao;
import com.ackerframework.admin.center.sys.entity.UserRights;
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

    public List<EtreeNavigator> getRightsNavigators(Integer userId, Integer roleId, Integer pid) {
        return sysDao.getRightsNavigators(userId, roleId, pid);
    }
}
