package com.example.vrexpo.treatments.claustrophobiaPlan;

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

public class Session26_CLAU extends AppCompatActivity {

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
                Intent dashIntent = new Intent(Session26_CLAU.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Session26_CLAU.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Session26_CLAU.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Session26_CLAU.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Session26_CLAU.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Session26_CLAU.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Session26_CLAU.this, ZegoCloudHomePatient.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session26_claustrophobia);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView b3_CLAU26 = findViewById(R.id.b3_CLAU26);
        b3_CLAU26.setMovementMethod(LinkMovementMethod.getInstance());
        b3_CLAU26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/igaha_puYmg?si=kiXJzej7bv3UA8Ph");
                Intent b3_CLAU26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b3_CLAU26);
            }
        });

        TextView b5_CLAU26 = findViewById(R.id.b5_CLAU26);
        b5_CLAU26.setMovementMethod(LinkMovementMethod.getInstance());
        b5_CLAU26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/nkKz8stbuWA?si=9-WeoBxXTnOyKhPr");
                Intent b5_CLAU26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b5_CLAU26);
            }
        });

        TextView b7_CLAU26 = findViewById(R.id.b7_CLAU26);
        b7_CLAU26.setMovementMethod(LinkMovementMethod.getInstance());
        b7_CLAU26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/h83_d3cvaXg?si=SJMyRMxjQEkGix-A");
                Intent b7_CLAU26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b7_CLAU26);
            }
        });

        TextView b9_CLAU26 = findViewById(R.id.b9_CLAU26);
        b9_CLAU26.setMovementMethod(LinkMovementMethod.getInstance());
        b9_CLAU26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/6aE1ti16AE8?si=TToWyiXwRLGY3q_-");
                Intent b9_CLAU26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b9_CLAU26);
            }
        });


    }
}

