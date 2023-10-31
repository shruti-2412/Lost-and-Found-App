package com.shruti.lofo.ui.Lost;

import android.os.Bundle;
import android.util.Log;
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
import com.shruti.lofo.R;



public class LostDetails extends AppCompatActivity {

    private ImageView img;
    private TextView title, address, mail, description, ownerName, phnum, dateLost, time, category;
    private Button call, sms, backBtn;
    private FirebaseFirestore db; // Initialize Firestore


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lost_details);

        // Initialize Firestore
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

        // Get the item ID from the Intent
        String itemId = getIntent().getStringExtra("itemId");

        // Fetch item details from Firestore based on the itemId
        DocumentReference itemRef = db.collection("lostItems").document(itemId);
        itemRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Get the data from the document
                    String imageUrl = documentSnapshot.getString("imageURI");
                    String itemTitle = documentSnapshot.getString("itemName");
                    String itemAddress = documentSnapshot.getString("location");
                    String itemMail = documentSnapshot.getString("email");
                    String itemDateLost = documentSnapshot.getString("dateLost");
                    String itemTime = documentSnapshot.getString("timeLost");
                    String itemDescription = documentSnapshot.getString("description");
                    String itemOwnerName = documentSnapshot.getString("ownerName");
                    String itemCategory = documentSnapshot.getString("category");
                    String itemPhNum = documentSnapshot.getString("phnum");


                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Log.d("Lost", "Image URL: " + imageUrl);
                        Glide.with(img)
                                .load(imageUrl)
                                .into(img);
                    }

                    title.setText(itemTitle);
                    address.setText(itemAddress);
                    mail.setText(itemMail);
                    dateLost.setText(itemDateLost);
                    time.setText(itemTime);
                    category.setText(itemCategory);
                    description.setText(itemDescription);
                    ownerName.setText(itemOwnerName);
                    phnum.setText(itemPhNum);
                }
                else {
                    System.out.println("Data not found!!");
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