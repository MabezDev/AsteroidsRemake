package com.mabez.game.gamestate;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mabez.game.managers.MyKeys;
import com.mabez.game.managers.SceneManager;

import java.util.HashMap;


/**
 * Created by user on 03/05/2014.
 */
public class MenuState extends BaseState {

    private BitmapFont font;
    private BitmapFont titleFont;

    private SpriteBatch sb;
    private String[] options;
    private String currentOption;
    private int index;


    public MenuState(SceneManager sm) {
        super(sm);
    }

    @Override
    public void init() {
        font= resManager.getFont("menu_text");
        titleFont = resManager.getFont("menu_title");
        sb = new SpriteBatch();
        font.setColor(1,1,1,1);
        titleFont.setColor(1, 1, 1, 1);
        options =  new String[]{"Play","HighScores","Options","Quit"};
        index = 0;


    }







    @Override
    public void draw() {
        sb.begin();
        drawFont(titleFont,150,"ASTEROIDS",Color.WHITE);

        for(int i = 0; i < options.length; i++) {
            int width = (int)font.getBounds(options[i]).width;
            if(index == i) font.setColor(Color.RED);
            else font.setColor(Color.WHITE);
            font.draw(
                    sb,
                    options[i],
                    (sm.cam.viewportWidth- width) / 2,
                    325 - 35 * i
            );
        }



        sb.end();
    }

    private void drawFont(BitmapFont f,int y,String Text,Color c){
        BitmapFont font1 = f;
        font1.setColor(c);
        font1.draw(sb,Text,sm.cam.viewportWidth/2-font1.getBounds(Text).width/2,sm.cam.viewportHeight/2-font1.getBounds(Text).height+ y);
    }

    @Override
    public void update(float dt) {


    }

    @Override
    public void HandleInput() {
        if(MyKeys.isPressed(MyKeys.W)){
            if(index > 0) {
               index--;
            }
        }
        if(MyKeys.isPressed(MyKeys.S)){
            if(index < options.length - 1) {
                index++;
            }
        }
        currentOption = options[index];
        if(MyKeys.isPressed(MyKeys.SPACE)) {
            doChoice(index);
        }


    }

    private void doChoice(int choice){
        String temp = options[choice];
        if(temp.equals("Play")){
            sm.setState(SceneManager.GAME);
        }
        if(temp.equals("HighScores")){
            sm.setState(SceneManager.HIGHSCORES);
        }
        if(temp.equals("Quit")){
            System.exit(0);
        }
        if(temp.equals("Options")){
            sm.setState(SceneManager.OPTIONS);
        }
    }

    @Override
    public void dispose() {

    }
}
