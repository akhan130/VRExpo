package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AdminDashboard extends AppCompatActivity {

    private static final String TAG = "VRExpo";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.admin_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent dashIntent = new Intent(AdminDashboard.this, AdminDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_therapist_accounts:
                Intent viewTherapistAcct = new Intent(AdminDashboard.this, AdminCreateTherapistAcct.class);
                startActivity(viewTherapistAcct);
                return true;
            case R.id.action_patient_accounts:
                Intent viewPatientAcct = new Intent(AdminDashboard.this, AdminViewPatient.class);
                startActivity(viewPatientAcct);
                return true;
            case R.id.action_admin_settings:
                Intent settingsAcct = new Intent(AdminDashboard.this, AdminAccountSettings.class);
                startActivity(settingsAcct);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.admin_toolbar);
        setSupportActionBar(myToolbar);
    }
}