package com.example.vrexpo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class WriteReport extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth auth;

    //Variables
    private Button submitButton;
    private EditText dateEditText;
    private EditText therapistNameEditText;
    private EditText patientNameEditText;
    private EditText treatmentPlanEditText;
    private EditText commentsEditText;

    Button btnDatePicker;
    EditText txtDate;
    private int mYear, mMonth, mDay;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private String currentTherapistName;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent dashIntent = new Intent(WriteReport.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(WriteReport.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(WriteReport.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(WriteReport.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(WriteReport.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(WriteReport.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(WriteReport.this, TherapistZegoCloudHome.class);
                startActivity(zoom);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_report);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        btnDatePicker = findViewById(R.id.selectDateButton);
        txtDate = findViewById(R.id.Date);
        btnDatePicker.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        // Initialize views
        submitButton = findViewById(R.id.submit_button);
        dateEditText = findViewById(R.id.Date);
        therapistNameEditText = findViewById(R.id.TherapistName);
        patientNameEditText = findViewById(R.id.PatientName);
        treatmentPlanEditText = findViewById(R.id.TreatmentPlan);
        commentsEditText = findViewById(R.id.Comments);

        // Firebase initialization
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Report");

        Intent intent = getIntent();
        String patientName = intent.getStringExtra("PATIENT_NAME");
        Log.d(TAG, "Received Patient Name: " + patientName);

        if (patientName != null) {
            patientNameEditText.setText(patientName);
        }

        fetchTherapistName();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    saveUserDataToDatabase();
                }
            }
        });
    }

    private void fetchTherapistName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && user.getEmail() != null) {
            String email = user.getEmail();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("TherapistInfo");

            ref.orderByChild("therapist_email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            currentTherapistName = childSnapshot.child("therapist_fullName").getValue(String.class);
                            Log.d(TAG, "Current Therapist Name: " + currentTherapistName);
                            therapistNameEditText.setText(currentTherapistName);  // Set the therapist name here
                        }
                    } else {
                        Log.d(TAG, "Therapist not found.");
                        Toast.makeText(WriteReport.this, "Therapist not found.", Toast.LENGTH_SHORT).show();
                    }
                }

                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d(TAG, "Error fetching therapist name: " + error.getMessage());
                    Toast.makeText(WriteReport.this, "Error fetching therapist name: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validateFields() {
        return validateDate() && validateTherapistName() && validatePatientName() &&
                validateTreatmentPlan()  && validateComments();
    }

    private void saveUserDataToDatabase() {
        String date = dateEditText.getText().toString();
        String therapistName = therapistNameEditText.getText().toString();
        String patientName = patientNameEditText.getText().toString();
        String treatmentPlan = treatmentPlanEditText.getText().toString();
        String comments = commentsEditText.getText().toString();

        if (validateFields()) {
            ReportHelperClass helperClass = new ReportHelperClass(date, therapistName, patientName, treatmentPlan, comments);

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
    public void onClick(View v) {
        if (v == btnDatePicker) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // Formatting the date to MM-dd-yyyy
                            String formattedDate = String.format("%02d-%02d-%04d", monthOfYear + 1, dayOfMonth, year);
                            txtDate.setText(formattedDate);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}