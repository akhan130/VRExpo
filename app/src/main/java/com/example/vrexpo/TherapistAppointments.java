package com.example.vrexpo;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TherapistAppointments extends AppCompatActivity {

    private Button setAvaliabilityButton;
    private RecyclerView appointmentsRecyclerView;
    private AppointmentAdapter appointmentAdapter;
    private List<AppointmentModel> appointmentList = new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent dashIntent = new Intent(TherapistAppointments.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(TherapistAppointments.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(TherapistAppointments.this, ViewPatients.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(TherapistAppointments.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(TherapistAppointments.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(TherapistAppointments.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(TherapistAppointments.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(TherapistAppointments.this, Zoom.class);
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

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        appointmentsRecyclerView = findViewById(R.id.upcoming_appointments_recycler_view);

        initializeRecyclerView();

        Button setAvailabilityButton = findViewById(R.id.set_availability_button);
        setAvailabilityButton.setOnClickListener(v -> startActivity(new Intent(TherapistAppointments.this, TherapistSetAvailability.class)));

        loadAppointments();
    }

    private void initializeRecyclerView() {
        appointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentAdapter = new AppointmentAdapter(appointmentList);
        appointmentsRecyclerView.setAdapter(appointmentAdapter);
    }


    private void loadAppointments() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Appointments");
            ref.orderByChild("therapist_fullName").equalTo(user.getDisplayName())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            appointmentList.clear();
                            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
                            for (DataSnapshot dateSnapshot : dataSnapshot.getChildren()) {
                                for (DataSnapshot timeSlotSnapshot : dateSnapshot.getChildren()) {
                                    TimeSlot slot = timeSlotSnapshot.getValue(TimeSlot.class);
                                    if (slot != null && "Upcoming".equals(slot.getAppointmentStatus())) {
                                        try {
                                            String dateString = dateSnapshot.getKey();
                                            Date date = sdf.parse(dateString);
                                            if (date != null && date.getTime() > System.currentTimeMillis()) {
                                                AppointmentModel appointment = new AppointmentModel();
                                                Map<String, TimeSlot> slots = new HashMap<>();
                                                slots.put(timeSlotSnapshot.getKey(), slot);
                                                appointment.setSlots(slots);
                                                appointment.setDate(dateString);
                                                appointmentList.add(appointment);
                                            }
                                        } catch (ParseException e) {
                                            Log.e(TAG, "Error parsing date", e);
                                        }
                                    }
                                }
                            }
                            appointmentAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.w(TAG, "loadAppointments:onCancelled", databaseError.toException());
                        }
                    });
        }
    }

}