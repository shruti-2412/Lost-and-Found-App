package com.shruti.lofo;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText signupName, signupPhone, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    private Toast toast;
    private Handler handler = new Handler();
    private boolean emailValid = true;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPhone = findViewById(R.id.signup_phone);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);
        signupEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString();
                if (!email.endsWith("@cumminscollege.in")) {
                    signupEmail.setTextColor(Color.RED);
                } else {
                    signupEmail.setTextColor(Color.BLACK);
                }
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String phone = signupPhone.getText().toString();
                String password = signupPassword.getText().toString();

                if (!email.endsWith("@cumminscollege.in")) {
                    // Email format is incorrect
                    Toast.makeText(Register.this, "Please sign up with @cumminscollege.in email.", Toast.LENGTH_SHORT).show();
                    return; // Exit the method, preventing further execution
                }

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, task -> {
                            if (task.isSuccessful()) {
                                // User is successfully registered and authenticated.
                                // Add the user's data to the Firestore database.
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    user.sendEmailVerification()
                                            .addOnCompleteListener(task1 -> {
                                                if (task1.isSuccessful()) {
                                                    // Step 5: Handle Verification Email Sent Event
                                                    // Email verification sent successfully
                                                    // Prompt users to verify their email
                                                    Toast.makeText(Register.this, "Verification email sent.", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    // Email verification not sent
                                                    Log.e(TAG, "sendEmailVerification", task1.getException());
                                                }
                                            });
                                }

                                FirebaseFirestore db = FirebaseFirestore.getInstance();

                                Map<String, Object> user1 = new HashMap<>();
                                user1.put("name", name);
                                user1.put("email", email);
                                user1.put("phone", phone);
                                user1.put("password", password);

                                db.collection("users")
                                        .add(user1)
                                        .addOnSuccessListener(documentReference -> {
                                            Toast.makeText(Register.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Register.this, Login.class);
                                            startActivity(intent);
                                        })
                                        .addOnFailureListener(e -> {
                                            // Handle any errors here
                                        });
                            } else {
                                // If sign-up fails, display a message to the user.
                                Toast.makeText(Register.this, "Registration failed might be due to Incorrect college mail format| Password less than 6 character | Invalid Phone number",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }



}