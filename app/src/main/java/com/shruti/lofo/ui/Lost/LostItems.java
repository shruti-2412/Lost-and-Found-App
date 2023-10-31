package com.shruti.lofo.ui.Lost;

public class LostItems {
    private String itemName;
    private String ownerName; // fetch from db
    private String userId ; // fetch from db
    private String category;
    private String dateLost;
    private String location;
    private String email; // fetch from db
    private Long phnum; // fetch from db
    private String description;
    private String timeLost;
    private String imageURI;
    private String tag = "Lost";


    public String getEmail() {
        return email;
    }

    public Long getPhnum() {
        return phnum;
    }

    public void setPhnum(Long phnum) {
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



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public String getDateLost() {
        return dateLost;
    }

    public String getTimeLost() {
        return timeLost;
    }

    public void setTimeLost(String timeLost) {
        this.timeLost = timeLost;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public void setDateLost(String dateLost) {
        this.dateLost = dateLost;
    }



    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public LostItems() {
    }
}
