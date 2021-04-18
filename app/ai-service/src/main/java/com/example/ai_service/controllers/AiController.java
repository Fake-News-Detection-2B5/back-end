package com.example.ai_service.controllers;

import com.example.ai_service.entities.SocialPostEntity;
import com.example.ai_service.services.SocialPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AiController{

    @Autowired
    public SocialPostService socialPostService;

    @GetMapping("/ai/{socialPostId}/score")
    public string getSocualPostScore(@PathVariable Long id){
        //return true/false/partial as string;
        return socialPostService.getScore(id);
    }
}
