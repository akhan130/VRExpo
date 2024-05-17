package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PostsessionQuestions extends AppCompatActivity {

    Button submitBtn;
    private static final String TAG = "PostsessionQuestions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postsession_questions);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        submitBtn = findViewById(R.id.submitButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dashboard:
                startActivity(new Intent(PostsessionQuestions.this, Dashboard.class));
                return true;
            case R.id.action_sessionStart:
                startActivity(new Intent(PostsessionQuestions.this, ZegoCloudHome.class));
                return true;
            case R.id.action_accountInfo:
                startActivity(new Intent(PostsessionQuestions.this, AccountInfo.class));
                return true;
            case R.id.action_appointments:
                startActivity(new Intent(PostsessionQuestions.this, PatientAppointments.class));
                return true;
            case R.id.action_find_therapist:
                startActivity(new Intent(PostsessionQuestions.this, FindTherapist.class));
                return true;
            case R.id.action_patient_settings:
                startActivity(new Intent(PostsessionQuestions.this, PatientSettings.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void submitData() {
        EditText editTextComfort = findViewById(R.id.editTextTextMultiLine2);
        EditText editTextImmersion = findViewById(R.id.editTextTextMultiLine3);
        EditText editTextHelpfulHarmful = findViewById(R.id.editTextTextMultiLine4);
        EditText editTextExpectations = findViewById(R.id.editTextTextMultiLine5);
        EditText editTextDifficultComponents = findViewById(R.id.editTextTextMultiLine6);
        EditText editTextModifications = findViewById(R.id.editTextTextMultiLine7);
        EditText editTextRating = findViewById(R.id.editTextTextMultiLine8);
        EditText editTextAdditionalFeedback = findViewById(R.id.editTextTextMultiLine9);

        if (isEmpty(editTextComfort) || isEmpty(editTextImmersion) || isEmpty(editTextHelpfulHarmful) ||
                isEmpty(editTextExpectations) || isEmpty(editTextDifficultComponents) || isEmpty(editTextModifications) ||
                isEmpty(editTextRating) || isEmpty(editTextAdditionalFeedback)) {
            Toast.makeText(this, "Please fill in all fields before submitting.", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            processSubmission(
                    editTextComfort.getText().toString().trim(),
                    editTextImmersion.getText().toString().trim(),
                    editTextHelpfulHarmful.getText().toString().trim(),
                    editTextExpectations.getText().toString().trim(),
                    editTextDifficultComponents.getText().toString().trim(),
                    editTextModifications.getText().toString().trim(),
                    editTextRating.getText().toString().trim(),
                    editTextAdditionalFeedback.getText().toString().trim()
            );
        } else {
            Toast.makeText(this, "No user is logged in.", Toast.LENGTH_SHORT).show();
        }
    }

    private void processSubmission(String comfort, String immersion, String helpfulHarmful, String expectations,
                                   String difficultComponents, String modifications, String rating, String additionalFeedback) {
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
                            sessionData.put("Comfort", comfort);
                            sessionData.put("Immersion", immersion);
                            sessionData.put("Helpful or Harmful", helpfulHarmful);
                            sessionData.put("Expectations", expectations);
                            sessionData.put("Difficult Components", difficultComponents);
                            sessionData.put("Modifications", modifications);
                            sessionData.put("Rating", rating);
                            sessionData.put("Additional Feedback", additionalFeedback);

                            reference.child(currentPhoneNumber).child("Post-Session Questions").child(dateTimeKey).setValue(sessionData)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(PostsessionQuestions.this, "Answers submitted successfully!", Toast.LENGTH_SHORT).show();
                                        Log.d(TAG, "Data submitted successfully");
                                        Intent sessionIntent = new Intent(PostsessionQuestions.this, AI_Analysis.class);
                                        sessionIntent.putExtra("Comfort", comfort);
                                        sessionIntent.putExtra("Immersion", immersion);
                                        sessionIntent.putExtra("HelpfulHarmful", helpfulHarmful);
                                        sessionIntent.putExtra("Expectations", expectations);
                                        sessionIntent.putExtra("DifficultComponents", difficultComponents);
                                        sessionIntent.putExtra("Modifications", modifications);
                                        sessionIntent.putExtra("Rating", rating);
                                        sessionIntent.putExtra("AdditionalFeedback", additionalFeedback);

                                        Log.d("Comfort: ", comfort);
                                        Log.d("Immersion: ", immersion);
                                        Log.d("HelpfulHarmful: ", helpfulHarmful);
                                        Log.d("Expectations: ", expectations);
                                        Log.d("DifficultComponents: ", difficultComponents);
                                        Log.d("Modifications: ", modifications);
                                        Log.d("Rating: ", rating);
                                        Log.d("AdditionalFeedback: ", additionalFeedback);

                                        startActivity(sessionIntent);
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(PostsessionQuestions.this, "Failed to submit answers.", Toast.LENGTH_SHORT).show();
                                        Log.e(TAG, "Failed to submit answers: " + e.getMessage());
                                    });
                        }
                    } else {
                        Toast.makeText(PostsessionQuestions.this, "User not found.", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "User not found");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(PostsessionQuestions.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Database error: " + error.getMessage());
                }
            });
        } else {
            Toast.makeText(PostsessionQuestions.this, "No user is logged in.", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "No user is logged in");
        }
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }
}
