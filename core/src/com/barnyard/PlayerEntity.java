package com.barnyard;

public class PlayerEntity extends MovingEntity {
	private int health;
	public int upKey;
	public int leftKey;
	public int rightKey;
	public int jumpKey;
	// variable for weapon eqquiped

	public PlayerEntity(int xPos, int yPos, int width, int height, MyGame parent, int xVelocity, int yVelocity, int leftKey, int rightKey, int jumpKey){
		super(xPos, yPos, width, height, parent, xVelocity, yVelocity);
		health = 100;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.jumpKey = jumpKey;
	}
	
	public void move(){
		setXPos(getXPos() + getXVelocity());
		if(getYVelocity() > -10){
			setYVelocity(getYVelocity() - parent.gravity);
		}
		grounded = false;
		for(StationaryEntity b : parent.blocks)
		{
			int dir = parent.isColliding(this, b);
			if(dir == 1){
				setYPos(b.getYPos() + b.getHeight());
				setYVelocity(0);
				grounded = true;
			}
			else if(dir == 2){
				setYPos(b.getYPos() - getHeight());
				setYVelocity(0);
			}
			else if(dir == 3){
				setXPos(b.getXPos() + b.getWidth());
				setXVelocity(0);
			}
			else if(dir == 4){
				setXPos(b.getXPos() - getWidth());
				setXVelocity(0);
			}
		}
		if(getYVelocity() != 0)
		{
			setYPos(getYPos() + getYVelocity());
		}
		
		if(getYPos() < 0){
			setYPos(0);
			setYVelocity(0);
			grounded = true;
		}
	}
	
	public void die(){
		// put code for death here
	}
}
