package com.example.vrexpo;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getInstance() {
        return application;
    }
}