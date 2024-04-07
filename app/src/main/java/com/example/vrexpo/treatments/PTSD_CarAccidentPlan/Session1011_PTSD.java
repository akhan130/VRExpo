package com.example.vrexpo.treatments.PTSD_CarAccidentPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class Session1011_PTSD extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session110_ptsd_caraccident);

        TextView b2_PTSD1011 = findViewById(R.id.b2_PTSD1011);
        b2_PTSD1011.setMovementMethod(LinkMovementMethod.getInstance());
        b2_PTSD1011.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/MdOMwqxAL3k?si=WP-hDjksfCUAs0an");
                Intent b2_PTSD1011 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_PTSD1011);
            }
        });

        TextView b4_PTSD1011 = findViewById(R.id.b4_PTSD1011);
        b4_PTSD1011.setMovementMethod(LinkMovementMethod.getInstance());
        b4_PTSD1011.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=EdaofVWvrrA");
                Intent b4_PTSD1011 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_PTSD1011);
            }
        });
    }
}
