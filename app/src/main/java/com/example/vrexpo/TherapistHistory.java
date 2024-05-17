package com.example.vrexpo;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TherapistHistory extends AppCompatActivity {

    private Button updateButton, cancelButton;
    private EditText certificateET, degreeET, educationET, workHistoryET;
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
                Intent dashIntent = new Intent(TherapistHistory.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(TherapistHistory.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(TherapistHistory.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(TherapistHistory.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(TherapistHistory.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(TherapistHistory.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(TherapistHistory.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(TherapistHistory.this, TherapistZegoCloudHome.class);
                startActivity(zoom);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_history);

        // Initialize EditText fields
        certificateET = findViewById(R.id.Certificates);
        degreeET = findViewById(R.id.Degree);
        educationET = findViewById(R.id.Education);
        workHistoryET = findViewById(R.id.WorkHistory);

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
                Intent intent = new Intent(TherapistHistory.this, TherapistAccountSettings.class);
                startActivity(intent);
            }
        });

        // Load existing therapist history
        loadTherapistHistory();

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    //Submits Info
    private void updateProfile() {

        if (isEmpty(certificateET) || isEmpty(degreeET) || isEmpty(educationET) ||
                isEmpty(workHistoryET)) {
            Toast.makeText(this, "Please fill in all fields before submitting.", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            processSubmission(
                    certificateET.getText().toString().trim(),
                    degreeET.getText().toString().trim(),
                    educationET.getText().toString().trim(),
                    workHistoryET.getText().toString().trim()
            );
        } else {
            Toast.makeText(this, "No user is logged in.", Toast.LENGTH_SHORT).show();
        }
    }

    private void processSubmission(String certificate, String degree, String education, String workHistory) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TherapistInfo");

            reference.orderByChild("therapist_email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String currentPhoneNumber = childSnapshot.getKey();

                            Map<String, String> sessionData = new HashMap<>();
                            sessionData.put("Certificate", certificate);
                            sessionData.put("Degree", degree);
                            sessionData.put("Education", education);
                            sessionData.put("WorkHistory", workHistory);

                            reference.child(currentPhoneNumber).child("TherapistHistory").setValue(sessionData)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(TherapistHistory.this, "History submitted successfully!", Toast.LENGTH_SHORT).show();
                                        Intent sessionIntent = new Intent(TherapistHistory.this, TherapistHistory.class);
                                        startActivity(sessionIntent);
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(TherapistHistory.this, "Failed to submit.", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    } else {
                        Toast.makeText(TherapistHistory.this, "User not found.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(TherapistHistory.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(TherapistHistory.this, "No user is logged in.", Toast.LENGTH_SHORT).show();
        }
}

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private void loadTherapistHistory() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TherapistInfo");

            reference.orderByChild("therapist_email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            DataSnapshot historySnapshot = childSnapshot.child("TherapistHistory");

                            // Retrieve and populate data into EditText fields
                            String certificate = historySnapshot.child("Certificate").getValue(String.class);
                            String degree = historySnapshot.child("Degree").getValue(String.class);
                            String education = historySnapshot.child("Education").getValue(String.class);
                            String workHistory = historySnapshot.child("WorkHistory").getValue(String.class);

                            certificateET.setText(certificate);
                            degreeET.setText(degree);
                            educationET.setText(education);
                            workHistoryET.setText(workHistory);
                        }
                    } else {
                        Toast.makeText(TherapistHistory.this, "Therapist history not found.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(TherapistHistory.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No user is logged in.", Toast.LENGTH_SHORT).show();
        }
    }

}
