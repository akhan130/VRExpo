package com.example.vrexpo.treatments.socialAnxietyPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class Session26_SA extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session26_social);

        TextView b4_SA26 = findViewById(R.id.b4_SA26);
        b4_SA26.setMovementMethod(LinkMovementMethod.getInstance());
        b4_SA26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=NGA54YdyiUw");
                Intent b4_SA26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b4_SA26);
            }
        });
    }
}
