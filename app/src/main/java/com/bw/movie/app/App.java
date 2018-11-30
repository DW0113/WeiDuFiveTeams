package com.bw.movie.app;

import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;

/*
 *作者：刘进
 *日期：2018/11/29
 **/public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
