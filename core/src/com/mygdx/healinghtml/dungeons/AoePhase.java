package com.mygdx.healinghtml.dungeons;

import com.mygdx.healinghtml.Constants;
import com.mygdx.healinghtml.GameData;
import com.mygdx.healinghtml.objects.HealthBar;

public class AoePhase extends Phase
{
    public AoePhase(Attack[] attacks, float frequency, Float frequencyDeviation, int endPercentage)
    {
        super(attacks, frequency, frequencyDeviation, endPercentage);
    }

    public void update()
    {
        // Timer reduction and/or reset
        timer -= Constants.TIME_STEP;
        if (timer <= 0f)
        {
            timer = random.nextFloat() * 2f * frequencyDeviation + (frequency - frequencyDeviation);
            int randomAttack = random.nextInt(attacks.length);
            for (HealthBar healthBar : GameData.healths)
            {
                if (!healthBar.dead)
                    performAttack(healthBar, attacks[randomAttack]);
            }
        }
    }
}