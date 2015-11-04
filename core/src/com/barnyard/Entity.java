package com.barnyard;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private Rectangle hitBox;
	private Sprite sprite;
	public BarnyardExplosion game;
	
	public Entity(int xPos, int yPos, int width, int height, BarnyardExplosion game, Sprite sprite){
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.game = game;
		this.sprite = sprite;
		hitBox = new Rectangle(xPos, yPos, width, height);
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	public void setXPos(int x){
		xPos = x;
		hitBox = new Rectangle(xPos, yPos, width, height);
	}
	
	public void setYPos(int y){
		yPos = y;
		hitBox = new Rectangle(xPos, yPos, width, height);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setWidth(int w){
		width = w;
		hitBox = new Rectangle(xPos, yPos, width, height);
	}
	
	public Rectangle getHitBox(){
		return hitBox;
	}
	
	public void setHeight(int h){
		height = h;
		hitBox = new Rectangle(xPos, yPos, width, height);
	}
	public void setSprite(Sprite s){
		sprite = s;
	}
	public Sprite getSprite(){
		return sprite;
	}
	public void drawEntity(){
		game.batch.draw(sprite, xPos, yPos);

	}
}
