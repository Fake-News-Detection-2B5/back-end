package com.example.ai_service.services;

import com.example.user_service.entities.SocialPostEntity;
import com.example.user_service.repositories.SocialPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static String getScore(Long id) throws IOException{
        SocialPostEntity sp = socialPostRepository.findById(id);

        if(checkIfItHasScrore(sp)){
            return sp.getScore();
        }else{
            return calculateScore(sp);
        }
    }

    private static String calculateScore(SocialPostRepository sp) throws IOException{
        String score = runPythonCalculation(sp.getUrl());
        socialPostRepository.update(sp.getUrl(), score , sp.getId());
        return score;
    }

    private static String runPythonCalculation(String url) throws IOException{
        
        Process p1 = Runtime.getRuntime().exec("python3 scor1.py " + url);
        BufferedReader in1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
        String score1 = in1.readLine();
        
        Process p2 = Runtime.getRuntime().exec("python3 scor2.py " + url);
        BufferedReader in2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
        String score2 = in2.readLine();

        return mergeScore(score1, score2);
    }
    
    private static String mergeScore(String s1, String s2){
        /* false(5), true(4), partialFalse(12)
        if
        * T T => T
        * T F => PF
        * F F => F
        * PF T => PF
        * PF F => F
        * PF PF => PF
        * */
        int l1 = s1.length();
        int l2 = s2.length();

        if (l1 == 4 && l2 == 4) //true and true
            return "True";

        if ((l1 == 4 && l2 == 5) || (l1 == 5 && l2 == 4)) //true and false
            return "Partially false";

        if(l1 == 5 && l2 == 5) //false and false
            return "False";

        if ((l1 == 12 && l2 == 4) || (l1 == 4 && l2 == 12)) //partialFalse and true
            return "Partially false";

        if ((l1 == 12 && l2 == 5) || (l1 == 5 && l2 == 12)) //partialFalse and false
            return "False";

        //partialFalse and PartialFalse
        return "Partially False";
    }

    private static boolean checkIfItHasScrore(SocialPostEntity sp){
        return sp.getScore() != null;
    }

}
