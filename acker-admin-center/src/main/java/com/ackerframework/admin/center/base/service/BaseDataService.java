package com.ackerframework.admin.center.base.service;

import com.ackerframework.admin.center.base.dao.BaseDataDao;
import com.ackerframework.admin.center.base.entity.BaseData;
import com.ackerframework.admin.center.base.entity.Combo;
import com.ackerframework.base.entity.Result;
import com.ackerframework.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BaseDataService extends BaseService<BaseDataDao, BaseData> {
    @Autowired
    protected BaseDataDao baseDataDao;

    public Result initCombo(String classCode) {
        return new Result(baseDataDao.initCombo(classCode));
    }

    public List<Combo> initComboList(String classCode) {
        return baseDataDao.initCombo(classCode);
    }
}
