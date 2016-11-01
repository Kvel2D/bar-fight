package com.mygdx.healinghtml.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.healinghtml.Constants;
import com.mygdx.healinghtml.Main;

public class Button
{
    public String buttonText;
    public Rectangle bounds;
    public int align;

    public Button(String buttonText, float x, float y, GlyphLayout layout, BitmapFont font)
    {
        this.buttonText = buttonText;
        layout.setText(font, buttonText);
        bounds = new Rectangle(x, y, layout.width, layout.height);
        align = 0;
    }

    /*
     * 0 = te|xt
     * -1 = text|
     * 1 = |text
     */
    public Button(String buttonText, float x, float y, int align, GlyphLayout layout, BitmapFont font)
    {
        this.buttonText = buttonText;
        layout.setText(font, buttonText);
        switch (align)
        {
            case 0:
            {
                bounds = new Rectangle(x - layout.width / 2, y, layout.width, layout.height);
                break;
            }
            case 1:
            {
                bounds = new Rectangle(x, y, layout.width, layout.height);
                break;
            }
            case -1:
            {
                bounds = new Rectangle(x - layout.width, y, layout.width, layout.height);
                break;
            }
            default:
            {
                bounds = new Rectangle(x, y, layout.width, layout.height);
                break;
            }
        }
        this.align = align;
    }

    public void draw(Vector3 touch, BitmapFont font)
    {
        if (bounds.contains(touch.x, touch.y))
            font.setColor(Constants.BUTTON_ACTIVE_COLOR);
        else
            font.setColor(Constants.BUTTON_INACTIVE_COLOR);
        font.draw(Main.spriteBatch, buttonText, bounds.x, bounds.y + bounds.height);
    }

    public void draw(Vector3 touch, BitmapFont font, Color overrideColor)
    {
        if (bounds.contains(touch.x, touch.y))
            font.setColor(Constants.BUTTON_ACTIVE_COLOR);
        else
            font.setColor(overrideColor);
        font.draw(Main.spriteBatch, buttonText, bounds.x, bounds.y + bounds.height);
    }

    public void draw(float xOffset, float yOffset, Vector3 touch, BitmapFont font)
    {
        if (bounds.contains(touch.x, touch.y))
            font.setColor(Constants.BUTTON_ACTIVE_COLOR);
        else
            font.setColor(Constants.BUTTON_INACTIVE_COLOR);
        font.draw(Main.spriteBatch, buttonText, bounds.x + xOffset, bounds.y + bounds.height + yOffset);
    }

    public void setPosition(float x, float y)
    {
        switch (align)
        {
            case 0:
            {
                bounds.setPosition(x - bounds.width / 2, y);
                break;
            }
            case 1:
            {
                bounds.setPosition(x, y);
                break;
            }
            case -1:
            {
                bounds.setPosition(x - bounds.width, y);
                break;
            }
            default:
            {
                bounds.setPosition(x, y);
                break;
            }
        }
    }
}
