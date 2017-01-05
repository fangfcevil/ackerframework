package com.ackerframework.admin.center.rights.dao;

import com.ackerframework.admin.center.rights.entity.Org;
import com.ackerframework.base.dao.BaseTreeDao;
import com.ackerframework.core.annotation.MyBatisDao;

import java.util.List;

@MyBatisDao
public interface OrgDao extends BaseTreeDao<Org> {

    List getConfRightsGridList();

}
