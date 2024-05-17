package com.example.vrexpo.treatments.aviophobiaPlan;

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

public class Session510_AVIO extends AppCompatActivity {

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
                Intent dashIntent = new Intent(Session510_AVIO.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Session510_AVIO.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Session510_AVIO.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Session510_AVIO.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Session510_AVIO.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Session510_AVIO.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Session510_AVIO.this, ZegoCloudHomePatient.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session510_aviophobia);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView b2_AVIO510 = findViewById(R.id.b2_AVIO510);
        b2_AVIO510.setMovementMethod(LinkMovementMethod.getInstance());
        b2_AVIO510.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=nOh0_Mc9sI0");
                Intent b2_AVIO510 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_AVIO510);
            }
        });

        TextView b4_AVIO510 = findViewById(R.id.b4_AVIO510);
        b4_AVIO510.setMovementMethod(LinkMovementMethod.getInstance());
        b4_AVIO510.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=HEEIzZ7UjRg&ab_channel=Blick");
                Intent b4_AVIO510 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_AVIO510);
            }
        });

        TextView b6_AVIO510 = findViewById(R.id.b6_AVIO510);
        b6_AVIO510.setMovementMethod(LinkMovementMethod.getInstance());
        b6_AVIO510.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=PeIiXiEdz-M");
                Intent b6_AVIO510 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b6_AVIO510);
            }
        });

        TextView b8_AVIO510 = findViewById(R.id.b8_AVIO510);
        b8_AVIO510.setMovementMethod(LinkMovementMethod.getInstance());
        b8_AVIO510.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=A0pgC5L7bM8");
                Intent b8_AVIO510 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b8_AVIO510);
            }
        });


    }
}
