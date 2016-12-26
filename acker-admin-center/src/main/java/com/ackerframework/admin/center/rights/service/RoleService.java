package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.RoleDao;
import com.ackerframework.admin.center.rights.entity.EgridUser;
import com.ackerframework.admin.center.rights.entity.Role;
import com.ackerframework.admin.center.rights.params.UserParam;
import com.ackerframework.base.entity.Result;
import com.ackerframework.base.service.BaseService;
import com.ackerframework.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleService extends BaseService<RoleDao, Role> {
    @Autowired
    protected RoleDao roleDao;


    public List<EgridUser> getGridList(UserParam userParam) {
        return roleDao.getGridList(userParam);
    }

    @Override
    public Result preInsert(Role role) {
        return validateUser(role);
    }

    @Override
    public Result preUpdate(Role role) {
        return validateUser(role);
    }

    private Result validateUser(Role role) {
        if (StringUtils.isBlank(role.getRoleName())) {
            return new Result(false, "角色名称不能为空!");
        }
        if (StringUtils.isBlank(role.getRoleCode())) {
            return new Result(false, "角色代码不能为空!");
        }
        return new Result();
    }
}
