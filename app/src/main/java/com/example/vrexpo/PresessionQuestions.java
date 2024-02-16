package com.example.vrexpo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PresessionQuestions extends AppCompatActivity {

    private static final String TAG = "VRExpo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presession_questions);

        //Setting up the action bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Find the buttons
        Button submitBtn = findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Submit button clicked");
                Toast.makeText(PresessionQuestions.this, "Submit button clicked", Toast.LENGTH_SHORT)
                        .show();

                //open postsession questions
                Intent sessionIntent = new Intent(PresessionQuestions.this, Session.class);
                startActivity(sessionIntent);
            }
        });
    }
}