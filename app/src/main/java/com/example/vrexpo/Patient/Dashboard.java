package com.example.vrexpo.Patient;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vrexpo.R;

public class Dashboard extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_pre:
                Intent preIntent = new Intent(Dashboard.this, PresessionQuestions.class);
                startActivity(preIntent);
                return true;
            case R.id.action_post:
                Intent postIntent = new Intent(Dashboard.this, PostsessionQuestions.class);
                startActivity(postIntent);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(Dashboard.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(Dashboard.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(Dashboard.this, FindTherapist.class);
                startActivity(findIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Setting up links
        /*
        TextView test = findViewById(R.id.testLink);
        test.setMovementMethod(LinkMovementMethod.getInstance());
        test.setLinkTextColor(Color.BLUE);
        */

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}