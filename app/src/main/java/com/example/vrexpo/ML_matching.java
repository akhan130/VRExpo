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
