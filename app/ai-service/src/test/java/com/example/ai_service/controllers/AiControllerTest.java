package com.example.ai_service.controllers;

import com.example.ai_service.services.SocialPostService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)

class AiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SocialPostService socialPostService;

    @Test
    public void TestAIController() throws Exception {
        Mockito.when(SocialPostService.getScore(Long.valueOf(1))).thenReturn(String.valueOf(1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/ai/1/score")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Mockito.verify(socialPostService, Mockito.times(1)).getScore(Long.valueOf(1));
    }
}
