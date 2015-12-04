package com.barnyard;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponEntity extends ItemEntity{
	private PlayerEntity pickedBy;
	private boolean isMelee;
	private int weaponDamage = 0;
	private int magazine;
	private int fireRate;
	private int bulletSpeed;
	private int bullet = 0;
	private Projectile[] shots;
	
	//Melee
	public WeaponEntity(int xPos, int yPos, int width, int height, BarnyardExplosion game, Sprite sprite, 
			int xVelocity,int yVelocity) {
		super(xPos, yPos, width, height, game, sprite, xVelocity, yVelocity);
		isMelee = true;
	}
	//Projectile Weapon
	public WeaponEntity(int xPos, int yPos, int width, int height, BarnyardExplosion game, Sprite sprite, 
			int xVelocity,int yVelocity, int damage) {
		super(xPos, yPos, width, height, game, sprite, xVelocity, yVelocity);
		shots = new Projectile[fireRate];
		isMelee = false;
	}
	
	public void pickup(PlayerEntity animal){
		pickedBy = animal;
	}
	public void drop(){
		pickedBy = null;
	}
	public void attack(){
		if(isMelee){
			stab();
		}else{
			shoot();
		}	
	}
	public void stab(){
			
	}
	public void shoot(){
		if(bullet == shots.length){
			bullet = 0;
		}
		shots[bullet] = new Projectile(getXPos(), getYPos(), getWidth(), getHeight(), game, getSprite(), bulletSpeed * pickedBy.direction, 0);
		bullet++;
	}
	
	
	
	
	
	//Projectiles
public class Projectile extends MovingEntity{

	public Projectile(int xPos, int yPos, int width, int height, BarnyardExplosion game, Sprite sprite,
				int xVelocity, int yVelocity) {
			super(xPos, yPos, width, height, game, sprite, xVelocity, yVelocity);
			
		}

	public void hitPlayer(){
		for(PlayerEntity current : game.players){
			if(current.getSprite().getBoundingRectangle().overlaps(getSprite().getBoundingRectangle())){
				current.gotHit(weaponDamage); //TODO Create implementation in player entity for taking damage
			}
			
		}
	}

	@Override
	public void move() {
		setXPos(getXPos() + getXVelocity());
	}
	 
 }
}
 