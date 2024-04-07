package com.example.vrexpo.treatments.claustrophobiaPlan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class Session26_CLAU extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session26_claustrophobia);

        TextView b3_CLAU26 = findViewById(R.id.b3_CLAU26);
        b3_CLAU26.setMovementMethod(LinkMovementMethod.getInstance());
        b3_CLAU26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/igaha_puYmg?si=kiXJzej7bv3UA8Ph");
                Intent b3_CLAU26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b3_CLAU26);
            }
        });

        TextView b5_CLAU26 = findViewById(R.id.b5_CLAU26);
        b5_CLAU26.setMovementMethod(LinkMovementMethod.getInstance());
        b5_CLAU26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/nkKz8stbuWA?si=9-WeoBxXTnOyKhPr");
                Intent b5_CLAU26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b5_CLAU26);
            }
        });

        TextView b7_CLAU26 = findViewById(R.id.b7_CLAU26);
        b7_CLAU26.setMovementMethod(LinkMovementMethod.getInstance());
        b7_CLAU26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/h83_d3cvaXg?si=SJMyRMxjQEkGix-A");
                Intent b7_CLAU26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b7_CLAU26);
            }
        });

        TextView b9_CLAU26 = findViewById(R.id.b9_CLAU26);
        b9_CLAU26.setMovementMethod(LinkMovementMethod.getInstance());
        b9_CLAU26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the YouTube video in a separate browser intent
                Uri uri = Uri.parse("https://youtu.be/6aE1ti16AE8?si=TToWyiXwRLGY3q_-");
                Intent b9_CLAU26 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(b9_CLAU26);
            }
        });


    }
}

