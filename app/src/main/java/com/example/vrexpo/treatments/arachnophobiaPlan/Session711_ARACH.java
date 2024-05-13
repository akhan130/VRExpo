package com.example.vrexpo.treatments.arachnophobiaPlan;

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
import com.example.vrexpo.TherapistMessages;
import com.example.vrexpo.TreatmentPlans;
import com.example.vrexpo.WriteReport;
import com.example.vrexpo.ZegoCloudHome;
import com.example.vrexpo.treatments.acrophobiaPlan.Session1_ACRO;

public class Session711_ARACH extends AppCompatActivity {

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
                Intent dashIntent = new Intent(Session711_ARACH.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Session711_ARACH.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Session711_ARACH.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Session711_ARACH.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(Session711_ARACH.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Session711_ARACH.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Session711_ARACH.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Session711_ARACH.this, ZegoCloudHome.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session711_arachnophobia);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView b2_ARACH711 = findViewById(R.id.b2_ARACH711);
        b2_ARACH711.setMovementMethod(LinkMovementMethod.getInstance());
        b2_ARACH711.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=l_94jyUTSe0");
                Intent b2_ARACH711 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_ARACH711);
            }
        });

        TextView b4_ARACH711 = findViewById(R.id.b4_ARACH711);
        b4_ARACH711.setMovementMethod(LinkMovementMethod.getInstance());
        b4_ARACH711.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=5H-4mfmiKP8&ab_channel=MVR");
                Intent b4_ARACH711 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_ARACH711);
            }
        });
    }
}
