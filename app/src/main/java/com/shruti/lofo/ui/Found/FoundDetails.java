package com.shruti.lofo.ui.Found;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.shruti.lofo.R;

public class FoundDetails extends AppCompatActivity {

    private ImageView img;
    private TextView title, address, email, dateFound, description, category, finderName;
    String phnum;
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
        backBtn = findViewById(R.id.backBtn);
        callBtn = findViewById(R.id.call);
        smsBtn = findViewById(R.id.sms);

        String itemId = getIntent().getStringExtra("itemId");

        // Perform a query to find the document with the specific itemName
        Query query = db.collection("foundItems").whereEqualTo("itemName", itemId);

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
                                String itemName = documentSnapshot.getString("itemName");
                                String itemLocation = documentSnapshot.getString("location");
                                String itemEmail = documentSnapshot.getString("email");
                                String itemDescription = documentSnapshot.getString("description");
                                String itemOwner = documentSnapshot.getString("finderName");
                                String itemPhnum = documentSnapshot.getString("phnum");
                                String itemDate = documentSnapshot.getString("dateFound");
                                String itemCategory = documentSnapshot.getString("category");

                                // Load the image using Glide and adjust the ImageView size
                                if (imageUrl != null && !imageUrl.isEmpty()) {
                                    Glide.with(FoundDetails.this)
                                            .load(imageUrl)
                                            .placeholder(R.drawable.placeholder_image)
                                            .error(R.drawable.baseline_image_search_24)
                                            .into(img);
                                }

                                // Set the text for the attributes in the .xml file
                                title.setText(itemName);
                                address.setText(itemLocation);
                                dateFound.setText(itemDate);
                                description.setText(itemDescription);
                                email.setText(itemEmail);
                                finderName.setText(itemOwner);
                                category.setText(itemCategory);
                               phnum=itemPhnum;
                            } else {
                                Toast.makeText(FoundDetails.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(FoundDetails.this, "Document not found!", Toast.LENGTH_SHORT).show();
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
                intent.putExtra("sms_body", "Hello, I want to inquire about item you found.");
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