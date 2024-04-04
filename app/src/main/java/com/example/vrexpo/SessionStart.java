package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class SessionStart extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presession_questions);

        //Find the buttons
        Button submitBtn = findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open Zoom
                Intent startSession = new Intent(SessionStart.this, Zoom.class);
                startActivity(startSession);
            }
        });
    }

}
