package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.NavigatorDao;
import com.ackerframework.admin.center.rights.entity.EtreeNavigator;
import com.ackerframework.admin.center.rights.entity.Navigator;
import com.ackerframework.base.entity.Result;
import com.ackerframework.base.service.BaseService;
import com.ackerframework.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class NavigatorService extends BaseService<NavigatorDao, Navigator> {
    @Autowired
    protected NavigatorDao navigatorDao;

    @Override
    public Result preInsert(Navigator navigator) {
        return validateUser(navigator);
    }

    @Override
    public Result preUpdate(Navigator navigator) {
        return validateUser(navigator);
    }

    private Result validateUser(Navigator navigator) {
        if (StringUtils.isBlank(navigator.getNavName())) {
            return new Result(false, "导航名称不能为空!");
        }
        if (StringUtils.isBlank(navigator.getNavType())) {
            return new Result(false, "导航类型不能为空!");
        }
        return new Result();
    }

    public Result authingNav(Integer pid, Integer roleId) {
        return new Result(navigatorDao.authingNav(pid, roleId));
    }

    public List<EtreeNavigator> authingButton(Integer pid, Integer roleId) {
        return navigatorDao.authingNav(pid, roleId);
    }

    public List<Navigator> getNodesByPid(Integer pid) {
        return navigatorDao.getNodesByPid(pid);
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

        List<Navigator> navigators = navigatorDao.getNodesByPid(pid);
        Iterator<Navigator> item = navigators.iterator();
        while (item.hasNext()) {
            ObjectNode node = arrayNode.addObject();
            Navigator navigator = item.next();
            node.put("id", navigator.getId());
            node.put("pid", navigator.getPid());
            node.put("text", navigator.getNavName());
            node.put("navCode", navigator.getNavCode());
            node.put("permission", navigator.getPermission());
            node.put("attributes", navigator.getAttributes());
            if (pid != id) {
                node.set("children", getTreeNode(navigator.getId(), id));
            }
        }
        return arrayNode;
    }
}
