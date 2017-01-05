package com.ackerframework.admin.center.rights.dao;

import com.ackerframework.admin.center.rights.entity.Rights;
import com.ackerframework.base.dao.BaseDao;
import com.ackerframework.core.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface RightsDao extends BaseDao<Rights> {
    Integer deleteRights(@Param("orgId") Integer orgId, @Param("userId") Integer userId,
                         @Param("roleId") Integer roleId);
}
