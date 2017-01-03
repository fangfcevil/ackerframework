package com.ackerframework.base.dao;

import com.ackerframework.base.entity.BaseTreeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO支持类实现,增 删 改 查
 */
public interface BaseTreeDao<T extends BaseTreeEntity> extends BaseDao<T> {

    List<T> getNodesByPid(Integer pid);

}
