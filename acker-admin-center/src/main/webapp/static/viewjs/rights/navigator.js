$.fView.url = {
    formlist: $.fView.contextPath + "/admin/center/rights/navigator/formlist",
    gridlist: $.fView.contextPath + "/admin/center/rights/navigator/gridlist",
    formitem: $.fView.contextPath + "/admin/center/rights/navigator/formitem",
    insert: $.fView.contextPath + "/admin/center/rights/navigator/insert",
    update: $.fView.contextPath + "/admin/center/rights/navigator/update",
    delete: $.fView.contextPath + "/admin/center/rights/navigator/delete"
};

function lookItem(id) {
    $.fForm.redirect($.fView.url.formitem, {viewState: $.fView.state.look, id: id});
}

function backList() {
    $.fForm.redirect($.fView.url.formlist);
}

function insertItem() {
    $.fForm.redirect($.fView.url.formitem, {viewState: $.fView.state.insert});
}

function updateItem(id) {
    id = id == null ? $("#itemId").val() : id;
    $.fForm.redirect($.fView.url.formitem, {viewState: $.fView.state.update, id: id});
}

function saveItem() {
    if ($.fForm.valid("detail")) {
        var formJson = $.fForm.collect("detail");
        if ($.fView.currentState == $.fView.state.update) {
            $.fForm.post($.fView.url.update, formJson).success(function (result) {
                if (result.status) {
                    $.fForm.redirect($.fView.url.formitem, {viewState: $.fView.state.look, id: result.data});
                }
            });
        }
        if ($.fView.currentState == $.fView.state.insert) {
            $.fForm.post($.fView.url.insert, formJson).success(function (result) {
                if (result.status) {
                    $.fForm.redirect($.fView.url.formitem, {viewState: $.fView.state.look, id: result.data});
                }
            });
        }
    }
}

function deleteItem(id) {
    id = id == null ? $("#userId").val() : id;
    $.messager.confirm("删除提醒", "确认删除该条数据？", function (r) {
        if (r) {
            $.fForm.get($.fView.url.delete, {id: id}).success(function (result) {
                if (result.data == 1) {
                    backList();
                } else {
                    $.fMsg.info({msg: "删除失败"});
                }
            });
        }
    })
}

function onBeforeLoad(param) {

}

$(document).ready(function () {
    $(".easyui-treegrid").treegrid({url: $.fView.url.gridlist});
    // $(".easyui-combotree").combotree("setValue",1);
    $(".open-serchbox").bind("click", function () {
        $(this).toggleClass("active");
        $(this).closest(".egrid-toolbar").find(".serching-box").toggleClass("hide");
        $(this).closest(".open-serchbox").find(".fa-caret-square-o-down").toggleClass("fa-caret-square-o-up");
        $(".easyui-datagrid").datagrid("resize", {width: '100%', height: '100 %'});
    });
});

