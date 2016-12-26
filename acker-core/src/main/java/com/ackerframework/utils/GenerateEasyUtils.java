package com.ackerframework.utils;

import com.ackerframework.base.entity.Button;

import java.util.List;
import java.util.UUID;

public class GenerateEasyUtils {

    public static String buttonsHtml(List<Button> buttons) {
        String id = "buttonHtmlDiv" + UUID.randomUUID().toString();
        String resultHtml = "<div id='" + id + "'>";
        for (Button button : buttons) {
            resultHtml += buttonHtml(button);
        }
        resultHtml += "</div><script>$.parser.parse('#" + id + "');</script>";
        return resultHtml;
    }

    public static String buttonHtml(Button button) {
        String resultHtml = "";
        if ("easyui-linkbutton".equals(button.getClassName())) {
            resultHtml += "<a class='egrid-button " + button.getClassName() + "'";
            if (button.getOnClick() != null) {
                resultHtml += " onclick='" + button.getOnClick() + "(" + button.getFnParam() + ")'";
            }
            resultHtml += ">";
            if (button.getIcon() != null) {
                resultHtml += button.getIcon();
            }
            if (button.getText() != null) {
                resultHtml += button.getText();
            }
            resultHtml += "</a>";
        }
        if ("easyui-splitbutton".equals(button.getClassName())) {
            String itemsDivId = "buttonItem" + UUID.randomUUID().toString();
            resultHtml += "<a class='" + button.getClassName() + "'";
            resultHtml += "data-options=\"menu:'#" + itemsDivId + "'\" ";
            if (button.getOnClick() != null) {
                resultHtml += " onclick='" + button.getOnClick() + "(" + button.getFnParam() + ")'";
            }
            resultHtml += ">";
            if (button.getIcon() != null) {
                resultHtml += button.getIcon();
            }
            if (button.getText() != null) {
                resultHtml += button.getText();
            }
            List<Button> itemButtons = button.getButtons();
            resultHtml += "</a><div id='" + itemsDivId + "'>";
            for (Button itemButton : itemButtons) {
                resultHtml += buttonHtml(itemButton);
            }
            resultHtml += "</div>";
        }
        return resultHtml;
    }
}
