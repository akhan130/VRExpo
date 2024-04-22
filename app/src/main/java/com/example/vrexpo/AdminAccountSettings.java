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

public class AdminAccountSettings extends AppCompatActivity {

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
                Intent dashIntent = new Intent(AdminAccountSettings.this, AdminDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_therapist_accounts:
                Intent viewTherapistAcct = new Intent(AdminAccountSettings.this, AdminCreateTherapistAcct.class);
                startActivity(viewTherapistAcct);
                return true;
            case R.id.action_patient_accounts:
                Intent viewPatientAcct = new Intent(AdminAccountSettings.this, AdminViewPatient.class);
                startActivity(viewPatientAcct);
                return true;
            case R.id.action_account_settings:
                Intent settingsAcct = new Intent(AdminAccountSettings.this, AdminAccountSettings.class);
                startActivity(settingsAcct);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account_settings);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button logoutBtn = findViewById(R.id.logoutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(AdminAccountSettings.this, Login.class);
                startActivity(logoutIntent);
            }
        });


    }
}