package com.gibbet.minimalgame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.gibbet.minimalgame.Screens.MiniSounds;

public class Player extends MoveableEntity{
	float score = 0;
	int lives = 3;
	float startx,starty;
	public Player(float SPEED, float rotation, Vector2 position, float height,
			float width) {
		super(SPEED, rotation, position, height, width);
		startx = position.x;
		starty = position.y;
		this.setTexture(new Texture("data/characters/triangle.png"));
		this.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.setSprite(new Sprite(this.getTexture()));
	}
	
	public void addScore(float score){
		this.score = this.score + score;
	}
	
	public float getScore(){
		return score;	
	}
	
	public void update(){
		if (health <= 0){
			health = 100;
			lives = lives -1;
			position.x = startx;
			position.y = starty;
			MiniSounds.splode();
		}
		else{
			position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
			if (position.x<=0)
				position.x = 0;
			if (position.y<=0)
				position.y = 0;
			//Gdx.app.log("Position", "x - "+position.x+" y - "+position.y);
			if (velocity.x !=0 || velocity.y !=0){
				rotation = velocity.angle() - 90;
			}
			else{
				rotation = velocity.angle();
			}
		}
		super.update();
	}

	public int getLives() {
		// TODO Auto-generated method stub
		return lives;
	}
}
