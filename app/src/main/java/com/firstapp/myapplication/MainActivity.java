package com.firstapp.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
//import openai;

import com.bumptech.glide.Glide;
import com.firstapp.myapplication.databinding.ActivityMainBinding;

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

public class MainActivity extends AppCompatActivity {

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


        binding.btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = binding.inputText.getText().toString();
                callAPI(text);
            }
        });
    }

    private void callAPI(String text) {
        progress(true);
        JSONObject object = new JSONObject();

        try {
            Log.d("MyTag",text);
            Log.d("Tag","started Call API");
            object.put("prompt",text);
            object.put("size","256x256");
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Log.d("Tag","requesting API");
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
                    Log.d("Tag","getting data from API\n\n");
                    //JSONObject jsonObject = new JSONObject(response.body().string());
                    String responseBody = response.body().string();
                    Log.d("Tag", "Response\n\n");
                    Log.d("Tag", responseBody); // Log the response
                    Log.d("Tag", "Response done\n\n");
                    JSONObject jsonObject = new JSONObject(responseBody); // Parse the JSON
                    imageUrl = jsonObject.getJSONArray("data").getJSONObject(0).getString("url");
                    Log.d("Tag","Got data from API");
                    loadImage(imageUrl);
                    Log.d("Tag","Showing pic");
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