package com.mygdx.game.repair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.GameJam;
import com.mygdx.game.entities.House;
import com.mygdx.game.entities.Plank;
import com.mygdx.game.helper.CamShake;
import com.mygdx.game.screens.MainGameScreen;

public class PlankRepair implements Screen {
    GameJam game;
    MainGameScreen main;
    public static OrthographicCamera cam;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private SpriteBatch batch = new SpriteBatch();
    //entities
    Plank plank;
    public PlankRepair(GameJam game, MainGameScreen main){
        this.game=game;
        this.main=main;
    }
    //TEMP
    ShapeRenderer shape = new ShapeRenderer();
    @Override
    public void show() {
        /*
        House.REPAIR_START=false;
        game.setScreen(main);*/
        //Init camera
        cam = new OrthographicCamera(160,120);
        cam.position.set(321,380,0);
        //Init Map
        map = new TmxMapLoader().load("repairmap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        //Init entities
        plank = new Plank(290,370);
    }

    @Override
    public void render(float delta) {
        //Update logic
        //Update plank logic
        cam.unproject(plank.boundClickPos);
        plank.update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Shake camera
        if (CamShake.getRumbleTimeLeft() > 0){
            CamShake.tick(Gdx.graphics.getDeltaTime());
            cam.translate(CamShake.getPos());
        }

        cam.update(); //Update camera
        batch.setProjectionMatrix(cam.combined);
        cam.unproject(plank.clickPos);
        //Draw
        mapRenderer.setView(cam);
        mapRenderer.render();
        batch.begin();
        plank.draw(batch);
        Gdx.graphics.setTitle(String.valueOf(Gdx.graphics.getFramesPerSecond())); //draw fps on title
        batch.end();
        if(House.REPAIR_START==false){
            House.CURRENT_PHASE++;
            game.setScreen(main);
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

    }
}
