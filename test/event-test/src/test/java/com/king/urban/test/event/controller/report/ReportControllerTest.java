package com.king.urban.test.event.controller.report;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.stp.StpUtil;
import com.king.urban.test.event.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class ReportControllerTest extends BaseTest {

    @Autowired(required = false)
    SaTokenDao dao = SaManager.getSaTokenDao();

    @BeforeAll
    public static void beforeClass() {
        System.out.println("\n\n------------------------ 基础测试 star ...");
        SaManager.getConfig().setActivityTimeout(180);
    }

    @Test
    public void telephoneReportTest() throws Exception {
        StpUtil.login(1);
        MockHttpServletResponse response = mockMvc.perform(post("/event/report/telephone")
                        .queryParam("id", "1")
                        .queryParam("create_time", "2023-01-01")
                        .queryParam("end_time", "2023-12-30")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0")).andReturn().getResponse();

        log.info(response.getContentAsString());
    }

}
