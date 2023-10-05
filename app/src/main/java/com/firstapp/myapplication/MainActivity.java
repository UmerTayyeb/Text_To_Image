package com.firstapp.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firstapp.myapplication.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements BottomSheetFragment.OnPromptSelectedListener {
    private String prompt0 = "";    //string for mode selection

    ActivityMainBinding binding;
    //Post to server request
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    String imageUrl;
    String fileName = "code";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        findViewById(R.id.btn_getpro).setOnClickListener(new View.OnClickListener() {   //Login activity
            @Override
            public void onClick(View view) {
               // Log.d("Tag","starting loging activity!");
                Intent intent = new Intent(MainActivity.this, login_activity.class);
                startActivity(intent);
            }
        });




        findViewById(R.id.mode).setOnClickListener(new View.OnClickListener() {     //mode selector menu
            @Override
            public void onClick(View view) {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.setPromptSelectedListener(MainActivity.this);   //listening for prompt0 in BottomSheetFragment
               // Log.d("mode tag",prompt0);
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        //show sizes
        Spinner spinner = findViewById(R.id.spinner);
        // Define an array of options
        String[] options = {"Size", "256x256", "512x512", "1024x1024"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Check if the hint item is selected (position 0)
                if (position == 0) {
                    // The hint item is selected, handle it here or take no action.
                    Log.d("Tag","option 0 is selected");
                } else {
                    // A regular item is selected, you can perform actions here.
                    Log.d("Tag", String.format("Option %d is selected", position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case when nothing is selected (optional).
            }
        });

        binding.btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Log.d("prompt in btn_gen",prompt0);

                String text;
                text = prompt0 + " " + binding.inputText.getText().toString();

                callAPI(text);
            }
        });
    }

    public void onPromptSelected(String prompt) {
        // Receive the selected prompt from BottomSheetFragment
        prompt0 = prompt;
        //Log.d("onPromptSelected tag", prompt0);
    }


    private void callAPI(String text) {
        progress(true);
        JSONObject object = new JSONObject();
        try {
            Log.d("MyTag",text);
            ToastHelper.showCustomToast(MainActivity.this,text);    //debugging
            //Log.d("Tag","started Call API");
            object.put("prompt",text);
            object.put("size","256x256");
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //Log.d("Tag","requesting API");
        RequestBody requestBody = RequestBody.create(object.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/images/generations")
                .header("Authorization","Bearer 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")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(MainActivity.this, "Failed to generate image", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                try {
                    //Log.d("Tag","getting data from API\n\n");
                    //JSONObject jsonObject = new JSONObject(response.body().string());
                    String responseBody = response.body().string();
                    /*Log.d("Tag", "Response\n\n");
                    Log.d("Tag", responseBody); // Log the response
                    Log.d("Tag", "Response done\n\n");*/
                    JSONObject jsonObject = new JSONObject(responseBody); // Parse the JSON
                    imageUrl = jsonObject.getJSONArray("data").getJSONObject(0).getString("url");
                    //Log.d("Tag","Got data from API");
                    loadImage(imageUrl);
                    Toast.makeText(MainActivity.this, "yeah", Toast.LENGTH_SHORT).show();
                    progress(false);
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void loadImage(String imageUrl) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Glide
                        .with(MainActivity.this)
                        .load(imageUrl)
                        .centerCrop()
                        .placeholder(R.drawable.loading)
                        .into(binding.generatedImg);
            }
        });
    }

    private  void progress(boolean inProgress){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (inProgress){
                    binding.animationView.setVisibility(View.VISIBLE);
                    binding.cardView.setVisibility(View.GONE);
                    binding.btnDownload.setVisibility(View.GONE);
                }
                else {
                    binding.animationView.setVisibility(View.GONE);
                    binding.cardView.setVisibility(View.VISIBLE);
                    binding.btnDownload.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}