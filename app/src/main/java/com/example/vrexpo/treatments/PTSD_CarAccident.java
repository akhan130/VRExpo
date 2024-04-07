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

public class PTSD_CarAccident extends AppCompatActivity {
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
                Intent treatmentPlans = new Intent(PTSD_CarAccident.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(PTSD_CarAccident.this, Zoom.class);
                startActivity(zoom);
                return true;
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PTSD_CarAccident.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_pre:
                Intent preIntent = new Intent(PTSD_CarAccident.this, PresessionQuestions.class);
                startActivity(preIntent);
                return true;
            case R.id.action_post:
                Intent postIntent = new Intent(PTSD_CarAccident.this, PostsessionQuestions.class);
                startActivity(postIntent);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(PTSD_CarAccident.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PTSD_CarAccident.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptsd_car_accident);

        // Session 1 - PTSD Car Accident
        Button Session1_PTSD = findViewById(R.id.Session1_PTSD);
        Session1_PTSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1_PTSD = new Intent(PTSD_CarAccident.this, com.example.vrexpo.treatments.PTSD_CarAccidentPlan.Session1_PTSD.class);
                startActivity(Session1_PTSD);
            }
        });

        // Session 2,6 - PTSD Car Accident
        Button Session26_PTSD = findViewById(R.id.Session26_PTSD);
        Session26_PTSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session26_PTSD = new Intent(PTSD_CarAccident.this, com.example.vrexpo.treatments.PTSD_CarAccidentPlan.Session26_PTSD.class);
                startActivity(Session26_PTSD);
            }
        });

        // Session 7,9 - PTSD Car Accident
        Button Session79_PTSD = findViewById(R.id.Session79_PTSD);
        Session79_PTSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session79_PTSD = new Intent(PTSD_CarAccident.this, com.example.vrexpo.treatments.PTSD_CarAccidentPlan.Session79_PTSD.class);
                startActivity(Session79_PTSD);
            }
        });

        // Session 10,11 - PTSD Car Accident
        Button Session1011_PTSD = findViewById(R.id.Session1011_PTSD);
        Session1011_PTSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1011_PTSD = new Intent(PTSD_CarAccident.this, com.example.vrexpo.treatments.PTSD_CarAccidentPlan.Session1011_PTSD.class);
                startActivity(Session1011_PTSD);
            }
        });

        // Session 12 - PTSD Car Accident
        Button Session12_PTSD = findViewById(R.id.Session12_PTSD);
        Session12_PTSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session12_PTSD = new Intent(PTSD_CarAccident.this, com.example.vrexpo.treatments.PTSD_CarAccidentPlan.Session12_PTSD.class);
                startActivity(Session12_PTSD);
            }
        });

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}




