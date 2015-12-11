package com.barnyard;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GameOptionsScreen implements InputProcessor, Screen {
	
	
	BarnyardExplosion game;
	
	private int playerCount = 0;	
	private Sprite[] characters = new Sprite[]{new Sprite(new Texture("Horse.png")),
			new Sprite(new Texture("Cow.png")),new Sprite(new Texture("Chicken.png")),
			new Sprite(new Texture("Pig.png"))};
			//Format:Standing,Walking,Walking,Walking,Walking,Walking,punch,speech
	
	private final int characterCount = characters.length;
    OrthographicCamera camera;
    int [][] controls = {{Keys.LEFT, Keys.RIGHT, Keys.UP, Keys.DOWN},{Keys.A, Keys.D, Keys.W, Keys.S},
    		{Keys.J, Keys.L, Keys.I, Keys.K},{Keys.F, Keys.H, Keys.T, Keys.G}};
    

	public GameOptionsScreen(BarnyardExplosion game){
		this.game = game;
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Gdx.input.setInputProcessor(this);
        for (int i = 0; i < characterCount; i++){
			characters[i].setPosition(32 * i + (400 - (characterCount - i) * 32), 320);
		}
        setLevel();
        
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.75f, .75f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        
        game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.currentLevel.drawLevel();
		for (int i = 0; i < characterCount; i++){
	        game.batch.setColor(new Color(2.40f,2.20f,1.00f,1)); // 0.0 -> no change
			game.batch.draw(new TextureRegion(characters[i].getTexture(),32,64), 32 * i + (400 - (characterCount - i) * 32) , 320);
			//game.batch.draw(, 100, 100);//Demo Texture Regions
		}
		game.batch.draw(new Texture("block.png"), 760, 0);
		for(PlayerEntity e : game.players){
			e.move();
			if(e.getXPos() >= 800 - (playerCount * 20)){
				e.setXVelocity(0);
			}
		}
		
		game.batch.end();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int X = (int)(((double)screenX/Gdx.graphics.getWidth())*800);
		int Y = (int)(((double)screenY/Gdx.graphics.getHeight())*480);
		if(playerCount < 4){
			for(Sprite current : characters){
				Rectangle selector = new Rectangle(current.getX(),current.getY(),32,64);

				if(selector.contains(X,480-Y)){
					PlayerEntity newGuy = new PlayerEntity((int)current.getX(),(int)current.getY(),32,64,
							game,current.getTexture(),0,0,
							controls[playerCount][0],controls[playerCount][1],controls[playerCount][2],
							controls[playerCount][3]); //this is the correct implementation for when we start using
													   //Texture Regions that is why animation isn't changing sprite
					game.players.add(newGuy);
					playerCount++;
				}
			}
		}
		if(X > 760 && Y > 440){
			setLevel();
			game.setScreen(new GameScreen(game));
		}
		return false;
	}
	public void setLevel(){
		ArrayList<BlockEntity> blocks = new ArrayList<BlockEntity>();
		for(int i = 0; i < 800; i += 64){
			blocks.add(new BlockEntity(i, 0, 64, 64, game, new Sprite(new Texture("GroundMiddle.png")), true, false));

		}
	        LevelEntity level = new LevelEntity(new Texture("background480x800.png"),blocks,game);	       
	        game.currentLevel = level;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	

}

	