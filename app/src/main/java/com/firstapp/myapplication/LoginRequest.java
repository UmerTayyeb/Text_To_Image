package com.firstapp.myapplication;

import static java.security.AccessController.getContext;

import android.content.Context;

public class LoginRequest {
    private String username;
    private String password;
    private Context context;    //remove

    public LoginRequest(Context context,String username, String password) {
        this.username = username;
        this.password = password;
        this.context = context; //remove
        ToastHelper.showCustomToast(context, "Reached in Login request");
    }

    // Getters and setters (if needed)
}
