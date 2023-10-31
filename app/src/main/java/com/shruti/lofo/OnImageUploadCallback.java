package com.shruti.lofo;

public interface OnImageUploadCallback {
    void onSuccess(String imageUrl);

    void onFailure();
}
