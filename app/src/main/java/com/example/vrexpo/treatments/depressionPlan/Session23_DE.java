package com.example.vrexpo.treatments.depressionPlan;

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

public class Session23_DE extends AppCompatActivity {

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
                Intent dashIntent = new Intent(Session23_DE.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Session23_DE.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Session23_DE.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Session23_DE.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Session23_DE.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Session23_DE.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Session23_DE.this, TherapistZegoCloudHome.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session23_depression);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView b2_DEP23 = findViewById(R.id.b2_DEP23);
        b2_DEP23.setMovementMethod(LinkMovementMethod.getInstance());
        b2_DEP23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=8Du1hrPrV38");
                Intent b2_DEP23 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_DEP23);
            }
        });
    }
}
