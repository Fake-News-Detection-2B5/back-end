package com.example.ai_service.services;

import com.example.ai_service.entities.SocialPostEntity;
import com.example.ai_service.repository.SocialPostRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)

public class SocialPostServiceTest {
    @InjectMocks
    private SocialPostService aiService;

    @Test
    public void TestScoreNotNull() throws IOException {
        Mockito.when(SocialPostRepository.findById(Long.valueOf(1))).thenReturn(new SocialPostEntity(Long.valueOf(1), "someURL", "true"));
        Assertions.assertEquals(aiService.getScore(Long.valueOf(1)), "true");
    }

    @Test
    public void TestScoreNull() throws IOException {
        Mockito.when(SocialPostRepository.findById(Long.valueOf(1))).thenReturn(new SocialPostEntity(Long.valueOf(1), "someOtherURL", null));
        //the test python file return string "hello"
        Assertions.assertEquals(aiService.getScore(Long.valueOf(1)), "hello");
    }
}
