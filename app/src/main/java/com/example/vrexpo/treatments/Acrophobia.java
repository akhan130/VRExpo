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
import com.example.vrexpo.TreatmentPlans;
import com.example.vrexpo.SearchPatient;
import com.example.vrexpo.WriteReport;
import com.example.vrexpo.ZegoCloudHomePatient;

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
            case R.id.action_home:
                Intent dashIntent = new Intent(Acrophobia.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Acrophobia.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Acrophobia.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Acrophobia.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Acrophobia.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Acrophobia.this, ZegoCloudHomePatient.class);
                startActivity(zoom);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Acrophobia.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
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



