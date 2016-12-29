package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.MenuDao;
import com.ackerframework.admin.center.rights.entity.MenuTree;
import com.ackerframework.admin.center.rights.entity.Menu;
import com.ackerframework.base.entity.Result;
import com.ackerframework.base.service.BaseService;
import com.ackerframework.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuService extends BaseService<MenuDao, Menu> {
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
        if (StringUtils.isBlank(menu.getNavName())) {
            return new Result(false, "导航名称不能为空!");
        }
        if (StringUtils.isBlank(menu.getNavType())) {
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

    public List<Menu> getNodesByPid(Integer pid) {
        return menuDao.getNodesByPid(pid);
    }

    //当前节点的子节点不显示
    public ArrayNode initComboTree(Integer id) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNodeRoot = mapper.createArrayNode();
        ObjectNode rootNode = arrayNodeRoot.addObject();
        rootNode.put("id", 0);
        rootNode.put("text", "无");
        ArrayNode childrenNode = rootNode.putArray("children");
        childrenNode.addAll(this.getTreeNode(0, id));
//        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        try {
//            String json = objectWriter.writeValueAsString(arrayNodeRoot);
//            System.out.print(json);
//        } catch (JsonProcessingException e) {
//
//        }
        return arrayNodeRoot;
    }

    private ArrayNode getTreeNode(Integer pid, Integer id) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        List<Menu> menus = menuDao.getNodesByPid(pid);
        Iterator<Menu> item = menus.iterator();
        while (item.hasNext()) {
            Menu menu = item.next();
            if (menu.getId() != id) {
                ObjectNode node = arrayNode.addObject();
                node.put("id", menu.getId());
                node.put("pid", menu.getPid());
                node.put("text", menu.getNavName());
                node.put("navCode", menu.getNavCode());
                node.put("permission", menu.getPermission());
                node.put("attributes", menu.getAttributes());
                node.set("children", getTreeNode(menu.getId(), id));
            }

        }
        return arrayNode;
    }
}
