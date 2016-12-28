package com.ackerframework.admin.center.base.dao;

import com.ackerframework.admin.center.base.entity.BaseData;
import com.ackerframework.admin.center.base.entity.Combo;
import com.ackerframework.base.dao.BaseDao;
import com.ackerframework.core.annotation.MyBatisDao;

import java.util.List;

@MyBatisDao
public interface BaseDataDao extends BaseDao<BaseData> {
    List<Combo> initCombo(String classCode);
}
