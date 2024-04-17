package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.accounts.Account;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AccountInfo extends AppCompatActivity {

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
                Intent dashIntent = new Intent(AccountInfo.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(AccountInfo.this, Zoom.class);
                startActivity(zoom);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(AccountInfo.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(AccountInfo.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(AccountInfo.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        Button HistoryBtn = findViewById(R.id.historyButton);

        HistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open history questions
                Intent histIntent = new Intent(AccountInfo.this, PatientHistory.class);
                startActivity(histIntent);
            }
        });

        Button recordBtn = findViewById(R.id.recordButton);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open patient info
                Intent recordIntent = new Intent(AccountInfo.this, PatientRecords.class);
                startActivity(recordIntent);
            }
        });

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}