package com.shruti.lofo.ui.DashBoard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashBoardViewModel extends ViewModel {

    int img;
    String name,description,posted_by;

    public DashBoardViewModel(int img,String name,String description,String posted_by){
        this.img = img;
        this.name = name;
        this.description = description;
        this.posted_by = posted_by;
    }

    public DashBoardViewModel() {

//        mText = new MutableLiveData<>();
//        mText.setValue("This is DashBoard fragment");
    }

}