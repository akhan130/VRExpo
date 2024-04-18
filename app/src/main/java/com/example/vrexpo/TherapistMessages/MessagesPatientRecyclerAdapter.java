package com.example.vrexpo.TherapistMessages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vrexpo.AndroidUtil;
import com.example.vrexpo.Chat;
import com.example.vrexpo.PatientModel;
import com.example.vrexpo.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MessagesPatientRecyclerAdapter extends FirebaseRecyclerAdapter<PatientModel, MessagesPatientRecyclerAdapter.PatientModelViewHolder> {

    Context context;

    public MessagesPatientRecyclerAdapter(@NonNull FirebaseRecyclerOptions<PatientModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PatientModelViewHolder holder, int position, @NonNull PatientModel model) {
        holder.patientNameText.setText(model.getName());
        holder.phoneNumberText.setText(model.getPhone());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Chat.class);
            AndroidUtil.passUserModelAsIntent(intent,model);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public PatientModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_patient_recycler_row, parent, false);
        return new PatientModelViewHolder(view);
    }

    class PatientModelViewHolder extends RecyclerView.ViewHolder {
        TextView patientNameText;
        TextView phoneNumberText;

        public PatientModelViewHolder(@NonNull View itemView) {
            super(itemView);
            patientNameText = itemView.findViewById(R.id.patient_name);
            phoneNumberText = itemView.findViewById(R.id.phone_number);
        }
    }
}