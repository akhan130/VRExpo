package com.example.vrexpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        holder.dateTextView.setText("Date: " + new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date(appointment.getDate())));
        holder.timeTextView.setText("Time: " + appointment.getAppointmentTime());
        holder.nameTextView.setText("Patient: " + appointment.getPatientName());
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
