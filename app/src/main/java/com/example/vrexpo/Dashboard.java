package com.example.vrexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    private static final String TAG = "VRExpo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Find the buttons
        Button presessionBtn = findViewById(R.id.presessionButton);
        Button postsessionBtn = findViewById(R.id.postsessionButton);
        Button historyBtn = findViewById(R.id.historyButton);
        //Set the buttons behavior
        presessionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Presession button clicked");
                Toast.makeText(Dashboard.this, "Presession button clicked", Toast.LENGTH_SHORT)
                        .show();

                //Open presession questions
                Intent preIntent = new Intent(Dashboard.this, PresessionQuestions.class);
                startActivity(preIntent);
            }
        });
        postsessionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Postsession button clicked");
                Toast.makeText(Dashboard.this, "Postsession button clicked", Toast.LENGTH_SHORT)
                        .show();

                //open postsession questions
                Intent postIntent = new Intent(Dashboard.this, PostsessionQuestions.class);
                startActivity(postIntent);
            }
        });
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "History button clicked");
                Toast.makeText(Dashboard.this, "History button clicked", Toast.LENGTH_SHORT)
                        .show();

                //open history questions
                Intent histIntent = new Intent(Dashboard.this, History.class);
                startActivity(histIntent);
            }
        });
    }
}