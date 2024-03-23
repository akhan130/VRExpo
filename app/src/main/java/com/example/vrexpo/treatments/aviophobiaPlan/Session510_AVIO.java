package com.example.vrexpo.treatments.aviophobiaPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class Session510_AVIO extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session510_aviophobia);

        TextView b2_AVIO510 = findViewById(R.id.b2_AVIO510);
        b2_AVIO510.setMovementMethod(LinkMovementMethod.getInstance());
        b2_AVIO510.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=nOh0_Mc9sI0");
                Intent b2_AVIO510 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_AVIO510);
            }
        });

        TextView b4_AVIO510 = findViewById(R.id.b4_AVIO510);
        b4_AVIO510.setMovementMethod(LinkMovementMethod.getInstance());
        b4_AVIO510.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=HEEIzZ7UjRg&ab_channel=Blick");
                Intent b4_AVIO510 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_AVIO510);
            }
        });

        TextView b6_AVIO510 = findViewById(R.id.b6_AVIO510);
        b6_AVIO510.setMovementMethod(LinkMovementMethod.getInstance());
        b6_AVIO510.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=PeIiXiEdz-M");
                Intent b6_AVIO510 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b6_AVIO510);
            }
        });

        TextView b8_AVIO510 = findViewById(R.id.b8_AVIO510);
        b8_AVIO510.setMovementMethod(LinkMovementMethod.getInstance());
        b8_AVIO510.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=A0pgC5L7bM8");
                Intent b8_AVIO510 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b8_AVIO510);
            }
        });


    }
}
