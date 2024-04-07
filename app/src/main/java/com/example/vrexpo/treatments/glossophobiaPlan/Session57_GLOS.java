package com.example.vrexpo.treatments.glossophobiaPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class Session57_GLOS extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session57_glossophobia);

        TextView b2_GLO57 = findViewById(R.id.b2_GLO57);
        b2_GLO57.setMovementMethod(LinkMovementMethod.getInstance());
        b2_GLO57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/LaFarAGJKTk?si=xHsdq9yfCRKjCKpO");
                Intent b2_GLO57 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_GLO57);
            }
        });

        TextView b4_GLO57 = findViewById(R.id.b4_GLO57);
        b4_GLO57.setMovementMethod(LinkMovementMethod.getInstance());
        b4_GLO57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=RrSFjjeq994");
                Intent b4_GLO57 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_GLO57);
            }
        });

        TextView b6_GLO57 = findViewById(R.id.b6_GLO57);
        b6_GLO57.setMovementMethod(LinkMovementMethod.getInstance());
        b6_GLO57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=a0gkYZuuQDU");
                Intent b6_GLO57 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b6_GLO57);
            }
        });

    }
}




