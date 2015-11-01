package com.barnyard;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LevelEntity {
	private Sprite background;
	ArrayList<BlockEntity> blocks = new ArrayList<BlockEntity>();
	
	public LevelEntity(Texture backImg,ArrayList<BlockEntity> blocks){
		this.blocks = blocks;
		background.setTexture(backImg);
	}
	
}
