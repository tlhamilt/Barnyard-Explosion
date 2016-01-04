package com.barnyard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameplayOptions extends ClickListener implements Screen{
	
	Skin skin;
	Stage stage;
    OrthographicCamera camera;
	final BarnyardExplosion game;

	public GameplayOptions(BarnyardExplosion barnyardExplosion) {
		
		this.game = barnyardExplosion;
		stage = new Stage();
        Gdx.input.setInputProcessor(stage);
 
        createBasicSkin();
        TextButton startButton = new TextButton("Item Spawn", skin);
        TextButton optionsButton = new TextButton("Life Bar", skin);
        TextButton creditsButton = new TextButton("Lives", skin); 

        startButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8 , Gdx.graphics.getHeight() / 2 + 90);
        optionsButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8 , Gdx.graphics.getHeight() / 2 + 10);
        creditsButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8 , Gdx.graphics.getHeight() / 2 - 70);

        startButton.setName("Item Spawn");
        optionsButton.setName("Life Bar");
        creditsButton.setName("Lives");

        stage.addActor(startButton);
        stage.addActor(optionsButton);
        stage.addActor(creditsButton);

        startButton.addListener(this); 
        optionsButton.addListener(this); 
        creditsButton.addListener(this); 
	}
	
	private void createBasicSkin(){
		  //Create a font
		  BitmapFont font = new BitmapFont();
		  skin = new Skin();
		  skin.add("default", font);
		 
		  //Create a texture
		  Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
		  pixmap.setColor(Color.WHITE);
		  pixmap.fill();
		  skin.add("background",new Texture(pixmap));
		 
		  //Create a button style
		  TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		  textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
		  textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
		  textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
		  textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
		  textButtonStyle.font = skin.getFont("default");
		  skin.add("default", textButtonStyle); 
		}
	
	public void render(float delta) {
        
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
    
	public void clicked(InputEvent event, float x, float y) {
    	
		if(event.getListenerActor().getName() == "Item Spawn"){
    		//Insert 
		 }else if(event.getListenerActor().getName() == "Options"){
			 System.out.println("Options");
			 dispose();
		 }else if(event.getListenerActor().getName() == "Credits"){
			 System.out.println("Credits");
			 dispose();
		 }else{
			 System.err.println("Error: Unassigned Button Selected");
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
		stage.dispose();		
	}	
}