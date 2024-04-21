package com.example.vrexpo.treatments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vrexpo.Dashboard;
import com.example.vrexpo.FindTherapist;
import com.example.vrexpo.R;
import com.example.vrexpo.PatientSelectAppointment;
import com.example.vrexpo.TreatmentPlans;
import com.example.vrexpo.Zoom;

public class Aviophobia extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch(item.getItemId()){
//            case R.id.action_treatmentPlans:
//                Intent treatmentPlans = new Intent(Aviophobia.this, TreatmentPlans.class);
//                startActivity(treatmentPlans);
//                return true;
//            case R.id.action_zoom:
//                Intent zoom = new Intent(Aviophobia.this, Zoom.class);
//                startActivity(zoom);
//                return true;
//            case R.id.action_dashboard:
//                Intent dashIntent = new Intent(Aviophobia.this, Dashboard.class);
//                startActivity(dashIntent);
//                return true;
//            case R.id.action_schedule:
//                Intent scheduleIntent = new Intent(Aviophobia.this, PatientSelectAppointment.class);
//                startActivity(scheduleIntent);
//                return true;
//            case R.id.action_find_therapist:
//                Intent findIntent = new Intent(Aviophobia.this, FindTherapist.class);
//                startActivity(findIntent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviophobia);

        // Session 1,2 - Aviophobia
        Button Session12_AVIO = findViewById(R.id.Session12_AVIO);
        Session12_AVIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session12_AVIO = new Intent(Aviophobia.this, com.example.vrexpo.treatments.aviophobiaPlan.Session12_AVIO.class);
                startActivity(Session12_AVIO);
            }
        });

        // Session 3,4 - Aviophobia
        Button Session34_AVIO = findViewById(R.id.Session34_AVIO);
        Session34_AVIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session34_AVIO = new Intent(Aviophobia.this, com.example.vrexpo.treatments.aviophobiaPlan.Session34_AVIO.class);
                startActivity(Session34_AVIO);
            }
        });

        // Session 5,10 - Aviophobia
        Button Session510_AVIO = findViewById(R.id.Session510_AVIO);
        Session510_AVIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session510_AVIO = new Intent(Aviophobia.this, com.example.vrexpo.treatments.aviophobiaPlan.Session510_AVIO.class);
                startActivity(Session510_AVIO);
            }
        });

        // Session 11 - Aviophobia
        Button Session11_AVIO = findViewById(R.id.Session11_AVIO);
        Session11_AVIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Session11_AVIO = new Intent(Aviophobia.this, com.example.vrexpo.treatments.aviophobiaPlan.Session11_AVIO.class);
                startActivity(Session11_AVIO);
            }
        });

        // Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}


