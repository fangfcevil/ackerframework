package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.UserDao;
import com.ackerframework.admin.center.rights.entity.EgridUsers;
import com.ackerframework.admin.center.rights.entity.User;
import com.ackerframework.admin.center.rights.params.UserParam;
import com.ackerframework.base.entity.Result;
import com.ackerframework.base.service.BaseService;
import com.ackerframework.utils.Constant;
import com.ackerframework.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService extends BaseService<UserDao, User> {
    @Autowired
    protected UserDao userDao;

    public User getByUserName(String userName) {
        User user = userDao.getByName(new UserParam(userName));
        return user;
    }

    public List<EgridUsers> getGridList(UserParam userParam) {
        return userDao.getGridList(userParam);
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
        if (StringUtils.isBlank(user.getUserName())) {
            return new Result(false, "用户名称不能为空!");
        }
        if (StringUtils.isBlank(user.getNickName())) {
            return new Result(false, "用户昵称不能为空!");
        }
        return new Result();
    }
}
