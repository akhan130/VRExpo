package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

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

    private ToggleButton toggleButton;
    private RelativeLayout preSessionContainer, postSessionContainer;

    Button btnDatePicker;
    EditText txtDate;
    private int mYear, mMonth, mDay;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_questions);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        preSessionContainer = findViewById(R.id.presessionContainer);
        postSessionContainer = findViewById(R.id.postsessionContainer);
        toggleButton = findViewById(R.id.toggleButton);

        btnDatePicker = findViewById(R.id.dateButton);
        txtDate = findViewById(R.id.dateText);
        btnDatePicker.setOnClickListener(this);


        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Post-session questions visible
                postSessionContainer.setVisibility(View.VISIBLE);
                preSessionContainer.setVisibility(View.GONE);
            } else {
                // Pre-session questions visible
                preSessionContainer.setVisibility(View.VISIBLE);
                postSessionContainer.setVisibility(View.GONE);
            }
        });
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
                        // Formatting the date as per Firebase keys
                        String formattedDate = String.format(Locale.getDefault(), "%02d_%02d_%d", monthOfYear + 1, dayOfMonth, year);
                        loadDataForDate(formattedDate);

                        // Update the EditText to show the date
                        String userFormattedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", monthOfYear + 1, dayOfMonth, year);
                        txtDate.setText(userFormattedDate);
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }

    private void loadDataForDate(String date) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference usersRef = database.getReference("PatientAccount");

            // First, fetch the user's phone number using their email.
            usersRef.orderByChild("email").equalTo(user.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                            String phoneNumber = userSnapshot.getKey();
                            DatabaseReference dateRef = database.getReference("PatientAccount")
                                    .child(phoneNumber).child("Pre-Session Questions").child(date);

                            dateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot questionSnapshot) {
                                    if (questionSnapshot.exists()) {
                                        String answerQ1 = questionSnapshot.child("Q1 - What do you expect?").getValue(String.class);
                                        String answerQ2 = questionSnapshot.child("Q2 - Notice any differences?").getValue(String.class);
                                        String answerQ3 = questionSnapshot.child("Q3 - How has VRExpo helped?").getValue(String.class);

                                        EditText preResponseQ1 = findViewById(R.id.preResponseQ1);
                                        EditText preResponseQ2 = findViewById(R.id.preResponseQ2);
                                        EditText preResponseQ3 = findViewById(R.id.preResponseQ3);

                                        preResponseQ1.setText(answerQ1);
                                        preResponseQ2.setText(answerQ2);
                                        preResponseQ3.setText(answerQ3);
                                    } else {
                                        // Reset the fields if no data is present on the selected date
                                        EditText preResponseQ1 = findViewById(R.id.preResponseQ1);
                                        EditText preResponseQ2 = findViewById(R.id.preResponseQ2);
                                        EditText preResponseQ3 = findViewById(R.id.preResponseQ3);

                                        preResponseQ1.setText("");
                                        preResponseQ2.setText("");
                                        preResponseQ3.setText("");

                                        Toast.makeText(PatientRecords.this, "No data for selected date", Toast.LENGTH_SHORT).show();                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(PatientRecords.this, "Failed to load question data", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    } else {
                        Toast.makeText(PatientRecords.this, "No data for selected date", Toast.LENGTH_SHORT).show();
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


}