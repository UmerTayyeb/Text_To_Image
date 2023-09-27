package com.firstapp.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastHelper {
    public static void showCustomToast(Context context, String message) {
        // Inflate the custom toast layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast_layout, null);

        // Find the TextView inside the custom layout
        TextView text = layout.findViewById(R.id.textViewToast);

        // Set the toast message text
        text.setText(message);

        // Create and show the custom toast
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
