package com.barnyard;

import com.badlogic.gdx.math.Rectangle;

public class Entity {
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private Rectangle hitBox;
	public MyGame parent;
	
	public Entity(int xPos, int yPos, int width, int height, MyGame parent){
		this.xPos = xPos;
		this.yPos = yPos;
		hitBox = new Rectangle(xPos, yPos, width, height);
		this.parent = parent;
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	public void setXPos(int x){
		xPos = x;
	}
	
	public void setYPos(int y){
		yPos = y;
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
}
