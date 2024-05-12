package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Locale;

public class PatientRecords extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout preSessionContainer, postSessionContainer;
    Button btnDatePicker;
    EditText txtDate;
    private int mYear, mMonth, mDay;
    private Switch toggleButton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PatientRecords.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent zoom = new Intent(PatientRecords.this, SessionStart.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PatientRecords.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(PatientRecords.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PatientRecords.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_messages:
                Intent messages = new Intent(PatientRecords.this, PatientMessages.class);
                startActivity(messages);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(PatientRecords.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_questions);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        preSessionContainer = findViewById(R.id.presessionContainer);
        postSessionContainer = findViewById(R.id.postsessionContainer);
        toggleButton = findViewById(R.id.viewQuestionButton);

        btnDatePicker = findViewById(R.id.dateButton);
        txtDate = findViewById(R.id.dateText);
        btnDatePicker.setOnClickListener(this);


        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switchContainers(isChecked);
            if (!txtDate.getText().toString().isEmpty()) {
                loadDataForDate(txtDate.getText().toString(), isChecked);
            }
        });
    }

    private void switchContainers(boolean isPostSession) {
        if (isPostSession) {
            postSessionContainer.setVisibility(View.VISIBLE);
            preSessionContainer.setVisibility(View.GONE);
        } else {
            preSessionContainer.setVisibility(View.VISIBLE);
            postSessionContainer.setVisibility(View.GONE);
        }
    }

    public void onClick(View v) {
        if (v == btnDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        String formattedDate = String.format(Locale.getDefault(), "%02d_%02d_%d", monthOfYear + 1, dayOfMonth, year);
                        String userFormattedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", monthOfYear + 1, dayOfMonth, year);
                        txtDate.setText(userFormattedDate);
                        loadDataForDate(formattedDate, toggleButton.isChecked());
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }

    private void loadDataForDate(String date, boolean checked) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference usersRef = database.getReference("PatientAccount");

            // Fetch the user's phone number using their email.
            usersRef.orderByChild("email").equalTo(user.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                            String phoneNumber = userSnapshot.getKey();  // Extracted phone number

                            // Determine which session type to query based on the toggle state
                            String sessionType = toggleButton.isChecked() ? "Post-Session Questions" : "Pre-Session Questions";
                            DatabaseReference dateRef = usersRef.child(phoneNumber).child(sessionType).child(date);

                            dateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot questionSnapshot) {
                                    if (questionSnapshot.exists()) {
                                        // Update UI with the fetched data
                                        updateUIWithSessionData(questionSnapshot, sessionType);
                                    } else {
                                        clearSessionFields();  // Reset the fields if no data is present
                                        Toast.makeText(PatientRecords.this, "No data for selected date", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(PatientRecords.this, "Failed to load question data", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(PatientRecords.this, "No user data found for the email", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(PatientRecords.this, "Failed to load user data", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(PatientRecords.this, "User not signed in", Toast.LENGTH_SHORT).show();
        }
    }

    // Use this method if updateUIWithSessionData indeed needs a String for the second parameter.
    private void updateUIWithSessionData(DataSnapshot questionSnapshot, String sessionType) {
        if (sessionType.equals("Pre-Session Questions")) {

            EditText preResponseQ1 = findViewById(R.id.preResponseQ1);
            EditText preResponseQ2 = findViewById(R.id.preResponseQ2);
            EditText preResponseQ3 = findViewById(R.id.preResponseQ3);

            preResponseQ1.setText(questionSnapshot.child("Q1 - What do you expect?").getValue(String.class));
            preResponseQ2.setText(questionSnapshot.child("Q2 - Notice any differences?").getValue(String.class));
            preResponseQ3.setText(questionSnapshot.child("Q3 - How has VRExpo helped?").getValue(String.class));

        } else if (sessionType.equals("Post-Session Questions")) {
            EditText postResponseQ1 = findViewById(R.id.postResponseQ1);
            EditText postResponseQ2 = findViewById(R.id.postResponseQ2);
            EditText postResponseQ3 = findViewById(R.id.postResponseQ3);
            EditText postResponseQ4 = findViewById(R.id.postResponseQ4);
            EditText postResponseQ5 = findViewById(R.id.postResponseQ5);
            EditText postResponseQ6 = findViewById(R.id.postResponseQ6);
            EditText postResponseQ7 = findViewById(R.id.postResponseQ7);
            EditText postResponseQ8 = findViewById(R.id.postResponseQ8);

            postResponseQ1.setText(questionSnapshot.child("Comfort").getValue(String.class));
            postResponseQ2.setText(questionSnapshot.child("Immersion").getValue(String.class));
            postResponseQ3.setText(questionSnapshot.child("Helpful or Harmful").getValue(String.class));
            postResponseQ4.setText(questionSnapshot.child("Expectations").getValue(String.class));
            postResponseQ5.setText(questionSnapshot.child("Difficult Components").getValue(String.class));
            postResponseQ6.setText(questionSnapshot.child("Modifications").getValue(String.class));
            postResponseQ7.setText(questionSnapshot.child("Ratings").getValue(String.class));
            postResponseQ8.setText(questionSnapshot.child("Additional Feedback").getValue(String.class));
        }
    }

//    private void updateUIWithSessionData(DataSnapshot questionSnapshot, boolean isPostSession) {
//        if (isPostSession) {
//            // Update post-session fields
//            EditText postResponseQ1 = findViewById(R.id.postResponseQ1);
//            EditText postResponseQ2 = findViewById(R.id.postResponseQ2);
//            EditText postResponseQ3 = findViewById(R.id.postResponseQ3);
//            EditText postResponseQ4 = findViewById(R.id.postResponseQ4);
//            EditText postResponseQ5 = findViewById(R.id.postResponseQ5);
//            EditText postResponseQ6 = findViewById(R.id.postResponseQ6);
//            EditText postResponseQ7 = findViewById(R.id.postResponseQ7);
//            EditText postResponseQ8 = findViewById(R.id.postResponseQ8);
//
//            postResponseQ1.setText(questionSnapshot.child("Comfort").getValue(String.class));
//            postResponseQ2.setText(questionSnapshot.child("Immersion").getValue(String.class));
//            postResponseQ3.setText(questionSnapshot.child("Helpful or Harmful").getValue(String.class));
//            postResponseQ4.setText(questionSnapshot.child("Expectations").getValue(String.class));
//            postResponseQ5.setText(questionSnapshot.child("Difficult Components").getValue(String.class));
//            postResponseQ6.setText(questionSnapshot.child("Modifications").getValue(String.class));
//            postResponseQ7.setText(questionSnapshot.child("Ratings").getValue(String.class));
//            postResponseQ8.setText(questionSnapshot.child("Additional Feedback").getValue(String.class));
//        } else {
//            // Update pre-session fields
//            EditText preResponseQ1 = findViewById(R.id.preResponseQ1);
//            EditText preResponseQ2 = findViewById(R.id.preResponseQ2);
//            EditText preResponseQ3 = findViewById(R.id.preResponseQ3);
//
//            preResponseQ1.setText(questionSnapshot.child("Q1 - What do you expect?").getValue(String.class));
//            preResponseQ2.setText(questionSnapshot.child("Q2 - Notice any differences?").getValue(String.class));
//            preResponseQ3.setText(questionSnapshot.child("Q3 - How has VRExpo helped?").getValue(String.class));
//        }
//    }

    private void clearSessionFields() {
        // Clear all relevant fields
        EditText preResponseQ1 = findViewById(R.id.preResponseQ1);
        EditText preResponseQ2 = findViewById(R.id.preResponseQ2);
        EditText preResponseQ3 = findViewById(R.id.preResponseQ3);
        EditText postResponseQ1 = findViewById(R.id.postResponseQ1); // Assume these IDs are correct

        preResponseQ1.setText("");
        preResponseQ2.setText("");
        preResponseQ3.setText("");
        postResponseQ1.setText(""); // Clear all fields
        // Repeat for all necessary fields
    }
}