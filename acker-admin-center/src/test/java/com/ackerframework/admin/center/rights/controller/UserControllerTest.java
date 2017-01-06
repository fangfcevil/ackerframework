package com.ackerframework.admin.center.rights.controller;

import org.apache.shiro.SecurityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.apache.shiro.mgt.SecurityManager;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@WebAppConfiguration
@ContextConfiguration({"classpath*:/springcontext-*.xml", "classpath*:/springmvc-base.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webContext;
    @Autowired
    private SecurityManager securityManager;
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
        //必须要初始化 SecurityManager
        SecurityUtils.setSecurityManager(securityManager);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void formlist() throws Exception {

    }

    @Test
    public void formitem() throws Exception {

    }

    @Test
    public void gridList() throws Exception {

    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/center/rights/user/get?id=1"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void initUserCombo() throws Exception {

    }

}