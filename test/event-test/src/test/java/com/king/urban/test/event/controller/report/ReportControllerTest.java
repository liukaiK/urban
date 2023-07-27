package com.king.urban.test.event.controller.report;

import cn.dev33.satoken.SaManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.king.urban.event.pojo.report.TelephoneReportDTO;
import com.king.urban.test.event.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class ReportControllerTest extends BaseTest {

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public static void beforeClass() {
        System.out.println("\n\n------------------------ 基础测试 star ...");
        SaManager.getConfig().setActivityTimeout(180);
    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void telephoneReportTest() throws Exception {
        TelephoneReportDTO reportDTO = new TelephoneReportDTO();
        reportDTO.setLatitude("111.111");
        reportDTO.setLongitude("111.111");

        MockHttpServletResponse response = mockMvc.perform(post("/event/report/telephone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reportDTO))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00000")).andReturn().getResponse();
        log.info(response.getContentAsString());
    }

}
