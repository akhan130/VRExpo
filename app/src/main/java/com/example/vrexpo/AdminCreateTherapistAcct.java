package com.example.vrexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCreateTherapistAcct extends AppCompatActivity {

    private FirebaseAuth auth;

    //Variables
    private EditText fullNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText phoneEditText;
    private EditText specializationEditText;
    private EditText genderEditText;
    private Button createAccountButton;
    private Button closeButton;


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_therapist_acct);

        auth = FirebaseAuth.getInstance();

        // Initialize views
        fullNameEditText = findViewById(R.id.therapist_full_name);
        emailEditText = findViewById(R.id.therapist_email);
        passwordEditText = findViewById(R.id.therapist_password);
        phoneEditText = findViewById(R.id.therapist_phone);
        specializationEditText = findViewById(R.id.therapist_specialization);
        genderEditText = findViewById(R.id.therapist_gender);
        createAccountButton = findViewById(R.id.createAccountButton);
        closeButton = findViewById(R.id.CloseButton);

        // Firebase initialization
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("TherapistInfo");

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTherapistUser();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCreateTherapistAcct.this, AdminDashboard.class);
                startActivity(intent);
            }
        });

    }

    private boolean validateFields() {
        return validateName() && validateEmail() && validatePassword() &&
                validatePhoneNo() && validateSpecialization() && validateGender();
    }

    public void registerTherapistUser() {
        if (!validateFields()) {
            return;
        }

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Use Firebase Authentication to create a new user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registration successful
                        Toast.makeText(AdminCreateTherapistAcct.this, "Registration successful", Toast.LENGTH_SHORT).show();

                        // Automatically log in the user after registration (optional)
                        // FirebaseUser user = auth.getCurrentUser();
                        // Perform any additional actions (e.g., navigate to the main screen)

                        saveUserDataToDatabase();
                    } else {
                        // Reason if fail
                        Toast.makeText(AdminCreateTherapistAcct.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserDataToDatabase() {
        String name = fullNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String specialization = specializationEditText.getText().toString();
        String gender = genderEditText.getText().toString();

        TherapistAccountHelperClass helperClass = new TherapistAccountHelperClass(name, specialization, gender, email, phone, password);

        // Save the additional user data to the Realtime Database
        reference.child(phone).setValue(helperClass);

        startActivity(new Intent(AdminCreateTherapistAcct.this, AdminCreateTherapistAcct.class));
    }


    private boolean validateGender() {
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

    private boolean validateSpecialization() {
        String specialization = String.valueOf(specializationEditText.getText());

        if(specialization.isEmpty()){
            specializationEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            specializationEditText.setError(null);
            return true;
        }
    }

    private boolean validatePhoneNo() {
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

    private boolean validatePassword() {
        String password = String.valueOf(passwordEditText.getText());
        if (password.isEmpty()){
            passwordEditText.setError("Field cannot be empty");
            return false;
        }
        else {
            passwordEditText.setError(null);
            passwordEditText.setEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
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

    private boolean validateName() {
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

}