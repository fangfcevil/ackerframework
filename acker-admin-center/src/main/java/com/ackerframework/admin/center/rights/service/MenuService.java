package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.MenuDao;
import com.ackerframework.admin.center.rights.entity.MenuTree;
import com.ackerframework.admin.center.rights.entity.Menu;
import com.ackerframework.base.entity.LoginUser;
import com.ackerframework.base.entity.Result;
import com.ackerframework.base.service.BaseService;
import com.ackerframework.base.service.BaseTreeService;
import com.ackerframework.utils.GlobalUtils;
import com.ackerframework.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuService extends BaseTreeService<MenuDao, Menu> {
    @Autowired
    protected MenuDao menuDao;

    @Override
    public Result preInsert(Menu menu) {
        return validateUser(menu);
    }

    @Override
    public Result preUpdate(Menu menu) {
        return validateUser(menu);
    }

    private Result validateUser(Menu menu) {
        if (StringUtils.isBlank(menu.getText())) {
            return new Result(false, "导航名称不能为空!");
        }
        if (StringUtils.isBlank(menu.getTypes())) {
            return new Result(false, "导航类型不能为空!");
        }
        return new Result();
    }

    public Result authingNav(Integer pid, Integer roleId) {
        return new Result(menuDao.authingNav(pid, roleId));
    }

    public List<MenuTree> authingButton(Integer pid, Integer roleId) {
        return menuDao.authingNav(pid, roleId);
    }

    //获取  导航权限菜单
    public List<Menu> generateTree(Integer pid) {
        LoginUser loginUser = GlobalUtils.getLoginUser();
        List<Menu> treeNodes = super.dao.getRightsTreeNode(loginUser.getId(), loginUser.getRoleId(), pid);
        Iterator<Menu> item = treeNodes.iterator();
        while (item.hasNext()) {
            Menu node = item.next();
            node.setChildren(this.generateTree(node.getId()));
        }
        return treeNodes;
    }

    //初始化父节点下拉框
    public List<Menu> initComboParentTree(Integer id) {
        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu();
        menu.setId(0);
        menu.setText("无");
        menu.setChildren(this.initComboParentTree(0, id));
        menus.add(menu);
        return menus;
    }
}
