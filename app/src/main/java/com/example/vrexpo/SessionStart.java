package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Note: This class controls the Pre-Session Questions
 */

public class SessionStart extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(SessionStart.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent zoom = new Intent(SessionStart.this, ZegoCloudHomePatient.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(SessionStart.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(SessionStart.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(SessionStart.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(SessionStart.this, PatientSettings.class);
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
        if (submitBtn != null) {
            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText answerOneEditText = findViewById(R.id.editTextTextMultiLine2);
                    EditText answerTwoEditText = findViewById(R.id.editTextTextMultiLine3);
                    EditText answerThreeEditText = findViewById(R.id.editTextTextMultiLine4);

                    String answerOne = answerOneEditText.getText().toString().trim();
                    String answerTwo = answerTwoEditText.getText().toString().trim();
                    String answerThree = answerThreeEditText.getText().toString().trim();

                    if (answerOne.isEmpty() || answerTwo.isEmpty() || answerThree.isEmpty()) {
                        Toast.makeText(SessionStart.this, "Please fill in all fields before submitting.", Toast.LENGTH_LONG).show();
                    } else {
                        processSubmission(answerOne, answerTwo, answerThree);
                    }
                }
            });
        } else {
            Log.e("", "Submit button is null");
        }
    }

    private void processSubmission(String answerOne, String answerTwo, String answerThree) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PatientAccount");

            reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String currentPhoneNumber = childSnapshot.getKey();
                            String dateTimeKey = new SimpleDateFormat("MM_dd_yyyy", Locale.getDefault()).format(new java.util.Date());

                            Map<String, String> sessionData = new HashMap<>();
                            sessionData.put("Q1 - What do you expect?", answerOne);
                            sessionData.put("Q2 - Notice any differences?", answerTwo);
                            sessionData.put("Q3 - How has VRExpo helped?", answerThree);


                            reference.child(currentPhoneNumber).child("Pre-Session Questions").child(dateTimeKey).setValue(sessionData)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(SessionStart.this, "Answers submitted successfully!", Toast.LENGTH_SHORT).show();
                                        Intent sessionIntent = new Intent(SessionStart.this, ZegoCloudHomePatient.class);
                                        startActivity(sessionIntent);
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(SessionStart.this, "Failed to submit answers.", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    } else {
                        Toast.makeText(SessionStart.this, "User not found.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SessionStart.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(SessionStart.this, "No user is logged in.", Toast.LENGTH_SHORT).show();
        }
    }


}
