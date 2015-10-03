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
		if(yVelocity > -10){
			yVelocity -= gravity;
		}
		grounded = false;
		int dir = parent.isColliding(this, parent.block);
		if(dir == 1){
			setYPos(parent.block.getYPos() + parent.block.getHeight());
			yVelocity = 0;
			grounded = true;
		}
		else if(dir == 2){
			setYPos(parent.block.getYPos() - getHeight());
			yVelocity = 0;
		}
		else if(dir == 3){
			setXPos(parent.block.getXPos() + parent.block.getWidth());
			xVelocity = 0;
		}
		else if(dir == 4){
			setXPos(parent.block.getXPos() - getWidth());
		}
		if(yVelocity != 0)
		{
			setYPos(getYPos() + yVelocity);
		}
		
		if(getYPos() < 0){
			setYPos(0);
			yVelocity = 0;
			grounded = true;
		}
	}
}
