package com.barnyard;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class GameScreen implements Screen{
	private Texture[] horseTextures; //We should probably store character data in an excel file
	private Texture[] cowTextures;   //and parse the data from it based on character selection
	private Texture[] pigTextures;
	private Texture[] chickenTextures;
	
	private BarnyardExplosion game;
    OrthographicCamera camera;
	Texture blockImg;
	public Texture punchImg;
	Sprite punchSprite;
	Listener keyboardListener;
	ArrayList<PlayerEntity> players = new ArrayList<PlayerEntity>();
	ArrayList<BlockEntity> blocks = new ArrayList<BlockEntity>();
	int gravity = 1;
	Sound sound;
	
	public GameScreen(BarnyardExplosion barnyardExplosion) {
		this.game = barnyardExplosion;
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

		horseTextures = new Texture[]{new Texture("HorseStanding.png"),new Texture("HorseWalking.png"),new Texture("HorsePunching.png"),new Texture("HorseSound.png")};
		cowTextures = new Texture[]{new Texture("CowStanding.png"),new Texture("CowWalking.png"),new Texture("CowPunching.png"),new Texture("CowSound.png")};
		pigTextures = new Texture[]{new Texture("PigStanding.png"),new Texture("PigWalking.png"),new Texture("PigPunching.png")};
		chickenTextures = new Texture[]{new Texture("ChickenStanding.png"),new Texture("ChickenWalking.png"),new Texture("ChickenPunching.png")};

		blockImg = new Texture("GroundMiddle.png");
		punchImg = new Texture("PunchEffect.png");
		
		keyboardListener = new Listener();
		Gdx.input.setInputProcessor(keyboardListener);
		//PlayerEntities now accept texture arrays
//		players.add(new PlayerEntity(10, 0, 32, 64, game,horseTextures, 0, 0, Keys.LEFT, Keys.RIGHT, Keys.UP, Keys.DOWN));
//		players.add(new PlayerEntity(42, 0, 32, 64, game, cowTextures, 0, 0, Keys.A, Keys.D, Keys.W, Keys.S));
//		players.add(new PlayerEntity(74, 0, 32, 64, game, pigTextures, 0, 0, Keys.J, Keys.L, Keys.I, Keys.K));
//		players.add(new PlayerEntity(106, 0, 32, 64, game, chickenTextures, 0, 0, Keys.F, Keys.H, Keys.T, Keys.G));
		//blocks.add(new BlockEntity(268, 0, 64, 64, this, new Sprite(blockImg), false, false));
		
		sound = Gdx.audio.newSound(Gdx.files.internal("RiverValleyBreakdown.mp3"));
		sound.loop();
		
	}

	public void render (float delta) {
		//delta is the equivalent of using gdx.graphics.getDeltaTime()
		//we should use this since it is implemented into the render method
		//System.out.println(Gdx.graphics.getDeltaTime());
		//System.out.println(delta);

		//Gdx.graphics.getDeltaTime();
		//Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
        camera.update();
        
        game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.currentLevel.drawLevel();		
		controller();

//		for(PlayerEntity p : players){
//			game.batch.draw(p.getSprite(), p.getXPos(), p.getYPos());
//			if(p.animationState == 2){
//				game.batch.draw(p.attackSprite, p.attackX, p.attackY);
//			}
//		}
		game.batch.end();
		
	}
	
	public void controller(){
		for(PlayerEntity p : game.players){
//			if(keyboardListener.keysPressed[p.attackKey] && p.controlEnabled & p.attackEnabled){ //Attack
//				p.setCharacterState(2);
//				if(p.grounded){
//					p.setXVelocity(0);
//				}
//				p.controlEnabled = false;
//				p.attackEnabled = false;
//			}
			if(!keyboardListener.keysPressed[p.attackKey]){
				p.attackEnabled = true;
			}
			if(keyboardListener.keysPressed[p.leftKey] && p.controlEnabled){ // move left
				p.setXVelocity(-2);
				if(p.direction == 1){
					p.direction = -1;
					p.getSprite().flip(true, false);
					p.attackX = p.getXPos() - (int)p.attackSprite.getWidth();
					p.attackSprite.flip(true, false);
				}
			}
			if(keyboardListener.keysPressed[p.rightKey] && p.controlEnabled){ //move right
				p.setXVelocity(2);
				if(p.direction == -1){
					p.direction = 1;
					p.getSprite().flip(true, false);
					p.attackX = p.getXPos() + p.getWidth();
					p.attackSprite.flip(true, false);
				}
			}
			if(!keyboardListener.keysPressed[p.leftKey] && !keyboardListener.keysPressed[p.rightKey] && p.controlEnabled){
				p.setXVelocity(0);
				p.setCharacterState(0);
			}
			if(keyboardListener.keysPressed[p.jumpKey] && p.grounded && p.controlEnabled){
				p.setYVelocity(17);
			}
			p.move();
			if(p.animationState == 2){
				if(p.grounded){
					p.setXVelocity(0);
				}
				p.attackCounter -= 1;
				if(p.attackCounter == 0){
					p.setCharacterState(0);
					p.controlEnabled = true;
					p.attackCounter = p.attackTime;
				}
			}
		}
	}
	
	

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		//This method starts cleaning up the system resources
		game.dispose();
		sound.dispose();
		for(Texture current:horseTextures){
			current.dispose();
		}
		for(Texture current:pigTextures){
			current.dispose();
		}
		for(Texture current:cowTextures){
			current.dispose();
		}
		for(Texture current:chickenTextures){
			current.dispose();
		}
	}

	
}
