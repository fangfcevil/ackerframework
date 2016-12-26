package com.ackerframework.admin.center.sys.dao;

import com.ackerframework.admin.center.rights.entity.Navigator;
import com.ackerframework.admin.center.sys.entity.UserRights;
import com.ackerframework.core.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface SysDao {

    //..获取当前登录用户的所有 组织权限
    List<UserRights> getUserRightses(Integer userId);

    //..获取当前登录用户的一个 组织权限

    UserRights getUserRights(@Param("userId") Integer userId, @Param("roleId") Integer roleId,
                             @Param("orgId") Integer orgId);

    List<Navigator> getRightsNavigators(@Param("userId") Integer userId, @Param("roleId") Integer roleId,
                                        @Param("pid") Integer pid);
}
