package com.barnyard;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//This is a comment
public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Listener keyboardListener;
	int xPos = 175;
	int yPos = 125;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		keyboardListener = new Listener();
		Gdx.input.setInputProcessor(keyboardListener);
	}

	@Override
	public void render () {
		if(keyboardListener.keysPressed[Keys.LEFT]){
			xPos = xPos - 5;
		}
		if(keyboardListener.keysPressed[Keys.RIGHT]){
			xPos = xPos + 5;
		}
		Gdx.gl.glClearColor(.5f, 0.25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, xPos, yPos);
		batch.end();
	}
}
