package com.example.vrexpo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PatientSelectAppointment extends AppCompatActivity {

    private CalendarView appointmentCalendarView;
    private RecyclerView timeslotRecyclerView;
    private Button bookButton;
    private TimeslotAdapter timeslotAdapter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
    private String selectedDate;
    private String selectedTimeslot;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_select_appointment);

        // Initialize Firebase Auth and Database
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        appointmentCalendarView = findViewById(R.id.appointmentCalendarView);
        timeslotRecyclerView = findViewById(R.id.timeslotRecyclerView);
        bookButton = findViewById(R.id.bookButton);

        setupRecyclerView();

        appointmentCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                selectedDate = dateFormat.format(calendar.getTime());
                loadTimeslots(selectedDate);
            }
        });

        bookButton.setOnClickListener(v -> {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null && selectedTimeslot != null && !selectedTimeslot.isEmpty()) {
                String patientEmail = currentUser.getEmail(); // Assuming the patient's email is the identifier

                DatabaseReference patientRef = (DatabaseReference) FirebaseDatabase.getInstance()
                        .getReference("PatientAccount")
                        .orderByChild("email")
                        .equalTo(patientEmail);

                patientRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                PatientModel patient = child.getValue(PatientModel.class);
                                if (patient != null) {
                                    String patientName = patient.getName();
                                    bookAppointment(patientName);
                                } else {
                                    // Handle null case
                                    Toast.makeText(PatientSelectAppointment.this, "Patient data not available.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(PatientSelectAppointment.this, "Patient data not found.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(PatientSelectAppointment.this, "Failed to load patient data.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(PatientSelectAppointment.this, "Please select a timeslot first.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setupRecyclerView() {
        timeslotRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        timeslotAdapter = new TimeslotAdapter(new ArrayList<>(), new TimeslotAdapter.OnTimeslotSelectedListener() {
            @Override
            public void onTimeslotSelected(String timeslot) {
                selectedTimeslot = timeslot; // This should now correctly hold the key for Firebase.
            }
        });

        timeslotRecyclerView.setAdapter(timeslotAdapter);
    }

    private void loadTimeslots(String date) {
        DatabaseReference timeslotRef = FirebaseDatabase.getInstance().getReference().child("Appointments").child(date);
        List<String> timeslots = new ArrayList<>();
        timeslotRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot slotSnapshot : dataSnapshot.getChildren()) {
                    // Here was the mistake, it should match the field name in your database
                    if ("Available".equals(slotSnapshot.child("appointment_status").getValue(String.class))) {
                        timeslots.add(slotSnapshot.child("appointmentTime").getValue(String.class));
                    }
                }
                timeslotAdapter.updateTimeslots(timeslots);
                if (timeslots.isEmpty()) {
                    Toast.makeText(PatientSelectAppointment.this, "No available times on this date.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PatientSelectAppointment.this, "Failed to load times.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void bookAppointment(String patientName) {
        // Ensure selectedTimeslot is the time string as it is the key in your database.
        if (selectedDate == null || selectedTimeslot == null || patientName == null || patientName.isEmpty()) {
            Toast.makeText(this, "Please select a timeslot and ensure patient name is available.", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference appointmentRef = FirebaseDatabase.getInstance().getReference()
                .child("Appointments")
                .child(selectedDate)
                .child(selectedTimeslot);

        // Create a map to update the existing appointment entry with the new status and patient name
        Map<String, Object> updates = new HashMap<>();
        updates.put("appointment_status", "Upcoming");
        updates.put("patient_name", patientName);

        // Update the existing entry for the timeslot
        appointmentRef.updateChildren(updates).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(PatientSelectAppointment.this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PatientSelectAppointment.this, "Failed to book appointment. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
