package com.example.vrexpo.TherapistMessages;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vrexpo.PatientModel;
import com.example.vrexpo.R;
import com.example.vrexpo.TherapistAccountSettings;
import com.example.vrexpo.TherapistAppointments;
import com.example.vrexpo.TherapistDashboard;
import com.example.vrexpo.TreatmentPlans;
import com.example.vrexpo.ViewPatients;
import com.example.vrexpo.WriteReport;
import com.example.vrexpo.Zoom;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class TherapistMessages extends AppCompatActivity {

    private MessagesPatientRecyclerAdapter adapter;

    EditText searchInput;
    ImageButton searchButton;
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
                Intent dashIntent = new Intent(TherapistMessages.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(TherapistMessages.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(TherapistMessages.this, ViewPatients.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(TherapistMessages.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(TherapistMessages.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(TherapistMessages.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(TherapistMessages.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(TherapistMessages.this, Zoom.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_messages);

        //Search
        searchInput = findViewById(R.id.search_patient);
        searchButton = findViewById(R.id.search_patient_button);
        recyclerView = findViewById(R.id.search_patient_recycler_view);

        searchInput.requestFocus();

        searchButton.setOnClickListener(v -> {
            String searchPatient = searchInput.getText().toString();
            if(searchPatient.isEmpty() || searchPatient.length()<3){
                searchInput.setError("Invalid Name");
                return;
            }
            setupSearchRecyclerView(searchPatient);
        });

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    //Searches Patients by their first name
    void setupSearchRecyclerView(String searchPatient) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("PatientAccount");
        Query query = databaseReference.orderByChild("name")
                .startAt(searchPatient)
                .endAt(searchPatient + "\uf8ff");

        FirebaseRecyclerOptions<PatientModel> options = new FirebaseRecyclerOptions.Builder<PatientModel>()
                .setQuery(query, PatientModel.class).build();

        adapter = new MessagesPatientRecyclerAdapter(options, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(adapter!=null)
            adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }
}