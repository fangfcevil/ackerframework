//..导航树 操作
function onClickNav(node) {
    //..如果击中节点是文件夹类型
    if (node.url == null) {
        $(".easyui-tree").tree(node.state == "open" ? "collapse" : "expand", node.target);
        $(".easyui-tree").tree("select");//不选中
        return;
    }
    //..如果已经打开过 或者 url 为空
    if ($('.easyui-tabs').tabs('exists', node.text)) {
        $('.easyui-tabs').tabs("select", node.text);
        return;
    }
    $('.easyui-tabs').tabs('add', {
        id: node.id,
        title: node.text,
        selected: true,
        fit: true,
        border: false,
        closable: true,
        bodyCls: "panel-body-nooverflow",
        content: "<iframe src='" + $.fView.contextPath + node.url + "' frameborder='no' marginheight='0' marginwidth='0' style='width: 100%;height: 100%'></iframe>"
    });
}
//Tabs 页签
function onSelectTab(title, index) {
    var selectedNode = $('.easyui-tabs').tabs('getTab', index);
    var treeNode = $('.easyui-tree').tree('find', $(selectedNode).panel("options").id);
    if (treeNode) {
        //..展开到当前节点，并选中
        $('.easyui-tree').tree('expandTo', treeNode.target).tree('select', treeNode.target)
    } else {
        $(".easyui-tree").tree("select");//不选中
    }
}


$(document).ready(function () {
    $('.easyui-tabs').tabs('add', {
        id: 'controlPanel',
        title: '首 页',
        selected: true,
        fit: true,
        border: false,
        bodyCls: "panel-body-nooverflow",
        content: "<iframe src='" + $.fView.contextPath + "/admin/center/base/home/formlist' frameborder='no' marginheight='0' marginwidth='0' style='width: 100%;height: 100%'></iframe>"
    });
});
function changeLanguage(newLang) {
    $.fForm.get($.fView.contextPath + "/admin/sys/language/change", {newLang: newLang}).success(function () {
        location.reload();
    });
}
