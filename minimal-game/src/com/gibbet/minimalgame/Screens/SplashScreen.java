package com.gibbet.minimalgame.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gibbet.minimalgame.MinimalGame;
import com.gibbet.minimalgame.TweenAccessors.SpriteTween;

public class SplashScreen implements Screen{
	Texture splashTexture;
	Sprite splashSprite;
	SpriteBatch batch;
	MinimalGame game;
	TweenManager manager;
	
	public SplashScreen(MinimalGame game){
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		manager.update(delta);
		batch.begin();
		splashSprite.draw(batch);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		splashTexture = new Texture("data/screens/gibbet_splash.png");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1, 1, 1, 0);
		splashSprite.setX(Gdx.graphics.getWidth()/ 2 - (splashSprite.getWidth() / 2));
		splashSprite.setY(Gdx.graphics.getHeight() / 2 - (splashSprite.getHeight()/2));
		
		batch = new SpriteBatch();
		
		TweenCallback cb = new TweenCallback(){

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();
				
			}
			
		};
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		manager = new TweenManager();
		
		Tween.to(splashSprite, SpriteTween.ALPHA, 2f).target(1).repeatYoyo(1, 2.5f).ease(TweenEquations.easeInQuad).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
	}
	
	private void tweenCompleted(){
		game.setScreen(new StartScreen(game));
	}
	
	@Override
	public void hide() {
	
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
	
	}

	@Override
	public void dispose() {

	}

}
