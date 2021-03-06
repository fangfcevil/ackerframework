package com.ackerframework.admin.filem.base.dao;

import com.ackerframework.admin.filem.base.entity.Director;
import com.ackerframework.base.dao.BaseDao;
import com.ackerframework.core.annotation.MyBatisDao;
import net.sf.json.JSONArray;

@MyBatisDao
public interface DirectorDao extends BaseDao<Director> {
    JSONArray getFilemDirectors(Integer filemId);
}
