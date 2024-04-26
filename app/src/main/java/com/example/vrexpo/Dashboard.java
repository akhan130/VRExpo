package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Dashboard extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(Dashboard.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(Dashboard.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(Dashboard.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Dashboard.this, PatientAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_sessionStart:
                Intent sessionStart = new Intent(Dashboard.this, SessionStart.class);
                startActivity(sessionStart);
                return true;
            case R.id.action_messages:
                Intent messages = new Intent(Dashboard.this, PatientMessages.class);
                startActivity(messages);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(Dashboard.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}