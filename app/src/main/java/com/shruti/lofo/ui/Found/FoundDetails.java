package com.shruti.lofo.ui.Found;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shruti.lofo.R;

public class FoundDetails extends AppCompatActivity {

    private ImageView img;
    private TextView title, address, email, dateFound, description, category, finderName, phnum;
    private Button backBtn, callBtn, smsBtn;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.found_details);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        img = findViewById(R.id.img);
        title = findViewById(R.id.title);
        address = findViewById(R.id.address);
        email = findViewById(R.id.mail);
        dateFound = findViewById(R.id.dateFound);
        finderName = findViewById(R.id.finderName);
        description = findViewById(R.id.description);
        category = findViewById(R.id.category);
        phnum = findViewById(R.id.phnum);
        backBtn = findViewById(R.id.backBtn);
        callBtn = findViewById(R.id.call);
        smsBtn = findViewById(R.id.sms);

        // Get the item ID from the Intent
        String itemId = getIntent().getStringExtra("itemId");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Fetch item details from Fire store based on the itemId
        DocumentReference itemRef = db.collection("foundItems").document(itemId);
        itemRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String imageUrl = documentSnapshot.getString("imageURI");
                    String itemName = documentSnapshot.getString("itemName");
                    String itemLocation = documentSnapshot.getString("location");
                    String itemEmail = documentSnapshot.getString("email");
                    String itemDescription = documentSnapshot.getString("description");
                    String itemOwner = documentSnapshot.getString("finderName");
                    String itemPhnum = documentSnapshot.getString("phnum");
                    String itemIate = documentSnapshot.getString("date");
                    String itemCategory = documentSnapshot.getString("category");


                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Log.d("Found", "Image URL: " + imageUrl);
                        Glide.with(img)
                                .load(imageUrl)
                                .into(img);
                    }

                    // set the text for the attributes in the .xml file
                    title.setText(itemName);
                    address.setText(itemLocation);
                    dateFound.setText(itemIate);
                    description.setText(itemDescription);
                    email.setText(itemEmail);
                    finderName.setText(itemOwner);
                    category.setText(itemCategory);
                    phnum.setText(itemPhnum);
                } else {
                    System.out.println("Document does not exist!");
                }
            }
        });

        // Set an onClickListener for the Call button
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the Call button is clicked, open the phone dialer
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phnum));
                startActivity(intent);
            }
        });

        // Set an onClickListener for the SMS button
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the SMS button is clicked, open the SMS app
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:" + phnum));
                intent.putExtra("sms_body", "Hello, I want to inquire about your lost item.");
                startActivity(intent);
            }
        });


        // Set an onClickListener for the Back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}