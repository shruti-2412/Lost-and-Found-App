package com.shruti.lofo.ui.MyProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shruti.lofo.R;

public class MyProfileFragment extends Fragment {
    TextView profileName, profileEmail, profilePhone, titleName;
    FirebaseFirestore database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_profile, container, false);
        profileName = root.findViewById(R.id.profileName);
        profileEmail = root.findViewById(R.id.profileEmail);
        profilePhone = root.findViewById(R.id.profilephone);
        titleName = root.findViewById(R.id.titlename);

        database = FirebaseFirestore.getInstance();

        fetchUserData();
        return root;
    }

    private void fetchUserData() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        String userEmail = currentUser.getEmail();
        database.collection("users")
                .whereEqualTo("email", userEmail)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String nameFromDB = documentSnapshot.getString("name");
                        String emailFromDB = documentSnapshot.getString("email");
                        String phoneFromDB = documentSnapshot.getString("phone");

                        // Set retrieved data to TextViews
                        titleName.setText(nameFromDB);
                        profileName.setText(nameFromDB);
                        profileEmail.setText(emailFromDB);
                        profilePhone.setText(phoneFromDB);
                    }
                })
                .addOnFailureListener(e -> {

                });
    }
}