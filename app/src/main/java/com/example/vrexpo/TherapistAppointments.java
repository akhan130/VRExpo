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

public class TherapistAppointments extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private Button setAvailabilityButton;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView, appointmentListView;
    private Map<String, List<TimeSlot>> appointmentsByDate = new HashMap<>();
    private List<TimeSlot> appointmentList = new ArrayList<>();
    private AppointmentAdapter appointmentAdapter;
    private String currentTherapistName;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                startActivity(new Intent(TherapistAppointments.this, TherapistDashboard.class));
                return true;
            case R.id.action_appointments:
                startActivity(new Intent(TherapistAppointments.this, TherapistAppointments.class));
                return true;
            case R.id.action_view_patient:
                startActivity(new Intent(TherapistAppointments.this, SearchPatient.class));
                return true;
            case R.id.action_write_report:
                startActivity(new Intent(TherapistAppointments.this, WriteReport.class));
                return true;
            case R.id.action_messages:
                startActivity(new Intent(TherapistAppointments.this, TherapistMessages.class));
                return true;
            case R.id.action_account_settings:
                startActivity(new Intent(TherapistAppointments.this, TherapistAccountSettings.class));
                return true;
            case R.id.action_treatmentPlans:
                startActivity(new Intent(TherapistAppointments.this, TreatmentPlans.class));
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(TherapistAppointments.this, TherapistZegoCloudHome.class);
                startActivity(zoom);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_appointments);
        initWidgets();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CalendarUtils.selectedDate = LocalDate.now();
        }
        setMonthView();

        appointmentAdapter = new AppointmentAdapter(appointmentList, "THERAPIST");
        appointmentListView.setLayoutManager(new LinearLayoutManager(this));
        appointmentListView.setAdapter(appointmentAdapter);

        fetchCurrentTherapistName();

        setAvailabilityButton = findViewById(R.id.set_availability_button);
        setAvailabilityButton.setOnClickListener(v -> startActivity(new Intent(TherapistAppointments.this, TherapistSetAvailability.class)));

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void fetchCurrentTherapistName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && user.getEmail() != null) {
            String email = user.getEmail();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("TherapistInfo");

            ref.orderByChild("therapist_email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            currentTherapistName = childSnapshot.child("therapist_fullName").getValue(String.class);
                            Log.d("TherapistAppointments", "Current Therapist Name: " + currentTherapistName);
                            fetchAppointments();
                        }
                    } else {
                        Log.d("TherapistAppointments", "Therapist not found.");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("TherapistAppointments", "Error fetching therapist name: " + error.getMessage());
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
                        if (timeSlot != null && currentTherapistName != null && currentTherapistName.equals(timeSlot.getTherapist_fullName()) && timeSlot.getPatient_name() != null) {
                            Log.d("TherapistAppointments", "TimeSlot: " + timeSlot.getAppointmentTime() + ", Patient: " + timeSlot.getPatient_name());
                            timeSlots.add(timeSlot);
                        } else {
                            Log.d("TherapistAppointments", "TimeSlot is null or does not match therapist name for date: " + date);
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
        String selectedDate = formatDate(CalendarUtils.selectedDate);
        appointmentList.clear();

        Log.d("TherapistAppointments", "Selected Date: " + selectedDate);

        if (appointmentsByDate.containsKey(selectedDate)) {
            appointmentList.addAll(appointmentsByDate.get(selectedDate));
        } else {
            Log.d("TherapistAppointments", "No appointments found for the selected date: " + selectedDate);
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
