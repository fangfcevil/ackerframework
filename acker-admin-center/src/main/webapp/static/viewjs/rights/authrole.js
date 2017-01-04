function onClickTree(node) {
    $('.easyui-treegrid').treegrid({
        onBeforeLoad: function (row, param) {
            param.id = node.id;
        }
    });
    $('.easyui-treegrid').treegrid("reload");
}