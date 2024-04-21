package com.example.vrexpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AdminDashboard extends AppCompatActivity {

    private static final String TAG = "VRExpo";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.admin_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent dashIntent = new Intent(AdminDashboard.this, AdminDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_create_account:
                Intent createAcctIntent = new Intent(AdminDashboard.this, AdminCreateTherapistAcct.class);
                startActivity(createAcctIntent);
                return true;
            case R.id.action_view_therapist:
                Intent viewTherapistAcct = new Intent(AdminDashboard.this, AdminViewTherapist.class);
                startActivity(viewTherapistAcct);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.admin_toolbar);
        setSupportActionBar(myToolbar);
    }
}