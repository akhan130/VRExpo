package com.example.vrexpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentTimeAdapter extends RecyclerView.Adapter<AppointmentTimeAdapter.ViewHolder> {
    private List<AppointmentTime> appointmentTimes;
    private int selectedPos = RecyclerView.NO_POSITION;

    public AppointmentTimeAdapter(List<AppointmentTime> appointmentTimes) {
        this.appointmentTimes = appointmentTimes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment_time, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppointmentTime appointmentTime = appointmentTimes.get(position);
        holder.appointmentTimeRadioButton.setText(appointmentTime.getTime());
        holder.appointmentTimeRadioButton.setEnabled(appointmentTime.isAvailable());

        // Radio button selection
        holder.appointmentTimeRadioButton.setChecked(selectedPos == holder.getAdapterPosition());

        // Set OnClickListener for the radio button
        holder.appointmentTimeRadioButton.setOnClickListener(v -> {
            // Update the position when a radio button is clicked
            int newPosition = holder.getAdapterPosition(); // Use getAdapterPosition to get the current position
            if (newPosition != RecyclerView.NO_POSITION) { // Check if position is still valid
                selectedPos = newPosition;
                notifyDataSetChanged(); // This will uncheck the previously checked radio button
            }
        });
    }


    @Override
    public int getItemCount() {
        return appointmentTimes.size();
    }

    // ViewHolder class for your RecyclerView items
    public class ViewHolder extends RecyclerView.ViewHolder {
        public RadioButton appointmentTimeRadioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appointmentTimeRadioButton = itemView.findViewById(R.id.appointment_time_radio_button);
        }
    }

    // Utility method to get the selected time
    public AppointmentTime getSelectedTime() {
        if (selectedPos != RecyclerView.NO_POSITION) {
            return appointmentTimes.get(selectedPos);
        }
        return null; // No selection made
    }

    // Utility method to update timeslots
    public void updateTimeslots(List<AppointmentTime> newTimeslots) {
        appointmentTimes.clear();
        appointmentTimes.addAll(newTimeslots);
        notifyDataSetChanged();
    }
}
