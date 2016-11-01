package com.mygdx.healinghtml.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.healinghtml.AssetPaths;
import com.mygdx.healinghtml.Constants;
import com.mygdx.healinghtml.Main;

public class Spell {
    public float x;
    public float y;
    public Texture texture;
    public Rectangle bounds;
    public float cost;
    public float duration;
    public float amount;
    public String tooltip;

    public Spell(float x, float y, int number) {
        this.x = x;
        this.y = y;
        switch (number) {
            case 0: {
                this.texture = Main.assets.get(AssetPaths.SPELL0);
                break;
            }
            case 1: {
                this.texture = Main.assets.get(AssetPaths.SPELL1);
                break;
            }
            case 2: {
                this.texture = Main.assets.get(AssetPaths.SPELL2);
                break;
            }
            case 3: {
                this.texture = Main.assets.get(AssetPaths.SPELL3);
                break;
            }
            case 4: {
                this.texture = Main.assets.get(AssetPaths.SPELL4);
                break;
            }
            default: {
                this.texture = Main.assets.get(AssetPaths.BADLOGIC);
                break;
            }
        }
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        this.cost = Constants.costs[number];
        this.duration = Constants.durations[number];
        this.amount = Constants.amounts[number];
        this.tooltip = Constants.tooltips[number];
    }
}