package com.firstapp.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_layout, container, false);


        ImageView imageView1 = view.findViewById(R.id.cardimageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "img is pressed");
                //Toast.makeText(BottomSheetFragment.this, "Blah blah", Toast.LENGTH_SHORT).show();
                ToastHelper.showCustomToast(getContext(), "img 1 is pressed!");
                dismiss();

            }
       });

        ImageView imageView2 = view.findViewById(R.id.cardimageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "img is pressed");
                //Toast.makeText(BottomSheetFragment.this, "Blah blah", Toast.LENGTH_SHORT).show();
                ToastHelper.showCustomToast(getContext(), "img 2 is pressed!");
                dismiss();

            }
        });

        ImageView imageView3 = view.findViewById(R.id.cardimageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "img is pressed");
                //Toast.makeText(BottomSheetFragment.this, "Blah blah", Toast.LENGTH_SHORT).show();
                ToastHelper.showCustomToast(getContext(), "img 3 is pressed!");
                dismiss();

            }
        });

        ImageView imageView4 = view.findViewById(R.id.cardimageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "img is pressed");
                ToastHelper.showCustomToast(getContext(), "img 4 is pressed!");
                dismiss();

            }
        });

        ImageView imageView5 = view.findViewById(R.id.cardimageView5);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "img is pressed");
                ToastHelper.showCustomToast(getContext(), "img 5 is pressed!");
                dismiss();

            }
        });

        ImageView imageView6 = view.findViewById(R.id.cardimageView6);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "img is pressed");
                ToastHelper.showCustomToast(getContext(), "img 6 is pressed!");
                dismiss();
            }
        });
        return view;
    }
}
