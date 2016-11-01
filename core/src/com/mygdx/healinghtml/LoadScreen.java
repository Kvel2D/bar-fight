package com.mygdx.healinghtml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadScreen extends ScreenAdapter
{
    SpriteBatch batch = Main.spriteBatch;
    float minimumShowTime = 0f;

    public void show()
    {
        for (String texture : AssetPaths.textures)
        {
            Main.assets.load(texture, Texture.class);
        }
        Main.assets.load(AssetPaths.FONT, BitmapFont.class);
        Main.assets.load(AssetPaths.FONT_BIG, BitmapFont.class);
        Main.assets.finishLoading();

        Main.selectScreen = new SelectScreen();
    }

    public void render(float deltaTime)
    {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.begin();

        batch.end();

        minimumShowTime -= deltaTime;
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            minimumShowTime = 0f;
        if (minimumShowTime <= 0 && Main.assets.update())
            Main.game.setScreen(Main.selectScreen);
    }
}
