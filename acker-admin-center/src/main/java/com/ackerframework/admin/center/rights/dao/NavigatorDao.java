package com.ackerframework.admin.center.rights.dao;

import com.ackerframework.admin.center.rights.entity.EtreeNavigator;
import com.ackerframework.admin.center.rights.entity.Navigator;
import com.ackerframework.base.dao.BaseDao;
import com.ackerframework.core.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface NavigatorDao extends BaseDao<Navigator> {

    List<EtreeNavigator> authingNav(@Param("pid") Integer pid, @Param("roleId") Integer roleId);

    List<EtreeNavigator> initComboTree(@Param("nodeId") Integer nodeId);
}