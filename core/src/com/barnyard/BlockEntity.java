package com.barnyard;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class BlockEntity extends StationaryEntity{
	
	public BlockEntity(int xPos, int yPos, int width, int height, MyGame parent, Sprite sprite, boolean topOpen, boolean bottomOpen) {
		super(xPos, yPos, width, height, parent, sprite, topOpen, bottomOpen);
	}
}
