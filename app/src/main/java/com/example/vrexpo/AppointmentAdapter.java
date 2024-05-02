package com.example.vrexpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    private List<TimeSlot> appointments;

    public AppointmentAdapter(List<TimeSlot> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcoming_appointment, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        TimeSlot appointment = appointments.get(position);
        // Assuming you have a date field now in TimeSlot class
        holder.dateTextView.setText("Date: " + formatDate(appointment.getDate()));
        holder.timeTextView.setText("Time: " + appointment.getAppointmentTime());
        holder.nameTextView.setText("Patient: " + appointment.getPatientName());
    }

    private String formatDate(String dateString) {
        // Assuming dateString is in "MM-dd-yyyy" format
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        try {
            Date date = format.parse(dateString);
            // Format date to a different format if required
            return new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString; // Return original string if parsing fails
        }
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public void setAppointments(List<TimeSlot> appointments) {
        this.appointments = appointments;
    }

    static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView, timeTextView, nameTextView;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date);
            timeTextView = itemView.findViewById(R.id.appointment_time);
            nameTextView = itemView.findViewById(R.id.person_name);
        }
    }
}
