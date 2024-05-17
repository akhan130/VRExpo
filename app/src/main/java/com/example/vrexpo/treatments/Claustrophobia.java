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
import com.example.vrexpo.TherapistMessages;
import com.example.vrexpo.TherapistZegoCloudHome;
import com.example.vrexpo.TreatmentPlans;
import com.example.vrexpo.SearchPatient;
import com.example.vrexpo.WriteReport;
import com.example.vrexpo.ZegoCloudHome;

public class Claustrophobia extends AppCompatActivity {
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
                Intent dashIntent = new Intent(Claustrophobia.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Claustrophobia.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Claustrophobia.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Claustrophobia.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(Claustrophobia.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Claustrophobia.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Claustrophobia.this, TherapistZegoCloudHome.class);
                startActivity(zoom);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Claustrophobia.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claustrophobia);

        // Session 1 - Claustrophobia
        Button Session1_CLAU = findViewById(R.id.Session1_CLAU);
        Session1_CLAU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1_CLAU = new Intent(Claustrophobia.this, com.example.vrexpo.treatments.claustrophobiaPlan.Session1_CLAU.class);
                startActivity(Session1_CLAU);
            }
        });

        // Session 2,6 - Claustrophobia
        Button Session26_CLAU = findViewById(R.id.Session26_CLAU);
        Session26_CLAU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session26_CLAU = new Intent(Claustrophobia.this, com.example.vrexpo.treatments.claustrophobiaPlan.Session26_CLAU.class);
                startActivity(Session26_CLAU);
            }
        });

        // Session 7,11 - Claustrophobia
        Button Session711_CLAU = findViewById(R.id.Session711_CLAU);
        Session711_CLAU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session711_CLAU = new Intent(Claustrophobia.this, com.example.vrexpo.treatments.claustrophobiaPlan.Session711_CLAU.class);
                startActivity(Session711_CLAU);
            }
        });

        // Session 12 - Claustrophobia
        Button Session12_CLAU = findViewById(R.id.Session12_CLAU);
        Session12_CLAU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session12_CLAU = new Intent(Claustrophobia.this, com.example.vrexpo.treatments.claustrophobiaPlan.Session12_CLAU.class);
                startActivity(Session12_CLAU);
            }
        });

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}



