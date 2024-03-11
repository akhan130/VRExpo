package com.example.vrexpo;

import android.annotation.SuppressLint;
import android.content.Intent;
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
        passwordEditText = findViewById(R.id.Password);
        fullNameEditText = findViewById(R.id.FullName);
        dobEditText = findViewById(R.id.DOB);
        emailEditText = findViewById(R.id.Email);
        addressEditText = findViewById(R.id.Address);

        // Firebase initialization
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("PatientAccount");

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private boolean validateFields() {
        return validateName() && validateDOB() && validateGender() &&
                validatePhoneNo() && validateAddress() && validateEmail() && validatePassword();
    }

    private void registerUser() {
        if (!validateFields()) {
            return;
        }

        String name = fullNameEditText.getText().toString();
        String dob = dobEditText.getText().toString();
        String gender = genderEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        AccountHelperClass helperClass = new AccountHelperClass(name, dob, gender, email, phone, address, password);

        reference.child(phone).setValue(helperClass);

        // Redirect to main activity or login page
        startActivity(new Intent(CreateAccount.this, MainActivity.class));
    }


    private Boolean validateName() {
        String name = String.valueOf(fullNameEditText.getText());

        if (name.isEmpty()) {
            fullNameEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            fullNameEditText.setError(null);
            fullNameEditText.setEnabled(false);
            return true;
        }
    }

    //VALIDATION METHODS FOR EACH FIELD

    private Boolean validateEmail(){
        String email = String.valueOf(emailEditText.getText());
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()){
            emailEditText.setError("Field cannot be empty");
            return false;
        } else if (!email.matches(emailPattern)) {
            emailEditText.setError("Invalid email address");
            return false;
        } else {
            emailEditText.setError(null);
            emailEditText.setEnabled(false);
            return true;
        }
    }

    //NEED TO FIX PASSWORD CONDITIONS:
    // Condition for WhiteSpace: String noWhiteSpace = "\\A\\w(4,20)\\z";

    private Boolean validatePassword(){
        String password = String.valueOf(passwordEditText.getText());
//        String passwordVal = "^" +
//                "(?=.*[0-9])" +         //at least 1 digit
//                "(?=.*[a-z])" +         //at least 1 lowercase letter
//                "(?=.*[A-Z])" +         //at least 1 uppercase letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=\\S+$])" +          //no white spaces
//                ".{4,}" +               //at least 4 characters
//                "$";

        if (password.isEmpty()){
            passwordEditText.setError("Field cannot be empty");
            return false;
//        } else if (!password.matches(passwordVal)) {
//            passwordEditText.setError("Password is too weak");
//            return false;
        }
        else {
            passwordEditText.setError(null);
            passwordEditText.setEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo(){
        String phone = String.valueOf(phoneEditText.getText());

        if(phone.isEmpty()){
            phoneEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            phoneEditText.setError(null);
            return true;
        }
    }

    private Boolean validateGender(){
        String gender = String.valueOf(genderEditText.getText());

        if(gender.isEmpty()){
            genderEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            genderEditText.setError(null);
            return true;
        }
    }

    private Boolean validateAddress(){
        String address = String.valueOf(addressEditText.getText());

        if(address.isEmpty()){
            addressEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            addressEditText.setError(null);
            return true;
        }
    }

    private Boolean validateDOB(){
        String dob = String.valueOf(dobEditText.getText());

        if(dob.isEmpty()){
            dobEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            dobEditText.setError(null);
            return true;
        }
    }

}

