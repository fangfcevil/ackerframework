$.fView.url = {
    orggridlist: $.fView.contextPath + "/admin/center/rights/rightsconf/orggridlist",
    usergridlist: $.fView.contextPath + "/admin/center/rights/rightsconf/usergridlist",
    delete: $.fView.contextPath + "/admin/center/rights/rightsconf/delete"
};

function onClickOrgRow(row) {
    console.log(row);
    $('.easyui-datagrid').datagrid({
        url: $.fView.url.usergridlist,
        onBeforeLoad: function (param) {
            param.orgId = row.id;
        }
    });
    $('.easyui-datagrid').datagrid("reload");
}

function deleteItem(orgId, userId, roleId) {
    $.fForm.get($.fView.url.delete, {orgId: orgId, userId: userId, roleId: roleId}).success(function (result) {
        if (result.status) {
            $('.easyui-datagrid').datagrid("reload");
        } else {
            $.fMsg.info({msg: "删除失败"});
        }
    });
}

function roleFormatter(value, row, index) {
    var tagHtml = "<div style='display: flex;'>";
    $.each(row.roleIds.split(","), function (i, roleid) {
        tagHtml += "<div style='margin: 2px;background-color: burlywood'>" +
            "<span>" + value.split(",")[i] + "</span>" +
            "<i style='cursor:pointer' class='fa fa-times' aria-hidden='true' onclick='deleteItem(" + row.orgId + "," + row.userId + "," + roleid + ")'></i>" +
            "</div>";
    })
    return tagHtml + "</div>";
}

function addUser(orgId) {
    setTimeout(function () {
        var row = $(".easyui-treegrid").treegrid("getSelected");
        if (row) {
            $(".easyui-window").window({
                title: "正在为  " + row.text + "  添加用户："
            });
            $(".easyui-window").window("open");
        }
    }, 700)
}

$(document).ready(function () {
    $(".easyui-treegrid").treegrid({url: $.fView.url.orggridlist});
});