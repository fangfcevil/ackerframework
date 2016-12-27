//region 验证控件  validatebox
$.extend($.fn.validatebox.defaults, {
    labelAlign: 'right',
    width: '100%',
    height: 25
});
//endregion

//region 文本框  TextBox
$.extend($.fn.textbox.defaults, {
    labelAlign: 'right',
    width: '100%',
    height: 25
});
//endregion

//region 密码框  passwordbox
$.extend($.fn.passwordbox.defaults, {
    labelAlign: 'right',
    width: '100%',
    height: 25
});
//endregion

//region 下拉框  ComBo
$.extend($.fn.combo.defaults, {
    labelAlign: 'right',
    width: '100%',
    method: 'get',
    panelHeight: 'auto',
    height: 25
});
//endregion

//region 下拉框  ComboBox
$.extend($.fn.combobox.defaults, {
    labelAlign: 'right',
    width: '100%',
    method: 'get',
    panelHeight: 'auto',
    height: 25
});
//endregion

//region 下拉列表  ComboGrid
$.extend($.fn.combogrid.defaults, {
    labelAlign: 'right',
    width: '100%',
    method: 'get',
    panelHeight: 'auto',
    height: 25
});
//endregion

//region 下拉树  ComboTree
$.extend($.fn.combotree.defaults, {
    labelAlign: 'right',
    width: '100%',
    method: 'get',
    panelHeight: 'auto',
    height: 25,
    valueField: "id",
    textField: "text"
});
//endregion

//region 日期控件  DateBox
$.extend($.fn.datebox.defaults, {
    labelAlign: 'right',
    width: '100%',
    height: 25
});
//endregion

//region 时间控件  TimeSpinner
$.extend($.fn.timespinner.defaults, {
    labelAlign: 'right',
    width: '100%',
    height: 25
});
//endregion

//region 日期时间控件  DateTimeBox
$.extend($.fn.datetimebox.defaults, {
    labelAlign: 'right',
    width: '100%',
    height: 25
});
//endregion

//region 数字框  NumberBox
$.extend($.fn.numberbox.defaults, {
    labelAlign: 'right',
    labelAlign: 'right',
    width: '100%',
    height: 25
});
//endregion

//region 数字Spinner框  NumberSpinner
$.extend($.fn.numberspinner.defaults, {
    labelAlign: 'right',
    width: '100%',
    height: 25
});
//endregion

//region 列表 Grid
$.extend($.fn.datagrid.defaults, {
    idField: "id",//默认主键为id
    method: "get",//默认get访问
    pagination: true,//默认显示分页
    rownumbers: true,//默认显示行号
    width: '100%',
    height: '100%',
    singleSelect: true,//单选
    striped: true,//隔行换色
    editIndex: -1,//编辑行的索引
    openEditGrid: false,
    loadMsg: null,
    loader: function (param, success, error) {
        var opts = $(this).datagrid("options");
        if (!opts.url) {
            return false;
        }
        $.fForm.get(opts.url, param, {type: opts.method}).success(function (result) {
            if (result.status == 0) {

            } else {
                success(result.data);
            }
        });
    },
    onClickCell: function (index, field) {
        if ($(this).datagrid("options").openEditGrid) {
            if ($(this).datagrid("options").editIndex != index) {
                if ($(this).datagrid("endEditing")) {
                    $(this).datagrid('selectRow', index);
                    $(this).datagrid('beginEdit', index);
                    var ed = $(this).datagrid('getEditor', {index: index, field: field});
                    if (ed) {
                        ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                    }
                    $(this).datagrid("options").editIndex = index;
                } else {
                    setTimeout(function () {
                        $(this).datagrid('selectRow', $(this).datagrid("options").editIndex);
                    }, 0);
                }
            }
        }
    },
    onLoadSuccess: function (data) {

    }
});

$.extend($.fn.datagrid.methods, {
    endEditing: function (jq) {
        var opts = jq.datagrid("options");
        if (opts.editIndex == -1) {
            return true;
        }
        if (jq.datagrid('validateRow', opts.editIndex)) {
            jq.datagrid('endEdit', opts.editIndex);
            opts.editIndex = -1;
            return true;
        } else {
            return false;
        }
    },
    addRow: function (jq, rowData) {
        if (jq.datagrid("endEditing")) {
            var opts = jq.datagrid("options");
            jq.datagrid('insertRow', {index: 0, row: rowData});
            opts.editIndex = 0;
            jq.datagrid('selectRow', opts.editIndex).datagrid('beginEdit', opts.editIndex);
        }
    }
});
//endregion

//region 树 Tree
$.extend($.fn.tree.defaults, {
    method: 'get'

});
//endregion

//region 树列表 TreeGrid
$.extend($.fn.treegrid.defaults, {
    idField: "id",//默认主键为id
    method: 'get',
    treeField: "text",
    rownumbers: true,//默认显示行号
    striped: true,//隔行换色
    loader: function (param, success, error) {
        console.log("1");
        var opts = $(this).treegrid("options");
        if (!opts.url) {
            return false;
        }
        $.fForm.get(opts.url, param, {type: opts.method}).success(function (result) {
            if (result.status == 0) {
                $.messager.alert('error', result.message);
                success({rows: []});
            } else {
                success(result.data.rows);
            }
        });
    }
});
//endregion

//region 面板控件  Panel
$.extend($.fn.panel.defaults, {
    fit: false
});
//endregion

//region 验证控件 validatebox
$.extend($.fn.validatebox.defaults.rules, {
    //验证两个控件是否相等
    samepw: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: "两次输入的密码不一致"
    },
    //验证手机号
    mobile: {
        validator: function (value, param) {
            var regu = /^[1][0-9][0-9]{9}$/;
            var re = new RegExp(regu);
            if (re.test(value)) {
                return true;
            } else {
                return false;
            }
        },
        message: '手机号格式错误！'
    }
});
//endregion

