package com.example.vrexpo.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vrexpo.Patient.PostsessionQuestions;
import com.example.vrexpo.Patient.PresessionQuestions;
import com.example.vrexpo.R;
import com.example.vrexpo.Patient.TherapySchedulerActivity;

public class PatientHistory extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PatientHistory.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_pre:
                Intent preIntent = new Intent(PatientHistory.this, PresessionQuestions.class);
                startActivity(preIntent);
                return true;
            case R.id.action_post:
                Intent postIntent = new Intent(PatientHistory.this, PostsessionQuestions.class);
                startActivity(postIntent);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(PatientHistory.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PatientHistory.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PatientHistory.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}