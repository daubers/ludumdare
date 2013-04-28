package com.gibbet.minimalgame.Screens;

import java.awt.Font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.gibbet.minimalgame.MinimalGame;

public class LoseScreen implements Screen{
	
	Texture bg = new Texture(Gdx.files.internal("data/screens/lose.png"));
	SpriteBatch batch;
	Sprite sprite = new Sprite(bg);
	MinimalGame game;
	float score;
	BitmapFont white;
	Stage stage;
	
	Label scoreLabel;
	
	public LoseScreen(MinimalGame game, float score){
		this.game = game;
		this.score = score;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
			sprite.draw(batch);
		batch.end();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		white = new BitmapFont(Gdx.files.internal("data/fonts/white.fnt"), false);
		batch = new SpriteBatch();
		stage = new Stage();
		scoreLabel = new Label("Final Score: "+this.score, new LabelStyle(white, Color.BLUE));
		scoreLabel.setPosition(Gdx.graphics.getHeight()/2, Gdx.graphics.getHeight()/2);
		stage.addActor(scoreLabel);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
