package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.NavigatorDao;
import com.ackerframework.admin.center.rights.entity.EtreeNavigator;
import com.ackerframework.admin.center.rights.entity.Navigator;
import com.ackerframework.base.entity.Result;
import com.ackerframework.base.service.BaseService;
import com.ackerframework.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class NavigatorService extends BaseService<NavigatorDao, Navigator> {
    @Autowired
    protected NavigatorDao navigatorDao;

    @Override
    public Result preInsert(Navigator navigator) {
        return validateUser(navigator);
    }

    @Override
    public Result preUpdate(Navigator navigator) {
        return validateUser(navigator);
    }

    private Result validateUser(Navigator navigator) {
        if (StringUtils.isBlank(navigator.getNavName())) {
            return new Result(false, "导航名称不能为空!");
        }
        if (StringUtils.isBlank(navigator.getNavType())) {
            return new Result(false, "导航类型不能为空!");
        }
        return new Result();
    }

    public List<EtreeNavigator> authingNav(Integer pid, Integer roleId) {
        return navigatorDao.authingNav(pid, roleId);
    }
}
