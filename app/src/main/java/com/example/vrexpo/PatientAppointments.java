package com.example.vrexpo;

import static com.example.vrexpo.CalendarUtils.daysInMonthArray;
import static com.example.vrexpo.CalendarUtils.formatDate;
import static com.example.vrexpo.CalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientAppointments extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private Button bookAppointmentButton, cancelAppointmentButton;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView, appointmentListView;
    private Map<String, List<TimeSlot>> appointmentsByDate = new HashMap<>();
    private List<TimeSlot> appointmentList = new ArrayList<>();
    private AppointmentAdapter appointmentAdapter;
    private String currentPatientName;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PatientAppointments.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent sessionStart = new Intent(PatientAppointments.this, SessionStart.class);
                startActivity(sessionStart);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PatientAppointments.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(PatientAppointments.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PatientAppointments.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(PatientAppointments.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointments);
        initWidgets();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CalendarUtils.selectedDate = LocalDate.now();
        }
        setMonthView();

        bookAppointmentButton = findViewById(R.id.book_appointment_button);
        bookAppointmentButton.setOnClickListener(v -> {
            Intent patientInfoIntent = new Intent(PatientAppointments.this, PatientSelectAppointment.class);
            startActivity(patientInfoIntent);
        });

        appointmentAdapter = new AppointmentAdapter(appointmentList, "PATIENT");
        appointmentListView.setLayoutManager(new LinearLayoutManager(this));
        appointmentListView.setAdapter(appointmentAdapter);

        fetchCurrentPatientName();

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void fetchCurrentPatientName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && user.getEmail() != null) {
            String email = user.getEmail();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("PatientAccount");

            ref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            currentPatientName = childSnapshot.child("name").getValue(String.class);
                            Log.d("PatientAppointments", "Current Patient Name: " + currentPatientName);
                            fetchAppointments(); // Now fetch appointments
                        }
                    } else {
                        Log.d("PatientAppointments", "Patient not found.");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("PatientAppointments", "Error fetching patient name: " + error.getMessage());
                }
            });
        }
    }

    private void fetchAppointments() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Appointments");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appointmentsByDate.clear();
                for (DataSnapshot dateSnapshot : dataSnapshot.getChildren()) {
                    String date = dateSnapshot.getKey();
                    List<TimeSlot> timeSlots = new ArrayList<>();
                    for (DataSnapshot timeSlotSnapshot : dateSnapshot.getChildren()) {
                        TimeSlot timeSlot = timeSlotSnapshot.getValue(TimeSlot.class);
                        if (timeSlot != null && currentPatientName != null && currentPatientName.equals(timeSlot.getPatient_name()) && timeSlot.getPatient_name() != null) {
                            Log.d("PatientAppointments", "TimeSlot: " + timeSlot.getAppointmentTime() + ", Therapist: " + timeSlot.getTherapist_fullName());
                            timeSlots.add(timeSlot);
                        } else {
                            Log.d("PatientAppointments", "TimeSlot is null or does not match patient name for date: " + date);
                        }
                    }
                    if (!timeSlots.isEmpty()) {
                        appointmentsByDate.put(date, timeSlots);
                    }
                }
                updateAppointmentsForSelectedDate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    private void updateAppointmentsForSelectedDate() {
        // Ensure date format consistency
        String selectedDate = formatDate(CalendarUtils.selectedDate);
        appointmentList.clear();

        Log.d("PatientAppointments", "Selected Date: " + selectedDate);

        if (appointmentsByDate.containsKey(selectedDate)) {
            appointmentList.addAll(appointmentsByDate.get(selectedDate));
        } else {
            Log.d("PatientAppointments", "No appointments found for the selected date: " + selectedDate);
        }
        appointmentAdapter.notifyDataSetChanged();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        appointmentListView = findViewById(R.id.appointmentListView);
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
            setMonthView();
            updateAppointmentsForSelectedDate();
        }
    }

    public void nextMonthAction(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
            setMonthView();
            updateAppointmentsForSelectedDate();
        }
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null) {
            CalendarUtils.selectedDate = date;
            setMonthView();
            updateAppointmentsForSelectedDate();
        }
    }
}
