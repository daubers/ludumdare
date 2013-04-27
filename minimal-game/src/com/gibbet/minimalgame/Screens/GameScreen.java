package com.gibbet.minimalgame.Screens;

import com.badlogic.gdx.Screen;
import com.gibbet.minimalgame.MinimalGame;
import com.gibbet.minimalgame.Views.World;
import com.gibbet.minimalgame.Views.WorldRenderer;

public class GameScreen implements Screen{
	
	WorldRenderer wr;
	World world;
	MinimalGame game;
	
	public GameScreen(MinimalGame game){
		this.game = game;
		world = new World(game);
		wr = new WorldRenderer(world);
	}
	
	@Override
	public void render(float delta) {
		world.update();
		wr.render();
		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void show() {
		
		
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
