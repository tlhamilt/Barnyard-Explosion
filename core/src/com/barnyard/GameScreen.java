package com.barnyard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

//CLEANUP: MOVE PLAYER CODE TO PLAYER ENTITY

public class GameScreen implements Screen{
	
	private BarnyardExplosion game;
    OrthographicCamera camera;
	Listener keyboardListener;
	Sound sound;
	
	public GameScreen(BarnyardExplosion barnyardExplosion) {
		this.game = barnyardExplosion;
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);		
		keyboardListener = new Listener();
		Gdx.input.setInputProcessor(keyboardListener);		
		sound = Gdx.audio.newSound(Gdx.files.internal("RiverValleyBreakdown.mp3"));
		sound.loop();
		
	}

	public void render (float delta) {
		//Implement delta speed corrections
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
        camera.update();
        
        game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.currentLevel.drawLevel();		
		controller();
		game.batch.end();
		
	}
	
	public void controller(){ //Cleanup! Move Attack code to Player Entity. Basically redo this the correct way
		for(PlayerEntity p : game.players){
			if(keyboardListener.keysPressed[p.attackKey] && p.controlEnabled & p.attackEnabled){ //Attack
				p.setCharacterState(6);
				if(p.grounded){
					p.setXVelocity(0);
				}
				p.controlEnabled = false;
				p.attackEnabled = false;
			}
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
			if(p.animationState == 6){
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
		/*for(Texture current:horseTextures){
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
		}*/
	}

	
}
