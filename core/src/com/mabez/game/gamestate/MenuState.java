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
    private HashMap<Integer,String> options;
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
        options = new HashMap<Integer, String>();
        options.put(0,"Play");
        options.put(1,"HighScores");
        options.put(2,"Quit");
        currentOption = "Play";
        index = 0;


    }







    @Override
    public void draw() {
        sb.begin();
        drawFont(titleFont,150,"ASTEROIDS",Color.WHITE);
        for(int i =0;i<options.size();i++){
            String temp = (String) options.get(i);

            if(temp.equals(currentOption)){
                drawFont(font,125+((i + 1)*-65),temp,Color.RED);
            }
            drawFont(font,125 + ((i + 1) * -65), temp, Color.WHITE);
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
            if(index<=0){
                index= options.size()-1;
            }else{
                index--;
            }
        }
        if(MyKeys.isPressed(MyKeys.S)){
            if(index>=options.size()-1){
                index=0;
            }else {
                index++;
            }
        }
        currentOption = options.get(index);
        if(MyKeys.isPressed(MyKeys.SPACE)) {
            doChoice(index);
        }


    }

    private void doChoice(int choice){
        String temp = options.get(choice);
        if(temp.equals("Play")){
            sm.setState(SceneManager.GAME);
        }
        if(temp.equals("Quit")){
            System.exit(0);
        }
    }

    @Override
    public void dispose() {

    }
}
