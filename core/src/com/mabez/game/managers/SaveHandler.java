package com.mabez.game.managers;

import com.badlogic.gdx.Gdx;
import com.mabez.game.entities.ScoreHolder;

import java.io.*;

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

    public static void AndroidSave(){
        try{


            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Gdx.files.getLocalStoragePath()+"scores.sav"));//need to find location that not READONLY
            out.writeObject(ScoreHolder);
            out.close();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }

    }

    public static void AndroidLoad(){
        try{
            if(!fileExists(Gdx.files.getLocalStoragePath()+"scores.sav")){
                initAndroid();
                ScoreHolder.init();
                return;

            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(Gdx.files.getLocalStoragePath()+"scores.sav"));
            ScoreHolder = (ScoreHolder)in.readObject();
            in.close();
        }catch(Exception e){

            e.printStackTrace();
        }
    }


    public static ScoreHolder getScoreHolderInstance(){
        return ScoreHolder;
    }

    public static void load(){
        try{
            if(!fileExists("scores.sav")){
                initDesktop();
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

    public static boolean fileExists(String path){
        File f = new File(path);
        return f.exists();
    }

    public static void initDesktop(){
        ScoreHolder = new ScoreHolder();
        save();
    }

    public static void initAndroid(){
        ScoreHolder = new ScoreHolder();
        AndroidSave();
    }
}
