package com.barnyard;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LevelEntity {
	private Sprite background;
	ArrayList<BlockEntity> blocks = new ArrayList<BlockEntity>();
	BarnyardExplosion game;
	private int gravity = 1;
	
	public LevelEntity(Texture backImg,ArrayList<BlockEntity> blocks, BarnyardExplosion game){
		background = new Sprite(backImg);
		this.blocks = blocks;		
		this.game = game;
	}
	public LevelEntity(Texture backImg, int gravity, ArrayList<BlockEntity> blocks, BarnyardExplosion game){
		background = new Sprite(backImg);
		this.blocks = blocks;
		this.game = game;
	}
	public LevelEntity(){}
	public void drawLevel(){
		game.batch.draw(background,0,0);//,800,480);
		for(BlockEntity b : blocks){
			game.batch.draw(b.getSprite(), b.getXPos(), b.getYPos());
		}	
	}
	public int getGravity() {
		return gravity;
	}
	public void changeGravity(){ //possible item causation
		
	}
}
