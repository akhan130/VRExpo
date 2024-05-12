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


public class SocialAnxiety extends AppCompatActivity {
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
                Intent dashIntent = new Intent(SocialAnxiety.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(SocialAnxiety.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(SocialAnxiety.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(SocialAnxiety.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(SocialAnxiety.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(SocialAnxiety.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(SocialAnxiety.this, ZegoCloudHome.class);
                startActivity(zoom);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(SocialAnxiety.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialanxiety);

        // Session 1 - Social Anxiety
        Button Session1_SA = findViewById(R.id.Session1_SA);
        Session1_SA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1_SA = new Intent(SocialAnxiety.this, com.example.vrexpo.treatments.socialAnxietyPlan.Session1_SA.class);
                startActivity(Session1_SA);
            }
        });

        // Session 2 to 6 - Social Anxiety
        Button Session26_SA = findViewById(R.id.Session26_SA);
        Session26_SA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session26_SA = new Intent(SocialAnxiety.this, com.example.vrexpo.treatments.socialAnxietyPlan.Session26_SA.class);
                startActivity(Session26_SA);
            }
        });

        // Session 7 to 11 - Social Anxiety
        Button Session711_SA = findViewById(R.id.Session711_SA);
        Session711_SA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session711_SA = new Intent(SocialAnxiety.this, com.example.vrexpo.treatments.socialAnxietyPlan.Session711_SA.class);
                startActivity(Session711_SA);
            }
        });

        // Session 12 to 13 - Social Anxiety
        Button Session1213_SA = findViewById(R.id.Session1213_SA);
        Session1213_SA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1213_SA = new Intent(SocialAnxiety.this, com.example.vrexpo.treatments.socialAnxietyPlan.Session1213_SA.class);
                startActivity(Session1213_SA);
            }
        });

        // Session 14 to 15 - Social Anxiety
        Button Session1415_SA = findViewById(R.id.Session1415_SA);
        Session1415_SA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session1415_SA = new Intent(SocialAnxiety.this, com.example.vrexpo.treatments.socialAnxietyPlan.Session1415_SA.class);
                startActivity(Session1415_SA);
            }
        });

        // Session 16 - Social Anxiety
        Button Session16_SA = findViewById(R.id.Session16_SA);
        Session16_SA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session16_SA = new Intent(SocialAnxiety.this, com.example.vrexpo.treatments.socialAnxietyPlan.Session16_SA.class);
                startActivity(Session16_SA);
            }
        });

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}
