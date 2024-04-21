package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PatientHistory extends AppCompatActivity {

    private RadioGroup rgMedicalCondition, rgPsychologicalCondition, rgPhobia, rgPTSD, rgDuration, rgImpact;
    private Button submitButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PatientHistory.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent zoom = new Intent(PatientHistory.this, Zoom.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PatientHistory.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(PatientHistory.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PatientHistory.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_messages:
                Intent messages = new Intent(PatientHistory.this, PatientMessages.class);
                startActivity(messages);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(PatientHistory.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        initializeRadioGroups();
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> submitPatientHistory());

        fetchAndPopulateData();
    }

    private void initializeRadioGroups() {
        rgMedicalCondition = findViewById(R.id.radioGroup);
        rgPsychologicalCondition = findViewById(R.id.radioGroup2);
        rgPhobia = findViewById(R.id.radioGroup3);
        rgPTSD = findViewById(R.id.radioGroup4);
        rgDuration = findViewById(R.id.radioGroup5);
        rgImpact = findViewById(R.id.radioGroup6);
    }

    private void fetchAndPopulateData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("PatientAccount");
            ref.orderByChild("email").equalTo(user.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String currentPhoneNumber = childSnapshot.getKey();
                            DatabaseReference patientHistoryRef = FirebaseDatabase.getInstance().getReference("PatientAccount")
                                    .child(currentPhoneNumber).child("PatientHistory");

                            patientHistoryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot historySnapshot) {
                                    if (historySnapshot.exists()) {
                                        populateData(historySnapshot);
                                        disableRadioGroups();
                                        submitButton.setEnabled(false);
                                        submitButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gray_out));
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(PatientHistory.this, "Failed to load patient history data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(PatientHistory.this, "User record not found.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(PatientHistory.this, "Database error on user lookup: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(this, "No user is logged in.", Toast.LENGTH_SHORT).show();
        }
    }


    private void populateData(DataSnapshot snapshot) {
        setRadioGroupSelection(rgMedicalCondition, snapshot.child("medicalCondition").getValue(String.class));
        setRadioGroupSelection(rgPsychologicalCondition, snapshot.child("psychologicalCondition").getValue(String.class));
        setRadioGroupSelection(rgPhobia, snapshot.child("phobia").getValue(String.class));
        setRadioGroupSelection(rgPTSD, snapshot.child("ptsd").getValue(String.class));
        setRadioGroupSelection(rgDuration, snapshot.child("duration").getValue(String.class));
        setRadioGroupSelection(rgImpact, snapshot.child("impact").getValue(String.class));
    }


    private void disableRadioGroups() {
        disableRadioGroup(rgMedicalCondition);
        disableRadioGroup(rgPsychologicalCondition);
        disableRadioGroup(rgPhobia);
        disableRadioGroup(rgPTSD);
        disableRadioGroup(rgDuration);
        disableRadioGroup(rgImpact);
    }

    private void disableRadioGroup(RadioGroup radioGroup) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(false);
        }
    }

    private void setRadioGroupSelection(RadioGroup radioGroup, String value) {
        if (value == null) return; // If the value is null, do nothing.
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
            if (rb.getText().toString().equals(value)) {
                rb.setChecked(true);
                break;
            }
        }
    }

    private void submitPatientHistory() {
        // Retrieve selections from each RadioGroup
        String medicalCondition = getSelectedRadioButtonText(rgMedicalCondition);
        String psychologicalCondition = getSelectedRadioButtonText(rgPsychologicalCondition);
        String phobia = getSelectedRadioButtonText(rgPhobia);
        String ptsd = getSelectedRadioButtonText(rgPTSD);
        String duration = getSelectedRadioButtonText(rgDuration);
        String impact = getSelectedRadioButtonText(rgImpact);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && user.getEmail() != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PatientAccount");
            reference.orderByChild("email").equalTo(user.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String currentPhoneNumber = childSnapshot.getKey();

                            DatabaseReference patientHistoryRef = reference.child(currentPhoneNumber).child("PatientHistory");
                            Map<String, String> data = new HashMap<>();
                            data.put("medicalCondition", medicalCondition);
                            data.put("psychologicalCondition", psychologicalCondition);
                            data.put("phobia", phobia);
                            data.put("ptsd", ptsd);
                            data.put("duration", duration);
                            data.put("impact", impact);

                            patientHistoryRef.setValue(data).addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PatientHistory.this, "History saved successfully!", Toast.LENGTH_SHORT).show();
                                    disableRadioGroups();
                                    submitButton.setEnabled(false);
                                    submitButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gray_out));
                                } else {
                                    Toast.makeText(PatientHistory.this, "Failed to save history!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(PatientHistory.this, "User record not found.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(PatientHistory.this, "Database error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(this, "No user is logged in or email is not available.", Toast.LENGTH_SHORT).show();
        }
    }

    private String getSelectedRadioButtonText(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        return radioButton != null ? radioButton.getText().toString() : "N/A";  // Returns "N/A" if no button is selected
    }
}
