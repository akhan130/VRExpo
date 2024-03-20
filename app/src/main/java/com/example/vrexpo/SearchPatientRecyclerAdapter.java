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

public class SearchPatientRecyclerAdapter extends FirebaseRecyclerAdapter<PatientModel, SearchPatientRecyclerAdapter.PatientModelViewHolder> {

    Context context;

    public SearchPatientRecyclerAdapter(@NonNull FirebaseRecyclerOptions<PatientModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PatientModelViewHolder holder, int position, @NonNull PatientModel model) {
        holder.patientNameText.setText(model.getPatientName());
        holder.phoneNumberText.setText(model.getPhoneNumber());
    }

    @NonNull
    @Override
    public PatientModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_patient_recycler_row, parent, false);
        return new PatientModelViewHolder(view);
    }

    static class PatientModelViewHolder extends RecyclerView.ViewHolder {
        TextView patientNameText;
        TextView phoneNumberText;
        ImageView profilePic;

        public PatientModelViewHolder(@NonNull View itemView) {
            super(itemView);
            patientNameText = itemView.findViewById(R.id.patient_name);
            phoneNumberText = itemView.findViewById(R.id.phone_number);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}