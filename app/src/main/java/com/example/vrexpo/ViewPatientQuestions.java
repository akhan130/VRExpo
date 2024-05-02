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

public class ViewPatientQuestions extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent dashIntent = new Intent(ViewPatientQuestions.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(ViewPatientQuestions.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(ViewPatientQuestions.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(ViewPatientQuestions.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(ViewPatientQuestions.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(ViewPatientQuestions.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(ViewPatientQuestions.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(ViewPatientQuestions.this, Zoom.class);
                startActivity(zoom);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_questions);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button presessionQuestions = findViewById(R.id.presessionButton);
        Button postsessionQuestions = findViewById(R.id.postsessionButton);

        presessionQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent therapistInfoIntent = new Intent(ViewPatientQuestions.this, ViewPatientPresession.class);
                startActivity(therapistInfoIntent);
            }
        });

        postsessionQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent therapistInfoIntent = new Intent(ViewPatientQuestions.this, ViewPatientPostsession.class);
                startActivity(therapistInfoIntent);
            }
        });

    }
}