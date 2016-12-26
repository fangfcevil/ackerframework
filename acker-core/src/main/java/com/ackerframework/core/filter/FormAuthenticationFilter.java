package com.ackerframework.core.filter;

import com.ackerframework.core.security.UsernamePasswordToken;
import com.ackerframework.utils.GlobalUtils;
import com.ackerframework.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    public static final String DEFAULT_MESSAGE_PARAM = "message";
    private String messageParam = DEFAULT_MESSAGE_PARAM;

    public String getMessageParam() {
        return messageParam;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request) == null ? "" : getPassword(request);
        boolean rememberMe = isRememberMe(request);
        String host = GlobalUtils.getRemoteAddr((HttpServletRequest) request);
        return new UsernamePasswordToken(username, password, rememberMe, host);
    }

    /**
     * 所有请求都会经过的方法。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
                        + "Authentication url [" + getLoginUrl() + "]");
            }
            if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
                saveRequestAndRedirectToLogin(request, response);
            } else {
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.println("{\"message\":\"此链接未授权\"}");
                out.flush();
                out.close();
            }
            return false;
        }
    }


    /*
     *	针对登入成功的处理方法。对于请求头是AJAX的之间返回JSON字符串。
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {
            issueSuccessRedirect(request, response);
        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println("{\"success\":true,\"message\":\"登入成功!\"}");
            out.flush();
            out.close();
        }
        return false;
    }

    /**
     * 登录失败调用事件
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
            String className = e.getClass().getName(), message = "";
            if (IncorrectCredentialsException.class.getName().equals(className)) {
                message = "您输入的密码不正确，请重新输入";
            } else if (UnknownAccountException.class.getName().equals(className)) {
                message = "您输入的账号不存在, 请重新输入";
            } else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")) {
                message = StringUtils.replace(e.getMessage(), "msg:", "");

            } else if (AuthenticationException.class.getName().equals(className)) {
                message = "您的账号存在异常, 请联系管理员";
            } else {
                message = "系统出现点问题，请稍后再试！";
                e.printStackTrace(); // 输出到控制台
            }
            request.setAttribute(getFailureKeyAttribute(), className);
            request.setAttribute(getMessageParam(), message);
            return true;
        }
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.println("{\"success\":false,\"message\":\"密码错误！\"}");
            } else if ("UnknownAccountException".equals(message)) {
                out.println("{\"success\":false,\"message\":\"账号不存在！\"}");
            } else if ("LockedAccountException".equals(message)) {
                out.println("{\"success\":false,\"message\":\"账号被锁定！\"}");
            } else {
                out.println("{\"success\":false,\"message\":\"未知错误！\"}");
            }
            out.flush();
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return true;
    }
}
