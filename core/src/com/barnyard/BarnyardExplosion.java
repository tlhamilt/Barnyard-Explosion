package com.barnyard;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class BarnyardExplosion extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    OrthographicCamera camera;

    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        
        this.setScreen(new GameScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}