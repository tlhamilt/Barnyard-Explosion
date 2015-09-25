package com.barnyard;

public class MovingEntity extends Entity {
	private int xVelocity;
	private int yVelocity;
	private int gravity = 1;
	public boolean grounded = false;
	
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
		if(yVelocity > -5){
			yVelocity -= gravity;
		}
		if(parent.isColliding(this, parent.block)){
			setYPos(parent.block.getYPos() + 32);
			yVelocity = 0;
			grounded = true;
		}
		setYPos(getYPos() + yVelocity);
		
		if(getYPos() < 0){
			setYPos(0);
			yVelocity = 0;
			grounded = true;
		}
	}
}
