package com.ackerframework.admin.center.rights.dao;

import com.ackerframework.admin.center.rights.entity.User;
import com.ackerframework.admin.center.rights.params.UserParam;
import com.ackerframework.base.dao.BaseDao;
import com.ackerframework.core.annotation.MyBatisDao;

@MyBatisDao
public interface UserDao extends BaseDao<User> {

    User getByAccount(String account);

}
