package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

public class PatientUpdateAccount extends AppCompatActivity {

    EditText etName, etDOB, etPhone, etAddress, etEmail, etPassword, etGender;
    Button updateButton;

    Button cancelButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PatientUpdateAccount.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent zoom = new Intent(PatientUpdateAccount.this, ZegoCloudHomePatient.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PatientUpdateAccount.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(PatientUpdateAccount.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PatientUpdateAccount.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(PatientUpdateAccount.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_update_account);

        etName = findViewById(R.id.patient_name);
        etDOB = findViewById(R.id.patient_dob);
        etPhone = findViewById(R.id.patient_phone);
        etAddress = findViewById(R.id.patient_address);
        etEmail = findViewById(R.id.patient_email);
        etPassword = findViewById(R.id.patient_password);
        etGender = findViewById(R.id.patient_gender);
        updateButton = findViewById(R.id.update_button);
        cancelButton = findViewById(R.id.cancel_button);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientUpdateAccount.this, PatientSettings.class);
                startActivity(intent);
            }
        });

    }

    // Allow Patient data to be viewed before editing.
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference reference = database.getReference("PatientAccount");
            reference.orderByChild("email").equalTo(user.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String currentPhoneNumber = childSnapshot.getKey();
                            DatabaseReference patientReference = database.getReference("PatientAccount").child(currentPhoneNumber);
                            patientReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        String name = snapshot.child("name").getValue(String.class);
                                        String dob = snapshot.child("dob").getValue(String.class);
                                        String phone = snapshot.child("phone").getValue(String.class);
                                        String address = snapshot.child("address").getValue(String.class);
                                        String email = snapshot.child("email").getValue(String.class);
                                        String password = snapshot.child("password").getValue(String.class);
                                        String gender = snapshot.child("gender").getValue(String.class);

                                        etName.setText(name);
                                        etDOB.setText(dob);
                                        etPhone.setText(phone);
                                        etAddress.setText(address);
                                        etEmail.setText(email);
                                        etPassword.setText(password);
                                        etGender.setText(gender);
                                    } else {
                                        Toast.makeText(PatientUpdateAccount.this, "No profile found", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(PatientUpdateAccount.this, "Failed to read data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(PatientUpdateAccount.this, "No profile found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(PatientUpdateAccount.this, "Failed to read data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // User is not logged in
            // Handle this case, e.g., redirect to login screen
        }
    }

    private void updateProfile() {
        String name = etName.getText().toString();
        String dob = etDOB.getText().toString();
        String phone = etPhone.getText().toString();
        String address = etAddress.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String gender = etGender.getText().toString();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference reference = database.getReference("PatientAccount");
            reference.orderByChild("email").equalTo(user.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String currentPhoneNumber = childSnapshot.getKey();
                            DatabaseReference patientReference = database.getReference("PatientAccount").child(currentPhoneNumber);

                            patientReference.child("name").setValue(name);
                            patientReference.child("dob").setValue(dob);
                            patientReference.child("phone").setValue(phone);
                            patientReference.child("address").setValue(address);
                            patientReference.child("email").setValue(email);
                            patientReference.child("password").setValue(password);
                            patientReference.child("gender").setValue(gender)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(PatientUpdateAccount.this, "Updated", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(PatientUpdateAccount.this, "Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(PatientUpdateAccount.this, "No profile found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(PatientUpdateAccount.this, "Failed to read data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // User is not logged in
            // Handle this case, e.g., redirect to login screen
        }
    }
}
