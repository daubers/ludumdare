package com.gibbet.minimalgame.Views;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gibbet.minimalgame.MapCollider;
import com.gibbet.minimalgame.MinimalGame;
import com.gibbet.minimalgame.Models.Bullet1;
import com.gibbet.minimalgame.Models.CircleEnemy;
import com.gibbet.minimalgame.Models.Player;
import com.gibbet.minimalgame.Models.SquareEnemy;
import com.gibbet.minimalgame.Screens.LoseScreen;
import com.gibbet.minimalgame.Screens.MiniSounds;

public class World {
	MinimalGame game;
	Player player;
	WorldRenderer wr;
	TileMapRenderer tileMapRenderer;
    TiledMap map;
    TileAtlas atlas;
    MapCollider mc;
    public Array<CircleEnemy> circles = new Array<CircleEnemy>();
    Iterator<CircleEnemy> cIter;
    CircleEnemy c;
    Array<SquareEnemy> squares = new Array<SquareEnemy>();
    Iterator<SquareEnemy> sIter;
    SquareEnemy s;
    Array<Bullet1> bullets = new Array<Bullet1>();
    Iterator<Bullet1> bIter;
    Bullet1 bullet;
    Iterator<TiledObject> toIter;
    TiledObject to;
    Random random = new Random();
    
    
	public World(MinimalGame game){
		this.game = game;
		player = new Player(20f,0,new Vector2(5,20),1,1);
		
		Gdx.input.setInputProcessor(new InputHandler(this));
		// Load the tmx file into map
        map = TiledLoader.createMap(Gdx.files.internal("data/maps/level1.tmx"));
        circles.add(new CircleEnemy(5f,0,new Vector2(5,25),1,1,player,this));
        toIter = map.objectGroups.get(0).objects.iterator();
        while (toIter.hasNext()){
        	to = toIter.next();
        	float x;
        	float y;
        	for (int i=0; i<100; i++){
	        	x = random.nextFloat()*to.width;
	        	y = random.nextFloat()*to.height;
	        	//Gdx.app.log("XY", "x="+to.x + " y="+to.y);
	        	//Gdx.app.log("XY", "X="+(to.x+x) + " Y="+(to.y+y));
	        	circles.add(new CircleEnemy(5f,0,new Vector2(to.x+x,(map.height*map.tileHeight)-to.y+y),1,1,player,this));
	        	//Gdx.app.log("Circle created", "x - "+(x+to.x)+" y - "+(y+to.y));
        	}
        }
        //Gdx.app.log("Circles created", String.valueOf(circles.size));
        toIter = map.objectGroups.get(1).objects.iterator();
        while (toIter.hasNext()){
        	to = toIter.next();
        	float x;
        	float y;
        	for (int i=0; i<20; i++){
	        	x = random.nextFloat()*to.width;
	        	y = random.nextFloat()*to.height;
	        	//Gdx.app.log("XY", "x="+to.x + " y="+to.y);
	        	//Gdx.app.log("XY", "X="+(to.x+x) + " Y="+(to.y+y));
	        	squares.add(new SquareEnemy(new Vector2(to.x+x,(map.height*map.tileHeight)-to.y+y),1,1));
	        	//Gdx.app.log("Circle created", "x - "+(x+to.x)+" y - "+(y+to.y));
        	}
        }
        //Gdx.app.log("Squares created", String.valueOf(squares.size));
        // Load the tiles into atlas
        atlas = new TileAtlas(map, Gdx.files.internal("data/tiles/"));
        // Create the renderer
        tileMapRenderer = new TileMapRenderer(map, atlas, 1, 1);
        mc = new MapCollider(map);
        mc.loadTileCollisions(Gdx.files.internal("data/tiles/level1.json").reader(100));
        
        MiniSounds.playLevel1();
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
		if (player.getLives() < 0){
			MiniSounds.stopLevel1();
			game.setScreen(new LoseScreen(game,player.getScore()));
		}
		if (mc.checkCollision(player.getPosition().add(player.getVelocity().tmp().mul(Gdx.graphics.getDeltaTime() * player.getSpeed())))==true){
			Gdx.app.log("BOOM", "Crash");
			player.reduceHealth(1);
			Gdx.app.log("Health", String.valueOf(player.getHealth()));
			MiniSounds.crash();
		}

		
		player.update();
		bIter = bullets.iterator();
		//Gdx.app.log("Bullets ", String.valueOf(bullets.size));
		while (bIter.hasNext()){
			boolean removed = false;
			bullet = bIter.next();
			bullet.update();
			//Gdx.app.log("Bullets ", String.valueOf(bullets.size));
			cIter = circles.iterator();
			while (cIter.hasNext()){
				c = cIter.next();
				if (c.getBounds().overlaps(bullet.getBounds())){
					c.reduceHealth(20);
					player.addScore(20);
					if (c.getHealth()<=0){
						cIter.remove();
					}
					bIter.remove();
					removed=true;
					break;
				}
			}
			sIter = squares.iterator();
			while (sIter.hasNext()){
				s = sIter.next();
				if (s.getBounds().overlaps(bullet.getBounds())){
					s.reduceHealth(20);
					player.addScore(20);
					if (s.getHealth()<=0){
						sIter.remove();
					}
					sIter.remove();
					removed=true;
					break;
				}
			}
			//destroy bullets if they're more than 100 units from the player
			if (removed == false && (bullet.getPosition().x > player.getPosition().x+bullet.getRange() || bullet.getPosition().y > player.getPosition().y+bullet.getRange() )){
				bIter.remove();
			}
		}
	}
	
}
