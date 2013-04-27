package com.gibbet.minimalgame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;

public class Bullet1 extends MoveableEntity{

	public static float SPEED = 60;
	private int range = 100;
	public Bullet1(float SPEED, float rotation, Vector2 position, float height,
			float width, Vector2 velocity) {
		super(SPEED, rotation, position, height, width);
		this.setTexture(new Texture("data/weapons/bullet1.png"));
		this.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.velocity = velocity;
		//Gdx.app.log("Velocity", "x - " + velocity.x + " y - "+velocity.y);
	}
	
	public int getRange(){
		return range;
	}
	@Override
	public void update(){
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
		//Gdx.app.log("Position", "x - " + position.x + " y - "+position.y);
		rotation = velocity.angle() - 90;
		super.update();
	}

}
