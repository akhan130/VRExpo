// Form of ML which performs classification using decision tree classifier

package com.example.vrexpo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ML_matching extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(ML_matching.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent zoom = new Intent(ML_matching.this, SessionStart.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(ML_matching.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(ML_matching.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(ML_matching.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_messages:
                Intent messages = new Intent(ML_matching.this, PatientMessages.class);
                startActivity(messages);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(ML_matching.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml_matching);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Call retrieveMatchedData method
        retrieveMatchedData();

        // Go to dashboard
        Button home = findViewById(R.id.homeButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(ML_matching.this, Dashboard.class);
                startActivity(home);
            }
        });
    }

    private void retrieveMatchedData() {
        String patientCondition = MatchedData.getPatientCondition();
        String patientPhobia = MatchedData.getPatientPhobia();
        String patientPTSD = MatchedData.getPatientPTSD();
        String therapistSpecialization = MatchedData.getTherapistSpecialization();
        List<String> matchedTherapistIds = MatchedData.getMatchedTherapistIds();

        // Log retrieved data
        Log.d("MatchedData", "Patient Condition: " + patientCondition);
        Log.d("MatchedData", "Patient Phobia: " + patientPhobia);
        Log.d("MatchedData", "Patient PTSD: " + patientPTSD);

        if (therapistSpecialization != null) {
            Log.d("MatchedData", "Therapist Specialization: " + therapistSpecialization);
        } else {
            Log.d("MatchedData", "Therapist Specialization: null");
        }

        if (matchedTherapistIds != null) {
            for (String therapistId : matchedTherapistIds) {
                Log.d("MatchedData", "Matched Therapist ID: " + therapistId);
            }
        } else {
            Log.d("MatchedData", "Matched Therapist IDs: null");
        }

        // Load the dataset
        List<String[]> data = loadData("vrexpo_testcases_populated.csv");

        // Log each line of the CSV file
        InputStream inputStream = getResources().openRawResource(R.raw.vrexpo_testcases_populated);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try {
            // Read each line of the CSV file and log its content
            while ((line = bufferedReader.readLine()) != null) {
                Log.d("CSVLine", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Split data into features and target variable
        List<String[]> features = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (String[] row : data) {
            features.add(new String[]{row[0], row[1], row[2], row[3]});
            labels.add(row[4]);
        }

        // Train the decision tree classifier
        DecisionTreeClassifier model = new DecisionTreeClassifier();
        Node root = model.train(features, labels);

        // Predict treatment plans for a new instance
        String[] newInstance = {patientCondition, patientPhobia, patientPTSD, therapistSpecialization};
        String predictedTreatmentPlan = model.predict(root, newInstance);
        Log.d("PredictedTreatmentPlan", "Predicted Treatment Plan: " + predictedTreatmentPlan);

        // Display the predicted treatment plan on the UI
        TextView predictedPlanTextView = findViewById(R.id.predictedPlanTextView);
        predictedPlanTextView.setText("Predicted Treatment Plan: " + predictedTreatmentPlan);

    }

    private List<String[]> loadData(String filename) {
        List<String[]> data = new ArrayList<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.vrexpo_testcases_populated);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
