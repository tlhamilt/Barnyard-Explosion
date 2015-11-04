package com.barnyard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class GameOptionsScreen implements InputProcessor, Screen {
	
	
	BarnyardExplosion game;
	
	private int playerCount = 0;	
	private Sprite[] characters = new Sprite[]{new Sprite(new Texture("HorseStanding.png")),
			new Sprite(new Texture("CowStanding.png")),new Sprite(new Texture("ChickenStanding.png")),
			new Sprite(new Texture("PigStanding.png"))};
	
	private final int characterCount = characters.length;
    OrthographicCamera camera;
    Listener mListener;
    GameScreen test;
    int [][] controls = {{Keys.LEFT, Keys.RIGHT, Keys.UP, Keys.DOWN},{Keys.A, Keys.D, Keys.W, Keys.S},{Keys.J, Keys.L, Keys.I, Keys.K},{Keys.F, Keys.H, Keys.T, Keys.G}};
    

	public GameOptionsScreen(BarnyardExplosion game){
		this.game = game;
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Gdx.input.setInputProcessor(this);
        for (int i = 0; i < characterCount; i++){
			characters[i].setPosition(32 * i + (400 - (characterCount - i) * 32), 320);
		}
        ArrayList<BlockEntity> blocks = new ArrayList<BlockEntity>();
        blocks.add(new BlockEntity(268, 64, 64, 64, game, new Sprite(new Texture("GroundMiddle.png")), true, true));
		//blocks.add(new BlockEntity(268, 2 * 64, 64, 64, game, new Sprite(new Texture("GroundMiddle.png")), true, false));
		blocks.add(new BlockEntity(268 - 64, 0, 64, 64, game, new Sprite(new Texture("GroundMiddle.png")), true, false));
        LevelEntity level = new LevelEntity(new Texture("background.png"),blocks,game);
        game.currentLevel = level;
        
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
		for (int i = 0; i < characterCount; i++){
			game.batch.draw(characters[i], 32 * i + (400 - (characterCount - i) * 32) , 320);
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
		if(playerCount < 4){
			for(Sprite current : characters){
				if(current.getBoundingRectangle().contains(screenX,480-screenY)){
					PlayerEntity newGuy = new PlayerEntity((int)current.getX(),(int)current.getY(),32,64,game,new Texture[]{current.getTexture(),current.getTexture()},0,0,controls[playerCount][0],controls[playerCount][1],controls[playerCount][2],controls[playerCount][3]);
					game.players.add(newGuy);
					playerCount++;
				}
			}
		}
		if(screenX > 760 && screenY > 440){
			game.setScreen(new GameScreen(game));
		}
		return false;
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

	