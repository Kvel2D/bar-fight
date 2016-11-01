package com.mygdx.healinghtml.dungeons;

import com.mygdx.healinghtml.Constants;
import com.mygdx.healinghtml.GameData;
import com.mygdx.healinghtml.objects.HealthBar;

public class FocusedPhase extends Phase
{
    HealthBar target;

    public FocusedPhase(Attack[] attacks, float frequency, float frequencyDeviation, int endPercentage)
    {
        super(attacks, frequency, frequencyDeviation, endPercentage);
    }

    public void init() {
        int randomHealth = random.nextInt(GameData.healths.length);
        while (GameData.healths[randomHealth].dead)
            randomHealth = random.nextInt(GameData.healths.length);
        target = GameData.healths[randomHealth];
    }

    public void update()
    {
        // Timer reduction and/or reset
        timer -= Constants.TIME_STEP;
        if (timer <= 0f)
        {
            timer = random.nextFloat() * 2f * frequencyDeviation + (frequency - frequencyDeviation);
            int randomAttack = random.nextInt(attacks.length);
            performAttack(target, attacks[randomAttack]);
        }
    }

    public void reset() {

    }

    public boolean ended(float percentage)
    {
        return (percentage < (endPercentage / 100f) || target.dead);
    }
}