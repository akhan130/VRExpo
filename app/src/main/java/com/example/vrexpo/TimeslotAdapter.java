package com.example.vrexpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimeslotAdapter extends RecyclerView.Adapter<TimeslotAdapter.TimeslotViewHolder> {

    private List<Timeslot> timeslots;
    private OnTimeslotSelectedListener listener;
    private int selectedPosition = -1;

    public interface OnTimeslotSelectedListener {
        void onTimeslotSelected(Timeslot timeslot);
    }

    public TimeslotAdapter(List<Timeslot> timeslots, OnTimeslotSelectedListener listener) {
        this.timeslots = timeslots;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TimeslotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment_time, parent, false);
        return new TimeslotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeslotViewHolder holder, int position) {
        Timeslot timeslot = timeslots.get(position);
        holder.timeslotButton.setText(timeslot.getTime());
        holder.therapistNameTextView.setText(timeslot.getTherapistName());

        holder.timeslotButton.setChecked(position == selectedPosition);
        holder.timeslotButton.setOnClickListener(v -> {
            int newPosition = holder.getAdapterPosition();
            if (newPosition != RecyclerView.NO_POSITION) {
                selectedPosition = newPosition;
                notifyDataSetChanged();
                if (listener != null) {
                    listener.onTimeslotSelected(timeslot);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeslots.size();
    }

    public void updateTimeslots(List<Timeslot> newTimeslots) {
        timeslots.clear();
        timeslots.addAll(newTimeslots);
        notifyDataSetChanged();
    }

    static class TimeslotViewHolder extends RecyclerView.ViewHolder {
        RadioButton timeslotButton;
        TextView therapistNameTextView;

        TimeslotViewHolder(View itemView) {
            super(itemView);
            timeslotButton = itemView.findViewById(R.id.appointment_time_radio_button);
            therapistNameTextView = itemView.findViewById(R.id.therapist_name_text_view);
        }
    }

    public static class Timeslot {
        private final String time;
        private final String therapistName;

        public Timeslot(String time, String therapistName) {
            this.time = time;
            this.therapistName = therapistName;
        }

        public String getTime() {
            return time;
        }

        public String getTherapistName() {
            return therapistName;
        }
    }
}
