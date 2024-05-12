package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TherapistSetAvailability extends AppCompatActivity {

    private CalendarView calendarView;
    private Button sendAvailabilityButton;
    private String therapistFullName;
    private long selectedDate;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent dashIntent = new Intent(TherapistSetAvailability.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(TherapistSetAvailability.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(TherapistSetAvailability.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(TherapistSetAvailability.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(TherapistSetAvailability.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(TherapistSetAvailability.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(TherapistSetAvailability.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(TherapistSetAvailability.this, ZegoCloudHome.class);
                startActivity(zoom);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_availability);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        calendarView = findViewById(R.id.calendar_view);
        sendAvailabilityButton = findViewById(R.id.send_availability_button);

        fetchTherapistFullName();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth);
                selectedDate = selectedCalendar.getTimeInMillis();
            }
        });

        sendAvailabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> selectedTimeSlots = getSelectedTimeSlots();
                sendAvailabilityToFirebase(selectedDate, selectedTimeSlots);
            }
        });

        fetchTherapistFullName();
    }

    private void fetchTherapistFullName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && user.getEmail() != null) {
            String email = user.getEmail();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("TherapistInfo");

            ref.orderByChild("therapist_email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            therapistFullName = childSnapshot.child("therapist_fullName").getValue(String.class);
                            Log.d("TherapistName", "Therapist Full Name: " + therapistFullName);
                        }
                    } else {
                        Log.d("TherapistName", "Therapist not found.");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("TherapistName", "Error fetching therapist name: " + error.getMessage());
                }
            });
        }
    }

    private List<String> getSelectedTimeSlots() {
        List<String> selectedTimeSlots = new ArrayList<>();
        int[] checkBoxIds = {
                R.id.timeOne, R.id.timeTwo, R.id.timeThree, R.id.timeFour,
                R.id.timeFive, R.id.timeSix, R.id.timeSeven, R.id.timeEight
        };
        for (int id : checkBoxIds) {
            CheckBox checkBox = findViewById(id);
            if (checkBox.isChecked()) {
                selectedTimeSlots.add(checkBox.getText().toString());
            }
        }
        return selectedTimeSlots;
    }


    private void sendAvailabilityToFirebase(long selectedDate, List<String> selectedTimeSlots) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        String dateString = dateFormat.format(new Date(selectedDate));

        DatabaseReference appointmentsRef = FirebaseDatabase.getInstance().getReference("Appointments").child(dateString);

        if (therapistFullName == null || therapistFullName.isEmpty()) {
            Toast.makeText(this, "Unable to retrieve therapist name. Try again.", Toast.LENGTH_LONG).show();
            return;
        }

        for (String timeSlot : selectedTimeSlots) {
            DatabaseReference timeSlotRef = appointmentsRef.child(timeSlot);
            timeSlotRef.child("appointmentTime").setValue(timeSlot);
            timeSlotRef.child("therapist_fullName").setValue(therapistFullName);
            timeSlotRef.child("appointment_status").setValue("Available");
        }

        clearCheckboxes();
        Toast.makeText(this, "Availability sent successfully!", Toast.LENGTH_SHORT).show();
    }

    private void clearCheckboxes(){
        int[] checkBoxIds = {
                R.id.timeOne, R.id.timeTwo, R.id.timeThree, R.id.timeFour,
                R.id.timeFive, R.id.timeSix, R.id.timeSeven, R.id.timeEight
        };
        for (int id : checkBoxIds) {
            CheckBox checkBox = findViewById(id);
            checkBox.setChecked(false);
        }
    }
}