package com.mabez.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by user on 21/03/2015.
 */
public class Alien extends SpaceObject {

    private static final float Pi = 3.14f;
    private boolean isAlive;

    private static float acceleration = 100;
    private static float retardation = 5;


    private float[] shapex;
    private float[] shapey;



    public Alien(float x, float y) {
        this.x = x;
        this.y =  y;
        this.maxSpeed = 200;

        shapex = new float[4];
        shapey = new float[4];





    }

    public void update(float dt){
        setShape();
    }

    public void draw(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.line(shapex[0],shapey[0],shapex[1],shapey[1]);
        sr.line(shapex[1],shapey[1],shapex[2],shapey[2]);
        sr.end();
    }

    public void setShape(){

        shapex[0] = x + MathUtils.cos(directionRad) * 8;
        shapey[0] = y + MathUtils.sin(directionRad) * 8;

        shapex[1] = x + MathUtils.cos(directionRad + (Pi/4)) * 10;
        shapey[1] = y + MathUtils.sin(directionRad + (Pi/4)) * 10;

        shapex[2] = x + MathUtils.cos(directionRad - (Pi/4)) * 20;
        shapey[2] = y + MathUtils.sin(directionRad - (Pi/4)) * 20;

    }

    public boolean isAlive(){
        return isAlive;
    }

    public void PathFindTo(Node[] nodes){
        Node start = nodes[nodes.length - 1];
        Node end = nodes[nodes.length - 2];
    }



}
