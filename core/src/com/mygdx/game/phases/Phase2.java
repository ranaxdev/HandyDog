package com.mygdx.game.phases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.FlowerCollectible;
import com.mygdx.game.entities.Player;

public class Phase2 extends Entity {
    public static boolean ACTIVE=false;
    public static boolean COMPLETE=false;
    public float displaytime=0f;
    private static int NUM_FLOWERS=20;
    private Array<FlowerCollectible> flowers = new Array<>();
    private int randX,randY;
    public int collected=0;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font/cs.fnt"),false);
    public Phase2(float x, float y) {
        super(x, y);
        ACTIVE=true;
        for(int i=0; i<NUM_FLOWERS;i++){
            randX = MathUtils.random(190,646);
            randY = MathUtils.random(65,131);
            flowers.add(new FlowerCollectible(randX,randY));
        }
        //font init
        font.getData().setScale(0.045f,0.045f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        //Draw flowers
        for(FlowerCollectible f: flowers){
            f.draw(batch);
        }

    }

    @Override
    public void update(float delta) {
        displaytime+=delta;
        if(flowers.size!=0){
            for(FlowerCollectible f: flowers){
                f.update(delta);
                if(f.delete){
                    collected++;
                    flowers.removeValue(f,true);
                }
            }
        }
        if(flowers.size==0){
            COMPLETE=true;
        }
    }
}
