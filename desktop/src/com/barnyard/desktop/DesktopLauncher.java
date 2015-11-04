package com.barnyard.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.barnyard.BarnyardExplosion;

public class DesktopLauncher{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Barnyard Explosion!";
	      config.width = 1024;
	      config.height = 1024;
		new LwjglApplication(new BarnyardExplosion(), config);
	}
}
