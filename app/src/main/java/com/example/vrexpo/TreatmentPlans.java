package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TreatmentPlans extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_zoom:
                Intent zoom = new Intent(TreatmentPlans.this, Zoom.class);
                startActivity(zoom);
                return true;
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(TreatmentPlans.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_pre:
                Intent preIntent = new Intent(TreatmentPlans.this, PresessionQuestions.class);
                startActivity(preIntent);
                return true;
            case R.id.action_post:
                Intent postIntent = new Intent(TreatmentPlans.this, PostsessionQuestions.class);
                startActivity(postIntent);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(TreatmentPlans.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(TreatmentPlans.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_plans);

        // Treatment 1 - Social Anxiety
        Button SocialAnxiety = findViewById(R.id.SocialAnxiety);
        SocialAnxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SocialAnxiety = new Intent(TreatmentPlans.this, com.example.vrexpo.treatments.SocialAnxiety.class);
                startActivity(SocialAnxiety);
            }
        });

        // Treatment 2 - Depression
        Button Depression = findViewById(R.id.Depression);
        Depression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Depression = new Intent(TreatmentPlans.this, com.example.vrexpo.treatments.Depression.class);
                startActivity(Depression);
            }
        });

        // Treatment 3 - Arachnophobia
        Button Arachnophobia = findViewById(R.id.Arachnophobia);
        Arachnophobia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Arachnophobia = new Intent(TreatmentPlans.this, com.example.vrexpo.treatments.Arachnophobia.class);
                startActivity(Arachnophobia);
            }
        });

        // Treatment 4 - Aviophobia
        Button Aviophobia = findViewById(R.id.Aviophobia);
        Aviophobia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Aviophobia = new Intent(TreatmentPlans.this, com.example.vrexpo.treatments.Aviophobia.class);
                startActivity(Aviophobia);
            }
        });

        // Treatment 5 - Acrophobia
        Button Acrophobia = findViewById(R.id.Acrophobia);
        Acrophobia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Acrophobia = new Intent(TreatmentPlans.this, com.example.vrexpo.treatments.Acrophobia.class);
                startActivity(Acrophobia);
            }
        });

        // Treatment 6 - Claustrophobia
        Button Claustrophobia = findViewById(R.id.Claustrophobia);
        Claustrophobia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Claustrophobia = new Intent(TreatmentPlans.this, com.example.vrexpo.treatments.Claustrophobia.class);
                startActivity(Claustrophobia);
            }
        });

        // Treatment 7 - PTSD_CarAccident
        Button PTSD_CarAccident = findViewById(R.id.PTSD_CarAccident);
        PTSD_CarAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PTSD_CarAccident = new Intent(TreatmentPlans.this, com.example.vrexpo.treatments.PTSD_CarAccident.class);
                startActivity(PTSD_CarAccident);
            }
        });

        // Treatment 8 - Glossophobia
        Button Glossophobia = findViewById(R.id.Glossophobia);
        Glossophobia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Glossophobia = new Intent(TreatmentPlans.this, com.example.vrexpo.treatments.Glossophobia.class);
                startActivity(Glossophobia);
            }
        });


        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}