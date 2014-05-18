package com.mabez.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mabez.game.managers.AndroidInputProcessor;
import com.mabez.game.managers.MyInputProccesor;
import com.mabez.game.managers.MyKeys;
import com.mabez.game.managers.SceneManager;

public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    private SceneManager sm;



    private OrthographicCamera cam;
    public float camHeight;
    public float camWidth;
    private String Device;

    public Main(String Device) {
        this.Device = Device;
    }

    @Override
    public void create () {
        cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if(Device.equals("_desktop")){
            System.out.println("Gathering Input From: DESKTOP");
            Gdx.input.setInputProcessor(new MyInputProccesor());
        } else if(Device.equals("_android")){
            Gdx.input.setCatchBackKey(true);
            System.out.println("Gathering Input From: ANDROID");
            Gdx.input.setInputProcessor(new AndroidInputProcessor());
        }
        sm = new SceneManager(cam,Device);


    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sm.update(Gdx.graphics.getDeltaTime());
        sm.draw();
        MyKeys.update();

    }


}