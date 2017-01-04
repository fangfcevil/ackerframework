package com.ackerframework.admin.center.rights.dao;

import com.ackerframework.admin.center.rights.entity.Menu;
import com.ackerframework.base.dao.BaseTreeDao;
import com.ackerframework.core.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface MenuDao extends BaseTreeDao<Menu> {

    //获取当前登录用户的 导航 菜单
    List<Menu> getRightsTreeNode(@Param("roleId") Integer roleId, @Param("pid") Integer pid);

    List<Menu> authingNav(@Param("pid") Integer pid, @Param("roleId") Integer roleId, @Param("types") Integer types);

    List<Menu> getNodesByPid(Integer pid);

    Integer addPermissions(@Param("menuId") Integer menuId, @Param("roleId") Integer roleId);

    Integer deletePermissions(@Param("menuId") Integer menuId, @Param("roleId") Integer roleId);
}