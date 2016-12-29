package com.ackerframework.admin.center.rights.dao;

import com.ackerframework.admin.center.rights.entity.MenuTree;
import com.ackerframework.admin.center.rights.entity.Menu;
import com.ackerframework.base.dao.BaseDao;
import com.ackerframework.core.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface MenuDao extends BaseDao<Menu> {

    List<MenuTree> authingNav(@Param("pid") Integer pid, @Param("roleId") Integer roleId);

    List<MenuTree> initComboTree(@Param("nodeId") Integer nodeId);

    List<Menu> getNodesByPid(Integer pid);
}