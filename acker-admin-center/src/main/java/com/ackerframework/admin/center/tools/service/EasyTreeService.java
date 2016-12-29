package com.ackerframework.admin.center.tools.service;

import com.ackerframework.admin.center.tools.dao.EasyTreeDao;
import com.ackerframework.admin.center.tools.entity.EasyTree;
import com.ackerframework.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EasyTreeService extends BaseService<EasyTreeDao, EasyTree> {
    @Autowired
    private EasyTreeDao easyTreeDao;

    public EasyTree getByTreeName(String treeName) {
        EasyTree easyTree = easyTreeDao.getByTreeName(treeName);
        return easyTree;
    }
}
