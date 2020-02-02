package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameJam;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.phases.Phase1;
import com.mygdx.game.phases.Phase2;
import com.mygdx.game.phases.Phase3;

public class House extends Entity {
    public Rectangle bounds;
    public static boolean REPAIR_START=false;
    public static int CURRENT_PHASE=1;
    public House(float x, float y) {
        super(x, y);
        texture = GameJam.assets.manager.get(Assets.house_start);
        width = texture.getWidth();
        height = texture.getHeight();
        bounds = new Rectangle(pos.x-6,pos.y-6,width+8,height);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture,pos.x,pos.y,width,height);
    }

    @Override
    public void update(float delta) {
        //Phase 1 finished (collected all wood), if pressed E on house, change to repair 1 screen (PLANK REPAIR)
        if(Phase1.COMPLETE && Phase1.ACTIVE){
            if(bounds.overlaps(Player.bounds) && Gdx.input.isKeyJustPressed(Input.Keys.E)){
                Phase1.COMPLETE=true;
                Phase1.ACTIVE=false;
                REPAIR_START=true;
            }
        }
        //Phase 2 finished (collected all flowers) press E on house, change to repair 2 screen (PAINTING)
        if(Phase2.COMPLETE && Phase2.ACTIVE){
            if(bounds.overlaps(Player.bounds) && Gdx.input.isKeyJustPressed(Input.Keys.E)){
                Phase2.COMPLETE=true;
                Phase2.ACTIVE=false;
                REPAIR_START=true;
            }
        }
        //Phase 3 finished (collected all foods) press E on house, cahnge to repair 3 screen (HANGING PICTURE)
        if(Phase3.COMPLETE && Phase3.ACTIVE){
            if(bounds.overlaps(Player.bounds) && Gdx.input.isKeyJustPressed(Input.Keys.E)){
                Phase3.COMPLETE=true;
                Phase3.ACTIVE=false;
                REPAIR_START=true;
            }
        }


        //Change texture depending on phase
        if(CURRENT_PHASE==2){
            texture = GameJam.assets.manager.get(Assets.house_middle);
        }
        if(CURRENT_PHASE==3){
            texture = GameJam.assets.manager.get(Assets.house_almost);
        }
        if(CURRENT_PHASE==4){
            texture = GameJam.assets.manager.get(Assets.house_complete);
        }
    }
}
