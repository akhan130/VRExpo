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
        //getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent dashIntent = new Intent(TreatmentPlans.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(TreatmentPlans.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(TreatmentPlans.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(TreatmentPlans.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(TreatmentPlans.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(TreatmentPlans.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(TreatmentPlans.this, ZegoCloudHomeTherapist.class);
                startActivity(zoom);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_plans);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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

    }
}