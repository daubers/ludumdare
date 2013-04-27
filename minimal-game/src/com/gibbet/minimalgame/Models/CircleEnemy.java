package com.gibbet.minimalgame.Models;



import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.gibbet.minimalgame.Screens.MiniSounds;
import com.gibbet.minimalgame.Views.World;

public class CircleEnemy extends MoveableEntity {
	Player player;
	World world;
	Iterator<CircleEnemy> cIter;
	CircleEnemy other;
	Random random = new Random();
	Bullet1 bullet;
	
	public CircleEnemy(float SPEED, float rotation, Vector2 position,
			float height, float width, Player player, World world) {
		super(SPEED, rotation, position, height, width);
		this.player = player;
		this.health = 20;
		this.setTexture(new Texture("data/characters/circle.png"));
		this.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.setSprite(new Sprite(this.getTexture()));
		
		this.world = world;
	}
	
	public float getHealth(){
		return health;
	}
	
	public void fire(){
		if (bullet == null){
			Vector2 bulletVelocity = new Vector2(player.position.tmp().sub(position).nor());
			bullet = new Bullet1(Bullet1.SPEED, 0, new Vector2(position), 1, 1, bulletVelocity);
		}
	}
	
	public void reduceHealth(float damage){
		health = health - damage;
		MiniSounds.hit();
	}
	
	public Bullet1 getBullet(){
		return bullet;
	}
	
	public void update(){
		if (bullet != null){
			if (bullet.position.x < position.tmp().x-100 || bullet.position.x > position.tmp().x+100 || bullet.position.y > position.tmp().y+100|| bullet.position.y < position.tmp().y-100){
				bullet = null;
			}
			if (bullet != null && bullet.getBounds().overlaps(player.getBounds())){
				player.reduceHealth(2);
				bullet = null;
				MiniSounds.hit();
			}
			if (bullet!=null){
				bullet.update();
			}
		}
		//Gdx.app.log("Pos", "x-" + position.x + " y - "+position.y +" shipX - "+player.position.x+" shipY - "+player.position.y);
		//only approach once the player is within 100 units
		if (position.x < player.position.x+20 && position.x > player.position.x-20 && position.y > player.position.y-20 && position.y < player.position.y+20){
			
			position.lerp(player.position, Gdx.graphics.getDeltaTime());
			//if we have a bullet, destroy it if it's more than 100 units away
			if (bullet == null){
				
				fire();
			}
			cIter =  new ArrayIterator(world.circles);
			while (cIter.hasNext()){
				other = cIter.next();
				//Gdx.app.log("LERP", "x - "+other.position.x+" y - "+other.position.y);
				if (!other.equals(this) && other.getBounds().overlaps(this.bounds)){
					position.add(random.nextFloat()-0.5f,random.nextFloat()-0.5f);
					//Gdx.app.log("LERP", "x - "+other.position.x+" y - "+other.position.y);
				}
			}
			
			//Gdx.app.log("LERP", "x - "+position.x+" y - "+position.y);
		}
		super.update();
	}
}
