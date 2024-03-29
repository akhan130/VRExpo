package com.example.vrexpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TherapistAdapter extends RecyclerView.Adapter<TherapistAdapter.TherapistViewHolder> {

    private List<Therapist> therapistList;

    public TherapistAdapter(List<Therapist> therapistList) {
        this.therapistList = therapistList;
    }

    @NonNull
    @Override
    public TherapistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.therapist_item, parent, false);
        return new TherapistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TherapistViewHolder holder, int position) {
        if (therapistList != null && position < therapistList.size()) {
            Therapist therapist = therapistList.get(position);
            holder.bind(therapist);
        }
    }

    @Override
    public int getItemCount() {
        return therapistList != null ? therapistList.size() : 0;
    }

    public static class TherapistViewHolder extends RecyclerView.ViewHolder {
        private TextView fullNameTextView;
        private TextView emailTextView;
        private TextView phoneTextView;
        private TextView specializationTextView;

        public TherapistViewHolder(@NonNull View itemView) {
            super(itemView);
            fullNameTextView = itemView.findViewById(R.id.therapist_name);
            emailTextView = itemView.findViewById(R.id.email);
            phoneTextView = itemView.findViewById(R.id.phone);
            specializationTextView = itemView.findViewById(R.id.specialization);
        }

        public void bind(Therapist therapist) {
            fullNameTextView.setText(therapist.getFullName());
            emailTextView.setText(therapist.getEmail());
            phoneTextView.setText(therapist.getPhoneNumber());
            specializationTextView.setText(therapist.getSpecialization());
        }
    }
}
