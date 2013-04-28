package com.gibbet.minimalgame.Models;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gibbet.minimalgame.Screens.MiniSounds;

public class SquareEnemy extends Entity{

	Array<Bullet1> bullets;
	Bullet1 bullet;
	Iterator<Bullet1> bIter;
	int maxShots = 2;
	int health = 100;
	
	public SquareEnemy(Vector2 position, float height, float width) {
		super(position, height, width);
		this.texture = new Texture(Gdx.files.internal("data/characters/square.png"));
		bullets = new Array<Bullet1>();
	}
	
	public void fire(Player player){
		Vector2 bulletVelocity = new Vector2(player.position.tmp().sub(position).nor());
		bullets.add(new Bullet1(Bullet1.SPEED, 0, new Vector2(position), 1, 1, bulletVelocity));
		MiniSounds.shoot();
	}
	
	public void update(Player player){
		
		if (bullets.size > 0){
			bIter = bullets.iterator();
			while (bIter.hasNext()){
				boolean removed = false;
				bullet = bIter.next();
				if (bullet.position.x < position.tmp().x-100 || bullet.position.x > position.tmp().x+100 || bullet.position.y > position.tmp().y+100|| bullet.position.y < position.tmp().y-100){
					bIter.remove();
					removed = true;
				}
				if ( !removed && bullet.getBounds().overlaps(player.getBounds())){
					player.reduceHealth(2);
					bIter.remove();
					removed = true;
					MiniSounds.hit();
				}
				if (!removed){
					bullet.update();
				}
			}
		}
		if (position.x < player.getPosition().x+100 && position.x > player.getPosition().x-100 && position.y < player.getPosition().y+100 && position.y > player.getPosition().y-100){
			if (bullets.size < maxShots){
				fire(player);
			}
		}
	}

	public void reduceHealth(int damage) {
		health = health - damage;
		MiniSounds.hit();
		
	}

	public int getHealth() {
		return health;
	}

	public Array<Bullet1> getBullets() {
		return bullets;
	}

	public float getRotation() {
		return 0;
	}

}
