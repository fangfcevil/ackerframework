package com.ackerframework.base.service;

import com.ackerframework.base.dao.BaseTreeDao;
import com.ackerframework.base.entity.BaseTreeEntity;
import com.ackerframework.base.entity.LoginUser;
import com.ackerframework.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Transactional(readOnly = true)
public abstract class BaseTreeService<D extends BaseTreeDao<T>, T extends BaseTreeEntity> extends BaseService<D, T> {

    @Autowired
    protected D dao;

    //初始化父节点下拉树(当前节点的子节点及其下级不能作为本身的父节点）
    public List<T> initComboParentTree(Integer pid, Integer id) {
        List<T> menus = dao.getNodesByPid(pid);
        List<T> resultMenus = new ArrayList<>();
        Iterator<T> item = menus.iterator();
        while (item.hasNext()) {
            T menu = item.next();
            if (menu.getId() != id) {
                resultMenus.add(menu);
                menu.setChildren(initComboParentTree(menu.getId(), id));
            }
        }
        return resultMenus;
    }


}
