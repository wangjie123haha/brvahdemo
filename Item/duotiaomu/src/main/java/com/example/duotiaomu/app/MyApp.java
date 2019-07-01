package com.example.duotiaomu.app;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jess.arms.base.BaseApplication;

public class MyApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
