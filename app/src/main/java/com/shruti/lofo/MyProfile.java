//package com.shruti.lofo;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import android.content.Intent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//public class MyProfile extends AppCompatActivity {
//    TextView profileName, profileEmail, profilePhone;
//    TextView titleName;
//    Button editProfile;
//    FirebaseFirestore database;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_my_profile);
//
//        profileName = findViewById(R.id.profileName);
//        profileEmail = findViewById(R.id.profileEmail);
//        profilePhone = findViewById(R.id.profilephone);
//        titleName = findViewById(R.id.titlename);
//        editProfile = findViewById(R.id.edit_button);
//        database = FirebaseFirestore.getInstance();
//        showAllUserData();
//        editProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                passUserData();
//            }
//        });
//    }
//
//    public void showAllUserData() {
//        Intent intent = getIntent();
//        String nameUser = intent.getStringExtra("name");
//        String emailUser = intent.getStringExtra("email");
//        String phoneUser = intent.getStringExtra("phone");
//        titleName.setText(nameUser);
//        profileName.setText(nameUser);
//        profileEmail.setText(emailUser);
//        profilePhone.setText(phoneUser);
//    }
//
//        public void passUserData () {
//            String userEmail = profileEmail.getText().toString().trim();
//
//            database.collection("users").document(userEmail).get()
//                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                        @Override
//                        public void onSuccess(DocumentSnapshot documentSnapshot) {
//                            if (documentSnapshot.exists()) {
//                                String nameFromDB = documentSnapshot.getString("name");
//                                String emailFromDB = documentSnapshot.getString("email");
//                                String phoneFromDB = documentSnapshot.getString("phone");
//                                String passwordFromDB = documentSnapshot.getString("password");
//
//                                Intent intent = new Intent(MyProfile.this, EditProfile.class);
//                                intent.putExtra("name", nameFromDB);
//                                intent.putExtra("email", emailFromDB);
//                                intent.putExtra("phone", phoneFromDB);
//                                intent.putExtra("password", passwordFromDB);
//                                startActivity(intent);
//                            }
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            // Handle the failure here.
//                        }
//                    });
//        }
//    }
