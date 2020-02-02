package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.GameJam;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.cutscenes.Cutscene1;

public class MainMenuScreen implements Screen {
    private GameJam game;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font/cs.fnt"),false);
    private SpriteBatch batch;
    private Texture bigdog = GameJam.assets.manager.get(Assets.bigdog);
    private Texture bighammer = GameJam.assets.manager.get(Assets.bighammer);
    private Texture backgroundtex = GameJam.assets.manager.get(Assets.background);
    private Image bg = new Image(backgroundtex);
    private Stage stage;
    public MainMenuScreen(GameJam game){
        this.game=game;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(bg);
        bg.setPosition(0,0);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Go into first cutscene
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            stage.addAction(Actions.sequence(Actions.fadeOut(1f),Actions.run(new Runnable() {
                @Override
                public void run() {
                    game.setScreen(new Cutscene1(game));
                    //game.setScreen(new MainGameScreen(game));
                }
            })));
        }
        //Clear the screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        //Update logic

        /*
        batch.begin();
        //Draw title
        font.setColor(Color.WHITE);
        font.getData().setScale(0.5f,0.5f);
        font.draw(batch,"Handy D0g",130,460);
        //Draw dog sprite
        batch.draw(bigdog,32,400);
        //Draw hammer sprite
        batch.draw(bighammer,512,400);
        //Draw movement text
        font.getData().setScale(0.1f,0.1f);
        font.setColor(Color.RED);
        font.draw(batch,"WASD - move dog", 320-64,240-32);
        //Draw E text
        font.setColor(Color.GREEN);
        font.draw(batch,"E - do stuff", 320-64,240);
        //Click stuff
        font.setColor(Color.CYAN);
        font.draw(batch,"left click - start",320-64,240-64);

        batch.end();*/
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
        backgroundtex.dispose();
    }
}
