function onClickTree(node) {
    console.log(node);
    if (node.navType == "L") {
        $('.easyui-treegrid').treegrid({
            onBeforeLoad: function (row, param) {
                param.id = node.id;
            }
        });
        $('.easyui-treegrid').treegrid("reload");
    }
}
function onBeforeLoad_Button(param) {
    return param == null ? false : param;
}