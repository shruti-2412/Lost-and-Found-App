package com.shruti.lofo.ui.DashBoard;

import androidx.lifecycle.ViewModel;

public class DashBoardViewModel extends ViewModel {
    String imageURI;
    String category, description, ownerName, finderName,tag,dateLost,dateFound,itemName;
    String collectionName; // New field to store the collection name

    public DashBoardViewModel(String imageURI, String category, String description, String ownerName, String finderName, String tag,String dateLost,String itemName,String dateFound) {
        this.imageURI = imageURI;
        this.category = category;
        this.description = description;
        this.ownerName = ownerName;
        this.finderName = finderName;
        this.itemName = itemName;
        this.tag = tag;
        this.dateLost = dateLost;
        this.dateFound = dateFound;
    }

    // A method to determine the tag based on the collection name
//    private String determineTag(String collectionName) {
//        if ("lostItems".equals(collectionName)) {
//            return "lost";
//        } else if ("foundItems".equals(collectionName)) {
//            return "found";
//        } else {
//            return ""; // Handle other cases as needed
//        }
//    }
    public String getCollectionName() {
        return collectionName;
    }

    public String getImageURI() {
        return imageURI;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public String getFinderName() {
        return finderName;
    }
    public String getDateLost() {
        return dateLost;
    }
    public String getDateFound() {return dateFound;}
    public String getTag() {return tag;}
    public String getItemName(){ return itemName;}

    public DashBoardViewModel() {

//        mText = new MutableLiveData<>();
//        mText.setValue("This is DashBoard fragment");
    }

}