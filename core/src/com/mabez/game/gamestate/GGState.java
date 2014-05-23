package com.mabez.game.gamestate;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mabez.game.entities.ScoreHolder;
import com.mabez.game.managers.MyKeys;
import com.mabez.game.managers.SaveHandler;
import com.mabez.game.managers.SceneManager;
import com.mabez.game.managers.ScoreHandler;
import com.sun.org.apache.xerces.internal.impl.io.ASCIIReader;

import java.util.Arrays;

/**
 * Created by user on 23/05/2014.
 */
public class GGState extends BaseState {

    private BitmapFont GAMEOVER_FONT;
    private BitmapFont NORMAL_FONT;
    private SpriteBatch sb;
    private char[] name;
    private int index = 0;

    public GGState(SceneManager sm) {
        super(sm);
    }

    @Override
    public void init() {
        GAMEOVER_FONT = resManager.getFont("menu_title");
        NORMAL_FONT = resManager.getFont("menu_text");
        sb = new SpriteBatch();
        name = new char[3];
        for(int i =0; i<3;i++){
            name[i] = 'A';
        }
    }

    @Override
    public void draw() {
        sb.begin();
        GAMEOVER_FONT.draw(sb,"GAMEOVER",sm.cam.viewportWidth/2-GAMEOVER_FONT.getBounds("GAMEOVER").width/2,500);

        for(int i = 0;i<3;i++){

            if(i==index){
                NORMAL_FONT.setColor(Color.RED);
            } else{
                NORMAL_FONT.setColor(Color.WHITE);
            }
            NORMAL_FONT.draw(sb,Character.toString(name[i]),sm.cam.viewportWidth/2 - (NORMAL_FONT.getBounds(Character.toString(name[i])).width+35) +i*35,350);//-NORMAL_FONT.getBounds(Character.toString(name[i])).width/2
        }

        sb.end();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void HandleInput() {
        if(MyKeys.isPressed(MyKeys.D)){

            if(index>=2){
                index=2;
                return;
            } else{
                index++;
            }

        }
        if(MyKeys.isPressed(MyKeys.A)){

            if(index<=0){
                index=0;
                return;
            } else{
                index--;
            }
        }
        if(MyKeys.isPressed(MyKeys.W)){
            char temp = name[index];
            int Asciicode = (int) temp;
            Asciicode +=1;
            if(Asciicode >= 90){
                Asciicode = 90;
            }
            char fin = (char) Asciicode;
            name[index] = fin;
        }
        if(MyKeys.isPressed(MyKeys.S)){
            char temp = name[index];
            int Asciicode = (int) temp;
            Asciicode -=1;
            if(Asciicode <= 65){
                Asciicode = 65;
            }
            char fin = (char) Asciicode;
            name[index] = fin;
        }
        if(MyKeys.isPressed(MyKeys.SPACE)){
            if(sm.Device.equals("_desktop")){
                saveHighScore();
            } else if(sm.Device.equals("_android")){
                androidSaveHighScore();
            }
            sm.setState(SceneManager.MENU);
        }

    }

    private void saveHighScore(){
        SaveHandler.load();
        SaveHandler.getScoreHolderInstance().addNewScore(ScoreHandler.getTempScore(),buildNameString());//prob add this GG Scene; Need name input methods
        SaveHandler.save();
    }
    private String  buildNameString(){
        String string ="";
        for(char c : name){
            string += Character.toString(c);
        }
        return string;
    }

    private void androidSaveHighScore(){
        SaveHandler.AndroidLoad();
        SaveHandler.getScoreHolderInstance().addNewScore(ScoreHandler.getTempScore(),buildNameString());//prob add this GG Scene; Need name input methods
        SaveHandler.AndroidSave();
    }

    @Override
    public void dispose() {

    }
}
