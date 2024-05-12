package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TherapistInfo extends AppCompatActivity {

    private EditText emailEditText, fullNameEditText, genderEditText, phoneEditText, specializationEditText, passwordEditText;
    private Button updateProfileButton;
    private Button cancelButton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent dashIntent = new Intent(TherapistInfo.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(TherapistInfo.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(TherapistInfo.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(TherapistInfo.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(TherapistInfo.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(TherapistInfo.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(TherapistInfo.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(TherapistInfo.this, ZegoCloudHome.class);
                startActivity(zoom);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_info);

        // Initialize EditText fields
        emailEditText = findViewById(R.id.Email);
        fullNameEditText = findViewById(R.id.FullName);
        genderEditText = findViewById(R.id.Gender);
        phoneEditText = findViewById(R.id.Phone);
        specializationEditText = findViewById(R.id.Specialization);
        passwordEditText = findViewById(R.id.Password);
        updateProfileButton = findViewById(R.id.update_button);
        cancelButton = findViewById(R.id.cancel_button);

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TherapistInfo.this, TherapistAccountSettings.class);
                startActivity(intent);
            }
        });

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            DatabaseReference reference = database.getReference("TherapistInfo");

            reference.orderByChild("therapist_email").equalTo(currentUser.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String currentPhoneNumber = childSnapshot.getKey();
                            DatabaseReference patientReference = database.getReference("TherapistInfo").child(currentPhoneNumber);
                            patientReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        String name = snapshot.child("therapist_fullName").getValue(String.class);
                                        String email = snapshot.child("therapist_email").getValue(String.class);
                                        String password = snapshot.child("therapist_password").getValue(String.class);
                                        String phone = snapshot.child("therapist_phoneNumber").getValue(String.class);
                                        String gender = snapshot.child("therapist_gender").getValue(String.class);
                                        String specialization = snapshot.child("therapist_specialization").getValue(String.class);

                                        emailEditText.setText(email);
                                        fullNameEditText.setText(name);
                                        genderEditText.setText(gender);
                                        phoneEditText.setText(phone);
                                        specializationEditText.setText(specialization);
                                        passwordEditText.setText(password);

                                    } else {
                                        Toast.makeText(TherapistInfo.this, "No profile found", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(TherapistInfo.this, "Failed to read data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(TherapistInfo.this, "No profile found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(TherapistInfo.this, "Failed to read data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // User is not logged in
            // Handle this case, e.g., redirect to login screen
        }
    }

    private void updateProfile() {
        String email = emailEditText.getText().toString();
        String name = fullNameEditText.getText().toString();
        String gender = genderEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String specialization = specializationEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            DatabaseReference reference = database.getReference("TherapistInfo");
            reference.orderByChild("therapist_email").equalTo(user.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String currentPhoneNumber = childSnapshot.getKey();
                            DatabaseReference patientReference = database.getReference("TherapistInfo").child(currentPhoneNumber);

                            patientReference.child("therapist_fullName").setValue(name);
                            patientReference.child("therapist_specialization").setValue(specialization);
                            patientReference.child("therapist_phoneNumber").setValue(phone);
                            patientReference.child("therapist_email").setValue(email);
                            patientReference.child("therapist_password").setValue(password);
                            patientReference.child("therapist_gender").setValue(gender)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(TherapistInfo.this, "Updated", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(TherapistInfo.this, "Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(TherapistInfo.this, "No profile found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(TherapistInfo.this, "Failed to read data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // User is not logged in
            // Handle this case, e.g., redirect to login screen
        }
    }

}
