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

public class Arachnophobia extends AppCompatActivity {
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
                Intent treatmentPlans = new Intent(Arachnophobia.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Arachnophobia.this, Zoom.class);
                startActivity(zoom);
                return true;
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(Arachnophobia.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(Arachnophobia.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(Arachnophobia.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arachnophobia);

        // Session 1 - Arachnophobia
        Button Session1_ARACH = findViewById(R.id.Session1_ARACH);
        Session1_ARACH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1_ARACH = new Intent(Arachnophobia.this, com.example.vrexpo.treatments.arachnophobiaPlan.Session1_ARACH.class);
                startActivity(Session1_ARACH);
            }
        });

        // Session 2,6 - Arachnophobia
        Button Session26_ARACH = findViewById(R.id.Session26_ARACH);
        Session26_ARACH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session26_ARACH = new Intent(Arachnophobia.this, com.example.vrexpo.treatments.arachnophobiaPlan.Session26_ARACH.class);
                startActivity(Session26_ARACH);
            }
        });

        // Session 7,11 - Arachnophobia
        Button Session711_ARACH = findViewById(R.id.Session711_ARACH);
        Session711_ARACH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session711_ARACH = new Intent(Arachnophobia.this, com.example.vrexpo.treatments.arachnophobiaPlan.Session711_ARACH.class);
                startActivity(Session711_ARACH);
            }
        });

        // Session 12,14 - Arachnophobia
        Button Session1214_ARACH = findViewById(R.id.Session1214_ARACH);
        Session1214_ARACH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1214_ARACH = new Intent(Arachnophobia.this, com.example.vrexpo.treatments.arachnophobiaPlan.Session1214_ARACH.class);
                startActivity(Session1214_ARACH);
            }
        });

        // Session 15 - Arachnophobia
        Button Session15_ARACH = findViewById(R.id.Session15_ARACH);
        Session15_ARACH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session15_ARACH = new Intent(Arachnophobia.this, com.example.vrexpo.treatments.arachnophobiaPlan.Session15_ARACH.class);
                startActivity(Session15_ARACH);
            }
        });


        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}

