package com.shruti.lofo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DrawerManipulator {

    public static void updateDrawerHeader(Context context, View headerView) {
        TextView usernameTextView = headerView.findViewById(R.id.usernameTextView); // Replace with your TextView ID
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("users").document(email);

            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {

                        String name = documentSnapshot.getString("name");
                        Log.d("Firestore", "Name retrieved: " + name);
                        if (name != null) {
                            usernameTextView.setText("Hello, " + name);
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Handle any errors
                }
            });
        }
    }
}
