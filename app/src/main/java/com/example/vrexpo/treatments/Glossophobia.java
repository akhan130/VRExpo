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

import com.example.vrexpo.R;
import com.example.vrexpo.TherapistAccountSettings;
import com.example.vrexpo.TherapistAppointments;
import com.example.vrexpo.TherapistDashboard;
import com.example.vrexpo.TherapistZegoCloudHome;
import com.example.vrexpo.TreatmentPlans;
import com.example.vrexpo.SearchPatient;
import com.example.vrexpo.WriteReport;


public class Glossophobia extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent dashIntent = new Intent(Glossophobia.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Glossophobia.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Glossophobia.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Glossophobia.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Glossophobia.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Glossophobia.this, TherapistZegoCloudHome.class);
                startActivity(zoom);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Glossophobia.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossophobia);

        // Session 1 - Glossophobia
        Button Session1_GLOS = findViewById(R.id.Session1_GLOS);
        Session1_GLOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1_GLOS = new Intent(Glossophobia.this, com.example.vrexpo.treatments.glossophobiaPlan.Session1_GLOS.class);
                startActivity(Session1_GLOS);
            }
        });

        // Session 2,4 - Glossophobia
        Button Session24_GLOS = findViewById(R.id.Session24_GLOS);
        Session24_GLOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session24_GLOS = new Intent(Glossophobia.this, com.example.vrexpo.treatments.glossophobiaPlan.Session24_GLOS.class);
                startActivity(Session24_GLOS);
            }
        });

        // Session 5,7 - Glossophobia
        Button Session57_GLOS = findViewById(R.id.Session57_GLOS);
        Session57_GLOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session57_GLOS = new Intent(Glossophobia.this, com.example.vrexpo.treatments.glossophobiaPlan.Session57_GLOS.class);
                startActivity(Session57_GLOS);
            }
        });

        // Session 8 - Glossophobia
        Button Session8_GLOS = findViewById(R.id.Session8_GLOS);
        Session8_GLOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session8_GLOS = new Intent(Glossophobia.this, com.example.vrexpo.treatments.glossophobiaPlan.Session8_GLOS.class);
                startActivity(Session8_GLOS);
            }
        });
        

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}





