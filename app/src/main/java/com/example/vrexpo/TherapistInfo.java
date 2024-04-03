package com.example.vrexpo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TherapistInfo extends AppCompatActivity {

    private EditText emailEditText, fullNameEditText, genderEditText,
            phoneEditText, specializationEditText, passwordEditText;

    private Button updateButton;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_info);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Therapist").child(userId);

        emailEditText = findViewById(R.id.Email);
        fullNameEditText = findViewById(R.id.FullName);
        genderEditText = findViewById(R.id.Gender);
        phoneEditText = findViewById(R.id.Phone);
        specializationEditText = findViewById(R.id.Specialization);
        passwordEditText = findViewById(R.id.Password);

        updateButton = findViewById(R.id.update_button);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update user profile logic here
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Therapist therapist = dataSnapshot.getValue(Therapist.class);
                if (therapist != null) {
                    emailEditText.setText(therapist.getEmail());
                    fullNameEditText.setText(therapist.getFullName());
                    phoneEditText.setText(therapist.getPhoneNumber());
                    genderEditText.setText(therapist.getGender());
                    specializationEditText.setText(therapist.getSpecialization());
                    passwordEditText.setText(therapist.getPassword());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}
