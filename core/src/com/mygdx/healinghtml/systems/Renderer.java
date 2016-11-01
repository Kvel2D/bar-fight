package com.mygdx.healinghtml.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.healinghtml.AssetPaths;
import com.mygdx.healinghtml.Constants;
import com.mygdx.healinghtml.GameData;
import com.mygdx.healinghtml.Main;
import com.mygdx.healinghtml.objects.HealthBar;
import com.mygdx.healinghtml.objects.Spell;

public class Renderer
{
    SpriteBatch batch = Main.spriteBatch;
    BitmapFont font = Main.assets.get(AssetPaths.FONT);
    GlyphLayout layout = new GlyphLayout();
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    OrthographicCamera camera;
    Texture spell2Small = Main.assets.get(AssetPaths.SPELL2_SMALL);
    Texture spell4Small = Main.assets.get(AssetPaths.SPELL4_SMALL);

    public Renderer()
    {
        float camWidth = Gdx.graphics.getWidth();
        float camHeight = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(camWidth, camHeight);
        camera.position.set(camWidth / 2, camHeight / 2, 0f);
        camera.update();
    }

    public void update()
    {
        Gdx.gl.glClearColor(GameData.backgroundColor.r, GameData.backgroundColor.g, GameData.backgroundColor.b, 1f);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.begin();
        // Draw spells
        for (Spell spell : GameData.spells)
        {
            batch.draw(spell.texture, spell.bounds.x, spell.bounds.y);
        }
        // Draw tooltips
        if (GameData.tooltipsOn)
        {
            font.setColor(Color.WHITE);
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f);
            camera.unproject(touch);
            for (Spell spell : GameData.spells)
            {
                if (spell.bounds.contains(touch.x, touch.y))
                {
                    layout.setText(font, spell.tooltip);
                    font.draw(batch, spell.tooltip, 1050f - layout.width / 2, 100f);
                }
            }
        }
        // Buffs
        for (HealthBar bar : GameData.healths)
        {
            if (bar.spellTimers[2] > 0f) // HoT
                batch.draw(spell2Small, bar.bounds.x + 5f, bar.bounds.y - 40f);
            if (bar.spellTimers[4] > 0f) // shield
                batch.draw(spell4Small, bar.bounds.x + 40f, bar.bounds.y - 40f);
        }
        batch.end();

        // Filled shapes
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        float frameSpacing = 2f;
        Gdx.gl.glEnable(GL20.GL_BLEND);
        // Healthbar background
        shapeRenderer.setColor(Constants.HP_BACKGROUND_COLOR);
        for (HealthBar bar : GameData.healths)
        {
            float x = bar.bounds.x + frameSpacing;
            float y = bar.bounds.y + frameSpacing;
            float width = bar.bounds.width - frameSpacing * 2;
            float height = bar.bounds.height - frameSpacing * 2;
            shapeRenderer.rect(x, y, width, height);
        }
        // Future healthbar
        shapeRenderer.setColor(Constants.FUTURE_HP_COLOR);
        for (HealthBar bar : GameData.healths)
        {
            float futureHp = bar.hp;
            int i = 0;
            while (i < bar.spellTimers.length)
            {
                if (bar.spellTimers[i] > 0f)
                    futureHp += Constants.amounts[i] * bar.spellTimers[i] / Constants.durations[i];
                i++;
            }
            if (futureHp > 100f) futureHp = 100f;

            float x = bar.bounds.x + frameSpacing;
            float y = bar.bounds.y + frameSpacing;
            float width = bar.bounds.width - frameSpacing * 2;
            float height = (bar.bounds.height - frameSpacing * 2) * (futureHp / Constants.HEALTH_MAX);
            shapeRenderer.rect(x, y, width, height);
        }
        // Healthbar filling
        shapeRenderer.setColor(Constants.HP_COLOR);
        for (HealthBar bar : GameData.healths)
        {
            float x = bar.bounds.x + frameSpacing;
            float y = bar.bounds.y + frameSpacing;
            float width = bar.bounds.width - frameSpacing * 2;
            float height = (bar.bounds.height - frameSpacing * 2) * (bar.hp / Constants.HEALTH_MAX);
            shapeRenderer.rect(x, y, width, height);
        }
        // Manabar filling
        shapeRenderer.setColor(Constants.MP_BACKGROUND_COLOR);
        float x = GameData.manaBar.bounds.x + frameSpacing;
        float y = GameData.manaBar.bounds.y + frameSpacing;
        float width = GameData.manaBar.bounds.width - frameSpacing * 2;
        float height = GameData.manaBar.bounds.height - frameSpacing * 2;
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.setColor(Constants.MP_COLOR);
        width *= (GameData.mana / Constants.MANA_MAX);
        shapeRenderer.rect(x, y, width, height);
        // DungeonBar filling
        shapeRenderer.setColor(Constants.DUNGEON_BACKGROUND_COLOR);
        x = 340f + frameSpacing;
        y = 650f + frameSpacing;
        width = 600f - frameSpacing * 2;
        height = 40f - frameSpacing * 2;
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.setColor(Constants.DUNGEON_COLOR);
        width *= GameData.dungeonBar;
        shapeRenderer.rect(x, y, width, height);
        // Cooldowns
        shapeRenderer.setColor(Constants.COOLDOWN_COLOR);
        if (GameData.cooldown > 0f)
        {
            for (Spell spell : GameData.spells)
            {
                shapeRenderer.rect(spell.x, spell.y, spell.bounds.width, spell.bounds.height * GameData.cooldown);
            }
        }
        // Buff cooldowns
        shapeRenderer.setColor(Constants.BUFF_DRAIN_COLOR);
        for (HealthBar bar : GameData.healths)
        {
            if (bar.spellTimers[2] > 0f) // HoT
                shapeRenderer.rect(bar.bounds.x + 5f, bar.bounds.y - 40f, 25f * (1f - bar.spellTimers[2] / GameData.spell2.duration), 25f);
            if (bar.spellTimers[4] > 0f) // shield
                shapeRenderer.rect(bar.bounds.x + 40f, bar.bounds.y - 40f, 25f * (1f - bar.spellTimers[4] / GameData.spell4.duration), 25f);
        }
        shapeRenderer.end();

        // Line shapes
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        // Healthbar frames
        shapeRenderer.setColor(Constants.HP_COLOR);
        for (HealthBar bar : GameData.healths)
        {
            shapeRenderer.rect(bar.bounds.x, bar.bounds.y, bar.bounds.width, bar.bounds.height);
        }
        // Target frame
        HealthBar target = GameData.target;
        if (target != null)
        {
            shapeRenderer.setColor(Color.GOLD);
            shapeRenderer.rect(target.bounds.x, target.bounds.y, target.bounds.width, target.bounds.height);
            shapeRenderer.rect(target.bounds.x - 1, target.bounds.y - 1, target.bounds.width + 2, target.bounds.height + 2);
        }
        // Manabar frame
        shapeRenderer.setColor(Constants.MP_COLOR);
        shapeRenderer.rect(GameData.manaBar.bounds.x, GameData.manaBar.bounds.y, GameData.manaBar.bounds.width, GameData.manaBar.bounds.height);
        // Dungeon frame
        shapeRenderer.setColor(Constants.DUNGEON_COLOR);
        x = 340f;
        y = 650f;
        width = 600f;
        height = 40f;
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }
}