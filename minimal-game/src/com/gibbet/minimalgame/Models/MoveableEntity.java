package com.gibbet.minimalgame.Models;

import com.badlogic.gdx.math.Vector2;

public abstract class MoveableEntity extends Entity{
	
	protected Vector2 velocity;
	protected float SPEED;
	protected float rotation;
	protected float health = 100;
	
	public MoveableEntity(float SPEED, float rotation, Vector2 position, float height, float width) {
		super(position, height, width);
		this.SPEED = SPEED;
		this.rotation = rotation;
		this.velocity = new Vector2(0,0);
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	public float getSpeed(){
		return SPEED;
	}
	public void update(){
		bounds.x = position.x;
		bounds.y = position.y;
	}
	
	public float getHealth(){
		return health;
	}
	
	public void reduceHealth(float damage){
		health = health - damage;
	}
	
	public void update(Player player) {
		// TODO Auto-generated method stub
		
	}
	
}
