package com.mygdx.healinghtml.systems;

import com.mygdx.healinghtml.Constants;
import com.mygdx.healinghtml.GameData;
import com.mygdx.healinghtml.objects.HealthBar;

public class BarUpdater
{

    public BarUpdater()
    {
    }

    public void update()
    {
        for (HealthBar it : GameData.healths) {
        int i = 0;
        if (it.hp <= 0f)
        {
            it.dead = true;
            it.hp = 0f;
            while (i < it.spellTimers.length)
            {
                it.spellTimers[i] = 0f;
                i++;
            }
        }

        if (!it.dead)
        {
            while (i < it.spellTimers.length)
            {
                if (it.spellTimers[i] > 0f)
                {
                    it.hp += Constants.amounts[i] * Constants.TIME_STEP / Constants.durations[i];
                    if (it.hp > Constants.HEALTH_MAX) it.hp = Constants.HEALTH_MAX;
                    it.spellTimers[i] -= Constants.TIME_STEP;
                }
                i++;
            }
        }
    }

        if (GameData.mana < Constants.MANA_MAX)
            GameData.mana += Constants.MANA_REGEN;
    }
}