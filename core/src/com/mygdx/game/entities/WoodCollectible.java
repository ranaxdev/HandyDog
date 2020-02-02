package com.mygdx.game.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameJam;
import com.mygdx.game.assets.Assets;

public class WoodCollectible extends Entity {
    private Rectangle bounds;

    public boolean delete=false;
    Sound woodsound = GameJam.assets.manager.get(Assets.woodsound);
    public WoodCollectible(float x, float y) {
        super(x, y);
        texture = GameJam.assets.manager.get(Assets.wood);
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
            delete=true;
            woodsound.play(1f);
        }
    }
}
