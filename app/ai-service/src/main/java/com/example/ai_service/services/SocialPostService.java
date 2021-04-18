package com.example.ai_service.services;

import com.example.user_service.entities.SocialPostEntity;
import com.example.user_service.repositories.SocialPostRepository;
import org.springframework.beans.factory.annottion.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class SocialPostService{

    @Autowired
    private final SocialPostRepository socialPostRepository;

    public SocialPostService(SocialPostRepository socialPostRepository) {
        this.socialPostRepository = socialPostRepository;
    }

    public string getScore(Long id){
        SocialPostEntity sp = socialPostRepository.findById(id);

        if(checkIfItHasScrore(sp)){
            return sp.getScore();
        }else{
            return calculateScore(sp);
        }
    }

    private string calculateScore(sp){
        String score = runPythonCalculation(sp.getUrl());
        socialPostRepository.update(sp.getUrl(), score , sp.getId());
        return score;
    }

    private String runPythonCalculation(String url){
        
        Process p = Runtime.getRuntime().exec("python3 scor1.py " + url);
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String score = in.readLine();

        return score;
    }

    private boolean checkIfItHasScrore(SocialPostEntity sp){
        return sp.getScore() != null;
    }

}