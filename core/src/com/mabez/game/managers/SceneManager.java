package com.mabez.game.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mabez.game.gamestate.BaseState;
import com.mabez.game.gamestate.GameState;
import com.mabez.game.gamestate.MenuState;
import com.mabez.game.gamestate.SplashState;

/**
 * Created by user on 03/05/2014.
 */
public class SceneManager {

    public static final int MENU = 0;
    public static final int GAME = 1;
    public static final int SPLASH = 3;
    public static final int GG = 2;
    private static BaseState currentState;
    public OrthographicCamera cam;
    public static ResourceManager resourceManager;
    public static String Device;

    public SceneManager(OrthographicCamera cam,String Device) {
        this.cam = cam;
        this.Device = Device;
        resourceManager = new ResourceManager();
        setState(SPLASH);

    }




    public void setState(int i){
        if(currentState!=null){
            currentState.dispose();
        }
        if(i==SPLASH){
            currentState = new SplashState(this);
        }
        if(i==MENU){
            currentState = new MenuState(this);
        }
        if(i==GAME){
            currentState = new GameState(this);
        }
    }

    public void update(float dt){
        currentState.update(dt);
        currentState.HandleInput();
    }
    public void draw(){
        currentState.draw();
    }
}
