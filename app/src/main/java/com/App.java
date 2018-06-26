package com;

import android.app.Application;

import until.DBUntil;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DBUntil.init(this);
    }
}
