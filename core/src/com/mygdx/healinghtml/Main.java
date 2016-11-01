package com.mygdx.healinghtml;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
    public static Game game = new Game() {
        public void create () {
            this.setScreen(new LoadScreen());
        }
    };
	public static SpriteBatch spriteBatch;
    public static AssetManager assets = new AssetManager();
    public static SelectScreen selectScreen;

	public void create () {
		spriteBatch = new SpriteBatch();
		Texture.setAssetManager(assets);
        game.create();
	}

	public void render () {
		game.render();
	}

    public void dispose() {
        assets.dispose();
        spriteBatch.dispose();
    }
}