package com.mygdx.healinghtml.objects;


import com.badlogic.gdx.math.Rectangle;

public class ManaBar {
    public float percentFilled = 1f;
    public Rectangle bounds;

    public ManaBar(float x, float y) {
        int width = 300;
        int height = 40;

        this.bounds = new Rectangle((int)(x - width / 10), (int)(y - height / 8), width + width / 5, height + height / 4);
    }
}
