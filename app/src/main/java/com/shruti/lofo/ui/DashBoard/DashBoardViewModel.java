package com.shruti.lofo.ui.DashBoard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashBoardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashBoardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is DashBoard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}