package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AdminViewTherapistDetails extends AppCompatActivity {

    private EditText nameEditText, emailEditText, phoneEditText, specializationEditText, passwordEditText;

    Button cancelButton, updateButton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_therapist_details);

        // Initialize EditTexts
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        specializationEditText = findViewById(R.id.specializationEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        cancelButton = findViewById(R.id.cancelButton);
        updateButton = findViewById(R.id.updateButton);

        // Get the phone number passed from the AdminViewTherapist activity
        String therapistPhone = getIntent().getStringExtra("therapist_phone");

        // Reference to the therapist's data in Firebase using the phone number as the key
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TherapistInfo").child(therapistPhone);

        // Fetch the therapist's details
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Therapist therapist = dataSnapshot.getValue(Therapist.class);
                    if (therapist != null) {
                        nameEditText.setText(therapist.getFullName());
                        emailEditText.setText(therapist.getEmail());
                        phoneEditText.setText(therapist.getPhoneNumber());
                        specializationEditText.setText(therapist.getSpecialization());
                        passwordEditText.setText(therapist.getPassword());
                    }
                } else {
                    // Handle the case where the therapist data does not exist
                    Toast.makeText(AdminViewTherapistDetails.this, "No data available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminViewTherapistDetails.this, "Failed to load data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminViewTherapistDetails.this, AdminViewTherapist.class);
                startActivity(intent);
            }
        });
    }

    private void updateProfile() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String specialization = specializationEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Get the phone number passed from the AdminViewTherapist activity
        String therapistPhone = getIntent().getStringExtra("therapist_phone");

        // Reference to the therapist's data in Firebase using the phone number as the key
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TherapistInfo").child(therapistPhone);

        // Create a Map to hold the updated values
        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("therapist_fullName", name);
        updateValues.put("therapist_specialization", specialization);
        updateValues.put("therapist_phoneNumber", phone);
        updateValues.put("therapist_email", email);
        updateValues.put("therapist_password", password);

        // Update the child fields of the therapist in Firebase
        databaseReference.updateChildren(updateValues)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AdminViewTherapistDetails.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AdminViewTherapistDetails.this, "Failed to update profile: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
