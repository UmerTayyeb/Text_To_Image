package com.firstapp.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    // Retrofit API service instance
    private ApiService apiService;

    // Remove any duplicate mLifecycleRegistry fields

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:3000/") // Replace with your server IP and port
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Initialize ApiService
        apiService = retrofit.create(ApiService.class);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        // Initialize Retrofit
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(ApiService.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login logic here
                ToastHelper.showCustomToast(LoginActivity.this, "trying to Log in!");
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Log.d("Tag", username);
                Log.d("Tag", password);

                // Validate the username and password
                if (!username.isEmpty() && !password.isEmpty()) {
                    // Create a LoginRequest object
                    ToastHelper.showCustomToast(LoginActivity.this, "Sending req!");
                    LoginRequest loginRequest = new LoginRequest(LoginActivity.this, username, password);
                    Log.d("LoginActivity", "LoginRequest: " + loginRequest.toString());

                    // Call the login API
                    Call<JsonElement> call = apiService.login(loginRequest);
                    ToastHelper.showCustomToast(LoginActivity.this, "sent req!");

                    call.enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            if (response.isSuccessful()) {
                                // Login successful, navigate to the main activity
                                ToastHelper.showCustomToast(LoginActivity.this, "Logged in!");
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish(); // Close the login activity
                            } else {
                                // Login failed, show an error message
                                ToastHelper.showCustomToast(LoginActivity.this, "Invalid credentials");
                                ToastHelper.showCustomToast(LoginActivity.this, "Please try again!");
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
                            // Handle network or server errors here
                            Log.e("LoginActivity", "Network request failed: " + t.getMessage());
                            t.printStackTrace();
                            ToastHelper.showCustomToast(LoginActivity.this, "req Failed");
                        }
                    });
                } else {
                    // Show an error message for empty fields
                    ToastHelper.showCustomToast(LoginActivity.this, "Username and password are required!");
                }
            }
        });
    }
}
