package com.shruti.lofo.ui.Found;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FoundViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FoundViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Found fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}