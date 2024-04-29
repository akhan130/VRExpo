package com.example.vrexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientRecords extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_records);

        Button PresessionAnswersButton = findViewById(R.id.presession_answers);
        Button PostsessionAnswersButton = findViewById(R.id.postsession_answers);

        PresessionAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open history questions
                Intent presessionAnswersIntent = new Intent(PatientRecords.this, PresessionAnswers.class);
                startActivity(presessionAnswersIntent);
            }
        });
        PostsessionAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open history questions
                Intent postsessionAnswersIntent = new Intent(PatientRecords.this, PostsessionAnswers.class);
                startActivity(postsessionAnswersIntent);
            }
        });
    }
}