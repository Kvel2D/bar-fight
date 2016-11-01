package com.mygdx.healinghtml.dungeons;

import com.mygdx.healinghtml.Constants;
import com.mygdx.healinghtml.GameData;
import com.mygdx.healinghtml.objects.HealthBar;

public class Dungeon
{
    public float healthMax;
    public float health;
    public Phase[] phases;
    public int current = 0;
    public boolean complete;

    public Dungeon(float healthMax,
                   Phase[] phases)
    {
        this.healthMax = healthMax;
        this.health = healthMax;
        this.phases = phases;
        this.complete = false;
    }

    public void update()
    {
        // Reduce boss health
        for(HealthBar h : GameData.healths) {
            if (!h.dead)
                health -= Constants.TIME_STEP / 5f;
        }

        phases[current].update();
        if (phases[current].ended(health / healthMax))
        {
            current++;
            phases[current].init();
        }

        GameData.dungeonBar = health / healthMax;
    }

    public void reset() {
        health = healthMax;
        for (Phase p : phases)
        {
            p.reset();
        }
        current = 0;
    }
}

