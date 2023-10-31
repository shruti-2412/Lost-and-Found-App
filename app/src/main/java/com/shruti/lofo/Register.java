package com.shruti.lofo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText signupName, signupPhone, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
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
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String phone = signupPhone.getText().toString();
                String password = signupPassword.getText().toString();

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, task -> {
                            if (task.isSuccessful()) {
                                // User is successfully registered and authenticated.
                                // Add the user's data to the Firestore database.
                                FirebaseFirestore db = FirebaseFirestore.getInstance();

                                Map<String, Object> user = new HashMap<>();
                                user.put("name", name);
                                user.put("email", email);
                                user.put("phone", phone);
                                user.put("password", password);

                                db.collection("users")
                                        .add(user)
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
                                Toast.makeText(Register.this, "Registration failed.",
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