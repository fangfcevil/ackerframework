$.fView.url = {
    assignrights: $.fView.contextPath + "/admin/center/rights/menu/assignrights",
};

function onClickTree(node) {
    $('.easyui-treegrid').treegrid({
        onBeforeLoad: function (row, param) {
            param.id = node.id;
        }
    });
    $('.easyui-treegrid').treegrid("reload");
}

function onCheckNode(node, checked) {
    console.log(node);
    console.log(checked);
    $.fForm.get($.fView.url.assignrights, {
        id: node.id,
        roleId: $("#roleid").val(),
        checked: checked
    })
}