package com.mabez.game.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by user on 08/05/2014.
 */
public class AndroidInputProcessor implements InputProcessor{



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
        MyKeys.setKeyState(MyKeys.SPACE,true);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        MyKeys.setKeyState(MyKeys.SPACE,false);
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

