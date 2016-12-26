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

/**
 * Service基类
 */
@Transactional(readOnly = true)
public abstract class BaseService<D extends BaseDao<T>, T extends BaseEntity> {

    protected Logger logger = LoggerFactory.getLogger(Constant.LOGGER_SYSLOG);
    protected LoginUser loginUser = GlobalUtils.getLoginUser();
    @Autowired
    protected D dao;

    public T get(Integer id) {
        return dao.get(id);
    }

    public List getGridList(BaseParam baseParam) {
        return dao.getGridList(baseParam);
    }

    public Integer listCount(BaseParam entity) {
        return dao.listCount(entity);
    }

    @Transactional(readOnly = false)
    public Result insert(T entity) {
        entity.setCreateName(loginUser.getUserName());
        entity.setCreateTime(new Date());
        Integer iCount = dao.insert(entity);
        return new Result(iCount);
    }

    @Transactional(readOnly = false)
    public Result update(T entity) {
        entity.setModifyName(loginUser.getUserName());
        entity.setModifyTime(new Date());
        Integer uCount = dao.update(entity);
        return new Result(uCount);
    }

    @Transactional(readOnly = false)
    public Result delete(Integer id) {
        Integer dCount = dao.delete(id);
        return new Result(dCount);
    }
}
