package com.mabez.game.managers;

import com.mabez.game.entities.ScoreHolder;

import java.io.*;

/**
 * Created by user on 18/05/2014.
 */
public class SaveHandler {

    public static ScoreHolder ScoreHolder;

    public static void save(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("scores.sav"));
            out.writeObject(ScoreHolder);
            out.close();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }

    }

    public static ScoreHolder getScoreHolderInstance(){
        return ScoreHolder;
    }

    public static void load(){
        try{
            if(!fileExists()){
                init();
                ScoreHolder.init();
                return;

            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("scores.sav"));
            ScoreHolder = (ScoreHolder)in.readObject();
            in.close();
        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public static boolean fileExists(){
        File f = new File("scores.sav");
        return f.exists();
    }

    public static void init(){
        ScoreHolder = new ScoreHolder();
        save();
    }
}
