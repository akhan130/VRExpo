package com.example.vrexpo.treatments.glossophobiaPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class Session24_GLOS extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session24_glossophobia);

        TextView b2_GLO24 = findViewById(R.id.b2_GLO24);
        b2_GLO24.setMovementMethod(LinkMovementMethod.getInstance());
        b2_GLO24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/mVatZIGJ32g?si=SiKrY5yaJVlihWq9");
                Intent b2_GLO24 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_GLO24);
            }
        });

        TextView b4_GLO24 = findViewById(R.id.b4_GLO24);
        b4_GLO24.setMovementMethod(LinkMovementMethod.getInstance());
        b4_GLO24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=fOtsw8rHpdw");
                Intent b4_GLO24 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_GLO24);
            }
        });

        TextView b6_GLO24 = findViewById(R.id.b6_GLO24);
        b6_GLO24.setMovementMethod(LinkMovementMethod.getInstance());
        b6_GLO24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=qVxi2OE-bm4");
                Intent b6_GLO24 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b6_GLO24);
            }
        });

    }
}



