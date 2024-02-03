package com.example.vrexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "VRExpo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the buttons
        ImageButton menuBtn = findViewById(R.id.menuButton);
        Button loginButton = findViewById(R.id.loginButton);
        //Set button behavior
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Menu button clicked");
                Toast.makeText(MainActivity.this, "Menu button clicked", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Login button clicked");
                Toast.makeText(MainActivity.this, "Login button clicked", Toast.LENGTH_SHORT)
                        .show();
                //Open dashboard
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
}