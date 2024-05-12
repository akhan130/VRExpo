package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    private String patientName;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PatientSelectAppointment.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent sessionStart = new Intent(PatientSelectAppointment.this, SessionStart.class);
                startActivity(sessionStart);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PatientSelectAppointment.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(PatientSelectAppointment.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PatientSelectAppointment.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_messages:
                Intent messages = new Intent(PatientSelectAppointment.this, PatientMessages.class);
                startActivity(messages);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(PatientSelectAppointment.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_select_appointment);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        appointmentCalendarView = findViewById(R.id.appointmentCalendarView);
        timeslotRecyclerView = findViewById(R.id.timeslotRecyclerView);
        bookButton = findViewById(R.id.bookButton);

        setupRecyclerView();
        setupBookButtonListener();

        appointmentCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                selectedDate = dateFormat.format(calendar.getTime());
                loadTimeslots(selectedDate);
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTimeslot != null && !selectedTimeslot.isEmpty()) {
                    fetchPatientFullName();
                    bookAppointment(patientName);
                } else {
                    Toast.makeText(PatientSelectAppointment.this, "Please select a timeslot first.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchPatientFullName(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && user.getEmail() != null) {
            String email = user.getEmail();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("PatientAccount");

            ref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            patientName = childSnapshot.child("name").getValue(String.class);
                            Log.d("PatientName", "Patient Full Name: " + patientName);
                            bookAppointment(patientName);  // Call bookAppointment here with the fetched name
                        }
                    } else {
                        Log.d("PatientName", "Patient not found.");
                        Toast.makeText(PatientSelectAppointment.this, "Patient name not found. Cannot book appointment.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("PatientName", "Error fetching patient name: " + error.getMessage());
                    Toast.makeText(PatientSelectAppointment.this, "Error fetching patient name.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setupBookButtonListener() {
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTimeslot != null && !selectedTimeslot.isEmpty()) {
                    fetchPatientFullName(); // This now initiates fetching the patient name and booking the appointment
                } else {
                    Toast.makeText(PatientSelectAppointment.this, "Please select a timeslot first.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupRecyclerView() {
        timeslotRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        timeslotAdapter = new TimeslotAdapter(new ArrayList<>(), new TimeslotAdapter.OnTimeslotSelectedListener() {
            @Override
            public void onTimeslotSelected(String timeslot) {
                selectedTimeslot = timeslot;
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
                    String status = slotSnapshot.child("appointment_status").getValue(String.class);
                    if ("Available".equals(status)) {
                        String time = slotSnapshot.child("appointmentTime").getValue(String.class);
                        timeslots.add(time);
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
        DatabaseReference appointmentsRef = FirebaseDatabase.getInstance().getReference()
                .child("Appointments")
                .child(selectedDate);

        appointmentsRef.orderByChild("appointmentTime").equalTo(selectedTimeslot).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        // Get the key of the timeslot to be booked
                        String key = childSnapshot.getKey();
                        if (key != null && "Available".equals(childSnapshot.child("appointment_status").getValue(String.class))) {
                            // Found the timeslot, now update it
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("appointment_status", "Upcoming");
                            updates.put("patient_name", patientName);

                            appointmentsRef.child(key).updateChildren(updates).addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PatientSelectAppointment.this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(PatientSelectAppointment.this, "Failed to book appointment. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                } else {
                    Toast.makeText(PatientSelectAppointment.this, "Selected timeslot is no longer available.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PatientSelectAppointment.this, "Failed to check timeslot availability.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
