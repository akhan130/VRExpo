package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

public class Chat extends AppCompatActivity {

    PatientModel patient;

    EditText messageInput;
    ImageButton sendMessageButton;
    ImageButton backButton;
    TextView messagePatient;
    RecyclerView recyclerView;

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
                Intent dashIntent = new Intent(Chat.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Chat.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Chat.this, ViewPatients.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(Chat.this, Messages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Chat.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Chat.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Chat.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Chat.this, Zoom.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        patient = AndroidUtil.getPatientModelFromIntent(getIntent());

        messageInput = findViewById(R.id.message_input);
        sendMessageButton = findViewById(R.id.send_message_button);
        backButton = findViewById(R.id.back_button);
        messagePatient = findViewById(R.id.patient_title);
        recyclerView = findViewById(R.id.chat_recycler_view);

        backButton.setOnClickListener(v -> onBackPressed());

        messagePatient.setText(patient.getName());
    }

    @Override
    public void onBackPressed() {
        if (isTaskRoot()) {
            // Handle back press when this activity is the root activity
            // For example, navigate to the home screen or exit the app
        } else {
            super.onBackPressed();
        }
    }

}