package com.mygdx.game.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameJam;
import com.mygdx.game.assets.Assets;

public class FlowerCollectible extends Entity {
    private Rectangle bounds;

    public boolean delete=false;
    Sound pluck = GameJam.assets.manager.get(Assets.pluck);
    public FlowerCollectible(float x, float y) {
        super(x, y);
        texture = GameJam.assets.manager.get(Assets.flower);
        width=texture.getWidth();
        height=texture.getHeight();
        bounds = new Rectangle(pos.x,pos.y,width/4,height/4);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(!delete){
            batch.draw(texture,pos.x,pos.y,width,height);
        }
    }

    @Override
    public void update(float delta) {
        checkCollision(Player.bounds);
    }
    public void checkCollision(Rectangle other){
        if(bounds.overlaps(other) && Gdx.input.isKeyJustPressed(Input.Keys.E)){
            pluck.play(1f);
            delete=true;
        }
    }
}
