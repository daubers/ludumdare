package com.gibbet.minimalgame.Views;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gibbet.minimalgame.MapCollider;
import com.gibbet.minimalgame.MinimalGame;
import com.gibbet.minimalgame.Models.Bullet1;
import com.gibbet.minimalgame.Models.CircleEnemy;
import com.gibbet.minimalgame.Models.Player;
import com.gibbet.minimalgame.Screens.MiniSounds;

public class World {
	MinimalGame game;
	Player player;
	WorldRenderer wr;
	TileMapRenderer tileMapRenderer;
    TiledMap map;
    TileAtlas atlas;
    MapCollider mc;
    Array<CircleEnemy> circles = new Array<CircleEnemy>();
    Iterator<CircleEnemy> cIter;
    CircleEnemy c;
    Array<Bullet1> bullets = new Array<Bullet1>();
    Iterator<Bullet1> bIter;
    Bullet1 bullet;
	public World(MinimalGame game){
		this.game = game;
		player = new Player(20f,0,new Vector2(5,20),1,1);
		circles.add(new CircleEnemy(5f,0,new Vector2(5,25),1,1,player));
		Gdx.input.setInputProcessor(new InputHandler(this));
		// Load the tmx file into map
        map = TiledLoader.createMap(Gdx.files.internal("data/maps/level1.tmx"));
        
        // Load the tiles into atlas
        atlas = new TileAtlas(map, Gdx.files.internal("data/tiles/"));
        // Create the renderer
        tileMapRenderer = new TileMapRenderer(map, atlas, 32, 32);
        mc = new MapCollider(map);
        mc.loadTileCollisions(Gdx.files.internal("data/tiles/level1.json").reader(100));
	}
	
	public WorldRenderer getRenderer() {
		return wr;
	}

	public void setRenderer(WorldRenderer wr) {
		this.wr = wr;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void addBullet(Bullet1 bullet){
		bullets.add(bullet);
	}
	
	public Array<Bullet1>getBullets(){
		return bullets;
	}
	
	public void update(){
		if (mc.checkCollision(player.getPosition().add(player.getVelocity().tmp().mul(Gdx.graphics.getDeltaTime() * player.getSpeed())))==true){
			Gdx.app.log("BOOM", "Crash");
			player.reduceHealth(1);
			Gdx.app.log("Health", String.valueOf(player.getHealth()));
			MiniSounds.crash();
		}
		player.update();
		bIter = bullets.iterator();
		Gdx.app.log("Bullets ", String.valueOf(bullets.size));
		while (bIter.hasNext()){
			bullet = bIter.next();
			bullet.update();
			Gdx.app.log("Bullets ", String.valueOf(bullets.size));
			cIter = circles.iterator();
			while (cIter.hasNext()){
				c = cIter.next();
				if (c.getBounds().overlaps(bullet.getBounds())){
					c.reduceHealth(20);
					if (c.getHealth()<=0){
						cIter.remove();	
					}
					bIter.remove();
					
				}
			}
			//destroy bullets if they're more than 100 units from the player
			if (bullet.getPosition().x > player.getPosition().x+bullet.getRange() || bullet.getPosition().y > player.getPosition().y+bullet.getRange() ){
				bIter.remove();
			}
		}
	}
	
}
