package com.mygdx.healinghtml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.healinghtml.objects.Button;

public class SelectScreen extends ScreenAdapter
{
    SpriteBatch batch = Main.spriteBatch;
    BitmapFont font = Main.assets.get(AssetPaths.FONT);
    BitmapFont fontBig = Main.assets.get(AssetPaths.FONT_BIG);
    GlyphLayout layout = new GlyphLayout();
    OrthographicCamera camera;
    Button tooltipToggle = new Button("Tooltips", 1180f, 20f, 0, layout, font);
    Button tutorial = new Button("Tutorial Castle", 640f, 500f, 0, layout, font);
    Button dungeon1 = new Button("Swamp", 640f, 450f, 0, layout, font);
    Button dungeon2 = new Button("Catacombs", 640f, 400f, 0, layout, font);
    Button dungeon3 = new Button("Water Temple", 640f, 350f, 0, layout, font);
    Button dungeon4 = new Button("Dragon's Lair", 640f, 300f, 0, layout, font);
    Button[] buttons = new Button[]{
        tutorial,
        dungeon1,
        dungeon2,
        dungeon3,
        dungeon4
    };

    public SelectScreen() {
        float camWidth = Gdx.graphics.getWidth();
        float camHeight = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(camWidth, camHeight);
        camera.position.set(camWidth / 2, camHeight / 2, 0f);
        camera.update();
    }

    public void render(float deltaTime) {
        draw();
        update();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        fontBig.setColor(Color.WHITE);
        layout.setText(fontBig, "Select a dungeon");
        fontBig.draw(batch, "Select a dungeon", 640f - layout.width / 2, 650f);

        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f);
        camera.unproject(touch);

        tooltipToggle.draw(touch, font);
        if (GameData.tooltipsOn) {
            font.draw(batch, "ON", 1230f, 35f);
        } else {
            font.draw(batch, "OFF", 1230f, 35f);
        }
        int i = 0;
        while (i < buttons.length) {
            if (GameData.dungeons[i].complete)
                buttons[i].draw(touch, font, Color.GRAY);
            else buttons[i].draw(touch, font);
            i++;
        }

        batch.end();
    }

    private void update() {
        if (Gdx.input.isTouched()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f);
            camera.unproject(touch);

            if (tutorial.bounds.contains(touch.x, touch.y)) {
                Main.game.setScreen(new TutorialScreen());
            } else if (dungeon1.bounds.contains(touch.x, touch.y)) {
                Main.game.setScreen(new GameScreen(1));
            } else if (dungeon2.bounds.contains(touch.x, touch.y)) {
                Main.game.setScreen(new GameScreen(2));
            } else if (dungeon3.bounds.contains(touch.x, touch.y)) {
                Main.game.setScreen(new GameScreen(3));
            } else if (dungeon4.bounds.contains(touch.x, touch.y)) {
                Main.game.setScreen(new GameScreen(4));
            }
        }
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f);
            camera.unproject(touch);

            if (tooltipToggle.bounds.contains(touch.x, touch.y)) {
                GameData.tooltipsOn = !GameData.tooltipsOn;
            }
        }
    }


}
