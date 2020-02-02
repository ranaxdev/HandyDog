package com.mygdx.game.phases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.FlowerCollectible;
import com.mygdx.game.entities.FoodCollectible;
import com.mygdx.game.entities.Player;
import com.mygdx.game.screens.MainGameScreen;

public class Phase3 extends Entity {
    public static boolean ACTIVE=false;
    public static boolean COMPLETE=false;
    public float displaytime=0f;
    private static int NUM_FOOD=30;
    private Array<FoodCollectible> foods = new Array<>();
    private int randX,randY,randArea;
    public int collected=0;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font/cs.fnt"),false);
    public Phase3(float x, float y) {
        super(x, y);
        ACTIVE=true;
        for(int i=0; i<NUM_FOOD;i++){
            randArea = MathUtils.random(0,1);
            if(randArea==0){
                randX = MathUtils.random(190,646);
                randY = MathUtils.random(65,131);
            } else{
                randX = MathUtils.random(286,631);
                randY = MathUtils.random(539,624);
            }
            foods.add(new FoodCollectible(randX,randY));
        }
        //font init
        font.getData().setScale(0.045f,0.045f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        //Draw foods
        for(FoodCollectible f: foods){
            f.draw(batch);
        }

    }

    @Override
    public void update(float delta) {
        displaytime+=delta;
        if(foods.size!=0){
            for(FoodCollectible f: foods){
                f.update(delta);
                if(f.delete){
                    collected++;
                    foods.removeValue(f,true);
                }
            }
        }
        if(foods.size==0){
            COMPLETE=true;

        }
    }
}
