package com.example.recoin.view;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class RECoinApp extends Application {

    private static RECoinApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Stetho.initializeWithDefaults(this);
    }

    public static RECoinApp getInstance() {
        return instance;
    }
}
