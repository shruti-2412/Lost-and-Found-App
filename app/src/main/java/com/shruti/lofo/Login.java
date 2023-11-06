package com.shruti.lofo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        // Check if the user is already logged in
        SharedPreferences sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // User is already logged in, navigate to the main activity
            Intent intent = new Intent(this, BindingNavigation.class);
            startActivity(intent);
            finish();
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateEmail() | !validatePassword()) {
                    Toast.makeText(Login.this, "Enter data in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    checkUser();
                }
            }
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateEmail() {
        String val = loginEmail.getText().toString();
        if (val.isEmpty()) {
            loginEmail.setError("Email cannot be empty");
            return false;
        } else {
            loginEmail.setError(null);
            return true;
        }
    }
    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userEmail = loginEmail.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(Login.this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login Successfully!", Toast.LENGTH_SHORT).show();

                        // Update the shared preferences to indicate that the user is now logged in
                        SharedPreferences sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();

                        Intent intent = new Intent(Login.this, BindingNavigation.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}