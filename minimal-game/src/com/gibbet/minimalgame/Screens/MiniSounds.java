package com.gibbet.minimalgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MiniSounds {
	public static Sound crash = Gdx.audio.newSound(Gdx.files.internal("data/sound/crash.wav"));
	public static Sound shoot = Gdx.audio.newSound(Gdx.files.internal("data/sound/shoot.wav"));
	public static Sound hit = Gdx.audio.newSound(Gdx.files.internal("data/sound/hit.wav"));
	
	public static void crash(){
		crash.play();
	}
	
	public static void shoot(){
		shoot.play();
	}
	
	public static void hit(){
		hit.play();
	}
}
