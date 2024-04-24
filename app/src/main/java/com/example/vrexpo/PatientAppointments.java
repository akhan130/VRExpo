package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PatientAppointments extends AppCompatActivity {

    private Button bookAppointmentButton;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PatientAppointments.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent zoom = new Intent(PatientAppointments.this, SessionStart.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PatientAppointments.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(PatientAppointments.this, PatientAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PatientAppointments.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_messages:
                Intent messages = new Intent(PatientAppointments.this, PatientMessages.class);
                startActivity(messages);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(PatientAppointments.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointments);

        bookAppointmentButton = findViewById(R.id.book_appointment_button);

        bookAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientInfoIntent = new Intent(PatientAppointments.this, PatientSelectAppointment.class);
                startActivity(patientInfoIntent);
            }
        });


        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}