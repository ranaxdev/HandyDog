package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    public Vector2 pos;
    public Texture texture;
    public float width,height;

    public Entity(float x,float y){
        pos = new Vector2(x,y);
        
    }
    public abstract void draw(SpriteBatch batch);
    public abstract void update(float delta);
}
