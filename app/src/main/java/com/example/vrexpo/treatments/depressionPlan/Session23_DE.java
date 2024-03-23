package com.example.vrexpo.treatments.depressionPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class Session23_DE extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session23_depression);

        TextView b2_DEP23 = findViewById(R.id.b2_DEP23);
        b2_DEP23.setMovementMethod(LinkMovementMethod.getInstance());
        b2_DEP23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=8Du1hrPrV38");
                Intent b2_DEP23 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b2_DEP23);
            }
        });
    }
}
