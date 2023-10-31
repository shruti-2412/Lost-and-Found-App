package com.shruti.lofo.ui.Lost;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import android.widget.*;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.shruti.lofo.R;


public class LostDetails extends AppCompatActivity {

    private ImageView img;
    private TextView title, address, mail, description, ownerName, dateLost, time, category, timeLost;
    private Button call, sms, backBtn;
    String phoneNum;
    private FirebaseFirestore db; // Initialize Fire Store


    @SuppressLint("MissingInflatedId")
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
        dateLost = findViewById(R.id.dateLost);
        category = findViewById(R.id.category);
        time = findViewById(R.id.timeLost);
        call = findViewById(R.id.call);
        sms = findViewById(R.id.sms);
        backBtn = findViewById(R.id.backBtn);
        timeLost = findViewById(R.id.timeLost);

        String itemId = getIntent().getStringExtra("itemId"); // Assuming you pass the itemName as an extra

        // Perform a query to find the document with the specific itemName
        Query query = db.collection("lostItems").whereEqualTo("itemName", itemId);

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
                                String itemDateLost = documentSnapshot.getString("dateLost");
                                String phone = String.valueOf(documentSnapshot.getLong("phnum")) ;
                                String itemtimeLost = documentSnapshot.getString("timeLost");
                                String categ = documentSnapshot.getString("category");

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
                                dateLost.setText(itemDateLost);
                                timeLost.setText(itemtimeLost);
                                category.setText(categ);
                                phoneNum=phone;
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
                intent.setData(Uri.parse("tel:" + phoneNum));
                startActivity(intent);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // When the SMS button is clicked, open the SMS app
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:" + phoneNum));
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