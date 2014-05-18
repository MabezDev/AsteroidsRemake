package com.mabez.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mabez.game.Main;

public class DesktopLauncher {

    public static String DEVICE = "_desktop";

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height=600;
        config.width=800;
        new LwjglApplication(new Main(DEVICE), config);
    }
}
