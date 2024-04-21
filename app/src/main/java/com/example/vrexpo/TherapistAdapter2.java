package com.example.vrexpo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

/**
 * USE THIS THERAPIST ADAPTER 2 CLASS ONLY FOR ADMIN CLASSES
 */

public class TherapistAdapter2 extends FirebaseRecyclerAdapter<Therapist, TherapistAdapter2.TherapistViewHolder> {

    private View.OnClickListener onItemClickListener;

    public TherapistAdapter2(@NonNull FirebaseRecyclerOptions<Therapist> options, Context context) {
        super(options);
    }

    @NonNull
    @Override
    public TherapistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.therapist_item, parent, false);
        TherapistViewHolder viewHolder = new TherapistViewHolder(view);

        view.setOnClickListener(v -> {
            int position = viewHolder.getBindingAdapterPosition();
            if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                onItemClickListener.onClick(v);
            }
        });

        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull TherapistViewHolder holder, int position, @NonNull Therapist model) {
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

    public void setOnItemClickListener(View.OnClickListener listener) {
        this.onItemClickListener = listener;
    }
}

