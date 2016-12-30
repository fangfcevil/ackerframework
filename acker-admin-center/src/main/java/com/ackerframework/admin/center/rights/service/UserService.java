package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.UserDao;
import com.ackerframework.admin.center.rights.entity.User;
import com.ackerframework.admin.center.rights.params.UserParam;
import com.ackerframework.base.entity.Result;
import com.ackerframework.base.service.BaseService;
import com.ackerframework.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService extends BaseService<UserDao, User> {
    @Autowired
    protected UserDao userDao;

    public User getByAccount(String account) {
        User user = userDao.getByAccount(account);
        return user;
    }

    @Override
    public Result preInsert(User user) {
        return validateUser(user);
    }

    @Override
    public Result preUpdate(User user) {
        return validateUser(user);
    }

    private Result validateUser(User user) {
        if (StringUtils.isBlank(user.getAccount())) {
            return new Result(false, "用户账号不能为空!");
        }
        if (StringUtils.isBlank(user.getNickname())) {
            return new Result(false, "用户昵称不能为空!");
        }
        return new Result();
    }
}
