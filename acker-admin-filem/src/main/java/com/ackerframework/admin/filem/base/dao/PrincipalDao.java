package com.ackerframework.admin.filem.base.dao;

import com.ackerframework.admin.filem.base.entity.Principal;
import com.ackerframework.base.dao.BaseDao;
import com.ackerframework.core.annotation.MyBatisDao;
import net.sf.json.JSONArray;

import java.util.List;

@MyBatisDao
public interface PrincipalDao extends BaseDao<Principal> {
    JSONArray getMainFilemList();
}
