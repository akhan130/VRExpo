package com.example.vrexpo.treatments.glossophobiaPlan;

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
import com.example.vrexpo.TherapistZegoCloudHome;
import com.example.vrexpo.TreatmentPlans;
import com.example.vrexpo.WriteReport;

public class Session57_GLOS extends AppCompatActivity {

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
                Intent dashIntent = new Intent(Session57_GLOS.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Session57_GLOS.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Session57_GLOS.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Session57_GLOS.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Session57_GLOS.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Session57_GLOS.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Session57_GLOS.this, TherapistZegoCloudHome.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session57_glossophobia);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView b2_GLO57 = findViewById(R.id.b2_GLO57);
        b2_GLO57.setMovementMethod(LinkMovementMethod.getInstance());
        b2_GLO57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/LaFarAGJKTk?si=xHsdq9yfCRKjCKpO");
                Intent b2_GLO57 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_GLO57);
            }
        });

        TextView b4_GLO57 = findViewById(R.id.b4_GLO57);
        b4_GLO57.setMovementMethod(LinkMovementMethod.getInstance());
        b4_GLO57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=RrSFjjeq994");
                Intent b4_GLO57 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_GLO57);
            }
        });

        TextView b6_GLO57 = findViewById(R.id.b6_GLO57);
        b6_GLO57.setMovementMethod(LinkMovementMethod.getInstance());
        b6_GLO57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=a0gkYZuuQDU");
                Intent b6_GLO57 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b6_GLO57);
            }
        });

    }
}




