package com.barnyard;

public class MovingEntity extends Entity {
	private int xVelocity;
	private int yVelocity;
	private int gravity = 1;
	
	public MovingEntity(int xPos, int yPos, int width, int height, MyGame parent, int xVelocity, int yVelocity){
		super(xPos, yPos, width, height, parent);
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	
	public int getXVelocity(){
		return xVelocity;
	}
	
	public void setXVelocity(int x){
		xVelocity = x;
	}
	
	public int getYVelocity(){
		return yVelocity;
	}
	
	public void setYVelocity(int y){
		yVelocity = y;
	}
	
	public void move(){
		setXPos(getXPos() + xVelocity);
		setYPos(getYPos() + yVelocity);
		yVelocity -= gravity;
		if(getYPos() < 0){
			setYPos(0);
			yVelocity = 0;
		}
	}
}
