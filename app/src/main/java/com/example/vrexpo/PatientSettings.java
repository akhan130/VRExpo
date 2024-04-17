package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PatientSettings extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PatientSettings.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent sessionStart = new Intent(PatientSettings.this, SessionStart.class);
                startActivity(sessionStart);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(PatientSettings.this, Zoom.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PatientSettings.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(PatientSettings.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PatientSettings.this, FindTherapist.class);
                startActivity(findIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_settings);

        Button infoBtn = findViewById(R.id.infoButton);

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open patient info
                Intent infoIntent = new Intent(PatientSettings.this, PatientInfo.class);
                startActivity(infoIntent);
            }
        });

        Button updateBtn = findViewById(R.id.updateButton);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(PatientSettings.this, PatientUpdateAccount.class);
                startActivity(updateIntent);
            }
        });

        Button logoutBtn = findViewById(R.id.logoutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(PatientSettings.this, Login.class);
                startActivity(logoutIntent);
            }
        });

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}