package com.mygdx.healinghtml;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TutorialScreen extends GameScreen
{
    SpriteBatch batch = Main.spriteBatch;
    BitmapFont font = Main.assets.get(AssetPaths.FONT);
    float timer = 0f;

    public TutorialScreen()
    {
        super(0);
    }


    public void render(float deltaTime)
    {
        super.render(deltaTime);

        timer += Constants.TIME_STEP;

        font.setColor(Color.WHITE);

        batch.begin();
            if(0f < timer && timer < 5f)
            {
                font.draw(batch, "The green bars are going down", 10f, 100f);
            }
            else if(5f < timer && timer < 10f)
            {
                font.draw(batch, "If all bars are completely down, you lose", 10f, 100f);
            }
            else if(10f < timer && timer < 15f)
            {
                font.draw(batch, "To make bars go up you can use spells", 10f, 100f);
            }
            else if(15f < timer && timer < 20f)
            {
                font.draw(batch, "First, click on the bar to target it", 10f, 100f);
            }
            else if(20f < timer && timer < 25f)
            {
                font.draw(batch, "Then cast the spell by clicking on it\nor pressing an appropriate\nnumber button(1-5)", 10f, 100f);
            }
            else if(25f < timer && timer < 30f)
            {
                font.draw(batch, "Hover over a spell to see it's details", 10f, 100f);
            }
            else if(30f < timer && timer < 35f)
            {
                font.draw(batch, "Spells use mana, so choose your spells well", 10f, 100f);
            }
            else if(35f < timer && timer < 40f)
            {
                font.draw(batch, "The dungeon is over when the red bar is empty", 10f, 100f);
            }
            else if(40f < timer && timer < 50f)
            {
                font.draw(batch, "You can exit out of the dungeon anytime\nby pressing ESC key", 10f, 100f);
            }

        batch.end();
    }
}
