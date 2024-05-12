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
import com.example.vrexpo.ZegoCloudHome;

public class Depression extends AppCompatActivity {
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
                Intent dashIntent = new Intent(Depression.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Depression.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Depression.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Depression.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(Depression.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Depression.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Depression.this, ZegoCloudHome.class);
                startActivity(zoom);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Depression.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression);

        // Session 1 - Depression
        Button Session1_DE = findViewById(R.id.Session1_DE);
        Session1_DE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1_DE = new Intent(Depression.this, com.example.vrexpo.treatments.depressionPlan.Session1_DE.class);
                startActivity(Session1_DE);
            }
        });

        // Session 2,3 - Depression
        Button Session23_DE = findViewById(R.id.Session23_DE);
        Session23_DE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session23_DE = new Intent(Depression.this, com.example.vrexpo.treatments.depressionPlan.Session23_DE.class);
                startActivity(Session23_DE);
            }
        });

        // Session 4,8 - Depression
        Button Session48_DE = findViewById(R.id.Session48_DE);
        Session48_DE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session48_DE = new Intent(Depression.this, com.example.vrexpo.treatments.depressionPlan.Session48_DE.class);
                startActivity(Session48_DE);
            }
        });

        // Session 9,12 - Depression
        Button Session912_DE = findViewById(R.id.Session912_DE);
        Session912_DE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session912_DE = new Intent(Depression.this, com.example.vrexpo.treatments.depressionPlan.Session912_DE.class);
                startActivity(Session912_DE);
            }
        });

        // Session 13,15 - Depression
        Button Session1315_DE = findViewById(R.id.Session1315_DE);
        Session1315_DE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1315_DE = new Intent(Depression.this, com.example.vrexpo.treatments.depressionPlan.Session1315_DE.class);
                startActivity(Session1315_DE);
            }
        });

        // Session 16,18 - Depression
        Button Session1618_DE = findViewById(R.id.Session1618_DE);
        Session1618_DE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1618_DE = new Intent(Depression.this, com.example.vrexpo.treatments.depressionPlan.Session1618_DE.class);
                startActivity(Session1618_DE);
            }
        });

        // Session 19,20 - Depression
        Button Session1920_DE = findViewById(R.id.Session1920_DE);
        Session1920_DE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1920_DE = new Intent(Depression.this, com.example.vrexpo.treatments.depressionPlan.Session1920_DE.class);
                startActivity(Session1920_DE);
            }
        });

        // Session 21,22 - Depression
        Button Session2122_DE = findViewById(R.id.Session2122_DE);
        Session2122_DE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session2122_DE = new Intent(Depression.this, com.example.vrexpo.treatments.depressionPlan.Session2122_DE.class);
                startActivity(Session2122_DE);
            }
        });

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}

