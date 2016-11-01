package com.mygdx.healinghtml.dungeons;

import com.mygdx.healinghtml.Constants;
import com.mygdx.healinghtml.GameData;
import com.mygdx.healinghtml.objects.HealthBar;

// Deals a sequence of attacks(one attack done once) to one random target until the defined end of the phase
// Target death DOES NOT end the phase
public class SequencedPhase extends Phase
{
    HealthBar target;
    int currentAttack = 0;

    public SequencedPhase(Attack[] attacks, float frequency, float frequencyDeviation)
    {
        super(attacks, frequency, frequencyDeviation, -1);
    }

    public void init() {
        int randomHealth = random.nextInt(GameData.healths.length);
        while (GameData.healths[randomHealth].dead)
            randomHealth = random.nextInt(GameData.healths.length);
        target = GameData.healths[randomHealth];
    }

    public void update()
    {
        // Run while there are attacks left
        if (currentAttack < attacks.length)
        {
            timer -= Constants.TIME_STEP;
            if (timer <= 0f)
            {
                timer = random.nextFloat() * 2f * frequencyDeviation + (frequency - frequencyDeviation);
                performAttack(target, attacks[currentAttack]);
                currentAttack++;
            }
        }
    }

    public void reset() {
        super.reset();
        currentAttack = 0;
    }

    public boolean ended(float percentage) { return (currentAttack>=(attacks.length) || target.dead); }
}
