package com.gibbet.minimalgame;

import com.badlogic.gdx.Game;
import com.gibbet.minimalgame.Screens.GameScreen;

public class MinimalGame extends Game {
	public static final String VERSION = "0.1-WIP";
	public static final String LOG = "Mininimal Game";
	
	
	@Override
	public void create() {
		setScreen(new GameScreen(this));
		
	}

}
