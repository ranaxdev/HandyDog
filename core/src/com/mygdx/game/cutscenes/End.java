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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.GameJam;
import com.mygdx.game.screens.MainGameScreen;

public class End implements Screen {
    GameJam game;
    Music song;
    private Stage stage;
    float timer=0f;
    float mvol= MainGameScreen.gamemusic2.getVolume();
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font/cs.fnt"),false);
    Label text1 = new Label("Made by Sharjeel Qaiser", new Label.LabelStyle(font, Color.WHITE));
    OrthographicCamera cam;
    public End(GameJam game, Music song){
        this.game=game;
        this.song=song;
        cam = new OrthographicCamera();
        cam.setToOrtho(false,640,480);
        stage = new Stage(new StretchViewport(640,480));
        stage.getViewport().apply();
        font.getData().setScale(0.2f,0.2f);
        text1.setPosition((640/2)-160,(480/2)-16);
        text1.setColor(1,1,1,0);
        //Add actors

        stage.addActor(text1);
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
        if(timer>10f){
            if(song.isPlaying()){
                mvol -= delta * 0.01f;
                if(mvol>0){
                    song.setVolume(mvol);
                } else{
                    song.stop();
                }
            }
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
