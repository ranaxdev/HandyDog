package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameJam;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.helper.AnimHelper;
import com.mygdx.game.repair.WallPaint;

public class Wall extends Entity {

    TextureRegion[] brushFrames;
    TextureRegion currentBrushFrame;
    Animation<TextureRegion> brushAnim;
    float r,g,b;
    float texttime=0f;
    boolean enableText=false;
    int times_brushed=0;
    public Rectangle bounds;
    public Vector3 clickPos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
    public Vector3 boundClickPos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);

    private float statetime=0f;
    BitmapFont font = new BitmapFont(Gdx.files.internal("font/cs.fnt"),false);
    Sound brushsound = GameJam.assets.manager.get(Assets.brushsound);


    public Wall(float x, float y) {
        super(x, y);
        texture = GameJam.assets.manager.get(Assets.wall);
        brushFrames = AnimHelper.getFrames(GameJam.assets.manager.get(Assets.brush),1,6);
        //Init brush animation
        brushAnim = new Animation<TextureRegion>(0.05f,brushFrames);
        r = g = b = 128f/255f;
        //Collision boundary
        bounds = new Rectangle(pos.x,pos.y,texture.getWidth(),texture.getHeight());
        font.getData().setScale(0.045f,0.045f);

    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(r,g,b,1);
        batch.draw(texture,pos.x,pos.y,58,23);
        batch.setColor(Color.WHITE);
        //Draw brush frame
        batch.draw(currentBrushFrame,clickPos.x,clickPos.y);

        if(enableText){
            font.draw(batch,"good boye!",pos.x+16,pos.y-16);
        }
    }

    @Override
    public void update(float delta) {
        statetime+=Gdx.graphics.getDeltaTime();
        clickPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
        boundClickPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
        WallPaint.cam.unproject(boundClickPos);

        //Paint wall
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && bounds.contains(boundClickPos.x,boundClickPos.y)){
            brushsound.play(1f);
            if(r<1f && g<1f && b<1f){
                r+=0.025;
                g+=0.025;
                b+=0.025;
            }
        }
        if(r>=1f || g>=1f || b>=1f){
            texttime+=Gdx.graphics.getDeltaTime();
            enableText=true;
            if(texttime>=4){
                enableText=false;
                House.REPAIR_START=false;
            }
        }

        //brush anim on lmb
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            statetime=0; //Rest hammer animation
        }

        if(!brushAnim.isAnimationFinished(statetime)){
            currentBrushFrame = brushAnim.getKeyFrame(statetime,false);
        }
    }
}
