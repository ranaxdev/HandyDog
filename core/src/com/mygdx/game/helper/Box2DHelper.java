package com.mygdx.game.helper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Box2DHelper {
    public static Body createBody(World world, float width, float height, Vector2 pos, BodyDef.BodyType type){
        //Body definition
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos.x+width/2, pos.y+height/2);
        bodyDef.angle =0;
        bodyDef.fixedRotation =true;
        bodyDef.type = type;
        body = world.createBody(bodyDef);

        //Fixture definition
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(width/2,height/2);

        fixtureDef.shape = rect;
        fixtureDef.restitution = 0.3f;
        body.createFixture(fixtureDef);
       rect.dispose();
       return body;
    }
}
