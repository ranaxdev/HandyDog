package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameJam;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.helper.AnimHelper;
import com.mygdx.game.helper.CamShake;
import com.mygdx.game.repair.PlankRepair;

public class Picture extends Entity {
    TextureRegion[] pictureFrames;
    TextureRegion[] hammerFrames;
    TextureRegion currentHammerFrame;
    Animation<TextureRegion> hammerAnim;
    int times_hammered=0;
    public Vector3 clickPos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
    public Vector3 boundClickPos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
    public Rectangle bounds;
    private float statetime=0f;
    private float texttime=0f;
    private boolean enableText=false;
    int texture_index=0;
    BitmapFont font = new BitmapFont(Gdx.files.internal("font/cs.fnt"),false);
    Sound hammersound = GameJam.assets.manager.get(Assets.hammersound);
    public Picture(float x, float y) {
        super(x, y);
        pictureFrames = AnimHelper.getFrames(GameJam.assets.manager.get(Assets.picture),1,4);
        hammerFrames = AnimHelper.getFrames(GameJam.assets.manager.get(Assets.hammer),1,7);
        //Init hammer animation
        hammerAnim = new Animation<TextureRegion>(0.05f,hammerFrames);
        currentHammerFrame = hammerAnim.getKeyFrame(0,false);
        //Collision boundary
        bounds = new Rectangle(pos.x,pos.y,64,64);

        font.getData().setScale(0.045f,0.045f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(pictureFrames[texture_index],pos.x,pos.y,80,60);

        //Draw hammer when hitting
        batch.draw(currentHammerFrame,clickPos.x,clickPos.y);

        if(enableText){
            font.draw(batch,"<3",pos.x-8,pos.y+36);
        }
    }

    @Override
    public void update(float delta) {
        statetime+=Gdx.graphics.getDeltaTime();

        clickPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
        boundClickPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
        PlankRepair.cam.unproject(boundClickPos);
        //Hammer anim on lmb
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            statetime=0; //Rest hammer animation
        }
        //Plank frame change on hitting nail
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && bounds.contains(boundClickPos.x,boundClickPos.y)){
            hammersound.play(1f);
            times_hammered++;
            CamShake.rumble(1,.2f);
            if(texture_index!=3 && times_hammered>8){
                texture_index++;
            }
        }

        if(!hammerAnim.isAnimationFinished(statetime)){
            currentHammerFrame = hammerAnim.getKeyFrame(statetime,false);
        }
        //End phase 3
        if(texture_index==3){
            enableText=true;
            texttime+=Gdx.graphics.getDeltaTime();
            if(texttime>=3){
                enableText=false;
                House.REPAIR_START=false;
            }
        }
    }
}
