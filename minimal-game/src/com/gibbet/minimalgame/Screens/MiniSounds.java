package com.gibbet.minimalgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MiniSounds {
	public static Sound crash = Gdx.audio.newSound(Gdx.files.internal("data/sound/crash.wav"));
	public static Sound shoot = Gdx.audio.newSound(Gdx.files.internal("data/sound/shoot.wav"));
	public static Sound hit = Gdx.audio.newSound(Gdx.files.internal("data/sound/hit.wav"));
	public static Music level1 = Gdx.audio.newMusic(Gdx.files.internal("data/music/music1.wav"));
	public static Sound splode = Gdx.audio.newSound(Gdx.files.internal("data/sound/splode.wav"));
	
	public static void crash(){
		crash.play();
	}
	
	public static void shoot(){
		shoot.play();
	}
	
	public static void hit(){
		hit.play();
	}
	
	public static void playLevel1(){
		level1.setLooping(true);
		level1.setVolume(0.1f);
		level1.play();
	}
	
	public static void splode(){
		splode.play();
	}
	
	public static void stopLevel1(){
		level1.stop();
	}
}
