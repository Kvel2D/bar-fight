package com.mygdx.healinghtml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.healinghtml.dungeons.Dungeon;
import com.mygdx.healinghtml.objects.HealthBar;
import com.mygdx.healinghtml.systems.BarUpdater;
import com.mygdx.healinghtml.systems.MyInputProcessor;
import com.mygdx.healinghtml.systems.Renderer;

public class GameScreen extends ScreenAdapter
{
    Renderer renderer = new Renderer();
    BarUpdater barUpdater = new BarUpdater();
    Dungeon dungeon;

    public GameScreen(int dungeonNumber)
    {
        dungeon = GameData.dungeons[dungeonNumber];


        Gdx.input.setInputProcessor(new MyInputProcessor());
        switch (dungeonNumber)
        {
            case 0:
            {
                GameData.backgroundColor = Color.GRAY;
                break;
            }
            case 1:
            {
                GameData.backgroundColor = new Color(68f / 256f, 102f / 256f, 0f, 1f);
                break;
            }
            case 2:
            {
                GameData.backgroundColor = Color.DARK_GRAY;
                break;
            }
            case 3:
            {
                GameData.backgroundColor = Color.ROYAL;
                break;
            }
            case 4:
            {
                GameData.backgroundColor = new Color(77f / 256f, 0f, 0f, 1f);
                break;
            }
        }
    }

    public void render(float deltaTime)
    {
        if (GameData.cooldown > 0f)
            GameData.cooldown -= Constants.TIME_STEP;

        dungeon.update();
        barUpdater.update();
        renderer.update();

        if (dungeon.health <= 0f)
        {
            dungeon.complete = true;
            reset();
            Main.game.setScreen(new EndScreen("win"));
        }
        boolean everyoneDead = true;
        for (HealthBar it : GameData.healths)
        {
            if (!it.dead)
                everyoneDead = false;
        }
        if (everyoneDead)
        {
            reset();
            Main.game.setScreen(new EndScreen("fail"));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            reset();
            Main.game.setScreen(Main.selectScreen);
        }
    }

    private void reset()
    {
        GameData.cooldown = 0f;
        for (HealthBar it : GameData.healths)
        {
            it.hp = 100f;
            it.spellTimers = new float[]{0f, 0f, 0f, 0f, 0f, 0f};
            it.dead = false;
        }
        dungeon.reset();
        GameData.mana = Constants.MANA_MAX;
        GameData.target = null;
        Gdx.input.setInputProcessor(null);
    }
}