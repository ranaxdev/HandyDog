package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DWorld {
    public World world;
    private Box2DDebugRenderer debugger;

    public Box2DWorld(){
        world = new World(new Vector2(0,0),true);
        debugger = new Box2DDebugRenderer();
    }
    public void tick(OrthographicCamera cam){

        //debugger.render(world,cam.combined);
        world.step(Gdx.app.getGraphics().getDeltaTime(),6,2);
        world.clearForces();
    }
}
