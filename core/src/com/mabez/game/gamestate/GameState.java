package com.mabez.game.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mabez.game.entities.Asteroid;
import com.mabez.game.entities.Bullet;
import com.mabez.game.entities.Player;
import com.mabez.game.entities.ScoreHolder;
import com.mabez.game.managers.MyKeys;
import com.mabez.game.managers.SaveHandler;
import com.mabez.game.managers.SceneManager;
import com.mabez.game.managers.ScoreHandler;

import java.util.ArrayList;

public class GameState extends BaseState {

    public GameState(SceneManager sm) {
        super(sm);
    }

    private static final int maxBullets = 4;
    private ShapeRenderer sr;
    private BitmapFont font;
    private SpriteBatch sb;
    private boolean isPaused;

    private Player player;
    private ArrayList<Asteroid> asteroids;
    public ArrayList<Bullet> bullets;
    private static int  NUM_ASTEROIDS = 4;
    private boolean EscapeToggle = false;
    private Sound bulletNoise;
    private Sound thruster;
    float counter = 0;

    private float accelX;
    private float accelY;
    private float accelZ;

    private ScoreHandler sh;

    @Override

    public void init() {
        isPaused=false;


        bulletNoise = resManager.getSound("bullet");//sound for bullets, pretty good Imo
        thruster = resManager.getSound("flame");//sound for thrusters, need work because it sounds retarded


        font = resManager.getFont("menu_text");//initialize font
        font.setColor(Color.WHITE);

        sb =  new SpriteBatch();// for font drawing

        sh = new ScoreHandler();


        player = new Player(sm.cam,sm);//create instance of the player
        asteroids = new ArrayList<Asteroid>();//instantiate array of asteroids
        bullets = new ArrayList<Bullet>();//instantiate array of bullets


        sr = new ShapeRenderer();//for rendering the player

        player.setShape();// initialize player shape
    }

    public void Pause(){ isPaused = true; } //setter for pausing the game

    public void Resume(){
        isPaused = false;
    } // setter for resuming the game


    @Override
    public void draw() {

        //if the player is alive draw the ship and bullets
        if(player.isAlive()) {
            player.draw(sr);

            for(Bullet b: bullets){
                b.draw(sr);
            }
        }
        //draw asteroid always
        for(int i =0; i<asteroids.size();i++){
            asteroids.get(i).draw(sr);
        }
        if(isPaused){
            sb.begin();
            font.draw(sb,"Paused",sm.cam.viewportWidth/2-(float)font.getBounds("Paused").width/2,
                    sm.cam.viewportHeight/2-font.getBounds("Paused").height/2+100);
            font.draw(sb,"Press Space to Quit, Escape to Resume!",sm.cam.viewportWidth/2-(float)font.getBounds("Press Space to Quit, Escape to Resume!").width/2,
                    sm.cam.viewportHeight/2-font.getBounds("Press Space to Quit, Escape to Resume!").height/2+(50));
            sb.end();
        }
        if(!player.isAlive()){
            sb.begin();
            font.draw(sb,"Game Over",sm.cam.viewportWidth/2-(float)font.getBounds("Game Over").width/2,
                    sm.cam.viewportHeight/2-font.getBounds("Game Over").height/2);
            sb.end();
        }

        sb.begin();

        font.draw(sb,sh.getScoreString(),0 + font.getBounds("00000000").width/4,
                sm.cam.viewportHeight-font.getBounds("00000000").height);
        sb.end();

    }

    @Override
    public void update(float dt) {
        //if paused don't update anything
        if(isPaused==false) {
            //if player is dead go back to menu after 2 seconds
            if(player.isAlive()) {
                player.update(dt);
                for (int i = 0; i < bullets.size(); i++) {
                    if (bullets.get(i).shouldRemove()) {
                        bullets.remove(i);
                    } else {
                        bullets.get(i).update(dt);
                    }

                }

            } else {
                counter += dt;
                if(counter > 2.5f) {// two seconds of delay till going back to the main menu
                    /*if(sm.Device.equals("_desktop")){//add this to GGSCene
                        SaveHandler.load();
                        SaveHandler.getScoreHolderInstance().addNewScore(sh.getCurrentScore(),"SCO");//prob add this GG Scene; Need name input methods
                        SaveHandler.save();
                    } else if(sm.Device.equals("_android")){
                        SaveHandler.AndroidLoad();
                        SaveHandler.getScoreHolderInstance().addNewScore(sh.getCurrentScore(),"SCO");//prob add this GG Scene; Need name input methods
                        SaveHandler.AndroidSave();
                    }*/
                    sh.setTempScore(sh.getCurrentScore());
                    sm.setState(SceneManager.GG);
                } else {

                }

            }
            //always update asteroids
            for (int i = 0; i < asteroids.size(); i++) {
                asteroids.get(i).update(dt);
            }


            if (asteroids.size() == NUM_ASTEROIDS) {

            } else {
                asteroids.add(new Asteroid(sm.cam));
            }



            checkCollisions();
        }
    }


    /*
        Creates a rectangle hit box around the objects then checks
        if any overlap returning a boolean where it collides
        or not.
     */
    private void checkCollisions(){
        for(int i=0;i<bullets.size();i++){
            for(int j=0;j<asteroids.size();j++){
                Bullet b = bullets.get(i);
                Asteroid a = asteroids.get(j);
                float xa= a.getX();
                float ya= a.getY();
                float xb= b.getX();
                float yb = b.getY();
                if(contains(xa,ya,xb,yb)){
                    asteroids.remove(j);// removes asteroid
                    bullets.get(i).setDead();//notifies loop for it to be cleaned up
                    sh.incrementScore(100);//increments score

                }

            }
        }

        for(int i =0; i<asteroids.size();i++){
            Asteroid a = asteroids.get(i);
            float asteroidx = a.getX();
            float asteroidy = a.getY();
            float playerx = player.getX();
            float playery = player.getY();
            if(contains(asteroidx,asteroidy,playerx+12,playery+10)){
                player.setDead();
            }
        }
    }

    private boolean contains(float asteroidx,float asteroidy,float bulletx, float bullety) {// function for creating hit box and comparing
        boolean b = false;
        if ((bulletx > asteroidx && bulletx < asteroidx + 40) && (bullety > asteroidy && bullety < asteroidy + 40)){
            b = true;
        }
        return b;
    }

    public void fire(float x,float y, float direction){
        if(!isPaused) {
            if (bullets.size() == maxBullets) {
                return;//do nothing
            } else {
                bullets.add(new Bullet(x, y, direction, sm.cam));//create new bullet instance
                bulletNoise.play(0.08f);//playSound


            }
        }
    }




    @Override
    public void HandleInput() {
        if(!isPaused) {
            if (sm.Device.equals("_desktop")) {
                if (MyKeys.isDown(MyKeys.W)) {
                    player.up = true;

                } else {
                    player.up = false;
                }
                if (MyKeys.isDown(MyKeys.A)) {
                    player.left = true;
                } else {
                    player.left = false;
                }
                if (MyKeys.isDown(MyKeys.D)) {
                    player.right = true;
                } else {
                    player.right = false;
                }

                if (MyKeys.isPressed(MyKeys.SHIFT)) {
                    player.shift = true;
                } else {
                    player.shift = false;
                }

            } else if (sm.Device.equals("_android")) {
                HandleAccelerometer();
            }

            if (MyKeys.isPressed(MyKeys.SPACE)) {
                if (player.isAlive()) {
                    fire(player.getX(), player.getY(), player.getDirectionRad());
                }
            }

            if (MyKeys.isPressed(MyKeys.ESCAPE)) {
                EscapeToggle = !EscapeToggle;
            }
        } else {
            if (MyKeys.isPressed(MyKeys.ESCAPE)) {
                EscapeToggle = !EscapeToggle;
            }
            if(MyKeys.isPressed(MyKeys.SPACE)){
                EscapeToggle = !EscapeToggle;
                player.setDead();
            }
        }
        if (EscapeToggle) {
            Pause();
        } else {
            Resume();
        }

    }

    public void HandleAccelerometer(){
        float orientation = Gdx.input.getRotation();
        //System.out.println("NATIVE-ORIENTATION: " + Float.toString(orientation));
        accelX = Gdx.input.getAccelerometerX();
        accelY = Gdx.input.getAccelerometerY();
        accelZ = Gdx.input.getAccelerometerZ();
        //System.out.println("ACCEL-X: "+Float.toString(accelX));
        //System.out.println("ACCEL-Y: "+Float.toString(accelY));
        //System.out.println("ACCEL-Z: "+Float.toString(accelZ));
        if(accelX<9 && accelX > 2){
            player.up = true;
        }
        else{
            player.up=false;
        }
        if(accelY>2){
            player.right=true;
        } else {
            player.right=false;
        }
        if(accelY<-2){
            player.left=true;
        }else{
            player.left=false;
        }



    }

    @Override
    public void dispose() {
        //resManager.unLoadSound("flame");
       // resManager.unLoadSound("bullet");
    }
}


