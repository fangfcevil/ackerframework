package com.ackerframework.admin.filem.base.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DirectorControllerTest extends BaseControllerTest{

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/filem/base/director/get?id=1"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}