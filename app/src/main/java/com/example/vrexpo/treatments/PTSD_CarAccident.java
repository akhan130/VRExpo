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
import com.example.vrexpo.TreatmentPlans;
import com.example.vrexpo.SearchPatient;
import com.example.vrexpo.WriteReport;
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
            case R.id.action_home:
                Intent dashIntent = new Intent(PTSD_CarAccident.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(PTSD_CarAccident.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(PTSD_CarAccident.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(PTSD_CarAccident.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(PTSD_CarAccident.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(PTSD_CarAccident.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(PTSD_CarAccident.this, Zoom.class);
                startActivity(zoom);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(PTSD_CarAccident.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
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




