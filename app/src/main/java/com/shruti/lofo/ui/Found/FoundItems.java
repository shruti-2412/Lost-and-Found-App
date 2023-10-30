package com.shruti.lofo.ui.Found;

public class FoundItems {
    private String itemName;
    private String finderName; // fetch from db
    private String finderId ; // fetch from db
    private String category;
    private String dateFound;
    private String location;
    private String email; // fetch from db
    private String phnum; // fetch from db
    private String description;
    private String imageURI;
    private String tag = "Found";
    public String getEmail() {
        return email;
    }
    public String getPhnum() {
        return phnum;
    }

    public void setPhnum(String phnum) {
        this.phnum = phnum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getDescription() {
        return description;
    }
    public String getTag(){return tag;};

    public void setDescription(String description) {
        this.description = description;
    }

    public String getfinderId() {
        return finderId;
    }

    public void setfinderId(String finderId) {
        this.finderId = finderId;
    }


    public String getDateFound() {
        return dateFound;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public void setDateFound(String dateFound) {
        this.dateFound = dateFound;
    }

    public String getfinderName() {
        return finderName;
    }

    public void setfinderName(String finderName) {
        this.finderName = finderName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public FoundItems() {
    }
}
