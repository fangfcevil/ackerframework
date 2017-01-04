package com.ackerframework.admin.center.rights.dao;

import com.ackerframework.admin.center.rights.entity.Org;
import com.ackerframework.base.dao.BaseTreeDao;
import com.ackerframework.core.annotation.MyBatisDao;

@MyBatisDao
public interface OrgDao extends BaseTreeDao<Org> {
}
