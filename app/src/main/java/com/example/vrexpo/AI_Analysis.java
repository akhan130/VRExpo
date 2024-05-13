// Simple form of NLP which performs sentiment analysis using Lexicon-based approach

package com.example.vrexpo;
import android.content.Intent;
import android.content.SharedPreferences;
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

import android.content.Context;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AI_Analysis extends AppCompatActivity {
    private static final String TAG = "AI_Analysis"; // Tag for log messages

    private TextView textViewSentimentAnalysis;
    private TextView textViewSentimentMessage;

    private List<String> responses;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(AI_Analysis.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_sessionStart:
                Intent zoom = new Intent(AI_Analysis.this, ZegoCloudHome.class);
                startActivity(zoom);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(AI_Analysis.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_appointments:
                Intent appointments = new Intent(AI_Analysis.this, PatientAppointments.class);
                startActivity(appointments);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(AI_Analysis.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_messages:
                Intent messages = new Intent(AI_Analysis.this, PatientMessages.class);
                startActivity(messages);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(AI_Analysis.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_questionnaire_analysis);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Initialize TextViews
        textViewSentimentAnalysis = findViewById(R.id.textViewSentimentAnalysis);
        textViewSentimentMessage = findViewById(R.id.textViewSentimentMessage);


        Button findTherapistNew = findViewById(R.id.newTherapist);
        findTherapistNew.setVisibility(View.GONE); // Initially hide the button

        Button dashboard = findViewById(R.id.dashboard);
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dashboard = new Intent(AI_Analysis.this, Dashboard.class);
                startActivity(dashboard);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String preSession1 = sharedPreferences.getString("Question1", "");
        String preSession2 = sharedPreferences.getString("Question2", "");
        String preSession3 = sharedPreferences.getString("Question3", "");

        Log.d("PreSession Questions Responses", "PRE SESSION RESPONSES");
        Log.d("Question 1", "Answer One: " + preSession1);
        Log.d("Question 2", "Answer Two: " + preSession2);
        Log.d("Question 3", "Answer Three: " + preSession3);

        String postSession1 = getIntent().getStringExtra("Comfort");
        String postSession2 = getIntent().getStringExtra("Immersion");
        String postSession3 = getIntent().getStringExtra("HelpfulHarmful");
        String postSession4 = getIntent().getStringExtra("Expectations");
        String postSession5 = getIntent().getStringExtra("DifficultComponents");
        String postSession6 = getIntent().getStringExtra("Modifications");
        String postSession7 = getIntent().getStringExtra("Rating");
        String postSession8 = getIntent().getStringExtra("AdditionalFeedback");

        Log.d("PostSession Questions Responses", "POST SESSION RESPONSES");
        Log.d("Question 1", "Comfort: " + postSession1);
        Log.d("Question 2", "Immersion: " + postSession2);
        Log.d("Question 3", "HelpfulHarmful: " + postSession3);
        Log.d("Question 4", "Expectations: " + postSession4);
        Log.d("Question 5", "DifficultComponents: " + postSession5);
        Log.d("Question 6", "Modifications: " + postSession6);
        Log.d("Question 7", "Rating: " + postSession7);
        Log.d("Question 8", "AdditionalFeedback: " + postSession8);


        // Call loadAfinnLexicon() to load the AFINN lexicon when the activity is created
        loadAfinnLexicon(getApplicationContext());

        // Example usage: Analyze sentiment of multiple texts

        responses = new ArrayList<>();
        // PRESESSION
        responses.add(preSession1);
        responses.add(preSession2);
        responses.add(preSession3);

        // POSTSESSION
        responses.add(postSession1);
        responses.add(postSession2);
        responses.add(postSession3);
        responses.add(postSession4);
        responses.add(postSession5);
        responses.add(postSession6);
        responses.add(postSession7);
        responses.add(postSession8);


        /**
        texts.add("Some of the more intense exposure exercises were a bit overwhelming.");
        texts.add("I really enjoyed the VR experience. It was very immersive.");
        texts.add("The user interface is confusing and hard to navigate."); **/

        int totalScore = 0;
        int totalWordCount = 0;
        boolean hasNegativeSentiment = false;

        for (String text : responses) {
            int[] sentimentScore = analyzeSentiment(text);
            totalScore += sentimentScore[0];
            totalWordCount += sentimentScore[1];
            if (sentimentScore[0] < 0) {
                hasNegativeSentiment = true;
            }
        }

        // Calculate average sentiment score
        double averageScore = (totalWordCount > 0) ? (double) totalScore / totalWordCount : 0;

        // Determine sentiment based on average score
        String overallSentiment;
        if (averageScore > 0) {
            overallSentiment = "Positive";
            textViewSentimentAnalysis.setText("Response sentiment: Positive");
            textViewSentimentMessage.setText("Its amazing you are having a positive experience so far.");

        } else if (averageScore < 0) {
            overallSentiment = "Negative";
            textViewSentimentAnalysis.setText("Response sentiment: Negative");
            textViewSentimentMessage.setText("Sorry you aren't having a good experience so far, you can look for a new therapist by clicking the button below.");
            // Show the button if sentiment is negative
            findTherapistNew.setVisibility(View.VISIBLE);
            // Set click listener for the button to go to FindTherapist class
            findTherapistNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent findTherapist = new Intent(AI_Analysis.this, FindTherapist.class);
                    startActivity(findTherapist);
                }
            });
        } else {
            overallSentiment = "Neutral";
            textViewSentimentAnalysis.setText("Response sentiment: Neutral");
            textViewSentimentMessage.setText("Its amazing you are having a good experience so far.");
        }

        // Log the overall sentiment score and overall sentiment
        Log.d(TAG, "Overall Sentiment Score: " + averageScore);
        Log.d(TAG, "Overall Sentiment: " + overallSentiment);

        // Display sentiment analysis based on sentiment
        if (hasNegativeSentiment) {
            Log.d(TAG, "Send a complaint to therapist or choose a new therapist");
        } else {
            Log.d(TAG, "It is wonderful you are having a positive experience so far");
        }
    }

    // Load AFINN lexicon from the res/raw directory
    private Map<String, Integer> afinnMap = new HashMap<>();
    private void loadAfinnLexicon(Context context) {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.afinn);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    afinnMap.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Analyze sentiment of a text using the loaded AFINN lexicon
    private int[] analyzeSentiment(String text) {
        // Convert text to lowercase and split into words
        String[] words = text.toLowerCase().split("\\s+");

        int totalScore = 0;
        int wordCount = 0;

        // Calculate sentiment score for each word
        for (String word : words) {
            if (afinnMap.containsKey(word)) {
                totalScore += afinnMap.get(word);
                wordCount++;
            }
        }

        return new int[]{totalScore, wordCount};
    }
}
