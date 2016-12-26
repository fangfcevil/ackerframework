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

    @Transactional(readOnly = false)
    public Result insert(User user) {
        Result result = validateUser(user, Constant.INSERT);
        if (result.getStatus()) {
            Integer count = userDao.insert(user);
            return new Result(user.getId());
        } else {
            return result;
        }
    }

    @Transactional(readOnly = false)
    public Result update(User user) {
        logger.debug("{log} server 测试");
        Result result = validateUser(user, Constant.UPDATE);
        if (result.getStatus()) {
            user.setModifyTime(new Date());
            Integer count = userDao.update(user);
            return new Result(user.getId());
        } else {
            return result;
        }
    }

    @Transactional(readOnly = false)
    public Result delete(Integer id) {
        Integer count = userDao.delete(id);
        return new Result(count);
    }

    public User getByUserName(String userName) {
        User user = userDao.getByName(new UserParam(userName));
        return user;
    }

    public List<EgridUsers> getGridList(UserParam userParam) {
        return userDao.getGridList(userParam);
    }

    private Result validateUser(User user, String action) {
        Boolean isInsert = Constant.INSERT.equals(action);
        Boolean isUpdate = Constant.UPDATE.equals(action);

        if (StringUtils.isBlank(user.getUserName()) && (isInsert || isUpdate)) {
            return new Result(false, "用户名称不能为空!");
        }

        if (StringUtils.isBlank(user.getNickName()) && (isInsert || isUpdate)) {
            return new Result(false, "用户昵称不能为空!");
        }
        return new Result();
    }
}
