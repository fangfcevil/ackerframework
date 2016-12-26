package com.ackerframework.base.dao;

import com.ackerframework.base.entity.BaseEntity;
import com.ackerframework.base.entity.BaseParam;

import java.util.List;

/**
 * DAO支持类实现,增 删 改 查
 */
public interface BaseDao<T extends BaseEntity> {

    /**
     * 获取单条数据
     */
    T get(Integer id);

    /**
     * 查询数据列表
     */

    List getGridList(BaseParam param);

    Integer listCount(BaseParam param);

    /**
     * 插入数据
     */
    Integer insert(T entity);

    /**
     * 更新数据
     */
    Integer update(T entity);

    /**
     * 删除数据（一般为逻辑删除）
     */
    Integer delete(Integer id);

}
