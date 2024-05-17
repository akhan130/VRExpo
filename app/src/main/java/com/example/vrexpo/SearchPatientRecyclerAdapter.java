package com.example.vrexpo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
        holder.patientNameText.setText(model.getName());
        holder.phoneNumberText.setText(model.getPhone());
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
        ImageView profilePic;
        ImageButton reportButton;
        ImageButton questionsButton;

        public PatientModelViewHolder(@NonNull View itemView) {
            super(itemView);
            patientNameText = itemView.findViewById(R.id.patient_name);
            phoneNumberText = itemView.findViewById(R.id.phone_number);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
            reportButton = itemView.findViewById(R.id.report_button);
//            questionsButton = itemView.findViewById(R.id.questions_button);

            reportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String patientName = patientNameText.getText().toString();
                    Intent intent = new Intent(context, WriteReport.class);
                    intent.putExtra("PATIENT_NAME", patientName);

                    context.startActivity(intent);
                }
            });

//            questionsButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, ViewPatientQuestions.class); // Update with actual Activity class
//                    context.startActivity(intent);
//                }
//            });
        }
    }
}