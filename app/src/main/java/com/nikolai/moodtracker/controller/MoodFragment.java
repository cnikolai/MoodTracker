package com.nikolai.moodtracker.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nikolai.moodtracker.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class MoodFragment extends Fragment {

    private static final String MY_NUM_KEY = "num";
    private static final String MY_COLOR_KEY = "color";
    public static final String SMILEY_TYPE = "smiley_type";

    private int mNum;
    private int mColor;
    private int mImageName;
    private static int mood_index;
    private String currentWeekday;

    private ImageButton mood_log;
    private ImageButton mood_chart;

    public static final String TAG = "MoodFragment";

    private SharedPreferences.Editor preferencesEditor;

    // Shared preferences object
    private SharedPreferences mPreferences;

    // Name of shared preferences file
    private String sharedPrefFile =
            "com.nikolai.moodtracker.moodsharedprefs";

    // You can modify the parameters to pass in whatever you want
    static MoodFragment newInstance(int num, int color, int smiley_type) {
        //mood_index = num+1;
        MoodFragment f = new MoodFragment();
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
        //Toast.makeText(this.getContext(), "Inside Fragment", Toast.LENGTH_SHORT).show();
        mNum = getArguments() != null ? getArguments().getInt(MY_NUM_KEY) : 0;
        mColor = getArguments() != null ? getArguments().getInt(MY_COLOR_KEY) : Color.BLACK;
        mImageName = getArguments() != null ? getArguments().getInt(SMILEY_TYPE) : R.drawable.ic_smiley_happy;

    }

    public int getShownIndex() {
        return getArguments().getInt(MY_NUM_KEY, 0);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        mPreferences = this.getContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        View v = inflater.inflate(R.layout.fragment_one, container, false);

        //change the image and the background color of the screen as the screen is swiped
        v.setBackgroundColor(mColor);
        ImageView image = (ImageView) v.findViewById(R.id.smiley_image);
        image.setImageResource(mImageName);
        Log.d(TAG, "onCreateView: mNum: " + mNum);
        mood_log = (ImageButton) v.findViewById(R.id.mood_log);
        mood_log.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog(arg0);
            }

        });
        mood_chart = (ImageButton) v.findViewById(R.id.mood_chart);
        mood_chart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), MoodChartActivity.class);
                startActivity(intent);
            }

        });
        return v;
    }

    private void showDialog(View arg0) {
        final EditText editText = new EditText(arg0.getContext());
        editText.setHint("Enter your mood log entry here...");
        AlertDialog alertDialog = new AlertDialog.Builder(arg0.getContext())
            //Read Update
            .setTitle("Mood Log")
            .setView(editText)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SimpleDateFormat sdf = new SimpleDateFormat("EEE");
                Date currentDate = new Date();
                currentWeekday = sdf.format(currentDate);
                preferencesEditor = mPreferences.edit();
                preferencesEditor.putString(currentWeekday+"moodnote", editText.getText().toString().trim());
                preferencesEditor.apply();
                Log.d(TAG, "inside edit text of moodfragment: " + editText.getText().toString().trim());
            }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            })
            .create();
        alertDialog.show();
    }

    @Override
    public void onResume () {
        super.onResume();
        Log.d(TAG, "inside onResume of fragment: " + mNum);
    }


}