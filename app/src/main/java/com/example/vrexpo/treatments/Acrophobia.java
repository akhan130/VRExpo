package com.example.vrexpo.treatments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vrexpo.Dashboard;
import com.example.vrexpo.FindTherapist;
import com.example.vrexpo.PostsessionQuestions;
import com.example.vrexpo.PresessionQuestions;
import com.example.vrexpo.R;
import com.example.vrexpo.TherapySchedulerActivity;
import com.example.vrexpo.TreatmentPlans;
import com.example.vrexpo.Zoom;

public class Acrophobia extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Acrophobia.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Acrophobia.this, Zoom.class);
                startActivity(zoom);
                return true;
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(Acrophobia.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_pre:
                Intent preIntent = new Intent(Acrophobia.this, PresessionQuestions.class);
                startActivity(preIntent);
                return true;
            case R.id.action_post:
                Intent postIntent = new Intent(Acrophobia.this, PostsessionQuestions.class);
                startActivity(postIntent);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(Acrophobia.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(Acrophobia.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acrophobia);

        // Session 1 - Acrophobia
        Button Session1_ACRO = findViewById(R.id.Session1_ACRO);
        Session1_ACRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1_ACRO = new Intent(Acrophobia.this, com.example.vrexpo.treatments.acrophobiaPlan.Session1_ACRO.class);
                startActivity(Session1_ACRO);
            }
        });

        // Session 2,9 - Acrophobia
        Button Session29_ACRO = findViewById(R.id.Session29_ACRO);
        Session29_ACRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session29_ACRO = new Intent(Acrophobia.this, com.example.vrexpo.treatments.acrophobiaPlan.Session29_ACRO.class);
                startActivity(Session29_ACRO);
            }
        });

        // Session 10,11 - Acrophobia
        Button Session1011_ACRO = findViewById(R.id.Session1011_ACRO);
        Session1011_ACRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1011_ACRO = new Intent(Acrophobia.this, com.example.vrexpo.treatments.acrophobiaPlan.Session1011_ACRO.class);
                startActivity(Session1011_ACRO);
            }
        });

        // Session 12 - Acrophobia
        Button Session12_ACRO = findViewById(R.id.Session12_ACRO);
        Session12_ACRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session12_ACRO = new Intent(Acrophobia.this, com.example.vrexpo.treatments.acrophobiaPlan.Session12_ACRO.class);
                startActivity(Session12_ACRO);
            }
        });

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}



