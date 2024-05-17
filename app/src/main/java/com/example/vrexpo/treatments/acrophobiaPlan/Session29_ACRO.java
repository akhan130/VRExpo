package com.example.vrexpo.treatments.acrophobiaPlan;

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

public class Session29_ACRO extends AppCompatActivity {
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
                Intent dashIntent = new Intent(Session29_ACRO.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Session29_ACRO.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Session29_ACRO.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Session29_ACRO.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Session29_ACRO.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Session29_ACRO.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Session29_ACRO.this, ZegoCloudHomePatient.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session29_acrophobia);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView b3_ACRO29 = findViewById(R.id.b3_ACRO29);
        b3_ACRO29.setMovementMethod(LinkMovementMethod.getInstance());
        b3_ACRO29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/nXbXajGITjw?si=XKd3yMdPGf3x_reO");
                Intent b3_ACRO29 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b3_ACRO29);
            }
        });

        TextView b4_ACRO29 = findViewById(R.id.b4_ACRO29);
        b4_ACRO29.setMovementMethod(LinkMovementMethod.getInstance());
        b4_ACRO29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/hGHVDxdlXKg?si=lFj8XF2fTyxFjhZM");
                Intent b4_ACRO29 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_ACRO29);
            }
        });

        TextView b5_ACRO29 = findViewById(R.id.b5_ACRO29);
        b5_ACRO29.setMovementMethod(LinkMovementMethod.getInstance());
        b5_ACRO29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/28GfLTg6i9Y?si=63rUUR79_bZq4Wad");
                Intent b5_ACRO29 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b5_ACRO29);
            }
        });

        TextView b6_ACRO29 = findViewById(R.id.b6_ACRO29);
        b6_ACRO29.setMovementMethod(LinkMovementMethod.getInstance());
        b6_ACRO29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/x9COxSyxjBk?si=sJHAVsouH9R9ug9p");
                Intent b6_ACRO29 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b6_ACRO29);
            }
        });

        TextView b7_ACRO29 = findViewById(R.id.b7_ACRO29);
        b7_ACRO29.setMovementMethod(LinkMovementMethod.getInstance());
        b7_ACRO29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/nOh0_Mc9sI0?si=nQtK6i20kroCBkKl");
                Intent b7_ACRO29 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b7_ACRO29);
            }
        });

        TextView b8_ACRO29 = findViewById(R.id.b8_ACRO29);
        b8_ACRO29.setMovementMethod(LinkMovementMethod.getInstance());
        b8_ACRO29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/nOh0_Mc9sI0?si=V_sURK_bNL0atbZq");
                Intent b8_ACRO29 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b8_ACRO29);
            }
        });

        TextView b9_ACRO29 = findViewById(R.id.b9_ACRO29);
        b9_ACRO29.setMovementMethod(LinkMovementMethod.getInstance());
        b9_ACRO29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/y90O4LMUFSo?si=aFgfYtr4RUfKCyCl");
                Intent b9_ACRO29 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b9_ACRO29);
            }
        });

        TextView b10_ACRO29 = findViewById(R.id.b10_ACRO29);
        b10_ACRO29.setMovementMethod(LinkMovementMethod.getInstance());
        b10_ACRO29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/YXlOnRERpCo?si=ksRYn5txmDs1cNUh");
                Intent b10_ACRO29 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b10_ACRO29);
            }
        });

        TextView b11_ACRO29 = findViewById(R.id.b11_ACRO29);
        b11_ACRO29.setMovementMethod(LinkMovementMethod.getInstance());
        b11_ACRO29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/RwSLIiBN9IQ?si=ektTIOkzpmmfiCap");
                Intent b11_ACRO29 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b11_ACRO29);
            }
        });
    }
}
