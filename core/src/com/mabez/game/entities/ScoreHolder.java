package com.mabez.game.entities;

import com.mabez.game.managers.MyKeys;

import javax.lang.model.element.Name;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by user on 18/05/2014.
 */
public class ScoreHolder implements Serializable {

    //will be serialize with top ten scores
    //will generate empty ones to fill gaps
    // i.e player = -----
    //will be loaded on splash screen
    //data from here will be displayed in HighScoresState
    //Sort form highest to lowest top 10, using bubble sort or something#

    protected static final long serialVersionUID = 1;

    protected static final int MAX_SCORES = 10;
    protected static final int PLAYER_NAME_SIZE = 3;


    private String[] Names;
    private long[] Scores;
    public ScoreHolder() {
        Scores = new long[MAX_SCORES];
        Names = new String[MAX_SCORES];
        init();
    }

    public void init(){
        for(int i=0; i<MAX_SCORES;i++){
            Scores[i] = 0;
            Names[i] = "---";
        }

    }
    public long[] getScoreArray(){
        return Scores;
    }

    public String[] getNameArray(){
        return Names;
    }

    private boolean isNewHighScore(long score){
        return score > Scores[MAX_SCORES-1];
    }

    public void addNewScore(long score,String name){
        if(isNewHighScore(score)){
            Scores[MAX_SCORES-1] = score;
            Names[MAX_SCORES- 1] = name;
            sortArray();

        }
    }

    private void sortArray(){
        for(int i = 0; i < MAX_SCORES; i++) {
            long score = Scores[i];
            String name = Names[i];
            int j;
            for(j = i - 1;
                j >= 0 && Scores[j] < score;
                j--) {
                Scores[j + 1] = Scores[j];
                Names[j + 1] = Names[j];
            }
            Scores[j + 1] = score;
            Names[j + 1] = name;
        }
        System.out.print(Arrays.toString(Scores));
    }





}
