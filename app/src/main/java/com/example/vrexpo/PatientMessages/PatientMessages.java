package com.example.vrexpo.PatientMessages;

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

import com.example.vrexpo.AccountInfo;
import com.example.vrexpo.FindTherapist;
import com.example.vrexpo.PatientSelectAppointment;
import com.example.vrexpo.PatientSettings;
import com.example.vrexpo.R;
import com.example.vrexpo.SessionStart;
import com.example.vrexpo.Therapist;
import com.example.vrexpo.Zoom;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class PatientMessages extends AppCompatActivity {

    private MessageTherapistRecyclerAdapter adapter;

    EditText searchInput;
    ImageButton searchButton;

    RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_sessionStart:
                Intent sessionStart = new Intent(PatientMessages.this, SessionStart.class);
                startActivity(sessionStart);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(PatientMessages.this, Zoom.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PatientMessages.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(PatientMessages.this, PatientSelectAppointment.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PatientMessages.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(PatientMessages.this, PatientMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(PatientMessages.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_messages);

        //Search
        searchInput = findViewById(R.id.search_therapist);
        searchButton = findViewById(R.id.search_therapist_button);
        recyclerView = findViewById(R.id.search_therapist_recycler_view);

        searchInput.requestFocus();

        searchButton.setOnClickListener(v -> {
            String searchTherapist = searchInput.getText().toString();
            if(searchTherapist.isEmpty() || searchTherapist.length()<3){
                searchInput.setError("Invalid Name");
                return;
            }
            setupSearchRecyclerView(searchTherapist);
        });

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    void setupSearchRecyclerView(String searchTherapist) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("TherapistInfo");
        Query query = databaseReference.orderByChild("therapist_fullName") // Use the exact key name from Firebase
                .startAt(searchTherapist)
                .endAt(searchTherapist + "\uf8ff");

        FirebaseRecyclerOptions<Therapist> options = new FirebaseRecyclerOptions.Builder<Therapist>()
                .setQuery(query, Therapist.class).build();

        adapter = new MessageTherapistRecyclerAdapter(options, this);
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