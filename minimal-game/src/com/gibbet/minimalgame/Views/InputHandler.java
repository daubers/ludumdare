package com.gibbet.minimalgame.Views;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gibbet.minimalgame.Models.Bullet1;
import com.gibbet.minimalgame.Models.Player;
import com.gibbet.minimalgame.Screens.MiniSounds;

public class InputHandler implements InputProcessor {
	
	World world;
	Player player;
	Vector3 touch = new Vector3();
	Vector2 vec2Touch = new Vector2();

	public InputHandler(World world){
		this.world = world;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		player = world.getPlayer();
		
		switch(keycode){
		case Keys.W:
			player.getVelocity().y = 1;
			break;
		case Keys.A:
			player.getVelocity().x = -1;
			break;
		case Keys.S:
			player.getVelocity().y = -1;
			break;
		case Keys.D:
			player.getVelocity().x = 1;
			break;
		case Keys.C:
			Gdx.app.log("Ship pos", "X=" + player.getPosition().x + " Y="+player.getPosition().y);
			break;
		/*case Keys.SPACE:
			Vector2 bulletVelocity = new Vector2(0,1);
			if (!player.getVelocity().equals(new Vector2(0,0))){
				bulletVelocity = new Vector2(player.getVelocity());
			}
			world.addBullet(new Bullet1(Bullet1.SPEED, 0, new Vector2(player.getPosition().x, player.getPosition().y), 1, 1,bulletVelocity));
			MiniSounds.shoot();*/

		default:
			break;
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		player = world.getPlayer();
		
		switch(keycode){
		case Keys.W:
			if (player.getVelocity().y == 1 )
			player.getVelocity().y = 0;
			break;
		case Keys.A:
			if (player.getVelocity().x == -1 )
			player.getVelocity().x = 0;
			break;
		case Keys.S:
			if (player.getVelocity().y == -1 )
			player.getVelocity().y = 0;
			break;
		case Keys.D:
			if (player.getVelocity().x == 1 )
			player.getVelocity().x = 0;
		default:
			break;
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touch.set(screenX,screenY,0);
		world.getRenderer().getCamera().unproject(touch);
		vec2Touch.set(touch.x, touch.y);
		player = world.getPlayer();
		world.addBullet(new Bullet1(Bullet1.SPEED,0,new Vector2(player.getPosition().x, player.getPosition().y),0.5f,0.5f,new Vector2(vec2Touch.sub(player.getPosition()).nor())));
		MiniSounds.shoot();
		return true;


	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
