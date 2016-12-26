package com.ackerframework.core.security;

import org.springframework.stereotype.Service;

/**
 * 用户和密码（包含验证码）令牌类
 */
@Service
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    public UsernamePasswordToken() {
        super();
    }

    public UsernamePasswordToken(String username, String password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }
}
