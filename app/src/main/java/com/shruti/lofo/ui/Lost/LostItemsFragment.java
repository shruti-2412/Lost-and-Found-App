package com.shruti.lofo.ui.Lost;

import static android.app.Activity.RESULT_OK;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shruti.lofo.Utility;

import com.google.firebase.firestore.DocumentReference;
import com.shruti.lofo.R;
import java.util.Calendar;

public class LostItemsFragment extends DialogFragment {
    private ImageButton datePickerButton;

    private ImageButton timePickerButton;
    private EditText dateEdit;
    private EditText timeEdit;
    private Spinner categorySpinner;
    ImageView image;
    Button upload;
    Uri imageUri;

    EditText description;
    private EditText otherCategoryEditText;
    private EditText location ;

    final int REQ_CODE=1000;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lost_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        description = view.findViewById(R.id.description);
        datePickerButton = view.findViewById(R.id.datePickerButton);
        timePickerButton= view.findViewById(R.id.timePickerButton);
        datePickerButton.setOnClickListener(v -> showDatePicker());
        timePickerButton.setOnClickListener(v -> showTimePicker());
        dateEdit= view.findViewById(R.id.selectedDateEditText);
        timeEdit= view.findViewById(R.id.selectedTimeEditText);
        location= view.findViewById(R.id.location);


        categorySpinner = view.findViewById(R.id.categorySpinner);
        otherCategoryEditText = view.findViewById(R.id.otherCategoryEditText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        final String[] selectedCategory = new String[1];
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getCount() - 1) {
                    otherCategoryEditText.setVisibility(View.VISIBLE);

                } else {
                    otherCategoryEditText.setVisibility(View.GONE);

                }
                selectedCategory[0] = categorySpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected

            }
        });

         upload = view.findViewById(R.id.uploadImageButton);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,REQ_CODE);
            }
        });

        Button submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {

            EditText item =  view.findViewById(R.id.item_name_edittext);
            String itemName =  item.getText().toString();
            if(selectedCategory[0].equals("Other")){
                EditText other =view.findViewById(R.id.otherCategoryEditText);
                selectedCategory[0] = other.getText().toString();
            }
            String date = updateDateButton();
            String time = updateTimeButton();

            LostItems lostItem = new LostItems();
            lostItem.setItemName(itemName);
            lostItem.setCategory(selectedCategory[0]);
            lostItem.setDateLost(date);
            lostItem.setTimeLost(time);
            lostItem.setImageURI(imageUri.toString());
            lostItem.setLocation(location.getText().toString());
            lostItem.setDescription(description.getText().toString());


            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            // for ownername, phnum, email, userId, fetch this from database

            lostItem.setUserId(currentUser.getUid());
            lostItem.setEmail(currentUser.getEmail());

            saveItemToFirebase(lostItem);

        });

    }
    void saveItemToFirebase(LostItems item) {
        try{
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferrenceForItems2().document();
        documentReference.set(item).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // notes is added
                    Utility.showToast(getContext(), "Item added successfully");
                   dismiss();
                } else {
                    Utility.showToast(getContext(), "Failed to add item");
                    dismiss();
                }
            }
        });
    }
        catch (Exception e) {
            e.printStackTrace();
            // Log the error or display a message to the user
            Utility.showToast(getContext(), "An error occurred while saving data");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == REQ_CODE){
                // for gallery
                imageUri = data.getData();
                upload.setText("Image added");


            }
        }
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDateButton();
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        updateDateButton();
    }

    private void showTimePicker() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(),
                (view, hourOfDay, minute) -> {
                    mHour = hourOfDay;
                    mMinute = minute;
                    updateTimeButton();
                }, mHour, mMinute, false);
        timePickerDialog.show();
        updateTimeButton();
    }

    private String updateDateButton() {
        String date = mDay + "/" + (mMonth + 1) + "/" + mYear;
        dateEdit.setText(date);
        return date;
    }

    private String updateTimeButton() {
        String AM_PM;
        if (mHour < 12) {
            AM_PM = "AM";
        } else {
            AM_PM = "PM";
        }
        int hour = mHour % 12;
        if (hour == 0) {
            hour = 12;
        }
        String time = hour + ":" + mMinute + " " + AM_PM;
      timeEdit.setText(time);
        return time;
    }

}
