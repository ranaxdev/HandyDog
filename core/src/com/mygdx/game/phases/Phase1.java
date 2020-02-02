package com.mygdx.game.phases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.cutscenes.Cutscene2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.WoodCollectible;
import com.mygdx.game.screens.MainGameScreen;

public class Phase1 extends Entity {
    public static boolean ACTIVE=false;
    public static boolean COMPLETE=false;
    private static int NUM_WOODS=15;
    private Array<WoodCollectible> woods = new Array<>();
    private int randX,randY;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font/cs.fnt"),false);
    public float displaytime=0f;
    public int collected=0;
    public Phase1(float x, float y) {
        super(x, y);
        ACTIVE=true; //Current phase is active

        //Generate random wood collectibles on the map
        for(int i=0; i<NUM_WOODS; i++){
            randX = MathUtils.random(286,631);
            randY = MathUtils.random(539,624);
            woods.add(new WoodCollectible(randX,randY));
        }
        //Init font
        font.getData().setScale(0.045f,0.045f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        //Draw wood collectibles
        for(WoodCollectible w: woods){
            w.draw(batch);
        }
        /*
        if(displaytime<4){
            font.draw(batch,"gotta get w00d", Player.bounds.x,Player.bounds.y-16);
        }*/
    }

    @Override
    public void update(float delta) {
        displaytime+=delta;

        //Remove wood if player collects it
        if(woods.size!=0){
            for(WoodCollectible w: woods){
                w.update(delta);
                if(w.delete){
                    woods.removeValue(w,true);
                    collected++;
                }
            }
        }
        //Check if all wood has been collected
        if(woods.size==0){
            COMPLETE=true;
        }

    }
}
