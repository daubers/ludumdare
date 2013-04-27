package com.gibbet.minimalgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.files.FileHandleStream;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.gibbet.minimalgame.Models.TileCollision;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MapCollider {
	TiledMap map;

	HashMap<Integer,TileCollision> tileColliders = new HashMap<Integer,TileCollision>();
	public MapCollider(TiledMap map){
		this.map = map;
	}
	
	public MapCollider(){

	}
	
	public boolean checkCollision(Vector2 position){
		//get the tile that we're on
		//Gdx.app.log("CheckCollision", String.valueOf(getTileId(0, (int)getMapXY(position).x, (int)getMapXY(position).y)+1));
		int tileId = getTileId(0, (int)getMapXY(position).x, (int)getMapXY(position).y);
		//Gdx.app.log("Tile is", String.valueOf(tileId));
		if (tileColliders.containsKey(tileId)){
			//Gdx.app.log("CheckCollision", this.getPosInTile(position).x + " - "+this.getPosInTile(position).y);
			if (tileColliders.get(tileId).isCollisionPoint(this.getPosInTile(position))){
				
				return true;
			}
		}
		
		
		return false;
	}
	
	public void addCollision(Integer tileID, TileCollision tileData){
		tileColliders.put(tileID, tileData);
	}
	
	public Vector2 getMapXY(Vector2 position){
		//remember 0,0 is the top left tile of the map
		float x = position.x / map.tileWidth;
		float y = position.y / map.tileHeight;
		//Gdx.app.log("MapXY", x + "-"+y);
		return new Vector2((float)Math.floor(x),(float)Math.floor(y));
	}
	
	public int getTileId(int layer, int x, int y){
		//dGdx.app.log("Hmmm", String.valueOf(x)+"-"+String.valueOf(map.height-1-y));
		if (x<0)
			x=0;
		if ((map.height-1-y)<0)
			y=0;
		return map.layers.get(layer).tiles[map.height-1-y][x];
	}
	
	public Vector2 getPosInTile(Vector2 position){
		float x = position.x / map.tileWidth;
		float y = position.y / map.tileHeight;
		
		x = x - (float) Math.floor(x);
		y = y - (float) Math.floor(y);
		
		return new Vector2((float)Math.floor(x*32),(float)Math.floor(y*32));
	}
	
	public void loadTileCollisions(BufferedReader filePath){
		GsonBuilder builder = new GsonBuilder();
		Gson gson =
	            builder.enableComplexMapKeySerialization().setPrettyPrinting().create();
			//input = new FileInputStream(filePath);
			Type type = new TypeToken<HashMap<Integer,TileCollision>>(){}.getType();
			tileColliders = gson.fromJson(filePath, type);	
	}
	public void saveTileCollisions(String filePath){
		GsonBuilder builder = new GsonBuilder();
		Gson gson =
	            builder.enableComplexMapKeySerialization().setPrettyPrinting().create();
		try {
			BufferedWriter reader = new BufferedWriter(new FileWriter(filePath));
			Type type = new TypeToken<HashMap<Integer,TileCollision>>(){}.getType();
			reader.write(gson.toJson(tileColliders, type));
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
