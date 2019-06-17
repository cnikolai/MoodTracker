package com.nikolai.moodtracker.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.Locale;

public class MoodFragment extends Fragment {

    private static final String MY_NUM_KEY = "num";
    private static final String MY_COLOR_KEY = "color";
    private static final String SMILEY_TYPE = "smiley_type";

    private int mNum;
    private int mColor;
    private int mImageName;
    private String currentWeekday;

    private ImageButton mood_log;
    private ImageButton mood_chart;

    private static final String TAG = "MoodFragment";

    // Shared preferences object
    //private SharedPreferences mPreferences;

    // Name of shared preferences file
    private final String sharedPrefFile =
            "com.nikolai.moodtracker.moodsharedprefs";
    private DataStorage dataStorage;

    /**
     * Instantiates a new fragment for each mood.  Passes arguments to the fragment. In this case, the number of the fragment, the color of the fragment layout, and the picture of the fragment that corresponds to the color and number of the fragment.
     *
     * @param num         the number of the fragment
     * @param color       the color that corresponds to the fragment mood that is being displayed
     * @param smiley_type the picture that corresponds to the fragment mood that is being displayed
     * @return a fragment for each mood
     */
    // We can modify the parameters to pass in whatever you want
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

    /**
     * Called when this fragment is started.  Gets the parameters that have been passed to it. Sets them to default values.
     *
     * @param savedInstanceState state of view before when it was killed by system
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(this.getContext(), "Inside Fragment", Toast.LENGTH_SHORT).show();
        mNum = getArguments() != null ? getArguments().getInt(MY_NUM_KEY) : 0;
        mColor = getArguments() != null ? getArguments().getInt(MY_COLOR_KEY) : Color.BLACK;
        mImageName = getArguments() != null ? getArguments().getInt(SMILEY_TYPE) : R.drawable.ic_smiley_happy;

    }

    /**
     * returns the index of the fragment that we are on
     *
     * @return the index of the fragment that we are on
     */
    public int getShownIndex() {
        return getArguments().getInt(MY_NUM_KEY, 0);
    }

    /**
     * Called when the view is created.
     *
     * @param inflater prepares and sets the layout
     * @param container
     * @param savedInstanceState state of view before when it was killed by system
     * @return a view
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        //mPreferences = this.getContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        dataStorage = new DataStorage(this.getContext());


        View v = inflater.inflate(R.layout.fragment_one, container, false);

        //change the image and the background color of the screen as the screen is swiped
        v.setBackgroundColor(mColor);
        ImageView image = v.findViewById(R.id.smiley_image);
        image.setImageResource(mImageName);
        Log.d(TAG, "onCreateView: mNum: " + mNum);
        mood_log = v.findViewById(R.id.mood_log);
        mood_log.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog(arg0);
            }

        });
        mood_chart = v.findViewById(R.id.mood_chart);
        mood_chart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), MoodChartActivity.class);
                startActivity(intent);
            }

        });
        return v;
    }

    /**
     * allows a user to enter text into a mood note
     *
     * @param arg0 the view that we are on
     */
    private void showDialog(View arg0) {
        final EditText editText = new EditText(arg0.getContext());
        editText.setHint("Enter your mood log entry here...");
        AlertDialog alertDialog = new AlertDialog.Builder(arg0.getContext())
                //Read Update
                .setTitle("Mood Log")
                .setView(editText)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SimpleDateFormat sdf = new SimpleDateFormat("EEE", Locale.US);
                        Date currentDate = new Date();
                        currentWeekday = sdf.format(currentDate);
//                        preferencesEditor = mPreferences.edit();
//                        preferencesEditor.putString(currentWeekday + "moodnote", editText.getText().toString().trim());
//                        preferencesEditor.apply();
                          dataStorage.storeStringData(currentWeekday + "moodnote", editText.getText().toString().trim());
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

    /**
     * lifecycle method of the fragment.  Called when the fragment resumes.
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "inside onResume of fragment: " + mNum);
    }


}