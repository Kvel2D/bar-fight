package com.mygdx.healinghtml;

import com.badlogic.gdx.graphics.Color;

public class Constants
{
    public static final int VIEWPORT_WIDTH = 1280;
    public static final int VIEWPORT_HEIGHT = 720;
    public static final double DEGTORAD = 0.0175;
    public static final float TIME_STEP = 0.016f;
    public static final float MANA_MAX = 100f;
    public static final float HEALTH_MAX = 100f;
    public static final float MANA_REGEN = 0.02f;

    public static Color BUTTON_INACTIVE_COLOR = Color.WHITE;
    public static Color BUTTON_ACTIVE_COLOR = Color.GREEN;
    public static Color HP_COLOR = new Color(0f, 153f / 255f, 0f, 1f);
    public static Color HP_BACKGROUND_COLOR = new Color(0f / 255f, 102f / 255f, 0f / 255f, 0.3f);
    public static Color FUTURE_HP_COLOR = new Color(0f, 153f / 255f, 0f, 0.2f);
    public static Color MP_BACKGROUND_COLOR = new Color(0f, 153f / 255f, 255f / 255f, 0.3f);
    public static Color MP_COLOR = new Color(0f, 153f / 255f, 255f / 255f, 1f);
    public static Color DUNGEON_BACKGROUND_COLOR = new Color(153f / 255f, 0f, 0f, 0.3f);
    public static Color DUNGEON_COLOR = new Color(153f / 255f, 0f, 0f, 1f);
    public static Color COOLDOWN_COLOR = new Color(0f, 0f, 0f, 0.3f);
    public static Color BUFF_DRAIN_COLOR = new Color(0f, 0f, 0f, 0.4f);

    public static float[] costs = new float[]{
        15f,
        4f,
        2f,
        20f,
        5f
    };
    public static float[] durations = new float[]{
        0.2f,
        0.15f,
        10f,
        0.5f,
        20f
    };
    public static float[] amounts = new float[]{
        75f,
        30f,
        30f,
        30f,
        0f
    };
    public static String[] tooltips = new String[]{
        "Heals 75% health\n15MP",
        "Heals 30% health\n4MP",
        "Heals 30% health over 10 seconds\n2MP",
        "Heals everybar for 30% health\n20MP",
        "Creates a magic shield around one bar\nthat absorbs 50% of all damage\n5MP"
    };
}
