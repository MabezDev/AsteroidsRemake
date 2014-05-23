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
        xdrag = screenX;
        ydrag = screenY;
        if(sm.getCurrentState().equals("MENU")){
            MyKeys.setKeyState(MyKeys.W,false);
            MyKeys.setKeyState(MyKeys.S,false);
            MyKeys.setKeyState(MyKeys.A,false);
            MyKeys.setKeyState(MyKeys.D,false);
            MyKeys.setKeyState(MyKeys.SPACE, false);


        }else if(sm.getCurrentState().equals("GG")){
            MyKeys.setKeyState(MyKeys.W,false);
            MyKeys.setKeyState(MyKeys.S,false);
            MyKeys.setKeyState(MyKeys.A,false);
            MyKeys.setKeyState(MyKeys.D,false);
            MyKeys.setKeyState(MyKeys.SPACE, false);

        }else if(sm.getCurrentState().equals("GAME")) {
            MyKeys.setKeyState(MyKeys.SPACE, true);
        }
            return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        afterx = screenX;
        aftery = screenY;
        int distancey = aftery - ydrag;
        int distancex = afterx - xdrag;

        System.out.println(distancex);
        //System.out.println(distancey);

        if(sm.getCurrentState().equals("MENU")){
            if(distancey>100){
                MyKeys.setKeyState(MyKeys.W,true);
            }else if(distancey<-100){
                MyKeys.setKeyState(MyKeys.S,true);
            } else if(distancey>-20 && distancey<20){
                MyKeys.setKeyState(MyKeys.SPACE, true);
            }


        } else if(sm.getCurrentState().equals("GAME")) {
            MyKeys.setKeyState(MyKeys.SPACE, false);

        } else if(sm.getCurrentState().equals("GG")){
            if(distancex<-100){
                MyKeys.setKeyState(MyKeys.A,true);
            } else if(distancex>100){
                MyKeys.setKeyState(MyKeys.D,true);
            }

            if(distancey>100){
                MyKeys.setKeyState(MyKeys.W,true);
            }else if(distancey<-100){
                MyKeys.setKeyState(MyKeys.S,true);
            }

            if(distancey>-20 && distancey<20){
                MyKeys.setKeyState(MyKeys.SPACE, true);
            }

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

