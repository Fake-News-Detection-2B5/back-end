package com.example.demo;

public class AIscore {
    private Integer newScore;
    private Boolean score;

    public AIscore(Integer newScore) {
        this.newScore = newScore;
    }


    public AIscore(){
        newScore = 0;
    }

    public Integer getNewScore() {
        return newScore;
    }

    public void setNewScore(Integer newScore) {
        this.newScore = newScore;
    }

    public boolean chackIfHasScore(){
        if(this.newScore == 0){
            score = false;
            return false;
        }
        else
        {
            score = true;
            return true;
        }
    }
}
