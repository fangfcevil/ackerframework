package com.ackerframework.base.service;

import com.ackerframework.base.dao.BaseTreeDao;
import com.ackerframework.base.entity.BaseTreeEntity;
import com.ackerframework.base.entity.LoginUser;
import com.ackerframework.utils.GlobalUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Transactional(readOnly = true)
public abstract class BaseTreeService<D extends BaseTreeDao<T>, T extends BaseTreeEntity> extends BaseService<D, T> {

    public List<T> generateTree(Integer pid) {
        LoginUser loginUser = GlobalUtils.getLoginUser();
        List<T> treeNodes = super.dao.getRightsTreeNode(loginUser.getId(), loginUser.getRoleId(), pid);
        Iterator<T> item = treeNodes.iterator();
        while (item.hasNext()) {
            T node = item.next();
            node.setChildren(this.generateTree(node.getId()));
        }
        return treeNodes;
    }

}
