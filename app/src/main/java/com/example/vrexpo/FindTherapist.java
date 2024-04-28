package com.example.vrexpo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FindTherapist extends AppCompatActivity {

    private TherapistAdapter adapter;
    private EditText searchInput;

    RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(FindTherapist.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent zoom = new Intent(FindTherapist.this, SessionStart.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(FindTherapist.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(FindTherapist.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(FindTherapist.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_messages:
                Intent messages = new Intent(FindTherapist.this, PatientMessages.class);
                startActivity(messages);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(FindTherapist.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_therapist);

        // Initialize searchInput and searchButton
        searchInput = findViewById(R.id.search_therapist);
        ImageButton searchButton = findViewById(R.id.search_therapist_button);

        searchInput.requestFocus();

        // Set up search button click listener
        searchButton.setOnClickListener(v -> {
            String searchTherapist = searchInput.getText().toString();
            if(searchTherapist.isEmpty() || searchTherapist.length()<3){
                searchInput.setError("Please enter at least 3 characters");
                return;
            }
            setupSearchRecyclerView(searchTherapist);
        });

        ImageButton refreshButton = findViewById(R.id.refresh_button);

        refreshButton.setOnClickListener(v -> {
            resetRecyclerView();
        });


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Reference to Firebase database
        DatabaseReference therapistsRef = FirebaseDatabase.getInstance().getReference("TherapistInfo");

        // Set up FirebaseRecyclerOptions
        FirebaseRecyclerOptions<Therapist> options =
                new FirebaseRecyclerOptions.Builder<Therapist>()
                        .setQuery(therapistsRef, Therapist.class)
                        .build();

        // Initialize adapter with FirebaseRecyclerOptions
        adapter = new TherapistAdapter(options, this);
        recyclerView.setAdapter(adapter);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setupSearchRecyclerView(String searchQuery) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("TherapistInfo");
        Query query = databaseReference.orderByChild("therapist_fullName")
                .startAt(searchQuery)
                .endAt(searchQuery + "\uf8ff");

        FirebaseRecyclerOptions<Therapist> options = new FirebaseRecyclerOptions.Builder<Therapist>()
                .setQuery(query, Therapist.class).build();

        // Update the adapter with the new options
        adapter.updateOptions(options);

        // Make sure to check if RecyclerView is computing a layout or scrolling
        if (recyclerView != null && !recyclerView.isComputingLayout() && !recyclerView.isLayoutSuppressed()) {
            adapter.notifyDataSetChanged();
        }
    }

    public void onPhobiaImageClicked(View view) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TherapistInfo");
        String specialization = "";

        switch (view.getId()) {
            case R.id.image_acrophobia:
                specialization = "Acrophobia";
                break;
            case R.id.image_arachnophobia:
                specialization = "Arachnophobia";
                break;
            case R.id.image_aviophobia:
                specialization = "Aviophobia";
                break;
            case R.id.image_car:
                specialization = "Car Accident PTSD";
                break;
            case R.id.image_claustrophobia:
                specialization = "Claustrophobia";
                break;
            case R.id.image_depression:
                specialization = "Depression";
                break;
            case R.id.image_glossophobia:
                specialization = "Glossophobia";
                break;
            case R.id.image_socialAnxiety:
                specialization = "Social Anxiety";
                break;
        }

        if (!specialization.isEmpty()) {
            Query query = databaseReference.orderByChild("therapist_specialization").equalTo(specialization);
            FirebaseRecyclerOptions<Therapist> options = new FirebaseRecyclerOptions.Builder<Therapist>()
                    .setQuery(query, Therapist.class).build();
            adapter.updateOptions(options);
        }
    }

    private void resetRecyclerView() {
        // Debugging: Log to make sure this method is called
        Log.d("FindTherapist", "Resetting RecyclerView");

        DatabaseReference therapistsRef = FirebaseDatabase.getInstance().getReference("TherapistInfo");
        FirebaseRecyclerOptions<Therapist> options =
                new FirebaseRecyclerOptions.Builder<Therapist>()
                        .setQuery(therapistsRef, Therapist.class)
                        .build();

        if (adapter != null) {
            adapter.stopListening(); // Stop listening to the previous query
            adapter.updateOptions(options); // Update the options
            adapter.startListening(); // Start listening to the new query
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }


}