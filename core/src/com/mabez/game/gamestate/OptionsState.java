package com.mabez.game.gamestate;

import com.mabez.game.managers.MyKeys;
import com.mabez.game.managers.SceneManager;

/**
 * Created by user on 02/11/2014.
 */
public class OptionsState extends BaseState {
    public OptionsState(SceneManager sm) {
        super(sm);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw() {

    }

    @Override
    public void update(float dt) {
        HandleInput();
    }

    @Override
    public void HandleInput() {
        if(MyKeys.isPressed(MyKeys.ESCAPE)){
            sm.setState(SceneManager.MENU);
        }
    }

    @Override
    public void dispose() {

    }
}
