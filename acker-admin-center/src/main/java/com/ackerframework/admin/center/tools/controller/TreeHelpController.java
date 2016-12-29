package com.ackerframework.admin.center.tools.controller;

import com.ackerframework.admin.center.rights.service.MenuService;
import com.ackerframework.admin.center.tools.service.EasyTreeService;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.base.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/admin/center/tools/treehelp")
public class TreeHelpController extends BaseController {
    @Autowired
    private EasyTreeService easyTreeService;
    @Autowired
    private MenuService menuService;

    // 重新生成树结构
    @ResponseBody
    @RequestMapping(value = "/regenerate")
    public Result reGenerate(@RequestParam(name = "treeName") String treeName) throws IOException {

//        ArrayNode arrayNode = getChildren(0);
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = objectWriter.writeValueAsString(arrayNode);
//        System.out.print(json);
       return new Result();
    }

//    private ArrayNode getChildren(Integer pid) {
//        List<Menu> navigators = navigatorService.getNodesByPid(pid);
//        Iterator<Menu> item = navigators.iterator();
//        ObjectMapper mapper = new ObjectMapper();
//        ArrayNode arrayNode = mapper.createArrayNode();
//        while (item.hasNext()) {
//            ObjectNode node = arrayNode.addObject();
//            Menu navigator = item.next();
//            node.put("id", navigator.getId());
//            node.put("pid", navigator.getPid());
//            node.put("text", navigator.getNavName());
//            node.put("navCode", navigator.getNavCode());
//            node.put("permission", navigator.getPermission());
//            node.put("attributes", navigator.getAttributes());
//            node.set("children", getChildren(navigator.getId()));
//        }
//        return arrayNode;
//    }
}
