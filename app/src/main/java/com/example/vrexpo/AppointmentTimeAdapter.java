package com.example.vrexpo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.view.LayoutInflater;

public class AppointmentTimeAdapter extends RecyclerView.Adapter<AppointmentTimeAdapter.ViewHolder> {
    private List<AppointmentTime> appointmentTimes;
    private OnItemClickListener listener;

    public AppointmentTimeAdapter(List<AppointmentTime> appointmentTimes, OnItemClickListener listener) {
        this.appointmentTimes = appointmentTimes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointment_time, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppointmentTime appointmentTime = appointmentTimes.get(position);
        holder.bind(appointmentTime);
    }

    @Override
    public int getItemCount() {
        return appointmentTimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button appointmentTimeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appointmentTimeButton = itemView.findViewById(R.id.appointment_time_button);
        }

        public void bind(final AppointmentTime appointmentTime) {
            appointmentTimeButton.setText(appointmentTime.getTime());

            // Set button color and clickability based on availability
            if (appointmentTime.isAvailable()) {
                appointmentTimeButton.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.purple_500));
                appointmentTimeButton.setEnabled(true);
                appointmentTimeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Show confirmation dialog
                        if (listener != null) {
                            listener.onItemClick(appointmentTime.getTime());
                        }
                    }
                });
            } else {
                appointmentTimeButton.setBackgroundColor(itemView.getContext().getResources().getColor(android.R.color.darker_gray));
                appointmentTimeButton.setEnabled(false);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String appointmentTime);
    }
}
