package com.mabez.game.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Asteroid extends SpaceObject {

    private float retardation = 10;
    private OrthographicCamera cam;//

    private boolean shouldRemove;

    public Asteroid(OrthographicCamera cam){
        shouldRemove=false;
        this.cam=cam;
        setMaxSpeed(50);
        dx = 0;
        dy=0;
        x = MathUtils.random(0, cam.viewportWidth);
        y = MathUtils.random(0,cam.viewportHeight);
        rotationSpeed = MathUtils.random(0.2f,1);

        shapex = new float[6];
        shapey = new float[6];

        directionRad = Pi/2;
        //setShape();
    }


    public void setShape(){
        shapex[0] = x + MathUtils.cos(directionRad)*50;
        shapey[0] = y +MathUtils.sin(directionRad)*50;

        shapex[1] = x + MathUtils.cos(directionRad - Pi/4) *30;
        shapey[1] = y + MathUtils.sin(directionRad - Pi/4)*30;

        shapex[2] = x + MathUtils.cos(directionRad + Pi)*10;
        shapey[2] = y + MathUtils.sin(directionRad + Pi)*10;

        shapex[3] = x + MathUtils.cos(directionRad + Pi/6)*20;
        shapey[3] = y + MathUtils.sin(directionRad + Pi/6)*20;

        shapex[4] = x+ MathUtils.cos(directionRad+ Pi/8)*15;
        shapey[4] = y +MathUtils.sin(directionRad+ Pi/8)*15;

        shapex[5] = x+ MathUtils.cos(directionRad+ Pi/7)*20;
        shapey[5] = y +MathUtils.sin(directionRad+ Pi/7)*20;


    }



    public boolean ShouldRemove(){
        return shouldRemove;
    }



    public void update(float dt){
        directionRad += rotationSpeed*dt;
        dx = MathUtils.cos(directionRad)*100;
        dx = MathUtils.sin(directionRad)*100;

        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if(vec > 0) {
            dx -= (dx / vec) * retardation * dt;
            dy -= (dy / vec) * retardation * dt;
        }
        if(vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        y+=dy*dt;
        x+=dx*dt;

        //setShape();


    }

    public void draw(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Line);

        sr.setColor(1,1,1,1);

        sr.rect(x,y,40,40);

       /* for(int i = 0, j = shapex.length - 1;i<shapex.length;j=i++){
            sr.line(shapex[i],shapey[i],shapex[j],shapey[j]);
        }
        */

        sr.end();
    }

}

