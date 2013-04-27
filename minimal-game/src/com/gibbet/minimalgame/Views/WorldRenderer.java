package com.gibbet.minimalgame.Views;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gibbet.minimalgame.Models.Bullet1;
import com.gibbet.minimalgame.Models.CircleEnemy;
import com.gibbet.minimalgame.Models.Player;

public class WorldRenderer {
	World world;
	float width, height;
	OrthographicCamera cam;
	SpriteBatch batch;
	Player player;
	CircleEnemy enemy;
	Iterator<CircleEnemy> cIter;
	Iterator<Bullet1> bIter;
	Bullet1 bullet;
	
	ShapeRenderer sr;
	private static final int[] layersList = { 0 };
	
	public WorldRenderer(World world){
		this.world = world;
		this.world.setRenderer(this);
		
		width = Gdx.graphics.getWidth()/10;
		height = Gdx.graphics.getHeight()/10;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();
		
		sr = new ShapeRenderer();
		sr.setColor(Color.CYAN);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);	
	}
	
	public void render(){
		//Clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//Gdx.app.log(world.game.LOG, "In render");
		player = world.getPlayer();
		cam.position.set(player.getPosition().x, player.getPosition().y, 0);
		cam.update();
		world.tileMapRenderer.getProjectionMatrix().set(cam.combined);
	    //Gdx.app.log("cam", "x - "+player.getPosition().x+" y - "+player.getPosition().y+" mapPos - "+player.getPosition().y/(world.map.tileWidth));
	    world.tileMapRenderer.render((int) player.getPosition().x, (int) player.getPosition().y,
	    		Gdx.graphics.getWidth(), Gdx.graphics.getWidth(),layersList);
		batch.setProjectionMatrix(cam.combined);
		//world.tileMapRenderer.getProjectionMatrix().set(cam.combined);
		//world.tileMapRenderer.render(cam);
		
		batch.begin();
			batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y, player.getWidth()/2, player.getHeight()/2, player.getWidth(), player.getHeight(), 1, 1, player.getRotation(), 0, 0, player.getTexture().getWidth(), player.getTexture().getHeight(), false, false);
			
			cIter = world.circles.iterator();
			while (cIter.hasNext()){
				enemy = cIter.next();
				enemy.update();
				batch.draw(enemy.getTexture(), enemy.getPosition().x, enemy.getPosition().y, enemy.getWidth()/2, enemy.getHeight()/2, enemy.getWidth(), enemy.getHeight(), 1, 1, enemy.getRotation(), 0, 0, enemy.getTexture().getWidth(), enemy.getTexture().getHeight(), false, false);
			}
			
			bIter = world.getBullets().iterator();
			while (bIter.hasNext()){
				bullet = bIter.next();
				batch.draw(bullet.getTexture(), bullet.getPosition().x, bullet.getPosition().y, bullet.getWidth()/2, bullet.getHeight()/2, bullet.getWidth(), bullet.getHeight(), 1, 1, bullet.getRotation(), 0, 0, bullet.getTexture().getWidth(), bullet.getTexture().getHeight(), false, false);

			}
		batch.end();
		/*sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Rectangle);
			sr.rect(player.getBounds().x, player.getBounds().y, player.getBounds().width, player.getBounds().height);
			sr.setColor(Color.RED);
		sr.end();*/
	}

	public OrthographicCamera getCamera() {
		// TODO Auto-generated method stub
		return cam;
	}
}
