package com.example.vrexpo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

/**
 * USE THIS PATIENT CLASS ONLY FOR ADMIN CLASSES
 */

public class PatientAdapter extends FirebaseRecyclerAdapter<PatientModel, PatientAdapter.PatientViewHolder> {

    private View.OnClickListener onItemClickListener;

    public PatientAdapter(@NonNull FirebaseRecyclerOptions<PatientModel> options, Context context) {
        super(options);
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_patient_recycler_row, parent, false);
        PatientViewHolder viewHolder = new PatientViewHolder(view);

        view.setOnClickListener(v -> {
            int position = viewHolder.getBindingAdapterPosition();
            if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                onItemClickListener.onClick(v);
            }
        });

        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull PatientAdapter.PatientViewHolder holder, int position, @NonNull PatientModel model) {
        holder.bind(model);
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView patientNameText;
        TextView phoneNumberText;
        ImageView profilePic;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            patientNameText = itemView.findViewById(R.id.patient_name);
            phoneNumberText = itemView.findViewById(R.id.phone_number);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }

        public void bind(PatientModel patient) {
            patientNameText.setText(patient.getName());
            phoneNumberText.setText(patient.getPhone());
        }
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        this.onItemClickListener = listener;
    }
}

