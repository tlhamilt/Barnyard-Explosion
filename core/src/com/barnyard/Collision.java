package com.barnyard;

import com.badlogic.gdx.math.Rectangle;

//NEEDS CLEANUP WORK: CONSOLIDATE COLLISION CODE!

public class Collision {
	int isColliding(MovingEntity e1, StationaryEntity e2){
		// This method determines if a player and a block are colliding, and if so,
		// which side the player is colliding with.
		// The program returns 0 if there is no collision,
		// returns 1 if the player is colliding with the north side,
		// returns 2 if the player is colliding with the south side,
		// returns 3 if the player is colliding with the east side,
		// and returns 4 if the player is colliding with the west side.
		// determines if there is a collision
		if(!e1.getHitBox().overlaps(e2.getHitBox())){
			return 0;
		}
		// for each possible side, a rectangle is generated containing all possible points that
		// the player's position could be if they are colliding on that side
		int x = e2.getXPos() - e1.getWidth();
		int y = e2.getYPos() + (e2.getHeight() / 2) - (e1.getHeight() / 2);
		int w = e1.getWidth() + e2.getWidth();
		int h = (e2.getHeight() / 2) + (e1.getHeight() / 2); // change here
		Rectangle northRect = new Rectangle(x, y, w, h);
		// if the created rectangle contains the player's position, the collision is on that side
		if(northRect.contains(e1.getXPos(), e1.getYPos()) && e1.getYPos() - e1.getYVelocity() >= e2.getYPos() + e2.getHeight() && e2.isTopOpen()){
			return 1;																		// change here
		}
		y = e2.getYPos() - e1.getHeight();
		h -= 1;
		Rectangle southRect = new Rectangle(x, y, w, h);
		if(southRect.contains(e1.getXPos(), e1.getYPos()) && e1.getYPos() + e1.getHeight() - e1.getYVelocity() <= e2.getYPos() && e2.isBottomOpen()){
			return 2;
		}
		x = e2.getXPos() + (e2.getWidth() / 2) - (e1.getWidth() / 2);
		y = e2.getYPos() - e1.getHeight();
		w = (e2.getWidth() / 2) + (e1.getWidth() / 2);
		h = e1.getHeight() + e2.getHeight();
		Rectangle eastRect = new Rectangle(x, y, w, h);
		if(eastRect.contains(e1.getXPos(), e1.getYPos())){
			return 3;
		}
		x = e2.getXPos() - e1.getWidth();
		w -= 1;
		Rectangle westRect = new Rectangle(x, y, w, h);
		if(westRect.contains(e1.getXPos(), e1.getYPos())){
			return 4;
		}
		return 0;
	}
}
