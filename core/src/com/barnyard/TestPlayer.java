package com.barnyard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TestPlayer extends MovingEntity {
	@SuppressWarnings("unused")
	private int health;
	public int attackKey;
	public int leftKey;
	public int rightKey;
	public int jumpKey;
	public int direction;
	private Texture[] characterAnimations;
	private int animationCounter = 0;
	public int animationState;
	public boolean controlEnabled;
	public boolean attackEnabled;
	public int attackTime = 20;
	public int attackCounter = attackTime;
	public int hurtTime = 10;
	public int attackX;
	public int attackY;
	public Sprite attackSprite;
	// variable for weapon eqquiped

	public TestPlayer(int xPos, int yPos, int width, int height,BarnyardExplosion game, Texture[] animations, int xVelocity, int yVelocity, int leftKey, int rightKey, int jumpKey, int attackKey){
		super(xPos, yPos, width, height,game, new Sprite(animations[0]), xVelocity, yVelocity);
		health = 100;
		characterAnimations = animations;
		animationState = 0;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.jumpKey = jumpKey;
		this.attackKey = attackKey;
		direction = 1;
		controlEnabled = true;

	}
	
	// This controls the animation image of the characters
	// Index Reference: 0 - Standing, 1 - Walking, 2 - Punching, 3 - Speaking/Sound
	public void setCharacterState(int state){ 
		setSprite(new Sprite(characterAnimations[state])); 
		animationState = state;
		if (direction == -1){
			getSprite().flip(true, false);
		}
	}
	
	public void move(){
		
		if(animationCounter % 10 == 0 && grounded && controlEnabled){//Temporary Walking Animation
			if(animationState == 0){			   //May need to change for added animations
				setCharacterState(1);
			}else{
				setCharacterState(0);
				}
		}
		setXPos(getXPos() + getXVelocity());
		if(getYVelocity() > -10){
			setYVelocity(getYVelocity() - 1);
		}
		grounded = false;
		
		if(getYVelocity() != 0)
		{
			setYPos(getYPos() + getYVelocity());
		}
		if(getYPos() < 0){
			setYPos(0);
			setYVelocity(0);
			grounded = true;
		}
		if(getXVelocity() != 0)//Walking Animation Timer
		animationCounter++;
	}
	public void die(){
		// put code for death here
	}
}
