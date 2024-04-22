package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AdminViewPatient extends AppCompatActivity {

    private PatientAdapter adapter;
    private EditText searchInput;
    RecyclerView recyclerView;
    private Context context;
    private View.OnClickListener onItemClickListener;

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
                Intent dashIntent = new Intent(AdminViewPatient.this, AdminDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_therapist_accounts:
                Intent viewTherapistAcct = new Intent(AdminViewPatient.this, AdminCreateTherapistAcct.class);
                startActivity(viewTherapistAcct);
                return true;
            case R.id.action_patient_accounts:
                Intent viewPatientAcct = new Intent(AdminViewPatient.this, AdminViewPatient.class);
                startActivity(viewPatientAcct);
                return true;
            case R.id.action_admin_settings:
                Intent settingsAcct = new Intent(AdminViewPatient.this, AdminAccountSettings.class);
                startActivity(settingsAcct);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_patient);

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

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Reference to Firebase database
        DatabaseReference patientRef = FirebaseDatabase.getInstance().getReference("PatientAccount");

        // Set up FirebaseRecyclerOptions
        FirebaseRecyclerOptions<PatientModel> options =
                new FirebaseRecyclerOptions.Builder<PatientModel>()
                        .setQuery(patientRef, PatientModel.class)
                        .build();

        // Initialize adapter with FirebaseRecyclerOptions
        adapter = new PatientAdapter(options, this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(view -> {
            int position = recyclerView.getChildAdapterPosition(view);
            PatientModel patient = adapter.getItem(position);
            Intent intent = new Intent(AdminViewPatient.this, AdminViewPatientDetails.class);
            intent.putExtra("phone", patient.getPhone());
            startActivity(intent);
        });

        adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);


        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void setupSearchRecyclerView(String searchQuery) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("PatientAccount");
        Query query = databaseReference.orderByChild("name")
                .startAt(searchQuery)
                .endAt(searchQuery + "\uf8ff");

        FirebaseRecyclerOptions<PatientModel> options = new FirebaseRecyclerOptions.Builder<PatientModel>()
                .setQuery(query, PatientModel.class).build();

        // Update the adapter with the new options
        adapter.updateOptions(options);

        // Make sure to check if RecyclerView is computing a layout or scrolling
        if (recyclerView != null && !recyclerView.isComputingLayout() && !recyclerView.isLayoutSuppressed()) {
            adapter.notifyDataSetChanged();
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