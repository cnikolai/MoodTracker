package com.nikolai.moodtracker.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nikolai.moodtracker.R;

public class FragmentOne extends Fragment {

    private static final String MY_NUM_KEY = "num";
    private static final String MY_COLOR_KEY = "color";
    public static final String SMILEY_TYPE = "smiley_type";

    private int mNum;
    private int mColor;
    private int mImageName;

    private ImageButton imageButton;


    // You can modify the parameters to pass in whatever you want
    static FragmentOne newInstance(int num, int color, int smiley_type) {
        FragmentOne f = new FragmentOne();
        Bundle args = new Bundle();
        args.putInt(MY_NUM_KEY, num);
        args.putInt(MY_COLOR_KEY, color);
        args.putInt(SMILEY_TYPE, smiley_type);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt(MY_NUM_KEY) : 0;
        mColor = getArguments() != null ? getArguments().getInt(MY_COLOR_KEY) : Color.BLACK;
        mImageName = getArguments() != null ? getArguments().getInt(SMILEY_TYPE) : R.drawable.ic_smiley_happy;

        addListenerOnButton();

    }

    public void addListenerOnButton() {



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        //change the image and the background color of the screen as the screen is swiped
        v.setBackgroundColor(mColor);
        ImageView image = (ImageView) v.findViewById(R.id.smiley_image);
        image.setImageResource(mImageName);
        //TextView textView = v.findViewById(R.id.textview);
        //textView.setText("Page " + mNum);
        imageButton = (ImageButton) v.findViewById(R.id.imageButton1);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

//                Toast.makeText(arg0.getContext(),
//                        "ImageButton clicked!", Toast.LENGTH_SHORT).show();

                EditText editText = new EditText(arg0.getContext());
                AlertDialog alertDialog = new AlertDialog.Builder(arg0.getContext())
                        //Read Update
                    .setTitle("hi")
                    .setMessage("this is my app")
                        .setView(editText)
                    .setPositiveButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions

                    }
                    })
                        .create();
                alertDialog.show();
             }

        });
        return v;
    }
}