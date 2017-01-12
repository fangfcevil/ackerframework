package com.ackerframework.admin.filem.base.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PrincipalControllerTest extends BaseControllerTest{

    @Test
    public void getMain() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/base/princ/list"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

}