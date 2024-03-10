package com.example.vrexpo.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.vrexpo.R;
import com.example.vrexpo.Session;

public class PresessionQuestions extends AppCompatActivity {

    private static final String TAG = "VRExpo";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(PresessionQuestions.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_post:
                Intent postIntent = new Intent(PresessionQuestions.this, PostsessionQuestions.class);
                startActivity(postIntent);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(PresessionQuestions.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(PresessionQuestions.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(PresessionQuestions.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presession_questions);

        //Setting up the action bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Find the buttons
        Button submitBtn = findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open postsession questions
                Intent sessionIntent = new Intent(PresessionQuestions.this, Session.class);
                startActivity(sessionIntent);
            }
        });
    }
}