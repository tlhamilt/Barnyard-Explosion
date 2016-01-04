package com.barnyard;

import com.badlogic.gdx.graphics.g2d.Sprite;

//CREATE ITEM SPAWNER AND IMPLEMENT THIS CLASS

public abstract class StationaryEntity extends Entity{
	
	private boolean topOpen;
	private boolean bottomOpen;

	public StationaryEntity(int xPos, int yPos, int width, int height, BarnyardExplosion game, Sprite sprite, boolean topOpen, boolean bottomOpen) {
		super(xPos, yPos, width, height, game, sprite);
		this.topOpen = topOpen;
		this.bottomOpen = bottomOpen;
	}
	
	public boolean isTopOpen(){
		return topOpen;
	}
	
	public boolean isBottomOpen(){
		return bottomOpen;
	}
}
