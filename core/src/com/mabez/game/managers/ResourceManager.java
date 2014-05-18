package com.mabez.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 07/05/2014.
 */
public class ResourceManager {

    private HashMap<String,Sound> sounds;
    private HashMap<String, BitmapFont> fonts;

    private static final ResourceManager INSTANCE = new ResourceManager();

    public ResourceManager(){
        sounds = new HashMap<String, Sound>();
        fonts = new HashMap<String, BitmapFont>();
    }//HaspMap of Sounds

    public void loadSound(String path,String Key){//loads the sound into memory
        Sound s = Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(Key,s);
    }

    public void unLoadSound(String Key){//unload sound from memory
        Sound s = sounds.get(Key);
        if(s!= null){
            s.dispose();
        }
        sounds.remove(Key);
    }

    public Sound getSound(String Key){//returns the sound file
        Sound s = sounds.get(Key);
        return s;
    }

    public void TTFLoader(String TTFPATH,int size,String key){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(TTFPATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = gen.generateFont(parameter);
        fonts.put(key,font);
    }

    public BitmapFont getFont(String key){
        BitmapFont f = fonts.get(key);
        return f;
    }

    public void unLoadFont(String key){
        BitmapFont f = fonts.get(key);
        f.dispose();
        fonts.remove(key);
    }

    public static ResourceManager getInstance()//returns the ResourceManager in its current state
    {
        return INSTANCE;
    }



}