package com.ackerframework.admin.center.tools.controller;

import com.ackerframework.admin.center.rights.entity.Navigator;
import com.ackerframework.admin.center.rights.service.NavigatorService;
import com.ackerframework.admin.center.tools.service.EasyTreeService;
import com.ackerframework.base.controller.BaseController;
import com.ackerframework.base.entity.Result;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/center/tools/treehelp")
public class TreeHelpController extends BaseController {
    @Autowired
    private EasyTreeService easyTreeService;
    @Autowired
    private NavigatorService navigatorService;

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
//        List<Navigator> navigators = navigatorService.getNodesByPid(pid);
//        Iterator<Navigator> item = navigators.iterator();
//        ObjectMapper mapper = new ObjectMapper();
//        ArrayNode arrayNode = mapper.createArrayNode();
//        while (item.hasNext()) {
//            ObjectNode node = arrayNode.addObject();
//            Navigator navigator = item.next();
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
