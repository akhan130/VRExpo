package com.example.vrexpo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    private static final String TAG = "VRExpo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Setting up links
        TextView test = findViewById(R.id.testLink);
        test.setMovementMethod(LinkMovementMethod.getInstance());
        test.setLinkTextColor(Color.BLUE);

        //Setting up the action bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Find the buttons
        Button presessionBtn = findViewById(R.id.presessionButton);
        Button postsessionBtn = findViewById(R.id.postsessionButton);
        Button historyBtn = findViewById(R.id.historyButton);
        Button scheduleBtn = findViewById(R.id.scheduleButton);
        Button findTherapistBtn = findViewById(R.id.findTherapistButton);

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
        scheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Schedule button clicked");
                Toast.makeText(Dashboard.this, "Schedule button clicked", Toast.LENGTH_SHORT)
                        .show();

                //open schedule
                Intent scheduleIntent = new Intent(Dashboard.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
            }
        });

        findTherapistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Find Therapist button clicked");
                Toast.makeText(Dashboard.this, "Find Therapist button clicked", Toast.LENGTH_SHORT)
                        .show();

                //open Find Therapist
                Intent findIntent = new Intent(Dashboard.this, FindTherapist.class);
                startActivity(findIntent);

            }
        });

    }
}