package com.example.vrexpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vrexpo.R;

import java.util.List;

public class TimeslotAdapter extends RecyclerView.Adapter<TimeslotAdapter.TimeslotViewHolder> {

    private List<String> timeslots;
    private OnTimeslotSelectedListener listener;

    private int selectedPosition = -1;

    public interface OnTimeslotSelectedListener {
        void onTimeslotSelected(String timeslot);
    }


    public TimeslotAdapter(List<String> timeslots, OnTimeslotSelectedListener listener) {
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
        String timeslot = timeslots.get(position);
        holder.timeslotButton.setText(timeslot);

        holder.timeslotButton.setChecked(position == selectedPosition);

        holder.timeslotButton.setOnClickListener(v -> {
            int newPosition = holder.getAdapterPosition();
            if (newPosition != RecyclerView.NO_POSITION) {
                selectedPosition = newPosition;
                notifyDataSetChanged();
                if (listener != null) {
                    listener.onTimeslotSelected(timeslots.get(newPosition));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return timeslots.size();
    }

    public void updateTimeslots(List<String> newTimeslots) {
        timeslots.clear();
        timeslots.addAll(newTimeslots);
        notifyDataSetChanged();
    }

    public static class TimeslotViewHolder extends RecyclerView.ViewHolder {
        RadioButton timeslotButton;

        public TimeslotViewHolder(View itemView) {
            super(itemView);
            timeslotButton = itemView.findViewById(R.id.appointment_time_radio_button);
        }
    }


}
