package com.example.vrexpo.treatments.socialAnxietyPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class Session1415_SA extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session1415_social);

        TextView b3_SA1415 = findViewById(R.id.b3_SA1415);
        b3_SA1415.setMovementMethod(LinkMovementMethod.getInstance());
        b3_SA1415.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=U4W2rGH9oWs");
                Intent b3_SA1415 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b3_SA1415);
            }
        });
    }
}
