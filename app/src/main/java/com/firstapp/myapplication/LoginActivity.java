package com.firstapp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login logic here
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Validate the username and password
                if (isValidCredentials(username, password)) {
                    // Successful login, navigate to the main activity
                    ToastHelper.showCustomToast(LoginActivity.this, "Logged in!");
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish(); // Close the login activity
                } else {
                    // Show an error message (e.g., incorrect credentials)
                    ToastHelper.showCustomToast(LoginActivity.this, "Invalid credentials");
                    ToastHelper.showCustomToast(LoginActivity.this, "Please try again!");
                }
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        // Implement your authentication logic here
        // For simplicity, this example always returns true
        return true;
    }
}
