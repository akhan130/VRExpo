package com.example.vrexpo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.vrexpo.MatchedData;

import java.util.List;

public class ML_matching extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml_matching);

        // Call retrieveMatchedData method
        retrieveMatchedData();
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
    }
}



/***
package com.example.vrexpo;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.bibig65.fastml.DecisionTree;
import com.github.bibig65.fastml.DataSet;
import com.github.bibig65.fastml.Instances;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ML_matching extends AppCompatActivity {

    private TextView predictionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml_matching);

        predictionTextView = findViewById(R.id.predictionTextView);

        try {
            // Load dataset from CSV file in assets
            DataSet dataSet = loadDatasetFromCSV("dataset.csv");

            // Separate features and labels
            Instances instances = dataSet.getInstances();
            List<String> features = instances.getAttributes();
            List<String> labels = instances.getLabels();

            // Train decision tree
            DecisionTree decisionTree = new DecisionTree();
            decisionTree.train(dataSet);

            // Make predictions
            List<String> patientCharacteristics = new ArrayList<>();
            // Add patient characteristics here
            String predictedTreatmentPlan = decisionTree.classify(patientCharacteristics);

            // Display predicted treatment plan
            predictionTextView.setText(predictedTreatmentPlan);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DataSet loadDatasetFromCSV(String filename) throws IOException {
        AssetManager assetManager = getAssets();
        InputStream inputStream = assetManager.open(filename);
        return DataSet.load(inputStream);
    }
}
 **/
