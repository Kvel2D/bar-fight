package com.mygdx.healinghtml.objects;

import com.badlogic.gdx.math.Rectangle;

public class HealthBar
{
    public Rectangle bounds;
    public float hp = 100f;
    public float[] spellTimers = new float[]{0f, 0f, 0f, 0f, 0f, 0f};
    public boolean dead = false;

    public HealthBar(float x, float y)
    {
        int width = 60;
        int height = 250;
        this.bounds = new Rectangle((int)(x - width / 10), (int)(y - height / 8), width + width / 5, height + height / 4);
    }
}