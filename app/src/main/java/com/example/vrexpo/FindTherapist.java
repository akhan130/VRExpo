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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
            case R.id.action_pre:
                Intent preIntent = new Intent(FindTherapist.this, PresessionQuestions.class);
                startActivity(preIntent);
                return true;
            case R.id.action_post:
                Intent postIntent = new Intent(FindTherapist.this, PostsessionQuestions.class);
                startActivity(postIntent);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(FindTherapist.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(FindTherapist.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(FindTherapist.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(FindTherapist.this, Zoom.class);
                startActivity(zoom);
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

    private void setupSearchRecyclerView(String searchQuery) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("TherapistInfo");
        Query query = databaseReference.orderByChild("therapist_fullName")
                .startAt(searchQuery)
                .endAt(searchQuery + "\uf8ff");

        FirebaseRecyclerOptions<Therapist> options = new FirebaseRecyclerOptions.Builder<Therapist>()
                .setQuery(query, Therapist.class).build();

        // Update the adapter with the new options
        adapter.updateOptions(options);
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