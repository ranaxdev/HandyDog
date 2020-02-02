package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.entities.Player;
import com.mygdx.game.helper.Box2DHelper;
import com.mygdx.game.helper.Box2DWorld;
import com.mygdx.game.screens.MainGameScreen;
import com.mygdx.game.screens.MainMenuScreen;

public class GameJam extends Game {

	public ShapeRenderer shapeRenderer;
	public static Assets assets;
	@Override
	public void create () {
		assets = new Assets();
		assets.load();
		assets.manager.finishLoading();
		//Create main menu instance and set it
		setScreen(new MainMenuScreen(this));
		/*
		cam = new OrthographicCamera(640,480);


		batch = new SpriteBatch();
		renderer = new Box2DDebugRenderer();
		world = new World(new Vector2(0,0),true);
		world.step(1/60f,6,2);
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(0,0));
		Body body = world.createBody(bodyDef);
		PolygonShape box = new PolygonShape();
		box.setAsBox(50,50);
		body.createFixture(box,0);
		box.dispose();*/
	}


	@Override
	public void render () {
		super.render();
		/*
		//Clear the screen
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		renderer.render(world,cam.combined);*/
	}
	
	@Override
	public void dispose () {
	}
}
