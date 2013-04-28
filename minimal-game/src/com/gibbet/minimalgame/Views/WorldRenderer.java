package com.gibbet.minimalgame.Views;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.gibbet.minimalgame.Models.Bullet1;
import com.gibbet.minimalgame.Models.CircleEnemy;
import com.gibbet.minimalgame.Models.Player;
import com.gibbet.minimalgame.Models.SquareEnemy;

public class WorldRenderer {
	World world;
	float width, height;
	OrthographicCamera cam;
	SpriteBatch batch;
	Player player;
	CircleEnemy enemy;
	Iterator<CircleEnemy> cIter;
	SquareEnemy s;
	Iterator<SquareEnemy> sIter;
	Iterator<Bullet1> bIter;
	Bullet1 bullet;
	BitmapFont white;
	ShapeRenderer sr;
	Stage stage;

	Label health;
	Label score;
	Label circlesLeft;
	Label squaresLeft;
	Label lives;
	
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
		white = new BitmapFont(Gdx.files.internal("data/fonts/white.fnt"), false);
		//white.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//white.setScale(0.1f);
		white.setColor(Color.BLUE);
		stage = new Stage();
		
		health = new Label("Health: "+world.player.getHealth(), new LabelStyle(white,Color.BLUE));
		health.setOriginX(0);
		health.setOriginY(0);
		
		score = new Label("Score: ", new LabelStyle(white,Color.BLUE));
		//score.setPosition(stage.getWidth() - score.getMaxWidth(),stage.getHeight() - score.getMaxHeight());
		score.setPosition(stage.getWidth() - 200 ,stage.getHeight()-30);
		
		circlesLeft = new Label("Circles: ", new LabelStyle(white,Color.BLUE));
		circlesLeft.setPosition(stage.getWidth() - 200, stage.getHeight()-60);
		squaresLeft = new Label("Squares: ", new LabelStyle(white,Color.BLUE));
		squaresLeft.setPosition(stage.getWidth() - 200, stage.getHeight()-90);
		lives = new Label("Lives: ", new LabelStyle(white,Color.BLUE));
		lives.setPosition(200,0);
		
		stage.addActor(lives);
		stage.addActor(squaresLeft);
		stage.addActor(circlesLeft);
		stage.addActor(score);
		stage.addActor(health);
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
		world.tileMapRenderer.render(cam,layersList);
		batch.setProjectionMatrix(cam.combined);
		
		//world.tileMapRenderer.getProjectionMatrix().set(cam.combined);
		//world.tileMapRenderer.render(cam);
		
		batch.begin();
		
			batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y, player.getWidth()/2, player.getHeight()/2, player.getWidth(), player.getHeight(), 1, 1, player.getRotation(), 0, 0, player.getTexture().getWidth(), player.getTexture().getHeight(), false, false);

			cIter = world.circles.iterator();
			while (cIter.hasNext()){
				enemy = cIter.next();
				enemy.update();
				if (enemy.getBullet() != null){
					batch.draw(enemy.getBullet().getTexture(), enemy.getBullet().getPosition().x, enemy.getBullet().getPosition().y, enemy.getBullet().getWidth()/2, enemy.getBullet().getHeight()/2, enemy.getBullet().getWidth(), enemy.getBullet().getHeight(), 1, 1, enemy.getBullet().getRotation(), 0, 0, enemy.getBullet().getTexture().getWidth(), enemy.getBullet().getTexture().getHeight(), false, false);
				}
				batch.draw(enemy.getTexture(), enemy.getPosition().x, enemy.getPosition().y, enemy.getWidth()/2, enemy.getHeight()/2, enemy.getWidth(), enemy.getHeight(), 2, 2, enemy.getRotation(), 0, 0, enemy.getTexture().getWidth(), enemy.getTexture().getHeight(), false, false);	 
			}
			
			sIter = world.squares.iterator();
			while (sIter.hasNext()){
				s = sIter.next();
				s.update(player);
				if (s.getBullets().size > 0){
					bIter = s.getBullets().iterator();
					while (bIter.hasNext()){
							bullet = bIter.next();
							batch.draw(bullet.getTexture(), bullet.getPosition().x, bullet.getPosition().y,bullet.getWidth()/2, bullet.getHeight()/2, bullet.getWidth(), bullet.getHeight(), 1, 1, bullet.getRotation(), 0, 0, bullet.getTexture().getWidth(), bullet.getTexture().getHeight(), false, false);
					}
				}
				batch.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, s.getWidth()/2, s.getHeight()/2, s.getWidth(), s.getHeight(), 2, 2, s.getRotation(), 0, 0, s.getTexture().getWidth(), s.getTexture().getHeight(), false, false);	 
			}
			
			bIter = world.getBullets().iterator();
			while (bIter.hasNext()){
				bullet = bIter.next();
				batch.draw(bullet.getTexture(), bullet.getPosition().x, bullet.getPosition().y, bullet.getWidth()/2, bullet.getHeight()/2, bullet.getWidth(), bullet.getHeight(), 1, 1, bullet.getRotation(), 0, 0, bullet.getTexture().getWidth(), bullet.getTexture().getHeight(), false, false);
			}
		batch.end();
		
		squaresLeft.setText("Squares: " + world.squares.size);
		circlesLeft.setText("Circles: "+ world.circles.size);
		health.setText("Health: "+player.getHealth());
		score.setText("Score: "+player.getScore());
		lives.setText("Lives: "+player.getLives());
		
		stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
		
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
