package com.example.vrexpo.Therapist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vrexpo.R;

public class WriteReport extends AppCompatActivity {

    private static final String TAG = "VRExpo";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(WriteReport.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_view_appointments:
                Intent appointmentsIntent = new Intent(WriteReport.this, ViewAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_time_available:
                Intent availabilityIntent = new Intent(WriteReport.this, TimeAvailability.class);
                startActivity(availabilityIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(WriteReport.this, ViewPatients.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(WriteReport.this, Messages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(WriteReport.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_dashboard);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}