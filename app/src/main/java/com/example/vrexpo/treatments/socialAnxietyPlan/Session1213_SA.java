package com.example.vrexpo.treatments.socialAnxietyPlan;

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

public class Session1213_SA extends AppCompatActivity {

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
                Intent dashIntent = new Intent(Session1213_SA.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Session1213_SA.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Session1213_SA.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Session1213_SA.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Session1213_SA.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Session1213_SA.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Session1213_SA.this, TherapistZegoCloudHome.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session1213_social);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView b3_SA1213 = findViewById(R.id.b3_SA1213);
        b3_SA1213.setMovementMethod(LinkMovementMethod.getInstance());
        b3_SA1213.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=wU0aFi5avsc");
                Intent b3_SA1213 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b3_SA1213);
            }
        });
    }
}
