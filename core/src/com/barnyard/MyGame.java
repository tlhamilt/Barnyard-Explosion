package com.barnyard;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture playerImg;
	Texture blockImg;
	Listener keyboardListener;
	ArrayList<PlayerEntity> players = new ArrayList<PlayerEntity>();
	ArrayList<StationaryEntity> blocks = new ArrayList<StationaryEntity>();
	int gravity = 1;
	Sound sound;
	@Override
	public void create () {
		batch = new SpriteBatch();
		playerImg = new Texture("player.png");
		blockImg = new Texture("block.png");
		
		keyboardListener = new Listener();
		Gdx.input.setInputProcessor(keyboardListener);
		players.add(new PlayerEntity(100, 50, 32, 32, this, new Sprite(playerImg), 0, 0, Keys.LEFT, Keys.RIGHT, Keys.SPACE));
		players.add(new PlayerEntity(150, 50, 32, 32, this, new Sprite(playerImg), 0, 0, Keys.A, Keys.D, Keys.C));
		players.add(new PlayerEntity(200, 50, 32, 32, this, new Sprite(playerImg), 0, 0, Keys.J, Keys.L, Keys.PERIOD));
		blocks.add(new StationaryEntity(50, 150, 32, 32, this, new Sprite(blockImg)));
		blocks.add(new StationaryEntity(170, 100, 32, 32, this, new Sprite(blockImg)));
		blocks.add(new StationaryEntity(290, 50, 32, 32, this, new Sprite(blockImg)));
		 sound = Gdx.audio.newSound(Gdx.files.internal("RiverValleyBreakdown.mp3"));
		 sound.loop();
	}

	@Override
	public void render () {
		for(PlayerEntity p : players){
			if(keyboardListener.keysPressed[p.leftKey]){
				p.setXVelocity(-5);
				if(p.direction == 1){
					p.direction = -1;
					p.getSprite().flip(true, false);
				}
			}
			if(keyboardListener.keysPressed[p.rightKey]){
				p.setXVelocity(5);
				if(p.direction == -1){
					p.direction = 1;
					p.getSprite().flip(true, false);
				}
			}
			if(!keyboardListener.keysPressed[p.leftKey] && !keyboardListener.keysPressed[p.rightKey]){
				p.setXVelocity(0);
			}
			if(keyboardListener.keysPressed[p.jumpKey] && p.grounded){
				p.setYVelocity(20);
			}
			p.move();
		}
		
		Gdx.gl.glClearColor(.5f, 0.25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(PlayerEntity p : players){
			batch.draw(playerImg, p.getXPos(), p.getYPos());
			batch.draw(p.getSprite(), p.getXPos(), p.getYPos());
		}
		for(StationaryEntity b : blocks)
		batch.draw(blockImg, b.getXPos(), b.getYPos());
		batch.end();
		
	}
	int isColliding(MovingEntity e1, Entity e2){
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
		int h = (e2.getHeight() / 2) + (e1.getHeight() / 2);
		Rectangle northRect = new Rectangle(x, y, w, h);
		// if the created rectangle contains the player's position, the collision is on that side
		if(northRect.contains(e1.getXPos(), e1.getYPos()) && e1.getYPos() - e1.getYVelocity() > e2.getYPos() + e2.getHeight()){
			return 1;
		}
		y = e2.getYPos() - e1.getHeight();
		h -= 1;
		Rectangle southRect = new Rectangle(x, y, w, h);
		if(southRect.contains(e1.getXPos(), e1.getYPos()) && e1.getYPos() + e1.getHeight() - e1.getYVelocity() < e2.getYPos()){
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
