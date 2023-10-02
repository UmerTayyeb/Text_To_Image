package com.firstapp.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class register_activity extends AppCompatActivity {

    TextInputEditText emailRegister, passwordRegister;
    Button buttonReg;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        mAuth = FirebaseAuth.getInstance();
        emailRegister = findViewById(R.id.usernameRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        buttonReg = findViewById(R.id.registerButton);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(emailRegister.getText());
                password = String.valueOf(passwordRegister.getText());

                //check for email n pass
                if (TextUtils.isEmpty(email)){
                    ToastHelper.showCustomToast(register_activity.this,"Fill out email");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    ToastHelper.showCustomToast(register_activity.this,"Fill out password");
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    ToastHelper.showCustomToast(register_activity.this, "Registered!");
                                    startActivity(new Intent(register_activity.this, MainActivity.class));
                                    finish(); // Close the register activity
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());


                                }
                            }
                        });


            }
        });



    }
}