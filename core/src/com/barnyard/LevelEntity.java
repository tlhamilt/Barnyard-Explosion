package com.barnyard;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LevelEntity {
	private Sprite background;
	ArrayList<BlockEntity> blocks = new ArrayList<BlockEntity>();
	BarnyardExplosion game;
	public int gravity = 1;
	
	public LevelEntity(Texture backImg,ArrayList<BlockEntity> blocks, BarnyardExplosion game){
		background = new Sprite(backImg);
		this.blocks = blocks;
		//background.setTexture(backImg);
		
		this.game = game;
	}
	public LevelEntity(Texture backImg, int gravity, ArrayList<BlockEntity> blocks, BarnyardExplosion game){
		this.blocks = blocks;
		background.setTexture(backImg);
		this.game = game;
	}
	public void drawLevel(){
		game.batch.draw(background,0,0);
		for(BlockEntity b : blocks){
			game.batch.draw(b.getSprite(), b.getXPos(), b.getYPos());
		}
		
	}
	public int getGravity() {
		return gravity;
	}
	
}
