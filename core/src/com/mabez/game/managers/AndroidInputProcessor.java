package com.mabez.game.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by user on 08/05/2014.
 */
public class AndroidInputProcessor implements InputProcessor{

    private SceneManager sm;
    private int xdrag;
    private int ydrag;
    private int afterx;
    private int aftery;
    public AndroidInputProcessor(SceneManager sm) {
        this.sm=sm;
        xdrag=0;
        ydrag=0;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode==Input.Keys.BACK){
            MyKeys.setKeyState(MyKeys.ESCAPE,true);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode==Input.Keys.BACK){
            MyKeys.setKeyState(MyKeys.ESCAPE,false);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(sm.getCurrentState().equals("MENU")){
            MyKeys.setKeyState(MyKeys.W,false);
            MyKeys.setKeyState(MyKeys.S,false);
            xdrag = screenX;
            ydrag = screenY;

        } else if(sm.getCurrentState().equals("GAME")) {
            MyKeys.setKeyState(MyKeys.SPACE, true);
        }
            return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(sm.getCurrentState().equals("MENU")){
            afterx = screenX;
            aftery = screenY;
            int distance = aftery - ydrag;
            System.out.println(distance);
            if(distance>10){
                MyKeys.setKeyState(MyKeys.W,true);
                System.out.println("UP");
            }else if(distance<-10){
                MyKeys.setKeyState(MyKeys.S,true);
                System.out.println("DOWN");
            } else if(distance>-10 && distance<10){
                MyKeys.setKeyState(MyKeys.SPACE, true);
            }


        } else if(sm.getCurrentState().equals("GAME")) {
            MyKeys.setKeyState(MyKeys.SPACE, false);
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {



        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }



}

