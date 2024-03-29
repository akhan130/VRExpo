package com.example.vrexpo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindTherapist extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TherapistAdapter adapter;
    private List<Therapist> therapistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_therapist);

        // Initialize RecyclerView and therapistList
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        therapistList = new ArrayList<>();
        adapter = new TherapistAdapter(therapistList);
        recyclerView.setAdapter(adapter);

        // Reference to Firebase database
        DatabaseReference therapistsRef = FirebaseDatabase.getInstance().getReference("TherapistInfo");

        // Read from the database
        therapistsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                therapistList.clear(); // Clear existing list

                for (DataSnapshot therapistSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve therapist information
                    String fullName = therapistSnapshot.child("therapist_fullName").getValue(String.class);
                    String email = therapistSnapshot.child("therapist_email").getValue(String.class);
                    String phoneNumber = therapistSnapshot.child("therapist_phoneNumber").getValue(String.class);
                    String specialization = therapistSnapshot.child("therapist_specialization").getValue(String.class);

                    // Check for null values
                    if (fullName != null && email != null && phoneNumber != null && specialization != null) {
                        Therapist therapist = new Therapist(fullName, email, phoneNumber, specialization);
                        therapistList.add(therapist);
                    }
                }

                // Notify adapter that the data has changed
                adapter.notifyDataSetChanged();

                // Log to check the size of the therapistList
                Log.d("FindTherapist", "Therapist List Size: " + therapistList.size());
            }

            private static final String TAG = "FindTherapist";

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
