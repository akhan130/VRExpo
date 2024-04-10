package com.example.vrexpo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TherapySchedulerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> appointmentTimes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy_scheduler);

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Initialize RecyclerView and populate appointment times
        recyclerView = findViewById(R.id.appointment_times_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initializeAppointmentTimes();

        // Initialize adapter with appointment times
        AppointmentTimeAdapter adapter = new AppointmentTimeAdapter(appointmentTimes);
        recyclerView.setAdapter(adapter);
    }

    private void initializeAppointmentTimes() {
        // Fetch available appointment times from the database
        // For simplicity, let's assume all times are available initially
        appointmentTimes.add(String.valueOf(new AppointmentTime("9AM-10AM", true)));
        appointmentTimes.add(String.valueOf(new AppointmentTime("10AM-11AM", true)));
        appointmentTimes.add(String.valueOf(new AppointmentTime("11AM-12PM", false))); // Assuming this time is not available initially
        appointmentTimes.add(String.valueOf(new AppointmentTime("12PM-1PM", true)));
        appointmentTimes.add(String.valueOf(new AppointmentTime("1PM-2PM", false))); // Assuming this time is not available initially
        appointmentTimes.add(String.valueOf(new AppointmentTime("2PM-3PM", true)));
        appointmentTimes.add(String.valueOf(new AppointmentTime("3PM-4PM", true)));
        appointmentTimes.add(String.valueOf(new AppointmentTime("4PM-5PM", false))); // Assuming this time is not available initially

        // You can set unavailable times to disabled here based on database data
    }


    private class AppointmentTimeAdapter extends RecyclerView.Adapter<AppointmentTimeAdapter.ViewHolder> {

        private List<String> appointmentTimes;

        public AppointmentTimeAdapter(List<String> appointmentTimes) {
            this.appointmentTimes = appointmentTimes;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment_time, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String appointmentTime = appointmentTimes.get(position);
            holder.bind(appointmentTime);
        }

        @Override
        public int getItemCount() {
            return appointmentTimes.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private Button appointmentTimeButton;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                appointmentTimeButton = itemView.findViewById(R.id.appointment_time_button);
            }

            public void bind(final String appointmentTime) {
                appointmentTimeButton.setText(appointmentTime);
                appointmentTimeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Show confirmation dialog
                        showConfirmationDialog(appointmentTime);
                    }
                });
            }
        }

        private void showConfirmationDialog(final String appointmentTime) {
            AlertDialog.Builder builder = new AlertDialog.Builder(TherapySchedulerActivity.this);
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure you want to select this appointment time?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Update the database with patient's name and change status
                    updateDatabase(appointmentTime);
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();
        }

        private void updateDatabase(String appointmentTime) {
            // Implement database update logic here
            // Update appointment status to "upcoming" and add patient's name
            // You need to have a reference to the database and update the appropriate node
        }
    }
}
