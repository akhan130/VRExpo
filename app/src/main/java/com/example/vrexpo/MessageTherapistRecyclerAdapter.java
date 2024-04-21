package com.example.vrexpo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MessageTherapistRecyclerAdapter extends FirebaseRecyclerAdapter<Therapist, MessageTherapistRecyclerAdapter.TherapistModelViewHolder> {

    Context context;

    public MessageTherapistRecyclerAdapter(@NonNull FirebaseRecyclerOptions<Therapist> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull TherapistModelViewHolder holder, int position, @NonNull Therapist model) {
        holder.therapistNameText.setText(model.getFullName());
        holder.phoneNumberText.setText(model.getPhoneNumber());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Chat.class);
            AndroidUtil.passTherapistModelAsIntent(intent,model);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public TherapistModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_therapist_recycler_row, parent, false);
        return new TherapistModelViewHolder(view);
    }

    class TherapistModelViewHolder extends RecyclerView.ViewHolder {
        TextView therapistNameText;
        TextView phoneNumberText;

        public TherapistModelViewHolder(@NonNull View itemView) {
            super(itemView);
            therapistNameText = itemView.findViewById(R.id.therapist_name);
            phoneNumberText = itemView.findViewById(R.id.phone_number);
        }
    }
}