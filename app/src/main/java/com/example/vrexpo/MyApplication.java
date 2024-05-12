package com.example.vrexpo;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        // Set the path to the service account key file
        String credentialsPath = "keys/vrexpo-421920-bb80706eb401.json";

        // Set the environment variable
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", credentialsPath);

        Log.d("Google Authentication Setup", "Initialization complete");
    }

    public static MyApplication getInstance() {
        return application;
    }
}
