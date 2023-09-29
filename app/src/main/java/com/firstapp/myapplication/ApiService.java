package com.firstapp.myapplication;
import android.util.Log;

import com.google.gson.JsonElement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<JsonElement> login(@Body LoginRequest loginRequest);

}

