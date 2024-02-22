package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.R;

public class CreateAccount extends AppCompatActivity {

    private TextView getStartedTextView;
    private Button createAccountButton;
    private EditText emergencyContactPhoneEditText;
    private EditText phoneEditText;
    private EditText genderEditText;
    private EditText passwordEditText;
    private EditText fullNameEditText;
    private EditText dobEditText;
    private EditText emergencyContactNameEditText;
    private EditText emailEditText;
    private EditText insuranceEditText;
    private EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize views
        getStartedTextView = findViewById(R.id.GetStarted);
        createAccountButton = findViewById(R.id.createAccountButton);
        emergencyContactPhoneEditText = findViewById(R.id.emergencyContactPhone);
        phoneEditText = findViewById(R.id.Phone);
        genderEditText = findViewById(R.id.Gender);
        passwordEditText = findViewById(R.id.password);
        fullNameEditText = findViewById(R.id.FullName);
        dobEditText = findViewById(R.id.DOB);
        emergencyContactNameEditText = findViewById(R.id.emergencyContactName);
        emailEditText = findViewById(R.id.Email);
        insuranceEditText = findViewById(R.id.insurance);
        addressEditText = findViewById(R.id.Address);

        // Set up click listener for the createAccountButton
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccount.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
}
