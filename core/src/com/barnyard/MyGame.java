package com.barnyard;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

//This is a comment
public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite playerSprite;
	Sprite blockSprite;
	Texture playerImg;
	Texture blockImg;
	World world;
	Body playerBody;
	Body blockBody;
	OrthographicCamera camera;
	final float PIXELS_TO_METERS = 100f;
	final short PLAYER_ENTITY = 0x1;
	final short BLOCK_ENTITY = 0x1 << 1;
	
	Listener keyboardListener;
	int xPos = 175;
	int yPos = 125;
	MovingEntity player;
	Entity block;
	int gravity = -5;
	Sound sound;
	@Override
	public void create () {
		batch = new SpriteBatch();
		playerImg = new Texture("player.png");
		blockImg = new Texture("block.png");
		
		keyboardListener = new Listener();
		Gdx.input.setInputProcessor(keyboardListener);
		player = new MovingEntity(100, 100, 32, 32, this, 0, 0);
		block = new Entity(50, 150, 32, 32, this);
		 sound = Gdx.audio.newSound(Gdx.files.internal("testSong.mp3"));
		 sound.play();
	}

	@Override
	public void render () {
		if(keyboardListener.keysPressed[Keys.LEFT]){
			player.setXVelocity(-5);
		}
		if(keyboardListener.keysPressed[Keys.RIGHT]){
			player.setXVelocity(5);
		}
		if(!keyboardListener.keysPressed[Keys.LEFT] && !keyboardListener.keysPressed[Keys.RIGHT]){
			player.setXVelocity(0);
		}
		if(keyboardListener.keysPressed[Keys.SPACE] && player.grounded){
			player.setYVelocity(20);
		}
		
		player.move();
		Gdx.gl.glClearColor(.5f, 0.25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		batch.draw(playerImg, player.getXPos(), player.getYPos());
		batch.draw(blockImg, block.getXPos(), block.getYPos());
		batch.end();
		
	}
	int isColliding(MovingEntity player, Entity block){
		// This method determines if a player and a block are colliding, and if so,
		// which side the player is colliding with.
		// The program returns 0 if there is no collision,
		// returns 1 if the player is colliding with the north side,
		// returns 2 if the player is colliding with the south side,
		// returns 3 if the player is colliding with the east side,
		// and returns 4 if the player is colliding with the west side.
		Rectangle playerRect = new Rectangle(player.getXPos(), player.getYPos(), 32, 32);
		Rectangle otherRect = new Rectangle(block.getXPos(), block.getYPos(), 32, 32);
		// determines if there is a collision
		if(!playerRect.overlaps(otherRect)){
			return 0;
		}
		// for each possible side, a rectangle is generated containing all possible points that
		// the player's position could be if they are colliding on that side
		int x = block.getXPos() - player.getWidth();
		int y = block.getYPos() + (block.getHeight() / 2) - (player.getHeight() / 2);
		int w = player.getWidth() + block.getWidth();
		int h = (block.getHeight() / 2) + (player.getHeight() / 2);
		Rectangle northRect = new Rectangle(x, y, w, h);
		// if the created rectangle contains the player's position, the collision is on that side
		if(northRect.contains(player.getXPos(), player.getYPos()) && player.getYPos() - player.getYVelocity() > block.getYPos() + block.getHeight()){
			return 1;
		}
		y = block.getYPos() - player.getHeight();
		h -= 1;
		Rectangle southRect = new Rectangle(x, y, w, h);
		if(southRect.contains(player.getXPos(), player.getYPos()) && player.getYPos() + player.getHeight() - player.getYVelocity() < block.getYPos()){
			return 2;
		}
		x = block.getXPos() + (block.getWidth() / 2) - (player.getWidth() / 2);
		y = block.getYPos() - player.getHeight();
		w = (block.getWidth() / 2) + (player.getWidth() / 2);
		h = player.getHeight() + block.getHeight();
		Rectangle eastRect = new Rectangle(x, y, w, h);
		if(eastRect.contains(player.getXPos(), player.getYPos())){
			return 3;
		}
		x = block.getXPos() - player.getWidth();
		w -= 1;
		Rectangle westRect = new Rectangle(x, y, w, h);
		if(westRect.contains(player.getXPos(), player.getYPos())){
			return 4;
		}
		return 0;
	}
}
