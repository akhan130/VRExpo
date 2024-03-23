package com.example.vrexpo.treatments.arachnophobiaPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class Session711_ARACH extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session711_arachnophobia);

        TextView b2_ARACH711 = findViewById(R.id.b2_ARACH711);
        b2_ARACH711.setMovementMethod(LinkMovementMethod.getInstance());
        b2_ARACH711.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=l_94jyUTSe0");
                Intent b2_ARACH711 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_ARACH711);
            }
        });

        TextView b4_ARACH711 = findViewById(R.id.b4_ARACH711);
        b4_ARACH711.setMovementMethod(LinkMovementMethod.getInstance());
        b4_ARACH711.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=5H-4mfmiKP8&ab_channel=MVR");
                Intent b4_ARACH711 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_ARACH711);
            }
        });
    }
}
