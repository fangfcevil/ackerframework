(function ($) {

//region 屏蔽backspace键，浏览器后退
    document.onkeydown = function () {
        if ((event.keyCode == 8)) //屏蔽退格删除键
        {
            if (window.event.srcElement.tagName.toUpperCase() != "INPUT"
                && window.event.srcElement.tagName.toUpperCase() != "TEXTAREA"
                && window.event.srcElement.tagName.toUpperCase() != "TEXT") {
                event.keyCode = 0;
                event.preventDefault();
            }
        }
    };
//endregion

//region 浏览器兼容设置
    if (!Array.indexOf) {
        Array.prototype.indexOf = function (obj) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == obj) {
                    return i;
                }
            }
            return -1;
        }
    }
//endregion

//region 拓展基础类型方法
    var formatStr = /\{([\s\S]+?)\}/g;
    String.prototype.format = function () {
        var me = this, args = (typeof arguments[0] == 'object') ? arguments[0] : arguments;
        return me.replace(formatStr, function (m, i) {
            return m == "{{}" || m == "{}}" ? i : args[i] || '';
        });
    };
//endregion

//region  配置定义
    $.fConfig = {};
//endregion

// region  界面常量定义
    $.fView = {};
    $.fView.state = {
        look: 'look',
        insert: 'insert',
        update: 'update'
    };
    $.fView.contextPath = "";
    $.fView.currentState = "";
    $.fView.url = {formlist: "", gridlist: "", formitem: "", insert: "", update: "", delete: ""};
//endregion

//region 辅助类 Utils
    $.fUtil = {
        typeOf: function (obj) {
            return Object.prototype.toString.apply(obj);
        },
        isString: function (obj) {
            return $.fUtil.typeOf(obj) == "[object String]";
        },
        isBoolean: function (obj) {
            return $.fUtil.typeOf(obj) == "[object Boolean]";
        },
        isNumber: function (obj) {
            return $.fUtil.typeOf(obj) == "[object Number]";
        },
        isArray: function (obj) {
            return $.fUtil.typeOf(obj) == "[object Array]";
        },
        isFunction: function (obj) {
            return $.fUtil.typeOf(obj) == "[object Function]";
        },
        isObject: function (obj) {
            return $.fUtil.typeOf(obj) == "[object Object]";
        },
        isNull: function (obj) {
            return $.fUtil.typeOf(obj) == "[object Null]";
        },
        isNullOrEmpty: function (obj) {
            if (!obj) {
                return true;
            } else {
                return false;
            }
        },
        isEmptyObject: function (obj) {
            return JSON.stringify(obj) == "{}";
        }
    }
//endregion

//region 表单操作
    $.fForm = {
        collect: function (groupName) {
            var result = {};
            $("[form-group='" + groupName + "']").each(function (index, element) {
                var className = $(element).attr("class") == undefined ? '' : $(element).attr("class").split(" ")[0];
                var widgetName = className.replace("easyui-", "");
                switch (className) {
                    case 'easyui-textbox':
                    case 'easyui-passwordbox':
                    case 'easyui-numberbox':
                    case 'easyui-numberspinner':
                    case 'easyui-datebox':
                    case 'easyui-datetimebox':
                    case 'easyui-datetimespinner':
                    case 'easyui-timespinner':
                        result[$(element).attr("textboxname")] = eval("$(element)." + widgetName + "('getValue')");
                        break;
                    case 'easyui-switchbutton':
                        result[$(element).attr("textboxname")] = $(element).switchbutton("options").checked;
                        break;
                    case 'easyui-combo':
                    case 'easyui-combobox':
                    case 'easyui-combogrid':
                    case 'easyui-combotree':
                        if (eval("$(element)." + widgetName + "('options').multiple")) {
                            var values = eval("$(element)." + widgetName + "('getValues')");
                            var value = "";
                            $.each(values, function (i, elem) {
                                value += elem + ",";
                            });
                            value = value.substring(0, value.length - 1);
                            result[$(element).attr("textboxname")] = value;
                        } else {
                            result[$(element).attr("textboxname")] = eval(" $(element)." + widgetName + "('getValue')");
                            if (widgetName == "combobox") {
                                console.log($(element).combobox("getValue"));
                            }
                        }
                        if ($(element).attr("nameText")) {
                            result[$(element).attr("nameText")] = eval(" $(element)." + widgetName + "('getText')");
                        }
                        break;
                    case 'easyui-datagrid':
                        var insertedRows = $(element).datagrid("getChanges", "inserted");
                        var updatedRows = $(element).datagrid("getChanges", "updated");
                        var deletedRows = $(element).datagrid("getChanges", "deleted");
                        $.each(insertedRows, function (i, row) {
                            $.extend(row, {rowState: "inserted"})
                        });
                        $.each(updatedRows, function (i, row) {
                            $.extend(row, {rowState: "updated"})
                        });
                        $.each(deletedRows, function (i, row) {
                            $.extend(row, {rowState: "deleted"})
                        });
                        var changedRows = insertedRows.concat(updatedRows).concat(deletedRows);
                        insertedRows = null;
                        updatedRows = null;
                        deletedRows = null;
                        result[$(element).datagrid("options").value] = changedRows;
                        break;
                    default:
                        if (element.nodeName == "INPUT") {
                            if (element.type == "checkbox") {
                                result[$(element).attr("name")] = element.checked;
                            } else {
                                result[$(element).attr("name")] = $(element).val();
                            }
                        }
                }
            });
            return result;
        },
        valid: function (groupName) {
            var result = true;
            $("[form-group='" + groupName + "']").each(function (index, element) {
                var className = $(element).attr("class") == undefined ? '' : $(element).attr("class").split(" ")[0];
                var widgetName = className.replace("easyui-", "");
                switch (className) {
                    case 'easyui-textbox':
                    case 'easyui-passwordbox':
                    case 'easyui-numberbox':
                    case 'easyui-numberspinner':
                    case 'easyui-datebox':
                    case 'easyui-datetimebox':
                    case 'easyui-datetimespinner':
                    case 'easyui-timespinner':
                    case 'easyui-switchbutton':
                    case 'easyui-combo':
                    case 'easyui-combobox':
                    case 'easyui-combogrid':
                    case 'easyui-combotree':
                        result = result && eval("$(element)." + widgetName + "('isValid')");
                        break;
                    default:
                }
            });
            return result;
        },
        get: function (url, data, options) {
            var defaultoptions = {};
            defaultoptions.type = "GET";
            defaultoptions.url = url;
            defaultoptions.data = data;
            defaultoptions.dataType = "json";
            defaultoptions.contentType = "application/json;charset=utf-8";
            defaultoptions.success = function (result, textStatus) {
                if (!result.status && result.message) {
                    $.fMsg.warning({msg: result.message});
                }
                $.unblockUI();
            };
            defaultoptions.error = function (XMLHttpRequest, textStatus) {
                $.fMsg.initLoading({message: "<i class='fa fa-exclamation-triangle fa-fw color-red'></i>" + textStatus});
                setTimeout($.unblockUI, 3000);
            };
            var ajaxOptions = $.extend(defaultoptions, options);
            $.fMsg.initLoading();
            return $.ajax(ajaxOptions);
        },
        post: function (url, data, options) {
            var defaultoptions = {};
            defaultoptions.type = "POST";
            defaultoptions.url = url;
            defaultoptions.data = JSON.stringify(data);
            defaultoptions.dataType = "json";
            defaultoptions.contentType = "application/json;charset=utf-8";
            defaultoptions.success = function (result, textStatus) {
                if (!result.status && result.message) {
                    $.fMsg.warning({title: result.exceptionName, msg: result.message});
                }
                $.unblockUI();
            };
            defaultoptions.error = function (XMLHttpRequest, textStatus) {
                $.fMsg.initLoading({message: "<i class='fa fa-exclamation-triangle fa-fw color-red'></i>" + textStatus});
                setTimeout($.unblockUI, 3000);
            };
            var ajaxOptions = $.extend(defaultoptions, options);
            $.fMsg.initLoading();
            return $.ajax(ajaxOptions);
        },
        redirect: function (url, params) {
            $("#form_redirect").remove();
            var form = "<form id='form_redirect' action='{action}' method='get'>".format({action: url}) +
                " <input hidden='hidden' name='__redirect' value='true'>" +
                " <input id='submit_redirect' type='submit'>";
            for (p in params) {
                form = form + " <input hidden='hidden' name='" + p + "' value='" + params[p] + "'>";
            }
            form = form + " </form>";
            $(form).appendTo('body');
            $('#form_redirect').submit();
        }
    }
//endregion

//region 消息提示
    $.fMsg = {
        error: function (params) {
            var options = {
                title: "错误提示",
                msg: "",
                icon: "error",
                fn: function () {
                }
            }
            $.extend(options, params);
            $.messager.alert(options.title, options.msg, options.icon, options.fn);
        },
        warning: function (params) {
            var options = {
                title: "警告",
                msg: "",
                icon: "warning",
                fn: function () {
                }
            }
            $.extend(options, params);
            $.messager.alert(options.title, options.msg, options.icon, options.fn);
        },
        confirm: function (params) {
            var options = {
                title: "请确认",
                msg: "",
                fn: function (r) {
                }
            }
            $.extend(options, params);
            $.messager.confirm(options.title, options.msg, options.fn);

        },
        prompt: function (params) {
            var options = {
                title: "请输入",
                msg: "",
                fn: function (r) {
                }
            }
            $.extend(options, params);
            $.messager.prompt(options.title, options.msg, options.fn);

        },
        info: function (params) {
            var options = {
                title: '消息提示',
                msg: '',
                showType: 'slide',
                timeout: 2500,
                style: {
                    right: '',
                    top: document.body.scrollTop + document.documentElement.scrollTop,
                    bottom: ''
                }
            }
            $.messager.show($.extend(options, params));
        },
        initLoading: function (params) {
            var options = {
                message: "<i class='fa fa-refresh fa-spin fa-fw'></i>加载中，请稍后...",
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .7,
                    color: '#fff'
                }
            }
            var result = $.extend(options, params);
            $.blockUI(result);
        },
        closeLoading: function (params) {
            var options = {
                message: "<i class='fa fa-check fa-fw'></i>已提交成功！",
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .7,
                    color: '#fff'
                }
            }
            var result = $.extend(options, params);
            $.blockUI(options);
            setTimeout($.unblockUI, 1000);
        }
    }
//endregion

})(jQuery);

