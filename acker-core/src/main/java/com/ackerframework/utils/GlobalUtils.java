package com.ackerframework.utils;

import com.ackerframework.base.entity.LoginUser;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.core.io.DefaultResourceLoader;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 全局配置类
 */
public class GlobalUtils {


    //当前对象实例
    private static GlobalUtils global = new GlobalUtils();

    //保存全局属性值
    private static Map<String, String> map = Maps.newHashMap();

    //属性文件加载对象
    private static PropertiesLoader loader = new PropertiesLoader(Constant.PROPERTIES_FILENAME);

    //获取当前对象实例
    public static GlobalUtils getInstance() {
        return global;
    }

    //获取配置
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    //获取管理端根路径
    public static String getAdminPath() {
        return getConfig("adminPath");
    }

    //获取前端根路径
    public static String getFrontPath() {
        return getConfig("frontPath");
    }

    //获取URL后缀
    public static String getUrlSuffix() {
        return getConfig("urlSuffix");
    }

    //页面获取常量
    public static Object getConst(String field) {
        try {
            return GlobalUtils.class.getField(field).get(null);
        } catch (Exception e) {
            // 异常代表无配置，这里什么也不做
        }
        return null;
    }

    //获取工程路径
    public static String getProjectPath() {
        // 如果配置了工程路径，则直接返回，否则自动获取。
        String projectPath = GlobalUtils.getConfig("projectPath");
        if (StringUtils.isNotBlank(projectPath)) {
            return projectPath;
        }
        try {
            File file = new DefaultResourceLoader().getResource("").getFile();
            if (file != null) {
                while (true) {
                    File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
                    if (f == null || f.exists()) {
                        break;
                    }
                    if (file.getParentFile() != null) {
                        file = file.getParentFile();
                    } else {
                        break;
                    }
                }
                projectPath = file.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projectPath;
    }

    //获得用户远程地址
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    //判断请求类型是否是Ajax
    public static Boolean isAjax(HttpServletRequest request) {
        if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
            return true;
        } else {
            return false;
        }
    }

    public static LoginUser getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        return subject == null ? null : (LoginUser)subject.getPrincipal();
    }

    public static Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        return subject == null ? null : subject.getSession();
    }
}
