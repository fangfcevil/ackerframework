package com.ackerframework.base.service;

import com.ackerframework.base.dao.BaseDao;
import com.ackerframework.base.entity.BaseEntity;
import com.ackerframework.base.entity.BaseParam;
import com.ackerframework.base.entity.LoginUser;
import com.ackerframework.base.entity.Result;
import com.ackerframework.utils.Constant;
import com.ackerframework.utils.GlobalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public abstract class BaseService<D extends BaseDao<T>, T extends BaseEntity> {

    protected Logger logger = LoggerFactory.getLogger(Constant.LOGGER_SYSLOG);

    @Autowired
    protected D dao;

    public T get(Integer id) {
        return dao.get(id);
    }

    public List getGridList(BaseParam baseParam) {
        return dao.getGridList(baseParam);
    }

    public List getList(BaseParam baseParam) {
        return dao.getList(baseParam);
    }

    public Integer listCount(BaseParam entity) {
        return dao.listCount(entity);
    }

    public Result preInsert(T entity) {
        return new Result();
    }

    @Transactional(readOnly = false)
    public Result insert(T entity) {
        Result result = preInsert(entity);
        if (result.getStatus()) {
            entity.setCreateName(GlobalUtils.getLoginUser().getAccount());
            entity.setCreateTime(new Date());
            dao.insert(entity);
            result.setData(entity.getId());
            return result;
        }
        return result;
    }

    public Result preUpdate(T entity) {
        return new Result();
    }

    @Transactional(readOnly = false)
    public Result update(T entity) {
        Result result = preUpdate(entity);
        if (result.getStatus()) {
            entity.setModifyName(GlobalUtils.getLoginUser().getAccount());
            entity.setModifyTime(new Date());
            dao.update(entity);
            result.setData(entity.getId());
        }
        return result;
    }

    @Transactional(readOnly = false)
    public Result delete(Integer id) {
        return new Result(dao.delete(id));
    }

}
