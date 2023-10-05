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
    private String prompt0 = "";
    public void setPromptSelectedListener(OnPromptSelectedListener listener) {
        this.promptSelectedListener = listener;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_layout, container, false);


        ImageView imageView1 = view.findViewById(R.id.cardimageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt0 = "Realistic pic of";
               /* Log.d("prompt in img 1",prompt0);
                Log.d("Tag", "img 1 is pressed");*/
                //Toast.makeText(BottomSheetFragment.this, "Blah blah", Toast.LENGTH_SHORT).show();
                //ToastHelper.showCustomToast(getContext(), "img 1 is pressed!");
                if (promptSelectedListener != null) {
                    promptSelectedListener.onPromptSelected(prompt0);
                }
                dismiss();
            }
       });

        ImageView imageView2 = view.findViewById(R.id.cardimageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt0 = "Anime pic of";
                Log.d("Tag", "img 2 is pressed");
                //Toast.makeText(BottomSheetFragment.this, "Blah blah", Toast.LENGTH_SHORT).show();
               // ToastHelper.showCustomToast(getContext(), "img 2 is pressed!");
                if (promptSelectedListener != null) {
                    promptSelectedListener.onPromptSelected(prompt0);
                }
                dismiss();
            }
        });

        ImageView imageView3 = view.findViewById(R.id.cardimageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt0 = "Creative pic of";
                Log.d("Tag", "img 3 is pressed");
                //Toast.makeText(BottomSheetFragment.this, "Blah blah", Toast.LENGTH_SHORT).show();
               // ToastHelper.showCustomToast(getContext(), "img 3 is pressed!");
                if (promptSelectedListener != null) {
                    promptSelectedListener.onPromptSelected(prompt0);
                }
                dismiss();
            }
        });

        ImageView imageView4 = view.findViewById(R.id.cardimageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt0 = "Portrait pic of";
                Log.d("Tag", "img 4 is pressed");
               // ToastHelper.showCustomToast(getContext(), "img 4 is pressed!");
                if (promptSelectedListener != null) {
                    promptSelectedListener.onPromptSelected(prompt0);
                }
                dismiss();
            }
        });

        ImageView imageView5 = view.findViewById(R.id.cardimageView5);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt0 = "Dream shaper pic of";
                Log.d("Tag", "img 5 is pressed");
             //   ToastHelper.showCustomToast(getContext(), "img 5 is pressed!");
                if (promptSelectedListener != null) {
                    promptSelectedListener.onPromptSelected(prompt0);
                }
                dismiss();
            }
        });

        ImageView imageView6 = view.findViewById(R.id.cardimageView6);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt0 = "Disney pic of";
                Log.d("Tag", "img 6 is pressed");
              //  ToastHelper.showCustomToast(getContext(), "img 6 is pressed!");
                if (promptSelectedListener != null) {
                    promptSelectedListener.onPromptSelected(prompt0);
                }
                dismiss();
            }
        });

        ImageView imageView7 = view.findViewById(R.id.cardimageView7);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt0 = "Absolute reality pic of";
                Log.d("Tag", "img 7 is pressed");
                //  ToastHelper.showCustomToast(getContext(), "img 6 is pressed!");
                if (promptSelectedListener != null) {
                    promptSelectedListener.onPromptSelected(prompt0);
                }
                dismiss();
            }
        });

        ImageView imageView8 = view.findViewById(R.id.cardimageView8);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt0 = "Magic mix pic of";
                Log.d("Tag", "img 8 is pressed");
                //  ToastHelper.showCustomToast(getContext(), "img 6 is pressed!");
                if (promptSelectedListener != null) {
                    promptSelectedListener.onPromptSelected(prompt0);
                }
                dismiss();
            }
        });
        return view;
    }
    public interface OnPromptSelectedListener {
        void onPromptSelected(String prompt);
    }
    private OnPromptSelectedListener promptSelectedListener;


}
