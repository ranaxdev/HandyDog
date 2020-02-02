package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameJam;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.cutscenes.Cutscene2;
import com.mygdx.game.cutscenes.Cutscene3;
import com.mygdx.game.cutscenes.Cutscene4;
import com.mygdx.game.cutscenes.Cutscene5;
import com.mygdx.game.entities.House;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.WoodCollectible;
import com.mygdx.game.helper.Box2DHelper;
import com.mygdx.game.helper.Box2DWorld;
import com.mygdx.game.helper.MapHelper;
import com.mygdx.game.phases.Phase1;
import com.mygdx.game.phases.Phase2;
import com.mygdx.game.phases.Phase3;
import com.mygdx.game.repair.PictureHang;
import com.mygdx.game.repair.PlankRepair;
import com.mygdx.game.repair.WallPaint;

public class MainGameScreen implements Screen {
    //Sprite batch to render all graphics
    public SpriteBatch batch;
    public SpriteBatch hudBatch;
    GameJam game;
    private OrthographicCamera cam; //Camera

    //Entities
    private Player player;
    private House house;
    //Map objects
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    //Collision objects
    Array<Rectangle> bounds = new Array<>();
    Box2DWorld box2d;

    //Phases
    Phase1 phase1;
    Phase2 phase2;
    Phase3 phase3;
    //Repair screens
    PlankRepair plankrepair;
    WallPaint wallpaint;
    PictureHang picturehang;
    //TEMP
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    BitmapFont font = new BitmapFont(Gdx.files.internal("font/cs.fnt"),false);
    Music gamemusic = GameJam.assets.manager.get(Assets.gamemusic2);
    public static Music gamemusic2 = GameJam.assets.manager.get(Assets.gamemusic1);
    float endtimer=0f;
    float mvol;
    public MainGameScreen(GameJam game){
        gamemusic.play();
        gamemusic.setLooping(true);
        mvol = gamemusic.getVolume();

        this.game=game; //Set game
        batch = new SpriteBatch(); //batch instance
        hudBatch = new SpriteBatch();
        plankrepair = new PlankRepair(game,this);
        wallpaint = new WallPaint(game,this);
        picturehang = new PictureHang(game,this);
        //Init map
        map = new TmxMapLoader().load("map.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        //Init collision objects
        box2d = new Box2DWorld(); //Create world
        bounds = MapHelper.getMapCollisionBoundaries(map.getLayers().get(3).getObjects());
        for(Rectangle r: bounds){ //Create collidables
            Box2DHelper.createBody(box2d.world,r.width,r.height,new Vector2(r.x,r.y), BodyDef.BodyType.StaticBody);
        }
        //Init camera
        cam = new OrthographicCamera(160,140); //Create new camera

        //Init entities
        player = new Player(440,412,box2d);
        house = new House(417,434);

        //init font
        font.getData().setScale(0.045f,0.045f);
        font.setUseIntegerPositions(false);
    }
    //CREATE EVENT
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
            //Update logic
        //Phase logic
        if(Phase1.ACTIVE==false && Phase1.COMPLETE==false){ //Phase 1
            phase1 = new Phase1(0,0);
        }else{
            phase1.update(delta);
        }
        if(Phase2.ACTIVE==false && Phase2.COMPLETE==false && House.CURRENT_PHASE==2){ //Phase 2
            phase2 = new Phase2(0,0);
        } else if(Phase2.ACTIVE==true && Phase2.COMPLETE==false && House.CURRENT_PHASE==2){
            phase2.update(delta);
        }
        if(Phase3.ACTIVE==false && Phase3.COMPLETE==false && House.CURRENT_PHASE==3){//phase3
            phase3 = new Phase3(0,0);
        } else if(Phase3.ACTIVE==true && Phase3.COMPLETE==false && House.CURRENT_PHASE==3){
            phase3.update(delta);
        }
        //Fade out music
        if(Phase3.COMPLETE){
            if(gamemusic.isPlaying()){
                mvol -= delta * 2;
                if(mvol>0){
                    gamemusic.setVolume(mvol);
                } else{
                    gamemusic.stop();
                }
            }
        }
        //House logic
        house.update(delta);
        if(House.REPAIR_START && House.CURRENT_PHASE==1){
            game.setScreen(plankrepair);
        }
        if(House.REPAIR_START && House.CURRENT_PHASE==2){
            game.setScreen(wallpaint);
        }
        if(House.REPAIR_START && House.CURRENT_PHASE==3){
            gamemusic2.setVolume(1f);
            gamemusic2.play();
            game.setScreen(picturehang);
        }
        //Ending
        if(House.CURRENT_PHASE==4){
            endtimer+=delta;
            if(endtimer>6f){
                game.setScreen(new Cutscene5(game,this,gamemusic2));
            }
        }

        //Player logic
        player.update(delta);

        //Camera logic
        cam.position.set(player.pos.x+player.width/2,player.pos.y+player.height/2,0);

        //Clear the screen
        Gdx.gl.glClearColor(109f/255f,247f/255f,177f/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Camera stuff
        cam.update();
        batch.setProjectionMatrix(cam.combined);


            //Draw stuff on screen

        //Map rendering
        mapRenderer.setView(cam);
        mapRenderer.render(new int[]{0,1,3});
        //Sprite drawing (batch)
        batch.begin();
        //Draw phases
        if(Phase1.ACTIVE){
            phase1.draw(batch);
        }
        if(Phase2.ACTIVE){
            phase2.draw(batch);
        }
        if(Phase3.ACTIVE){
            phase3.draw(batch);
        }
        player.draw(batch); //draw player
        house.draw(batch); //draw house
        Gdx.graphics.setTitle(String.valueOf(Gdx.graphics.getFramesPerSecond())); //draw fps on title
        batch.end();
        mapRenderer.render(new int[]{2});
        //HUD DRAWING
        hudBatch.setProjectionMatrix(cam.combined);
        hudBatch.begin();
        //Phase 1
        if(Phase1.ACTIVE && House.CURRENT_PHASE==1){
            hudBatch.draw(GameJam.assets.manager.get(Assets.hudWood),cam.position.x-cam.viewportWidth/2,cam.position.y+cam.viewportHeight/2-8);
            font.draw(hudBatch,": "+String.valueOf(phase1.collected)+"/15",cam.position.x-cam.viewportWidth/2+8,cam.position.y+cam.viewportHeight/2);
            if(phase1.displaytime<6){
                font.draw(hudBatch,"gotta get w00d first",cam.position.x,cam.position.y-36);
            }
            //TRIGGER CUTSCENE
            if(phase1.collected>=7 && !Cutscene2.CUTSCENE_OVER){
                game.setScreen(new Cutscene2(game,this));
            }
        }
        //Draw objective text
        if( Phase2.ACTIVE && House.CURRENT_PHASE==2){
            hudBatch.draw(GameJam.assets.manager.get(Assets.hudFlower),cam.position.x-cam.viewportWidth/2,cam.position.y+cam.viewportHeight/2-8);
            font.draw(hudBatch,": "+String.valueOf(phase2.collected)+"/20",cam.position.x-cam.viewportWidth/2+8,cam.position.y+cam.viewportHeight/2);

            if(phase2.displaytime<4){
                font.draw(hudBatch,"n0w flowers for dye", cam.position.x,cam.position.y-16);
            }
            //TRIGGER CUTSCENE
            if(phase2.collected>=10 && !Cutscene3.CUTSCENE_OVER){
                game.setScreen(new Cutscene3(game,this));
            }
        }
        if(Phase3.ACTIVE && House.CURRENT_PHASE==3){
            hudBatch.draw(GameJam.assets.manager.get(Assets.hudFood),cam.position.x-cam.viewportWidth/2,cam.position.y+cam.viewportHeight/2-8);
            font.draw(hudBatch,": "+String.valueOf(phase3.collected)+"/30",cam.position.x-cam.viewportWidth/2+8,cam.position.y+cam.viewportHeight/2);

            if(phase3.displaytime<4){
                font.draw(hudBatch,"am hunger time for snack",cam.position.x-8,cam.position.y+24);
            }
            //TRIGGER CUTSCENE
            if(phase3.collected>=15 && !Cutscene4.CUTSCENE_OVER){
                game.setScreen(new Cutscene4(game,this));
            }
        }
        hudBatch.end();
        //DEBUGGING
        box2d.tick(cam); //COLLISION DEBUGGER


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
