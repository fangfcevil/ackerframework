package com.ackerframework.admin.filem.sys.controller;

import com.ackerframework.admin.filem.base.controller.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.servlet.http.Cookie;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerTest extends BaseControllerTest {
    @Test
    public void login() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username", "ackerman")
                .param("password", "123456")
                .param("rememberMe", "true")
                .cookie(new Cookie("storm.session.id", "0240ef34-80ae-429f-a98e-c00de54f1776"))
        ).andExpect(status().isOk()).andDo(print());
    }
}