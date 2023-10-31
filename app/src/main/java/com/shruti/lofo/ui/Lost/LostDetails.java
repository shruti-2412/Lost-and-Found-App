package com.shruti.lofo.ui.Lost;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.*;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.shruti.lofo.R;
import com.shruti.lofo.Utility;


public class LostDetails extends AppCompatActivity {

    private ImageView img;
    private TextView title, address, mail, description, ownerName, phnum, dateLost, time, category;
    private Button call, sms, backBtn;
    private FirebaseFirestore db; // Initialize Fire Store


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lost_details);

        // Initialize Fire Store
        db = FirebaseFirestore.getInstance();

        img = findViewById(R.id.img);
        title = findViewById(R.id.title);
        address = findViewById(R.id.address);
        mail = findViewById(R.id.mail);
        description = findViewById(R.id.description);
        ownerName = findViewById(R.id.ownerName);
        phnum = findViewById(R.id.phnum);
        dateLost = findViewById(R.id.dateLost);
        category = findViewById(R.id.category);
        time = findViewById(R.id.timeLost);
        call = findViewById(R.id.call);
        sms = findViewById(R.id.sms);
        backBtn = findViewById(R.id.backBtn);

        String itemName = getIntent().getStringExtra("itemId"); // Assuming you pass the itemName as an extra

        // Perform a query to find the document with the specific itemName
        Query query = db.collection("lostItems").whereEqualTo("itemName", itemName);

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    // Get the document reference for the first (or only) document with the matching itemName
                    DocumentReference itemRef = queryDocumentSnapshots.getDocuments().get(0).getReference();

                    // Now you can use this itemRef to fetch the document and display its details
                    itemRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String imageUrl = documentSnapshot.getString("imageURI");
                                String itemTitle = documentSnapshot.getString("itemName");
                                String itemAddress = documentSnapshot.getString("location");
                                String itemMail = documentSnapshot.getString("email");
                                String itemDescription = documentSnapshot.getString("description");
                                String itemOwnerName = documentSnapshot.getString("ownerName");
                                String itemPhNum = documentSnapshot.getString("phone");
                                String itemDateLost = documentSnapshot.getString("dateLost");

                                // Load the image using Glide and adjust the ImageView size
                                if (imageUrl != null && !imageUrl.isEmpty()) {
                                    Glide.with(LostDetails.this)
                                            .load(imageUrl)
                                            .placeholder(R.drawable.placeholder_image)
                                            .error(R.drawable.baseline_image_search_24)
                                            .into(img);
                                }


                                // Set data to TextViews
                                title.setText(itemTitle);
                                address.setText(itemAddress);
                                mail.setText(itemMail);
                                description.setText(itemDescription);
                                ownerName.setText(itemOwnerName);
                                phnum.setText(itemPhNum);
                                dateLost.setText(itemDateLost);
                            } else {
                                Toast.makeText(LostDetails.this, "Data not found!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LostDetails.this, "Document not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Set click listeners for call and SMS buttons
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the Call button is clicked, open the phone dialer
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phnum));
                startActivity(intent);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // When the SMS button is clicked, open the SMS app
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:" + phnum));
                intent.putExtra("sms_body", "Hello, I want to inquire about your lost item.");
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the LostDetails activity and return to the previous screen
            }
        });
    }
}