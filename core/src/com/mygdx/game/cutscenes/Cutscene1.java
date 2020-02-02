package com.mygdx.game.cutscenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.GameJam;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.screens.MainGameScreen;

public class Cutscene1 implements Screen {
    GameJam game;
    Stage stage;
    float timer=0f;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font/cs.fnt"),false);
    float text1a,text2a,text3a,text4a,text5a,text6a,text7a;
    public static Music gamemusic =  GameJam.assets.manager.get(Assets.gamemusic1);
    //Actors (labels)
    Label text1 = new Label("Dear friend..", new Label.LabelStyle(font, Color.WHITE));
    Label text2 = new Label("Stop playing dead.", new Label.LabelStyle(font, Color.WHITE));
    Label text3 = new Label("That was supposed to be...", new Label.LabelStyle(font, Color.WHITE));
    Label text4 = new Label("My game.", new Label.LabelStyle(font, Color.WHITE));

    Label text5 = new Label("Dear friend..", new Label.LabelStyle(font, Color.WHITE));
    Label text6 = new Label("I will finish repairing your house.", new Label.LabelStyle(font, Color.WHITE));
    Label text7 = new Label("So you can sleep inside", new Label.LabelStyle(font, Color.WHITE));
    Image sadimage = new Image(GameJam.assets.manager.get(Assets.sad));
    float mvol=gamemusic.getVolume();
    boolean stopped=false;
    OrthographicCamera cam;
    public Cutscene1(GameJam game){
        text1a = text2a = text3a = text4a = text5a = text6a = text7a =0;
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false,640,480);
        stage = new Stage(new StretchViewport(640,480));
        stage.getViewport().apply();
        Gdx.input.setInputProcessor(stage);
        font.getData().setScale(0.2f,0.2f);
        //Actors
        text1.setPosition(64,328);
        text1.setColor(1,1,1,text1a);

        text2.setPosition(64,text1.getY()-36);
        text2.setColor(1,1,1,text2a);

        text3.setPosition(64,text2.getY()-36);
        text3.setColor(1,1,1,text3a);

        text4.setPosition(64,text3.getY()-36);
        text4.setColor(1,1,1,text4a);

        text5.setPosition(64,328);
        text5.setColor(1,1,1,text5a);

        text6.setPosition(64,text5.getY()-36);
        text6.setColor(1,1,1,text6a);

        text7.setPosition(64,text6.getY()-36);
        text7.setColor(1,1,1,text7a);

        //add
        stage.addActor(text2);
        stage.addActor(text1);
        stage.addActor(text3);
        stage.addActor(text4);
        stage.addActor(text5);
        stage.addActor(text6);
        stage.addActor(text7);

    }

    @Override
    public void show() {
        stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2f)));
    }

    @Override
    public void render(float delta) {
        timer+=delta;
        if(timer>4f){
            gamemusic.play(); //Play music
            if(text1a<=1){
                text1.setColor(1,1,1,text1a+=0.1);
            }
        }
        if(timer>12f){
            if(text2a<=1){
                text2.setColor(1,1,1,text2a+=0.1);
            }
        }
        if(timer>19f){
            if(text3a<=1){
                text3.setColor(1,1,1,text3a+=0.1);
            }
        }
        if(timer>26f){
            if(text4a<=1){
                text4.setColor(1,1,1,text4a+=0.1);
            }
        }
        if(timer>35f){
            text1.addAction(Actions.removeActor());
            text2.addAction(Actions.removeActor());
            text3.addAction(Actions.removeActor());
            text3.addAction(Actions.removeActor());
            text4.addAction(Actions.removeActor());
            stage.addActor(sadimage);
        }
        if(timer>42f){
            sadimage.addAction(Actions.removeActor());
        }
        if(timer>45f){
            if(text5a<=1){
                text5.setColor(1,1,1,text5a+=0.1);
            }
        }
        if(timer>50f){
            if(text6a<=1){
                text6.setColor(1,1,1,text6a+=0.1);
            }
        }
        if(timer>55f){
            if(text7a<=1){
                text7.setColor(1,1,1,text7a+=0.1);
            }
        }
        if(timer>60f){
            text5.addAction(Actions.removeActor());
            text6.addAction(Actions.removeActor());
            text7.addAction(Actions.removeActor());

            if(gamemusic.isPlaying()){
                mvol -= delta * 0.1;
                if(mvol>0){
                    gamemusic.setVolume(mvol);
                } else{
                    gamemusic.stop();
                    stopped = true;
                }
            }
        }
        stage.act(delta);
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();

        if(!gamemusic.isPlaying() && stopped){
            game.setScreen(new MainGameScreen(game));
        }


    }

    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();

        gamemusic.dispose();
        font.dispose();
    }
}
