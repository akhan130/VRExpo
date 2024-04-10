package com.example.vrexpo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TherapistInfo extends AppCompatActivity {

    private EditText emailEditText;
    private EditText fullNameEditText;
    private EditText genderEditText;
    private EditText phoneEditText;
    private EditText specializationEditText;
    private EditText passwordEditText;

    private Button editProfileButton;
    private Button updateProfileButton;
    private Button cancelButton;

    private DatabaseReference therapistRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_info);

        // Initialize Firebase Auth instance
        mAuth = FirebaseAuth.getInstance();

        // Initialize EditText fields
        emailEditText = findViewById(R.id.Email);
        fullNameEditText = findViewById(R.id.FullName);
        genderEditText = findViewById(R.id.Gender);
        phoneEditText = findViewById(R.id.Phone);
        specializationEditText = findViewById(R.id.Specialization);
        passwordEditText = findViewById(R.id.Password);

        // Get the current logged-in user
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Get the user's unique ID
            String therapistPhone = currentUser.getUid();

            // Get a reference to the Firebase Realtime Database node for therapists
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            therapistRef = database.getReference("TherapistInfo").child(therapistPhone);

            // Read data from the database
            therapistRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Retrieve therapist data from the database
                    Therapist therapist = dataSnapshot.getValue(Therapist.class);
                    if (therapist != null) {
                        // Set EditText fields with therapist information
                        emailEditText.setText(therapist.getEmail());
                        fullNameEditText.setText(therapist.getFullName());
                        genderEditText.setText(therapist.getGender());
                        phoneEditText.setText(therapist.getPhoneNumber());
                        specializationEditText.setText(therapist.getSpecialization());
                        passwordEditText.setText(therapist.getPassword());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                    Log.e("Firebase", "Error reading therapist data", databaseError.toException());
                }
            });
        }
    }
}
