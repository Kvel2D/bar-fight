package com.mygdx.healinghtml;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.healinghtml.dungeons.*;
import com.mygdx.healinghtml.objects.HealthBar;
import com.mygdx.healinghtml.objects.ManaBar;
import com.mygdx.healinghtml.objects.Spell;

public class GameData
{
    public static HealthBar health1 = new HealthBar(210f, 300f);
    public static HealthBar health2 = new HealthBar(410f, 300f);
    public static HealthBar health3 = new HealthBar(610f, 300f);
    public static HealthBar health4 = new HealthBar(810f, 300f);
    public static HealthBar health5 = new HealthBar(1010f, 300f);
    public static HealthBar[] healths = new HealthBar[]{health1, health2, health3, health4, health5};

    public static Spell spell0 = new Spell(415f, 150f, 0);
    public static Spell spell1 = new Spell(515f, 150f, 1);
    public static Spell spell2 = new Spell(615f, 150f, 2);
    public static Spell spell3 = new Spell(715f, 150f, 3);
    public static Spell spell4 = new Spell(815f, 150f, 4);
    public static Spell[] spells = new Spell[]{spell0, spell1, spell2, spell3, spell4};
    public static ManaBar manaBar = new ManaBar(490f, 50f);

    public static HealthBar target = null;
    public static float mana = Constants.MANA_MAX;
    public static float cooldown = 0f;

    public static Dungeon[] dungeons = new Dungeon[]{
        // Tutorial castle
        new Dungeon(50f, new Phase[]{
            new Phase(new Attack[]{new Attack(5f)}, 1f, 0.2f, 90),
            new Phase(new Attack[]{new Attack(5f), new Attack(10f)}, 1.5f, 0.2f, -1)
        }),
        // Easy
        new Dungeon(50f, new Phase[]{
            new Phase(new Attack[]{new Attack(0f)}, 1f, 0.2f, 95),

            new Phase(new Attack[]{new Attack(15f)}, 1f, 0.2f, 75),
            new Phase(new Attack[]{new Attack(15f), new Attack(25f)}, 1f, 0.2f, 50),
            new AoePhase(new Attack[]{new Attack(5f)}, 0.2f, 0.1f, 40),
            new Phase(new Attack[]{new Attack(15f)}, 1.5f, 0.2f, 15),
            new AoePhase(new Attack[]{new Attack(4f)}, 0.3f, 0.1f, -1)
        }),
        // Dungeon1
        new Dungeon(90f, new Phase[]{
            new Phase(new Attack[]{new Attack(0f)}, 1f, 0.2f, 95),

            new Phase(new Attack[]{new Attack(10f), new Attack(30f)}, 1f, 0.2f, 70),
            new FocusedPhase(new Attack[]{new Attack(20f), new Attack(30f)}, 1f, 0.2f, 50),
            new AoePhase(new Attack[]{new Attack(3f), new Attack(5f)}, 0.5f, 0.1f, 30),
            new Phase(new Attack[]{new Attack(5f), new Attack(8f)}, 2.5f, 0.5f, 20),
            new Phase(new Attack[]{new Attack(10f), new Attack(40f)}, 1.2f, 0.3f, -1)
        }),
        // Water temple
        new Dungeon(50f, new Phase[]{
            new Phase(new Attack[]{new Attack(0f)}, 1f, 0.2f, 95),

            new Phase(new Attack[]{new Attack(10f), new Attack(30f)}, 1f, 0.2f, 80),
            new AoePhase(new Attack[]{new Attack(1f)}, 0.3f, 0.2f, 77),
            new AoePhase(new Attack[]{new Attack(50f)}, 10f, 0.2f, 75),
            new Phase(new Attack[]{new Attack(10f), new Attack(15f)}, 1f, 0.3f, 60),
            new AoePhase(new Attack[]{new Attack(1f)}, 10f, 0.2f, 57),
            new AoePhase(new Attack[]{new Attack(60f)}, 10f, 0.2f, 55),
            new Phase(new Attack[]{new Attack(10f), new Attack(20f)}, 1.2f, 0.3f, 40),
            new AoePhase(new Attack[]{new Attack(1f)}, 10f, 0.2f, 37),
            new AoePhase(new Attack[]{new Attack(70f)}, 10f, 0.2f, 35),
            new Phase(new Attack[]{new Attack(20f), new Attack(25f)}, 1.2f, 0.3f, 15),
            new AoePhase(new Attack[]{new Attack(1f)}, 0.3f, 0.2f, 13),
            new AoePhase(new Attack[]{new Attack(70f)}, 10f, 0.2f, 10),
            new Phase(new Attack[]{new Attack(5f), new Attack(8f)}, 0.7f, 0.3f, -1)
        }),
        // Dragon's Lair
        new Dungeon(60f, new Phase[]{
            new Phase(new Attack[]{new Attack(0f)}, 1f, 0.2f, 95),

            new Phase(new Attack[]{new Attack(10f), new Attack(15f)}, 1f, 0.2f, 85),
            new SequencedPhase(new Attack[]{new Attack(2f), new Attack(5f), new Attack(10f), new Attack(20f), new Attack(35f), new Attack(45f), new Attack(70f), new Attack(110f)}, 1f, 0f),
            new AoePhase(new Attack[]{new Attack(20f)}, 0.2f, 0.05f, 74),
            new Phase(new Attack[]{new Attack(10f), new Attack(15f)}, 1f, 0.2f, 65),
            new SequencedPhase(new Attack[]{new Attack(2f), new Attack(5f), new Attack(10f), new Attack(20f), new Attack(35f), new Attack(45f), new Attack(70f), new Attack(110f)}, 1f, 0f),
            new AoePhase(new Attack[]{new Attack(20f)}, 0.2f, 0.05f, 54),
            new Phase(new Attack[]{new Attack(10f), new Attack(15f)}, 1f, 0.2f, 45),
            new SequencedPhase(new Attack[]{new Attack(2f), new Attack(5f), new Attack(10f), new Attack(20f), new Attack(35f), new Attack(45f), new Attack(70f), new Attack(110f)}, 1f, 0f),
            new AoePhase(new Attack[]{new Attack(20f)}, 0.2f, 0.05f, 34),
            new Phase(new Attack[]{new Attack(10f), new Attack(15f)}, 1f, 0.2f, 25),
            new SequencedPhase(new Attack[]{new Attack(2f), new Attack(5f), new Attack(10f), new Attack(20f), new Attack(35f), new Attack(45f), new Attack(70f), new Attack(110f)}, 1f, 0f),
            new AoePhase(new Attack[]{new Attack(20f)}, 0.2f, 0.05f, 14),
            new Phase(new Attack[]{new Attack(10f), new Attack(15f)}, 0.5f, 0.05f, -1)
        })
    };

    public static float dungeonBar = 1f;

    public static boolean tooltipsOn = true;
    public static Color backgroundColor = Color.GRAY;
}
