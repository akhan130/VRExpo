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

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;


public class AdminViewPatientDetails extends AppCompatActivity {

    private EditText nameEditText, emailEditText, phoneEditText, passwordEditText, dobEditText, addressEditText, genderEditText;

    Button cancelButton, updateButton, deleteButton;
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
        deleteButton = findViewById(R.id.deleteButton);


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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
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
        String patientPhone = getIntent().getStringExtra("phone");

        // Reference to the therapist's data in Firebase using the phone number as the key
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PatientAccount").child(patientPhone);

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

    private void deletePatientAccount() {
        String patientPhone = getIntent().getStringExtra("phone");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PatientAccount").child(patientPhone);

        // Delete the patient account
        databaseReference.removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AdminViewPatientDetails.this, "Account deleted successfully!", Toast.LENGTH_SHORT).show();
                            // Redirect to the list of patients or home
                            Intent intent = new Intent(AdminViewPatientDetails.this, AdminViewPatient.class);
                            startActivity(intent);
                            finish();  // Close this activity
                        } else {
                            Toast.makeText(AdminViewPatientDetails.this, "Failed to delete account: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminViewPatientDetails.this);
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Are you sure you want to delete this account?");
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletePatientAccount();
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
