package com.ackerframework.admin.filem.base.dao;

import com.ackerframework.admin.filem.base.entity.Performer;
import com.ackerframework.base.dao.BaseDao;
import com.ackerframework.core.annotation.MyBatisDao;
import net.sf.json.JSONArray;

@MyBatisDao
public interface PerformerDao extends BaseDao<Performer> {

    JSONArray getFilemPerformers(Integer filemId);

}
