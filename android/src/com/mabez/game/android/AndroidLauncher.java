package com.mabez.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mabez.game.Main;

public class AndroidLauncher extends AndroidApplication {

    public static String DEVICE = "_android";
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useGLSurfaceView20API18 = true;
        config.useAccelerometer =true;
        config.useCompass = true;
        config.resolutionStrategy.calcMeasures(800,600);
        initialize(new Main(DEVICE), config);
    }
}
