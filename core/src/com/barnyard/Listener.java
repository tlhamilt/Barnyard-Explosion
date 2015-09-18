package com.barnyard;

import com.badlogic.gdx.InputProcessor;

public class Listener implements InputProcessor {
	int numKeys = 999;
	boolean[] keysPressed = new boolean[numKeys];

	@Override
	public boolean keyDown(int keycode) {
		keysPressed[keycode] = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		keysPressed[keycode] = false;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
