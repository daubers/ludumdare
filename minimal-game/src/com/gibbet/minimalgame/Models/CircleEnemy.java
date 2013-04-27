package com.gibbet.minimalgame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.gibbet.minimalgame.Screens.MiniSounds;

public class CircleEnemy extends MoveableEntity {
	Player player;
	public CircleEnemy(float SPEED, float rotation, Vector2 position,
			float height, float width, Player player) {
		super(SPEED, rotation, position, height, width);
		this.player = player;
		this.health = 20;
		this.setTexture(new Texture("data/characters/circle.png"));
		this.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.setSprite(new Sprite(this.getTexture()));
	}
	
	public float getHealth(){
		return health;
	}
	
	public void reduceHealth(float damage){
		health = health - damage;
		MiniSounds.hit();
	}
	
	public void update(){
		position.lerp(player.position, Gdx.graphics.getDeltaTime());
		
		super.update();
	}
}
