package com.mygdx.healinghtml.systems;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.healinghtml.GameData;
import com.mygdx.healinghtml.objects.HealthBar;

public class MyInputProcessor extends InputAdapter
{
    public boolean keyDown(int keycode)
    {
        HealthBar target = GameData.target;

        if (GameData.cooldown <= 0f)
        {
            if (target != null && !target.dead)
            {
                switch (keycode)
                {
                    case Input.Keys.NUM_1:
                    {
                        // BIG
                        if (GameData.mana > GameData.spell0.cost)
                        {
                            target.spellTimers[0] += GameData.spell0.duration;
                            GameData.mana -= GameData.spell0.cost;
                            GameData.cooldown = 1f;
                        }
                        break;
                    }
                    case Input.Keys.NUM_2:
                    {
                        // SMALL
                        if (GameData.mana > GameData.spell1.cost)
                        {
                            target.spellTimers[1] += GameData.spell1.duration;
                            GameData.mana -= GameData.spell1.cost;
                            GameData.cooldown = 1f;
                        }
                        break;
                    }
                    case Input.Keys.NUM_3:
                    {
                        // HoT
                        if (GameData.mana > GameData.spell2.cost)
                        {
                            target.spellTimers[2] = GameData.spell2.duration;
                            GameData.mana -= GameData.spell2.cost;
                            GameData.cooldown = 1f;
                        }
                        break;
                    }
                    case Input.Keys.NUM_5:
                    {
                        // SHIELD
                        if (GameData.mana > GameData.spell4.cost)
                        {
                            for (HealthBar it : GameData.healths)
                            {
                                it.spellTimers[4] = 0f;
                            }
                            target.spellTimers[4] = GameData.spell4.duration;
                            GameData.mana -= GameData.spell4.cost;
                            GameData.cooldown = 1f;
                        }
                        break;
                    }
                }
            }

            // AOE
            if (GameData.cooldown <= 0f && keycode == Input.Keys.NUM_4 && GameData.mana > GameData.spell3.cost)
            {
                for (HealthBar it : GameData.healths)
                {
                    it.spellTimers[3] += GameData.spell3.duration;
                    if (it.dead) it.spellTimers[3] = 0f;
                }
                GameData.mana -= GameData.spell3.cost;
                GameData.cooldown = 1f;
            }
        }

        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Vector2 touch = new Vector2(screenX, 720f - screenY);

        for (HealthBar it : GameData.healths)
        {
            if (it.bounds.contains(touch.x, touch.y)) GameData.target = it;
        }

        if (GameData.cooldown <= 0f)
        {
            if (GameData.target != null && !GameData.target.dead)
            {
                HealthBar target = GameData.target;

                if (GameData.spell0.bounds.contains(touch) && GameData.mana > GameData.spell0.cost)
                {
                    // BIG
                    target.spellTimers[0] += GameData.spell0.duration;
                    GameData.mana -= GameData.spell0.cost;
                    GameData.cooldown = 1f;
                } else if (GameData.spell1.bounds.contains(touch) && GameData.mana > GameData.spell1.cost)
                {
                    // SMALL
                    target.spellTimers[1] += GameData.spell1.duration;
                    GameData.mana -= GameData.spell1.cost;
                    GameData.cooldown = 1f;
                } else if (GameData.spell2.bounds.contains(touch) && GameData.mana > GameData.spell2.cost)
                {
                    // HoT
                    target.spellTimers[2] = GameData.spell2.duration;
                    GameData.mana -= GameData.spell2.cost;
                    GameData.cooldown = 1f;
                } else if (GameData.spell4.bounds.contains(touch) && GameData.mana > GameData.spell4.cost)
                {
                    // SHIELD
                    for (HealthBar it : GameData.healths)
                        it.spellTimers[4] = 0f;
                    target.spellTimers[4] = GameData.spell4.duration;
                    GameData.mana -= GameData.spell4.cost;
                    GameData.cooldown = 1f;
                }
            }
        }

        if (GameData.spell3.bounds.contains(touch) && GameData.mana > GameData.spell3.cost)
        {
            // AOE
            for (HealthBar it : GameData.healths)
            {
                it.spellTimers[3] += GameData.spell3.duration;
                if (it.dead) it.spellTimers[3] = 0f;
            }
            GameData.mana -= GameData.spell3.cost;
            GameData.cooldown = 1f;
        }

        return false;
    }
}