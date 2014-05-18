package com.mabez.game.gamestate;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mabez.game.entities.ScoreHolder;
import com.mabez.game.managers.SaveHandler;
import com.mabez.game.managers.SceneManager;

/**
 * Created by user on 18/05/2014.
 */
public class HighScoresState extends BaseState{

    private SaveHandler s;
    private long[] Scores;
    private String[] Names;
    private String format="";
    private float widthOfFont;

    private BitmapFont font;
    private SpriteBatch sb;

    public HighScoresState(SceneManager sm) {
        super(sm);
    }

    @Override
    public void init() {
        s = new SaveHandler();
        SaveHandler.init();
        SaveHandler.load();
        ScoreHolder sc = SaveHandler.getScoreHolderInstance();
        Names = sc.getNameArray();
        Scores = sc.getScoreArray();
        font = resManager.getFont("high_scores_text");
        sb = new SpriteBatch();

    }

    @Override
    public void draw() {
        sb.begin();
        widthOfFont=0f;
        for(int i=0; i<Scores.length;i++){
            format = String.format("%2d.,%7s,%s",i+1,Scores[i],Names[i]);
            widthOfFont = font.getBounds(format).width;
            font.draw(sb,format,(sm.cam.viewportWidth- widthOfFont)/2,400 - (i+1)*30);
        }
        sb.end();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void HandleInput() {

    }

    @Override
    public void dispose() {

    }
}
