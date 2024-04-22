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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AdminViewPatientDetails extends AppCompatActivity {

    private EditText nameEditText, emailEditText, phoneEditText, passwordEditText, dobEditText, addressEditText, genderEditText;

    Button cancelButton, updateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_patient_details);

        // Initialize EditTexts
        nameEditText = findViewById(R.id.nameEditText);
        dobEditText = findViewById(R.id.dobEditText);
        addressEditText = findViewById(R.id.addressEditText);
        genderEditText = findViewById(R.id.genderEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        cancelButton = findViewById(R.id.cancelButton);
        updateButton = findViewById(R.id.updateButton);

        // Get the phone number passed from the AdminViewPatient activity
        String patientPhone = getIntent().getStringExtra("phone");

        // Reference to the therapist's data in Firebase using the phone number as the key
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PatientAccount").child(patientPhone);

        // Fetch the patient's details
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    PatientModel patient = dataSnapshot.getValue(PatientModel.class);
                    if (patient != null) {
                        nameEditText.setText(patient.getName());
                        dobEditText.setText(patient.getDob());
                        addressEditText.setText(patient.getAddress());
                        genderEditText.setText(patient.getGender());
                        phoneEditText.setText(patient.getPhone());
                        emailEditText.setText(patient.getEmail());
                        passwordEditText.setText(patient.getPassword());
                    }
                } else {
                    // Handle the case where the therapist data does not exist
                    Toast.makeText(AdminViewPatientDetails.this, "No data available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminViewPatientDetails.this, "Failed to load data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(AdminViewPatientDetails.this, AdminViewPatient.class);
                startActivity(intent);
            }
        });
    }

    private void updateProfile() {
        String name = nameEditText.getText().toString();
        String dob = dobEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String gender = genderEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Get the phone number passed from the AdminViewPatient activity
        String therapistPhone = getIntent().getStringExtra("phone");

        // Reference to the therapist's data in Firebase using the phone number as the key
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PatientAccount").child(therapistPhone);

        // Create a Map to hold the updated values
        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("name", name);
        updateValues.put("dob", dob);
        updateValues.put("address", address);
        updateValues.put("gender", gender);
        updateValues.put("phone", phone);
        updateValues.put("email", email);
        updateValues.put("password", password);

        // Update the child fields of the patient in Firebase
        databaseReference.updateChildren(updateValues)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AdminViewPatientDetails.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AdminViewPatientDetails.this, "Failed to update profile: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
