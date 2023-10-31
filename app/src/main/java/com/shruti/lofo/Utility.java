package com.shruti.lofo;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


import android.content.Context;

public class Utility {
    public static void showToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static CollectionReference getCollectionReferrenceForItems2() { // for lost items
        return FirebaseFirestore.getInstance().collection("lostItems");

    }
    public static CollectionReference getCollectionReferrenceForFound() { // for found items
        return FirebaseFirestore.getInstance().collection("foundItems");

    }
}
