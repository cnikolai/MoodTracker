package com.nikolai.moodtracker.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nikolai.moodtracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MoodChartActivity extends AppCompatActivity {

    private String currentWeekday;
    private int Today;
    private String TodayMoodNote;
    private String Tminus1day;
    private LinearLayout lltoday;
    private float percent;
    private ConstraintLayout clmoodchartlayout;
    private ConstraintSet constraintSet;

    // Shared preferences object
    private SharedPreferences mPreferences;

    // Name of shared preferences file
    private String sharedPrefFile =
            "com.nikolai.moodtracker.moodsharedprefs";
    private SharedPreferences.Editor preferencesEditor;
    public static final String TAG = "MoodChartActivity";
    private int resID;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_chart);
        ArrayList<Integer> days = new ArrayList<Integer>();
        days.add(R.id.today);
        days.add(R.id.one_day_ago);
        days.add(R.id.two_days_ago);
        days.add(R.id.three_days_ago);
        days.add(R.id.four_days_ago);
        days.add(R.id.five_days_ago);
        days.add(R.id.six_days_ago);

        ArrayList<Integer> mood_charts = new ArrayList<Integer>();
        mood_charts.add(R.id.today_mood_note);
        mood_charts.add(R.id.one_day_ago_mood_note);
        mood_charts.add(R.id.two_days_ago_mood_note);
        mood_charts.add(R.id.three_days_ago_mood_note);
        mood_charts.add(R.id.four_days_ago_mood_note);
        mood_charts.add(R.id.five_days_ago_mood_note);
        mood_charts.add(R.id.six_days_ago_mood_note);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        // retrieve shared preferences for last 7 days
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date currentDate = new Date();

        //get the previous 1 day
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(String.valueOf(currentDate)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                currentWeekday = sdf.format(currentDate);
                Today = mPreferences.getInt(currentWeekday, 0);
                TodayMoodNote = mPreferences.getString(currentWeekday + "moodnote", "000");
                Log.d(TAG, currentWeekday + ": " + Today);
                Log.d(TAG, currentWeekday + " moodnote: " + TodayMoodNote);
            } else {
                //Decrementing the date by 1 day
                c.add(Calendar.DAY_OF_MONTH, -1);
                Tminus1day = sdf.format(c.getTime());
                currentWeekday = Tminus1day;
                Today = mPreferences.getInt(Tminus1day, 0);
                TodayMoodNote = mPreferences.getString(Tminus1day + "moodnote", "000");
                Log.d(TAG, Tminus1day + ": " + Today);
                Log.d(TAG, Tminus1day + " moodnote: " + TodayMoodNote);
            }

            // hook the data to the interface layout
            lltoday = (LinearLayout) findViewById(days.get(i));
            switch (Today) {
                case 1:
                    percent = .9999f;
                    lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                    Log.d(TAG, "onCreate: setting background color to yellow");
                    break;
                case 2:
                    percent = .8f;
                    lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                    Log.d(TAG, "onCreate: setting background color to green");
                    break;
                case 3:
                    percent = .6f;
                    lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                    Log.d(TAG, "onCreate: setting background color to blue");
                    break;
                case 4:
                    percent = .4f;
                    lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey));
                    Log.d(TAG, "onCreate: setting background color to grey");
                    break;
                case 5:
                    percent = .2f;
                    lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                    Log.d(TAG, "onCreate: setting background color to red");
                    break;
                default:
                    //there is no data for this day
                    percent = .9999f;
                    lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
                    Log.d(TAG, "onCreate: setting background color to green inside default");
            }

            //set the constraint width (width of the layout for the day)
            clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
            constraintSet = new ConstraintSet();
            constraintSet.clone(clmoodchartlayout);
            constraintSet.constrainPercentWidth(days.get(i), percent);
            constraintSet.applyTo(clmoodchartlayout);

            //display the 000 for this day, if it exists
            ImageView ivToday = (ImageView) findViewById(mood_charts.get(i));
            if (TodayMoodNote != "000") {
                final String mymoodnote = TodayMoodNote;
                ivToday.setVisibility(View.VISIBLE);
                ivToday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        Toast.makeText(arg0.getContext(), mymoodnote, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}