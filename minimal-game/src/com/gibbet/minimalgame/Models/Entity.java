package com.gibbet.minimalgame.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/*
 * This is the base class for all of our objects in the game
 */

public abstract class Entity {

	protected Texture texture;
	protected Sprite sprite;
	protected Vector2 position;
	protected float height;
	protected float width;
	protected Rectangle bounds;
	
	public Entity(Vector2 position, float height, float width){
		this.position = position;
		this.height = height;
		this.width = width;
		bounds = new Rectangle();
		this.bounds.set(position.x, position.y, width, height);
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
}
