package com.example.vrexpo.StartScreens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vrexpo.Patient.Dashboard;
import com.example.vrexpo.R;
import com.example.vrexpo.Therapist.TherapistDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

public class Login extends AppCompatActivity {

    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private Button loginButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.Email);
        passwordEditText = findViewById(R.id.Password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password);
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login successful
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            //Condition for Therapist LogIn
                            if (email.endsWith("@vrexpo.com")) {
                                startActivity(new Intent(Login.this, TherapistDashboard.class));
                            } else {
                                //Patient LogIn
                                startActivity(new Intent(Login.this, Dashboard.class));
                            }
                        } else {
                            // Login fail
                            Toast.makeText(Login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                            // Reason for failing
                            if (task.getException() != null) {
                                Log.e("LoginActivity", "Exception: " + task.getException().getMessage());
                            }
                        }
                    }
                });
    }
}