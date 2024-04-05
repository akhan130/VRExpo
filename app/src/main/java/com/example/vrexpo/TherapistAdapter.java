package com.example.vrexpo;

import static com.google.firebase.database.DatabaseKt.getSnapshots;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;

import java.util.List;

public class TherapistAdapter extends FirebaseRecyclerAdapter<Therapist, TherapistAdapter.TherapistViewHolder> {

    Context context;

    public TherapistAdapter(@NonNull FirebaseRecyclerOptions<Therapist> options, Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public TherapistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.therapist_item, parent, false);
        return new TherapistViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull TherapistViewHolder holder, int position, @NonNull Therapist model) {
        // Log the position and therapist name
        Log.d("FindTherapist", "Binding position: " + position + ", Therapist: " + model.getFullName());
        holder.bind(model);
    }


    public static class TherapistViewHolder extends RecyclerView.ViewHolder {
        private TextView fullNameTextView;
        private TextView emailTextView;
        private TextView phoneTextView;
        private TextView specializationTextView;

        public TherapistViewHolder(@NonNull View itemView) {
            super(itemView);
            fullNameTextView = itemView.findViewById(R.id.therapist_name);
            emailTextView = itemView.findViewById(R.id.therapist_email);
            phoneTextView = itemView.findViewById(R.id.therapist_phone);
            specializationTextView = itemView.findViewById(R.id.therapist_specialization);
        }

        public void bind(Therapist therapist) {
            fullNameTextView.setText(therapist.getFullName());
            emailTextView.setText(therapist.getEmail());
            phoneTextView.setText(therapist.getPhoneNumber());
            specializationTextView.setText(therapist.getSpecialization());
        }
    }

    public void updateOptions(@NonNull FirebaseRecyclerOptions<Therapist> options) {
        super.updateOptions(options);
        notifyDataSetChanged();
    }

    private void updateOptions(FirebaseRecyclerOptions<Therapist> options, ObservableSnapshotArray<Therapist> snapshots) {
        // Update the options of the adapter
        super.updateOptions(options);
    }

}


