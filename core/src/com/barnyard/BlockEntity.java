package com.barnyard;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class BlockEntity extends StationaryEntity{
	
	public BlockEntity(int xPos, int yPos, int width, int height, BarnyardExplosion game, Sprite sprite, boolean topOpen, boolean bottomOpen) {
		super(xPos - 1, yPos - 1, width , height + 1, game, sprite, topOpen, bottomOpen);
	}
}
