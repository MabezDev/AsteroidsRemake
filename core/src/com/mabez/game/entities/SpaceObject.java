package com.mabez.game.entities;

/**
 * Created by user on 18/05/2014.
 */
public class SpaceObject {

    protected static final float Pi = 3.14f;

    protected float x;
    protected float y;

    protected float dx;
    protected float dy;

    protected float directionRad;
    protected float maxSpeed;
    protected float rotationSpeed;

    protected int width;
    protected int height;

    protected float[] shapex;
    protected float[] shapey;

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getDirectionRad(){
        return directionRad;
    }

    public void setDirectionRad (float d){
        directionRad = d;
    }

    public void setMaxSpeed(float s){
        maxSpeed = s;
    }
}
