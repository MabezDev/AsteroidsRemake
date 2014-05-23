package com.mabez.game.managers;


public class ScoreHandler {

    private int currentScore;
    private static final int sigFigScore = 8;
    private static long tempScore =0;

    public ScoreHandler(){
        this.currentScore = 0;
    }


    public int getCurrentScore(){
        return  currentScore;
    }

    public String getScoreString(){
        String tempString = "";
        int initialLength = Integer.toString(currentScore).length();
        if(initialLength < sigFigScore){
            int zeroes2add = sigFigScore-initialLength;
            for(int i =0;i< zeroes2add;i++){
                tempString += "0";
            }
            tempString += Integer.toString(currentScore);
        } else {
            tempString = Integer.toString(currentScore);
        }
        return tempString;
    }

    public static void setTempScore(long i){
        tempScore = i;
    }

    public static long getTempScore(){
        return tempScore;
    }

    public void incrementScore(int i){
        currentScore += i;
    }

    public void reset(){
        currentScore = 0;
    }
}
