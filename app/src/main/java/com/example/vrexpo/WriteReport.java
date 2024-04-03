package com.example.vrexpo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteReport extends AppCompatActivity {

    private FirebaseAuth auth;

    //Variables
    private Button submitButton;
    private EditText dateEditText;
    private EditText therapistNameEditText;
    private EditText patientNameEditText;
    private EditText treatmentPlanEditText;
    private EditText sessionNumberEditText;
    private EditText commentsEditText;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_report);

        auth = FirebaseAuth.getInstance();

        // Initialize views
        submitButton = findViewById(R.id.submit_button);
        dateEditText = findViewById(R.id.Date);
        therapistNameEditText = findViewById(R.id.TherapistName);
        patientNameEditText = findViewById(R.id.PatientName);
        treatmentPlanEditText = findViewById(R.id.TreatmentPlan);
        sessionNumberEditText = findViewById(R.id.SessionNumber);
        commentsEditText = findViewById(R.id.Comments);

        // Firebase initialization
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Report");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    saveUserDataToDatabase();
                }
            }
        });
    }


    private boolean validateFields() {
        return validateDate() && validateTherapistName() && validatePatientName() &&
                validateTreatmentPlan() && validateSessionNumber() && validateComments();
    }

    private void saveUserDataToDatabase() {
        String date = dateEditText.getText().toString();
        String therapistName = therapistNameEditText.getText().toString();
        String patientName = patientNameEditText.getText().toString();
        String treatmentPlan = treatmentPlanEditText.getText().toString();
        String sessionNumber = sessionNumberEditText.getText().toString();
        String comments = commentsEditText.getText().toString();

        if (validateFields()) {
            ReportHelperClass helperClass = new ReportHelperClass(date, therapistName, patientName, treatmentPlan, sessionNumber, comments);

            // Save the additional user data to the Realtime Database
            reference.child(patientName).setValue(helperClass);

            Toast.makeText(WriteReport.this, "Report submitted successfully", Toast.LENGTH_SHORT).show();
        }
    }


    private Boolean validateDate() {
        String name = String.valueOf(dateEditText.getText());

        if (name.isEmpty()) {
            dateEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            dateEditText.setError(null);
            dateEditText.setEnabled(false);
            return true;
        }
    }

    private Boolean validateTherapistName(){
        String phone = String.valueOf(therapistNameEditText.getText());

        if(phone.isEmpty()){
            therapistNameEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            therapistNameEditText.setError(null);
            return true;
        }
    }

    private Boolean validatePatientName(){
        String gender = String.valueOf(patientNameEditText.getText());

        if(gender.isEmpty()){
            patientNameEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            patientNameEditText.setError(null);
            return true;
        }
    }

    private Boolean validateTreatmentPlan(){
        String address = String.valueOf(treatmentPlanEditText.getText());

        if(address.isEmpty()){
            treatmentPlanEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            treatmentPlanEditText.setError(null);
            return true;
        }
    }

    private Boolean validateSessionNumber(){
        String dob = String.valueOf(sessionNumberEditText.getText());

        if(dob.isEmpty()){
            sessionNumberEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            sessionNumberEditText.setError(null);
            return true;
        }
    }

    private Boolean validateComments(){
        String dob = String.valueOf(commentsEditText.getText());

        if(dob.isEmpty()){
            commentsEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            commentsEditText.setError(null);
            return true;
        }
    }
}
