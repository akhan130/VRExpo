package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.util.Calendar;

public class ViewPatientQuestions extends AppCompatActivity implements View.OnClickListener{

    private Switch viewQuestionsButton;
    private RelativeLayout preSessionContainer, postSessionContainer;

    Button btnDatePicker;
    EditText txtDate;
    private int mYear, mMonth, mDay;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent dashIntent = new Intent(ViewPatientQuestions.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(ViewPatientQuestions.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(ViewPatientQuestions.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(ViewPatientQuestions.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(ViewPatientQuestions.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(ViewPatientQuestions.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(ViewPatientQuestions.this, TherapistZegoCloudHome.class);
                startActivity(zoom);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_questions);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        btnDatePicker = findViewById(R.id.dateButton);
        txtDate = findViewById(R.id.dateText);
        btnDatePicker.setOnClickListener(this);

        preSessionContainer = findViewById(R.id.presessionContainer);
        postSessionContainer = findViewById(R.id.postsessionContainer);
        viewQuestionsButton = findViewById(R.id.viewQuestionButton);

        viewQuestionsButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                preSessionContainer.setVisibility(View.GONE);
                postSessionContainer.setVisibility(View.VISIBLE);
            } else {
                preSessionContainer.setVisibility(View.VISIBLE);
                postSessionContainer.setVisibility(View.GONE);
            }
        });

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
                            String formattedDate = String.format("%02d-%02d-%d", monthOfYear + 1, dayOfMonth, year);
                            txtDate.setText(formattedDate);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}