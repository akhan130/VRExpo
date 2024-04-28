package com.example.vrexpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TherapistAdapterNLP extends RecyclerView.Adapter<TherapistAdapterNLP.TherapistViewHolder> {
    private List<Therapist> therapistList;

    public TherapistAdapterNLP(List<Therapist> therapistList) {
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
        Therapist therapist = therapistList.get(position);
        holder.nameTextView.setText(therapist.getFullName());
        holder.phoneTextView.setText(therapist.getPhoneNumber());
        holder.emailTextView.setText(therapist.getEmail());
        holder.specializationTextView.setText(therapist.getSpecialization());
    }

    @Override
    public int getItemCount() {
        return therapistList.size();
    }

    public static class TherapistViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView phoneTextView;
        private TextView emailTextView;
        private TextView specializationTextView;

        public TherapistViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.therapist_name);
            emailTextView = itemView.findViewById(R.id.therapist_email);
            phoneTextView = itemView.findViewById(R.id.therapist_phone);
            specializationTextView = itemView.findViewById(R.id.therapist_specialization);

        }
    }
}
