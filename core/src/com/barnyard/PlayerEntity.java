package com.barnyard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class PlayerEntity extends MovingEntity {
	private int health;
	public int attackKey;
	public int leftKey;
	public int rightKey;
	public int jumpKey;
	public int direction;
	private Texture characterAnimations;
	private int animationCounter = 0;
	public int animationState;
	public boolean controlEnabled;
	public boolean attackEnabled;
	public int attackTime = 20;
	public int attackCounter = attackTime;
	public int hurtTime = 0;
	public int attackX;
	public int attackY;
	Collision parent;
	public Sprite attackSprite;
	// variable for weapon eqquiped

	public PlayerEntity(int xPos, int yPos, int width, int height, BarnyardExplosion game, 
			Texture animations, int xVelocity, int yVelocity, int leftKey, int rightKey, int jumpKey, 
			int attackKey){
		super(xPos, yPos, width, height, game, new Sprite(new TextureRegion(animations,32,64)), xVelocity, yVelocity);
		health = 100;
		characterAnimations = animations;
		animationState = 0;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.jumpKey = jumpKey;
		this.attackKey = attackKey;
		direction = 1;
		controlEnabled = true;
		attackEnabled = true;
		parent = new Collision();
		attackSprite = new Sprite(new Texture("PunchEffect.png"));
		attackY = getYPos() + (getHeight() / 2) - (int)(attackSprite.getHeight() / 2) + 2;
		attackX = getXPos() + getWidth();
		health = 100;
	}
	
	// This controls the animation image of the characters
	// Index Reference: 0 - Standing, 1 - Walking, 2 - Punching, 3 - Speaking/Sound
	public void setCharacterState(int state){ 
		setSprite(new Sprite(new TextureRegion(characterAnimations,32 * state, 0 , 32, 64))); 
		animationState = state;
		if (direction == -1){
			getSprite().flip(true, false);
		}
	}
	public void walking(){
		if(animationCounter % 2 == 1){//Temporary Walking Animation
			if(animationState == 0){			   //May need to change for added animations
				setCharacterState(1);
			}
			else{
				setCharacterState(animationState + 1);
				if(animationState == 6){
					setCharacterState(1);
				}
			}
		}
		animationCounter++;
	}
	public void attacking(){
		game.batch.draw(attackSprite, attackX, attackY);
	}
	public void collision(){
		for(BlockEntity b : game.currentLevel.blocks)
		{
			int dir = parent.isColliding(this, b);
			if(dir == 1){
				setYPos(b.getYPos() + b.getHeight());
				setYVelocity(0);					// change here
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
		for(PlayerEntity p : game.players){
			Rectangle hurtbox = new Rectangle(p.attackX, p.attackY, p.attackSprite.getWidth(), p.attackSprite.getHeight());
			if(p.animationState == 6 && p != this && hurtTime == 0 && getHitBox().overlaps(hurtbox)){
				controlEnabled = false;
				hurtTime = 10;
				attackTime = attackCounter;
				health -= 20;
				setCharacterState(0);
				if(p.direction == 1){
					setXVelocity(5);
				}
				else{
					setXVelocity(-5);
				}
			}
		}
		if(health <= 0){
			die();
		}
	}
	public void move(){
		if(getXVelocity() != 0 && grounded && controlEnabled){
			walking();
		}
		setXPos(getXPos() + getXVelocity());
		if(getYVelocity() > -10){
			setYVelocity(getYVelocity() - game.currentLevel.getGravity());
		}
		setYPos(getYPos() + getYVelocity());
		grounded = false;

		collision();
		if(getYPos() < 0){
			setYPos(0);
			setYVelocity(0);
			grounded = true;
		}
		if(direction == 1){
			attackX = getXPos() + getWidth();
		}
		else{
			attackX = getXPos() - (int)attackSprite.getWidth();
		}
		attackY = getYPos() + (getHeight() / 2) - (int)(attackSprite.getHeight() / 2) + 2;
		drawEntity();
		if(animationState == 6){
			attacking();
		}
		if(hurtTime > 0){
			hurtTime--;
			if(hurtTime == 0){
				controlEnabled = true;
			}
		}
	}
	public void gotHit(int damage){
		health -= damage;
	}
	public void die(){
		health = 100;
		setXPos(100);
		setYPos(100);
		setCharacterState(0);
		controlEnabled = true;
		hurtTime = 0;
		attackCounter = attackTime;
	}
}
