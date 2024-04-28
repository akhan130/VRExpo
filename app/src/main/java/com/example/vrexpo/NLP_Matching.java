package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vrexpo.treatments.Acrophobia;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.text.similarity.CosineSimilarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NLP_Matching extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView messageTextView;
    private DatabaseReference databaseReference;
    private List<String> selectedOptions;
    private TherapistAdapterNLP adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(NLP_Matching.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent zoom = new Intent(NLP_Matching.this, SessionStart.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(NLP_Matching.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(NLP_Matching.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(NLP_Matching.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_messages:
                Intent messages = new Intent(NLP_Matching.this, PatientMessages.class);
                startActivity(messages);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(NLP_Matching.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nlp_matching);

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        recyclerView = findViewById(R.id.recycler_view_matched_therapists);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageTextView = findViewById(R.id.NLP_Match_Message1);

        // Get selected options passed from previous activity
        String diagnosed = getIntent().getStringExtra("Diagnosed");
        String condition = getIntent().getStringExtra("Condition");
        String phobia = getIntent().getStringExtra("Phobia");
        String ptsd = getIntent().getStringExtra("PTSD");
        String duration = getIntent().getStringExtra("Duration");
        String impact = getIntent().getStringExtra("Impact");

        // Print out the selected options for debugging
        Log.d("NLP_MATCHING_DEBUG", "Diagnosed: " + diagnosed);
        Log.d("NLP_MATCHING_DEBUG", "Condition: " + condition);
        Log.d("NLP_MATCHING_DEBUG", "Phobia: " + phobia);
        Log.d("NLP_MATCHING_DEBUG", "PTSD: " + ptsd);
        Log.d("NLP_MATCHING_DEBUG", "Duration: " + duration);
        Log.d("NLP_MATCHING_DEBUG", "Impact: " + impact);

        // Add selected options to the list
        selectedOptions = new ArrayList<>();
        selectedOptions.add(diagnosed);
        selectedOptions.add(condition);
        selectedOptions.add(phobia);
        selectedOptions.add(ptsd);
        selectedOptions.add(duration);
        selectedOptions.add(impact);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("TherapistInfo");

        // Perform NLP matching
        performNLPMatching();

        // Session 1 - Acrophobia
        Button TreatmentPlans = findViewById(R.id.treatmentPlansButton);
        TreatmentPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent TreatmentPlans = new Intent(NLP_Matching.this, ML_matching.class);
                startActivity(TreatmentPlans);
            }
        });
    }

    private void performNLPMatching() {
        Query query = databaseReference.orderByChild("therapist_specialization");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> matchedTherapists = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String specialization = snapshot.child("therapist_specialization").getValue(String.class);
                    Log.d("TherapistSpecialization", "Specialization: " + specialization);
                    if (specialization != null && containsAnyNLP(specialization, selectedOptions)) {
                        matchedTherapists.add(snapshot.getKey());
                        // Set matched data
                        MatchedData.setTherapistSpecialization(specialization);
                        // Set other matched data
                        MatchedData.setPatientCondition(selectedOptions.get(0)); // Assuming 'diagnosed' is the patient condition
                        MatchedData.setPatientPhobia(selectedOptions.get(2)); // Assuming 'phobia' is the patient phobia
                        MatchedData.setPatientPTSD(selectedOptions.get(3)); // Assuming 'ptsd' is the patient PTSD
                        MatchedData.setMatchedTherapistIds(matchedTherapists);
                        break; // Exit the loop after finding the first match
                    }
                }

                updateUI(matchedTherapists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    // Method to check if a string contains any of the specified substrings using NLP techniques
    private boolean containsAnyNLP(String input, List<String> substrings) {
        // Preprocess input and options for NLP techniques
        Map<CharSequence, Integer> inputVector = preprocessText(input);
        List<Map<CharSequence, Integer>> optionVectors = new ArrayList<>();
        for (String option : substrings) {
            optionVectors.add(preprocessText(option));
        }

        // Calculate similarity using Cosine Similarity
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        for (Map<CharSequence, Integer> optionVector : optionVectors) {
            double similarity = cosineSimilarity.cosineSimilarity(inputVector, optionVector);
            if (similarity > 0.3) { // Adjust threshold as needed
                return true;
            }
        }
        return false;
    }

    // Method to preprocess text for NLP techniques
    private Map<CharSequence, Integer> preprocessText(String text) {
        // Initialize a map to store word counts
        Map<CharSequence, Integer> wordCounts = new HashMap<>();

        // Tokenize the text
        String[] words = text.split("\\s+");

        // Count word occurrences
        for (String word : words) {
            // Normalize the word (convert to lowercase)
            CharSequence normalizedWord = word.toLowerCase();

            // Update word count in the map
            wordCounts.put(normalizedWord, wordCounts.getOrDefault(normalizedWord, 0) + 1);
        }

        return wordCounts;
    }


    // Method to update the UI based on the matched therapists
    private void updateUI(List<String> matchedTherapists) {
        if (matchedTherapists.isEmpty()) {
            messageTextView.setText("Sorry, you have not been matched with a therapist.\n\nClick the button below to be directed to the Find Therapist section and look for a therapist you might be interested in.\n\n\n");
            messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            Button findTherapistButton = findViewById(R.id.findTherapistButton);
            findTherapistButton.setVisibility(View.VISIBLE);
            findTherapistButton.setOnClickListener(v -> {
                Intent intent = new Intent(NLP_Matching.this, FindTherapist.class);
                startActivity(intent);
            });
        } else {
            Toast.makeText(getApplicationContext(), "Congrats, you have been matched with a therapist", Toast.LENGTH_LONG).show();
            List<Therapist> therapists = new ArrayList<>();
            AtomicInteger count = new AtomicInteger();

            for (String phone : matchedTherapists) {
                databaseReference.child(phone).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.child("therapist_fullName").getValue(String.class);
                        if (name != null) {
                            Therapist therapist = new Therapist(name, snapshot.child("therapist_email").getValue(String.class), phone, snapshot.child("therapist_specialization").getValue(String.class), snapshot.child("therapist_password").getValue(String.class));
                            therapists.add(therapist);
                        }
                        // Check if all callbacks have completed
                        if (count.incrementAndGet() == matchedTherapists.size()) {
                            adapter = new TherapistAdapterNLP(therapists);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("DB_ERROR", "Failed to read value.", databaseError.toException());
                    }
                });
            }
        }
    }

}


/***
Toast.makeText(getApplicationContext(), "Congrats, you have been matched with a therapist", Toast.LENGTH_LONG).show();
// Create a list to hold the matched therapists
List<Therapist> therapists = new ArrayList<>();

// Loop through all matched therapists
for (String therapistId : matchedTherapists) {
    // Retrieve each matched therapist from the database
    databaseReference.child(therapistId).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // Convert the dataSnapshot to a Therapist object
            Therapist therapist = dataSnapshot.getValue(Therapist.class);
            if (therapist != null) {
                // Add the therapist to the list
                therapists.add(therapist);

                // Update the UI once all therapists are retrieved
                if (therapists.size() == matchedTherapists.size()) {
                    displayMatchedTherapists(therapists);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            // Handle error
        }
    });
}
}
}

// Method to display the matched therapists in the RecyclerView
private void displayMatchedTherapists(List<Therapist> therapists) {
// Create FirebaseRecyclerOptions using the list of matched therapists
FirebaseRecyclerOptions<Therapist> options = new FirebaseRecyclerOptions.Builder<Therapist>()
    .setIndexedQuery(databaseReference.child("TherapistInfo"), databaseReference, Therapist.class)
    .build();

// Initialize adapter with FirebaseRecyclerOptions
adapter = new TherapistAdapter(options, this);
recyclerView.setAdapter(adapter);
}

} **/


/***
 * After submit button is selected in patient history
 * Go to NLP_Matching.java
 *
 * Print out --> you have been matched with a therapist
 *
 * call firebase database to retrieve
 * therapist email
 * therapist number
 * therapist full name
 * therapist specialization
 */