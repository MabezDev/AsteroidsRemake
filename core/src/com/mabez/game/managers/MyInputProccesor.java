package com.mabez.game.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by user on 03/05/2014.
 */
public class MyInputProccesor implements InputProcessor {


    @Override
    public boolean keyDown(int keycode) {
        if(keycode== Input.Keys.W){
            MyKeys.setKeyState(MyKeys.W,true);
        }
        if(keycode== Input.Keys.A){
            MyKeys.setKeyState(MyKeys.A,true);
        }
        if(keycode== Input.Keys.S){
            MyKeys.setKeyState(MyKeys.S,true);
        }
        if(keycode== Input.Keys.D){
            MyKeys.setKeyState(MyKeys.D,true);
        }
        if(keycode== Input.Keys.SPACE){
            MyKeys.setKeyState(MyKeys.SPACE,true);
        }
        if(keycode== Input.Keys.SHIFT_LEFT || keycode == Input.Keys.SHIFT_RIGHT){
            MyKeys.setKeyState(MyKeys.SHIFT,true);
        }
        if(keycode == Input.Keys.ESCAPE){
            MyKeys.setKeyState(MyKeys.ESCAPE,true);
        }



        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode== Input.Keys.W){
            MyKeys.setKeyState(MyKeys.W,false);
        }
        if(keycode== Input.Keys.A){
            MyKeys.setKeyState(MyKeys.A,false);
        }
        if(keycode== Input.Keys.S){
            MyKeys.setKeyState(MyKeys.S,false);
        }
        if(keycode== Input.Keys.D){
            MyKeys.setKeyState(MyKeys.D,false);
        }
        if(keycode== Input.Keys.SPACE){
            MyKeys.setKeyState(MyKeys.SPACE,false);
        }
        if(keycode== Input.Keys.SHIFT_LEFT || keycode== Input.Keys.SHIFT_RIGHT){
            MyKeys.setKeyState(MyKeys.SHIFT,false);
        }
        if(keycode ==  Input.Keys.ESCAPE){
            MyKeys.setKeyState(MyKeys.ESCAPE,false);
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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