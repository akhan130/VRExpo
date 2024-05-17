package com.example.vrexpo.treatments.PTSD_CarAccidentPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vrexpo.R;
import com.example.vrexpo.SearchPatient;
import com.example.vrexpo.TherapistAccountSettings;
import com.example.vrexpo.TherapistAppointments;
import com.example.vrexpo.TherapistDashboard;
import com.example.vrexpo.TreatmentPlans;
import com.example.vrexpo.WriteReport;
import com.example.vrexpo.ZegoCloudHomePatient;

public class Session26_PTSD extends AppCompatActivity {

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
                Intent dashIntent = new Intent(Session26_PTSD.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Session26_PTSD.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Session26_PTSD.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Session26_PTSD.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Session26_PTSD.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Session26_PTSD.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Session26_PTSD.this, ZegoCloudHomePatient.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session26_ptsd_caraccident);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView b2_PTSD26 = findViewById(R.id.b2_PTSD26);
        b2_PTSD26.setMovementMethod(LinkMovementMethod.getInstance());
        b2_PTSD26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/DhE9J_XbeK4?si=wAzlNO6cp2T_1Oku");
                Intent b2_PTSD26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_PTSD26);
            }
        });

        TextView b4_PTSD26 = findViewById(R.id.b4_PTSD26);
        b4_PTSD26.setMovementMethod(LinkMovementMethod.getInstance());
        b4_PTSD26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/Re6GEscZY1I?si=MoRpvyRxcHNHWg5i");
                Intent b4_PTSD26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_PTSD26);
            }
        });

        TextView b6_PTSD26 = findViewById(R.id.b6_PTSD26);
        b6_PTSD26.setMovementMethod(LinkMovementMethod.getInstance());
        b6_PTSD26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/lwRaubd7TYw?si=j9b7kit7AtOHC3mo");
                Intent b6_PTSD26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b6_PTSD26);
            }
        });

        TextView b8_PTSD26 = findViewById(R.id.b8_PTSD26);
        b8_PTSD26.setMovementMethod(LinkMovementMethod.getInstance());
        b8_PTSD26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/9CIfntMu_Gs?si=1QH0hfeam-bCo8vE");
                Intent b8_PTSD26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b8_PTSD26);
            }
        });

        TextView b10_PTSD26 = findViewById(R.id.b10_PTSD26);
        b10_PTSD26.setMovementMethod(LinkMovementMethod.getInstance());
        b10_PTSD26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/DJEjeKD-Jb8?si=VB27YdbPsk_HK5IB");
                Intent b10_PTSD26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b10_PTSD26);
            }
        });


    }
}


