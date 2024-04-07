package com.example.vrexpo.treatments.PTSD_CarAccidentPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class Session79_PTSD extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session79_ptsd_caraccident);

        TextView b2_PTSD79 = findViewById(R.id.b2_PTSD79);
        b2_PTSD79.setMovementMethod(LinkMovementMethod.getInstance());
        b2_PTSD79.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/2oZ24oxd2gk?si=O7wlbwOd6972sUp1");
                Intent b2_PTSD79 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_PTSD79);
            }
        });
    }
}