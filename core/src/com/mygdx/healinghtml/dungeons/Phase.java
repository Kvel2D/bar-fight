package com.mygdx.healinghtml.dungeons;

import com.mygdx.healinghtml.Constants;
import com.mygdx.healinghtml.GameData;
import com.mygdx.healinghtml.objects.HealthBar;

import java.util.Random;

public class Phase
{
    Attack[] attacks;
    float frequency;
    float frequencyDeviation;
    int endPercentage;
    Random random = new Random();
    public float timer = 0f;

    public Phase(Attack[] attacks,
                 float frequency,
                 float frequencyDeviation,
                 int endPercentage)
    {
        this.attacks = attacks;
        this.frequency = frequency;
        this.frequencyDeviation = frequencyDeviation;
        this.endPercentage = endPercentage;
    }

    public void init() {

    }


    public void update()
    {
        // Timer reduction and/or reset
        timer -= Constants.TIME_STEP;
        if (timer <= 0f)
        {
            timer = random.nextFloat() * 2f * frequencyDeviation + (frequency - frequencyDeviation);
            int randomHealth = random.nextInt(GameData.healths.length);
            while (GameData.healths[randomHealth].dead)
                randomHealth = random.nextInt(GameData.healths.length);
            int randomAttack = random.nextInt(attacks.length);
            performAttack(GameData.healths[randomHealth], attacks[randomAttack]);
        }
    }

    public void performAttack(HealthBar target, Attack attack)
    {
        float damage = attack.damage;
        // shield 50% absorption
        if (target.spellTimers[4] > 0f)
        {
            target.hp -= damage / 2f;
        } else
        {
            target.hp -= damage;
        }
    }

    public void reset() {
        timer = 0f;
    }

    public boolean ended(float percentage) {
        return percentage < (endPercentage / 100f);
    }
}
