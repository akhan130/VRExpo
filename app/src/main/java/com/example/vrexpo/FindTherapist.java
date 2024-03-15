package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class FindTherapist extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(FindTherapist.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_pre:
                Intent preIntent = new Intent(FindTherapist.this, PresessionQuestions.class);
                startActivity(preIntent);
                return true;
            case R.id.action_post:
                Intent postIntent = new Intent(FindTherapist.this, PostsessionQuestions.class);
                startActivity(postIntent);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(FindTherapist.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_schedule:
                Intent scheduleIntent = new Intent(FindTherapist.this, TherapySchedulerActivity.class);
                startActivity(scheduleIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_therapist);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

}
