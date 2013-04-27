package com.gibbet.minimalgame.Models;

import java.util.ArrayList;
import java.util.Objects;

import com.badlogic.gdx.math.Vector2;

public class TileCollision {
	ArrayList<Vector2> collisionPoints = new ArrayList<Vector2>();
	
	public void addCollisionPoint(Vector2 point){
		collisionPoints.add(point);
	}
	
	public void delCollisionPoint(Vector2 point){
		collisionPoints.remove(point);
	}
	
	public Boolean isCollisionPoint(Vector2 point){
		if (collisionPoints.contains(new Vector2(point.x,32-point.y))){
			return true;
		}	
		else{
			return false;
		}
	}

}
