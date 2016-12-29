package com.ackerframework.admin.center.tools.dao;

import com.ackerframework.admin.center.tools.entity.EasyTree;
import com.ackerframework.base.dao.BaseDao;
import com.ackerframework.core.annotation.MyBatisDao;

@MyBatisDao
public interface EasyTreeDao extends BaseDao<EasyTree> {

    EasyTree getByTreeName(String treeName);

}
