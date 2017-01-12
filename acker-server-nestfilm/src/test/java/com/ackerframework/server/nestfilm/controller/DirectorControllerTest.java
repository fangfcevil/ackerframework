package com.ackerframework.server.nestfilm.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DirectorControllerTest extends BaseControllerTest {

    @Test
    public void insert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/director")
                .param("name", "名称")
                .param("originalName", "name")
                .param("alt", "/di/sdf/sd/dfas")
                .param("avatarSmall", "")
                .param("avatarLarge", "")
                .param("avatarMedium", "")
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/director/1"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getList() throws Exception {

    }

}