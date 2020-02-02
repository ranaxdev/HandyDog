package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.GameJam;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.helper.AnimHelper;
import com.mygdx.game.helper.Box2DHelper;
import com.mygdx.game.helper.Box2DWorld;

public class Player extends Entity {
    private float movespeed = 200f;
    private float statetime=0f;
    private float dirX,dirY;
    public static Rectangle bounds;
    private static final int FRAME_ROWS =1, FRAME_COLS=4;
    public Body body;
    public Animation<TextureRegion> walkupAnim,walkdownAnim,walkleftAnim,walkrightAnim;
    public TextureRegion currentFrame;
    public TextureRegion[] walkup,walkdown,walkleft,walkright;
    private Anims currentAnim;
    private enum Anims{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    private Sound footsteps = GameJam.assets.manager.get(Assets.footstep);
    long id = footsteps.play(0f);
    float timeToFootstep=0f;
    public Player(float x, float y, Box2DWorld box2d) {
        super(x, y);
        //Create frames
        walkup = AnimHelper.getFrames(GameJam.assets.manager.get(Assets.dog_walkup),1,4);
        walkdown = AnimHelper.getFrames(GameJam.assets.manager.get(Assets.dog_walkdown),1,4);
        walkleft = AnimHelper.getFrames(GameJam.assets.manager.get(Assets.dog_walkleft),4,1);
        walkright = AnimHelper.getFrames(GameJam.assets.manager.get(Assets.dog_walkright),4,1);
        //Create animation objects
        walkupAnim = new Animation<TextureRegion>(0.05f,walkup);
        walkdownAnim = new Animation<TextureRegion>(0.05f,walkdown);
        walkleftAnim = new Animation<TextureRegion>(0.05f,walkleft);
        walkrightAnim = new Animation<TextureRegion>(0.05f,walkright);
        footsteps.setLooping(id,true);

        width = 16;
        height = 16;
        //Create dynamic body for collisions
        body = Box2DHelper.createBody(box2d.world, width,height,pos, BodyDef.BodyType.DynamicBody);

        //Inital animation
        currentFrame = walkdownAnim.getKeyFrame(statetime,true);
        currentAnim = Anims.DOWN;

        //init bounds
        bounds = new Rectangle(pos.x,pos.y,width,height);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame,pos.x,pos.y,width,height);

    }

    @Override
    public void update(float delta) {
        //Update bounds
        bounds.setPosition(pos.x,pos.y);
        statetime+=Gdx.graphics.getDeltaTime();

        //Handle animation
        if(dirX>0){
            currentFrame = walkrightAnim.getKeyFrame(statetime,true);
            currentAnim = Anims.RIGHT;
        }
        if(dirX<0){
            currentFrame = walkleftAnim.getKeyFrame(statetime,true);
            currentAnim = Anims.LEFT;
        }
        if(dirY > 0){
            currentFrame = walkupAnim.getKeyFrame(statetime,true);
            currentAnim = Anims.UP;
        }
        if(dirY<0){
            currentFrame = walkdownAnim.getKeyFrame(statetime,true);
            currentAnim = Anims.DOWN;
        }
        //Moving, so play footstep sound
        if(dirX!=0 || dirY!=0){
            timeToFootstep-=delta;
            if(timeToFootstep<0){
                footsteps.play(1f);
                while(timeToFootstep<0){
                    timeToFootstep+=0.2f;
                }
            }
        } else{
            timeToFootstep=0;
        }
        //If not moving, get first frame of anim
        if((dirX==0 && dirY==0)){
            switch (currentAnim){
                case UP:{
                    currentFrame = walkupAnim.getKeyFrame(statetime,false);
                    statetime=0;
                    break;
                }
                case DOWN:{
                    currentFrame = walkdownAnim.getKeyFrame(statetime,false);
                    statetime=0;
                    break;
                }
                case LEFT:{
                    currentFrame = walkleftAnim.getKeyFrame(statetime,false);
                    statetime=0;
                    break;
                }
                case RIGHT:{
                    currentFrame = walkrightAnim.getKeyFrame(statetime,false);
                    statetime=0;
                    break;

                }
            }
        }
        dirX=0;
        dirY=0;
        //Input
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            dirY=1*movespeed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            dirX=-1*movespeed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            dirY=-1*movespeed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            dirX=1*movespeed;
        }
        body.setLinearVelocity(dirX,dirY);
        pos.x = body.getPosition().x - width/2;
        pos.y = body.getPosition().y-height/2;
    }
}
