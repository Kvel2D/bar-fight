package com.mygdx.healinghtml.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.healinghtml.Constants;
import com.mygdx.healinghtml.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Healing";
		config.width  = Constants.VIEWPORT_WIDTH;
		config.height = Constants.VIEWPORT_HEIGHT;
		config.fullscreen = false;
		config.resizable = false;
		config.samples = 4;

		new LwjglApplication(new Main(), config);
	}
}
