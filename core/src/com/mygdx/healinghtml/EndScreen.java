package com.mygdx.healinghtml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndScreen extends ScreenAdapter
{
    SpriteBatch batch = Main.spriteBatch;
    GlyphLayout layout = new GlyphLayout();
    BitmapFont fontBig = Main.assets.get(AssetPaths.FONT_BIG);
    BitmapFont fontSmall = Main.assets.get(AssetPaths.FONT);
    String text;
    Color backgroundColor;
    float fadeIn = 0f;

    public EndScreen(String result)
    {
        if (result == "win")
        {
            text = "YOU WIN";
            backgroundColor = Color.FOREST;
        } else if (result == "fail")
        {
            text = "EVERYBAR IS DEAD";
            backgroundColor = Color.RED;
        } else
        {
            text = "SOMETHING WENT WRONG";
            backgroundColor = Color.PINK;
        }
        fontBig.setColor(Color.WHITE);
    }

    public void render(float deltaTime)
    {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, fadeIn);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.begin();
        batch.enableBlending();

        fadeIn += (1f - fadeIn) * 0.05f;
        if (fadeIn > 0.9f)
            fadeIn = 1f;
        fontBig.setColor(1f, 1f, 1f, fadeIn);
        layout.setText(fontBig, text);
        fontBig.draw(batch, text, 640f - layout.width / 2, 360f);

        if (fadeIn == 1f)
        {
            fontSmall.setColor(Color.WHITE);
            layout.setText(fontSmall, "Press any button to continue");
            fontSmall.draw(batch, "Press any button to continue", 640f - layout.width / 2, 100f);
        }

        batch.end();


        if (fadeIn == 1f && Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
            Main.game.setScreen(Main.selectScreen);

    }
}