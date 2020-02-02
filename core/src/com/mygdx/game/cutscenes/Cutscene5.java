package com.mygdx.game.cutscenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.GameJam;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.screens.MainGameScreen;

public class Cutscene5 implements Screen {
    GameJam game;
    MainGameScreen main;
    Music song;
    Stage stage;
    float timer=0f;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font/cs.fnt"),false);

    //Actors
    Image pic = new Image(GameJam.assets.manager.get(Assets.memory4));
    Label text1 = new Label("Dear friend..", new Label.LabelStyle(font, Color.WHITE));
    Label text2 = new Label("I made things right.", new Label.LabelStyle(font, Color.WHITE));
    Label text3 = new Label("Thank you for being by my side", new Label.LabelStyle(font, Color.WHITE));
    Label text4 = new Label("Goodbye friend.", new Label.LabelStyle(font, Color.WHITE));
    OrthographicCamera cam;
    public Cutscene5(GameJam game, MainGameScreen main, Music song) {
        this.song=song;
        this.game=game;
        this.main=main;
        cam = new OrthographicCamera();
        cam.setToOrtho(false,640,480);
        stage = new Stage(new StretchViewport(640,480));
        stage.getViewport().apply();
        font.getData().setScale(0.2f,0.2f);

        text1.setPosition(32,328);
        text1.setColor(1,1,1,0);

        text2.setPosition(32,text1.getY()-36);
        text2.setColor(1,1,1,0);

        text3.setPosition(32,text2.getY()-36);
        text3.setColor(1,1,1,0);

        text4.setPosition(32,text3.getY()-36);
        text4.setColor(1,1,1,0);

        //Add actors
        stage.addActor(pic);
        stage.addActor(text1);
        stage.addActor(text2);
        stage.addActor(text3);
        stage.addActor(text4);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        timer+=delta;
        if(timer>4f){
            if(text1.getColor().a<=1f){
                text1.setColor(1,1,1,text1.getColor().a+=0.05f);
            }
        }
        if(timer>8f){
            if(text2.getColor().a<=1f){
                text2.setColor(1,1,1,text2.getColor().a+=0.05f);
            }
        }
        if(timer>12f){
            if(text3.getColor().a<=1f){
                text3.setColor(1,1,1,text3.getColor().a+=0.05f);
            }
        }
        if(timer>16f){
            if(text4.getColor().a<=1f){
                text4.setColor(1,1,1,text4.getColor().a+=0.05f);
            }
        }
        if(timer>24f){

            stage.addAction(Actions.sequence(Actions.fadeOut(3f),Actions.run(new Runnable() {
                @Override
                public void run() {
                    game.setScreen(new End(game,song));
                }
            })));
        }
        //Clear the screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
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

    }
}
