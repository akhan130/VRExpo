package com.example.vrexpo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {

    //Variables
    private Button createAccountButton;
    private EditText phoneEditText;
    private EditText genderEditText;
    private EditText passwordEditText;
    private EditText fullNameEditText;
    private EditText dobEditText;
    private EditText emailEditText;
    private EditText addressEditText;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize views
        createAccountButton = findViewById(R.id.createAccountButton);
        phoneEditText = findViewById(R.id.Phone);
        genderEditText = findViewById(R.id.Gender);
        passwordEditText = findViewById(R.id.password);
        fullNameEditText = findViewById(R.id.FullName);
        dobEditText = findViewById(R.id.DOB);
        emailEditText = findViewById(R.id.Email);
        addressEditText = findViewById(R.id.Address);

        //Save data in FireBase on button click
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("PatientAccount");

                //Get all values from text fields.
                String name = String.valueOf(fullNameEditText.getText());
                String dob = String.valueOf(dobEditText.getText());
                String gender = String.valueOf(genderEditText.getText());
                String email = String.valueOf(emailEditText.getText());
                String phone = String.valueOf(phoneEditText.getText());
                String address = String.valueOf(addressEditText.getText());
                String password = String.valueOf(passwordEditText.getText());

                AccountHelperClass helperClass = new AccountHelperClass(name, dob, gender, email, phone, address, password);

                reference.child(phone).setValue(helperClass);
            }
        });

    }
}
