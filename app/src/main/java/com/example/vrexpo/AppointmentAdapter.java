package com.example.vrexpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<TimeSlot> appointments;
    private String role; // Add role parameter

    public AppointmentAdapter(List<TimeSlot> appointments, String role) {
        this.appointments = appointments;
        this.role = role; // Initialize role
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
        Log.d("AppointmentAdapter", "Binding data: Time: " + appointment.getAppointmentTime());

        holder.timeTextView.setText("Time: " + appointment.getAppointmentTime());

        if ("PATIENT".equals(role)) {
            holder.nameTextView.setText("Therapist: " + appointment.getTherapist_fullName());
        } else if ("THERAPIST".equals(role)) {
            holder.nameTextView.setText("Patient: " + appointment.getPatient_name());
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
        TextView timeTextView, nameTextView;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.appointment_time);
            nameTextView = itemView.findViewById(R.id.person_name);
        }
    }
}
