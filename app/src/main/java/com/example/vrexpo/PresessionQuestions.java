package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PresessionQuestions extends AppCompatActivity {

    private static final String TAG = "PresessionQuestions";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PresessionQuestions.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent zoom = new Intent(PresessionQuestions.this, ZegoCloudHomePatient.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PresessionQuestions.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(PresessionQuestions.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PresessionQuestions.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(PresessionQuestions.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presession_questions);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button submitBtn = findViewById(R.id.submitBtn);

        if (submitBtn == null) {
            Log.e(TAG, "Submit button is null");
        } else {
            submitBtn.setOnClickListener(view -> {
                Log.d(TAG, "Submit button clicked");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user == null) {
                    Toast.makeText(PresessionQuestions.this, "No user is logged in.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "No user logged in");
                } else {
                    String email = user.getEmail();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PatientAccount");

                    reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (!snapshot.exists()) {
                                Toast.makeText(PresessionQuestions.this, "User not found.", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "User not found in database");
                                return;
                            }

                            for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                String currentPhoneNumber = childSnapshot.getKey();
                                Log.d(TAG, "Current phone number: " + currentPhoneNumber);

                                String dateTimeKey = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new java.util.Date());
                                String answerOne = ((EditText) findViewById(R.id.editTextTextMultiLine2)).getText().toString();
                                String answerTwo = ((EditText) findViewById(R.id.editTextTextMultiLine3)).getText().toString();
                                String answerThree = ((EditText) findViewById(R.id.editTextTextMultiLine4)).getText().toString();

                                // Check if any of the answers is empty
                                if (answerOne.isEmpty() || answerTwo.isEmpty() || answerThree.isEmpty()) {
                                    Toast.makeText(PresessionQuestions.this, "Please fill in all fields before submitting.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                // Save session data in Firebase
                                Map<String, String> sessionData = new HashMap<>();
                                sessionData.put("Question1", answerOne);
                                sessionData.put("Question2", answerTwo);
                                sessionData.put("Question3", answerThree);

                                assert currentPhoneNumber != null;
                                reference.child(currentPhoneNumber).child("Sessions").child(dateTimeKey).setValue(sessionData)
                                        .addOnSuccessListener(aVoid -> {
                                            Log.d(TAG, "Answers submitted successfully");
                                            Toast.makeText(PresessionQuestions.this, "Answers submitted successfully!", Toast.LENGTH_SHORT).show();

                                            // Store data in SharedPreferences
                                            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("Question1", answerOne);
                                            editor.putString("Question2", answerTwo);
                                            editor.putString("Question3", answerThree);
                                            editor.apply();

                                            Log.d("Question 1: ", answerOne);
                                            Log.d("Question 2: ", answerTwo);
                                            Log.d("Question 3: ", answerThree);

                                            Intent sessionIntent = new Intent(PresessionQuestions.this, ZegoCloudHomePatient.class);
                                            startActivity(sessionIntent);
                                        })

                                        .addOnFailureListener(e -> {
                                            Log.e(TAG, "Failed to submit answers", e);
                                            Toast.makeText(PresessionQuestions.this, "Failed to submit answers.", Toast.LENGTH_SHORT).show();
                                        });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e(TAG, "Database error", error.toException());
                            Toast.makeText(PresessionQuestions.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}


