package com.gibbet.minimalgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.gibbet.minimalgame.MinimalGame;

public class WinScreen implements Screen{
	
	Stage stage;
	Texture texture;
	Sprite sprite;
	BitmapFont white;
	SpriteBatch batch;
	MinimalGame game;
	TextButton button;
	TextureAtlas atlas;
	Skin skin;
	
	public WinScreen(MinimalGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		batch.begin();
			sprite.draw(batch);
		batch.end();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null){
			stage = new Stage(width, height, true);
		}
		stage.clear();
		Gdx.input.setInputProcessor(stage);

		// TODO Auto-generated method stub
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("up");
		style.down = skin.getDrawable("down");
		style.font = white;
		
		button = new TextButton("Play Again", style);
		button.setWidth(400);
		button.setHeight(100);
		button.setX(Gdx.graphics.getWidth()/2 - button.getWidth()/2);
		button.setY(Gdx.graphics.getHeight()/2 - button.getHeight()/2-100);
		
		button.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, 
					int pointer, int button){
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, 
					int pointer, int button){
				game.setScreen(new GameScreen(game));
			}
		});
		
		stage.addActor(button);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/screens/win.png"));
		sprite = new Sprite(texture);
		white = new BitmapFont(Gdx.files.internal("data/fonts/white.fnt"), false);
		white.setColor(Color.BLACK);
		atlas = new TextureAtlas("data/buttons/button.pack");
		skin= new Skin();
		skin.addRegions(atlas);

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
