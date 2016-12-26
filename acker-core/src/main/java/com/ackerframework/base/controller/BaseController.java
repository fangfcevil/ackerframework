package com.ackerframework.base.controller;

import com.ackerframework.base.entity.LoginUser;
import com.ackerframework.utils.Constant;
import com.ackerframework.utils.DateUtils;
import com.ackerframework.utils.GlobalUtils;
import com.ackerframework.utils.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class BaseController {

    //日志对象
    protected Logger logger = LoggerFactory.getLogger(Constant.LOGGER_SYSLOG);

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
        //Map 类型转换
        binder.registerCustomEditor(Map.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(StringUtils.toMap(text));
            }
        });
        //list 类型转换
        binder.registerCustomEditor(List.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(StringUtils.toListMap(text));
            }
        });
    }

    @ModelAttribute
    public void populateModel(@RequestParam(value = Constant.VIEWSTATE, required = false) String viewState, Model model) {
        if (viewState != null) {
            model.addAttribute(Constant.LOOK, Constant.LOOK.equals(viewState) ? true : false);
            model.addAttribute(Constant.INSERT, Constant.INSERT.equals(viewState) ? true : false);
            model.addAttribute(Constant.UPDATE, Constant.UPDATE.equals(viewState) ? true : false);
            model.addAttribute(Constant.VIEWSTATE, viewState);
        }
    }
}
