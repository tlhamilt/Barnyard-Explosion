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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
		/*playerSprite = new Sprite(playerImg);
		playerSprite.setPosition(20 -playerSprite.getWidth() / 2, 100 - playerSprite.getHeight() / 2);
		blockSprite = new Sprite(blockImg);
		blockSprite.setPosition(20 - blockSprite.getWidth() / 2, 50 - blockSprite.getHeight() / 2);	
		world = new World(new Vector2(0, -1f), true);
		
		BodyDef playerBodyDef = new BodyDef();
		playerBodyDef.type = BodyDef.BodyType.DynamicBody;
		playerBodyDef.position.set((playerSprite.getX() + playerSprite.getWidth() / 2) / PIXELS_TO_METERS, (playerSprite.getY() + playerSprite.getHeight() / 2) / PIXELS_TO_METERS);
		playerBody = world.createBody(playerBodyDef);
		
		BodyDef blockBodyDef = new BodyDef();
		blockBodyDef.type = BodyDef.BodyType.StaticBody;
		blockBodyDef.position.set((blockSprite.getX() + blockSprite.getWidth() / 2) / PIXELS_TO_METERS, (blockSprite.getY() + blockSprite.getHeight() / 2) / PIXELS_TO_METERS);
		blockBody = world.createBody(blockBodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(playerSprite.getWidth() / 2 / PIXELS_TO_METERS, playerSprite.getHeight() / 2 / PIXELS_TO_METERS);
		FixtureDef playerFixtureDef = new FixtureDef();
		playerFixtureDef.shape = shape;
		playerFixtureDef.density = 0.1f;
		playerFixtureDef.restitution = 0.5f;
		playerFixtureDef.filter.categoryBits = PLAYER_ENTITY;
		playerFixtureDef.filter.categoryBits = BLOCK_ENTITY;
		playerBody.createFixture(playerFixtureDef);
		
		PolygonShape shape2 = new PolygonShape();
		shape2.setAsBox(blockSprite.getWidth() / 2 / PIXELS_TO_METERS, blockSprite.getHeight() / 2 / PIXELS_TO_METERS);
		FixtureDef blockFixtureDef = new FixtureDef();
		blockFixtureDef.shape = shape;
		blockFixtureDef.filter.categoryBits = BLOCK_ENTITY;
	    blockFixtureDef.filter.maskBits = PLAYER_ENTITY;
	    blockBody.createFixture(blockFixtureDef);
	    
	    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		*/
		keyboardListener = new Listener();
		Gdx.input.setInputProcessor(keyboardListener);
		player = new MovingEntity(20, 100, 32, 32, this, 0, 0);
		block = new Entity(20, 50, 32, 32, this);
		 sound = Gdx.audio.newSound(Gdx.files.internal("testSong.mp3"));
		 sound.play();
	}

	@Override
	public void render () {
		/*camera.update();
		world.step(1f / 60f, 6, 2);
		playerSprite.setPosition((playerBody.getPosition().x * PIXELS_TO_METERS) - playerSprite.
                getWidth()/2,
        (playerBody.getPosition().y * PIXELS_TO_METERS) - playerSprite.getHeight()/2 );
		playerSprite.setRotation((float)Math.toDegrees(playerBody.getAngle()));*/
		int futureXPos = player.getXPos() + player.getXVelocity();
		int futureYPos = player.getYPos() + player.getYVelocity();
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
		player.grounded = false;
		player.move();
		/*if(player.getXPos() + player.getWidth() > block.getXPos() && player.getXPos() < block.getXPos() + block.getWidth() && player.getYPos() < block.getYPos() + block.getHeight() + player.getHeight() && player.getYPos() > block.getYPos() - player.getYPos()){
			player.setXVelocity(0);
			player.setXPos(block.getXPos() - player.getWidth());
		}
		
		if(player.getYPos() <= block.getYPos() + block.getHeight() && player.getYPos() >= block.getYPos() && player.getXPos() >= block.getXPos() - player.getWidth() && player.getXPos() <= block.getXPos() + block.getWidth()){
			player.setYVelocity(0);
			player.setYPos(block.getYPos() + block.getHeight());
		}
		*/
		Gdx.gl.glClearColor(.5f, 0.25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		/*batch.draw(playerSprite, playerSprite.getX(), playerSprite.getY(),playerSprite.getOriginX(),
                playerSprite.getOriginY(),
                playerSprite.getWidth(),playerSprite.getHeight(),playerSprite.getScaleX(),playerSprite.
                        getScaleY(),playerSprite.getRotation());
		batch.draw(blockSprite, blockSprite.getX(), blockSprite.getY(),blockSprite.getOriginX(),
                blockSprite.getOriginY(),
                blockSprite.getWidth(), blockSprite.getHeight(),blockSprite.getScaleX(),blockSprite.
                        getScaleY(),blockSprite.getRotation());*/
		batch.draw(playerImg, player.getXPos(), player.getYPos());
		batch.draw(blockImg, block.getXPos(), block.getYPos());
		batch.end();
		
	}
	boolean isColliding(MovingEntity player, Entity block){
		Rectangle playerRect = new Rectangle(player.getXPos(), player.getYPos(), 32, 32);
		Rectangle otherRect = new Rectangle(block.getXPos(), block.getYPos(), 32, 32);
		return playerRect.overlaps(otherRect);
	}
}
