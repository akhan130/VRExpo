package com.example.vrexpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    private List<AppointmentModel> appointmentList;

    public AppointmentAdapter(List<AppointmentModel> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcoming_appointment, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        AppointmentModel appointment = appointmentList.get(position);
        // Simplified for clarity, you should format the date properly
        holder.dateTextView.setText("Date: " + appointment.getDate());
        // Example: just display one of the slots or handle them appropriately
        if (!appointment.getSlots().isEmpty()) {
            TimeSlot firstSlot = appointment.getSlots().values().iterator().next();
            holder.timeTextView.setText("Time: " + firstSlot.getAppointmentTime());
            holder.nameTextView.setText("Patient: " + firstSlot.getPatientName());
        }
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
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
