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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AdminViewTherapist extends AppCompatActivity {

    private TherapistAdapter2 adapter;
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
                Intent dashIntent = new Intent(AdminViewTherapist.this, AdminDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_create_account:
                Intent createAcctIntent = new Intent(AdminViewTherapist.this, AdminCreateTherapistAcct.class);
                startActivity(createAcctIntent);
                return true;
            case R.id.action_view_therapist:
                Intent viewTherapistAcct = new Intent(AdminViewTherapist.this, AdminViewTherapist.class);
                startActivity(viewTherapistAcct);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_therapist);

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
        adapter = new TherapistAdapter2(options, this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(view -> {
            int position = recyclerView.getChildAdapterPosition(view);
            Therapist therapist = adapter.getItem(position);
            Intent intent = new Intent(AdminViewTherapist.this, AdminViewTherapistDetails.class);
            intent.putExtra("therapist_phone", therapist.getPhoneNumber());
            startActivity(intent);
        });

        adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);


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