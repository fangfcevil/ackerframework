package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.NavigatorDao;
import com.ackerframework.admin.center.rights.entity.Navigator;
import com.ackerframework.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class NavigatorService extends BaseService<NavigatorDao, Navigator> {
    @Autowired
    protected NavigatorDao navigatorDao;


}
