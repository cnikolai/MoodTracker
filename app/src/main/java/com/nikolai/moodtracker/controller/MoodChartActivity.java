package com.nikolai.moodtracker.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.nikolai.moodtracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MoodChartActivity extends AppCompatActivity {

    private String currentWeekday;
    private int Today, TodayMinus1Day, TodayMinus2Days, TodayMinus3Days, TodayMinus4Days, TodayMinus5Days, TodayMinus6Days;
    private String Tminus1day;
    private String Tminus2days;
    private String Tminus3days;
    private String Tminus4days;
    private String Tminus5days;
    private String Tminus6days;
    private LinearLayout lltoday;
    private LinearLayout lltodayminus1day;
    private LinearLayout lltodayminus2days;
    private LinearLayout lltodayminus3days;
    private LinearLayout lltodayminus4days;
    private LinearLayout lltodayminus5days;
    private LinearLayout lltodayminus6days;

    // Shared preferences object
    private SharedPreferences mPreferences;

    // Name of shared preferences file
    private String sharedPrefFile =
            "com.nikolai.moodtracker.moodsharedprefs";
    private SharedPreferences.Editor preferencesEditor;
    public static final String TAG = "MoodChartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_chart);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        //TODO: what if there is not enough data for the last 7 days in shared preferences?

        // retrieve shared preferences for last 7 days
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date currentDate = new Date();
        currentWeekday = sdf.format(currentDate);
        Today = mPreferences.getInt(currentWeekday,0);
        Log.d(TAG, currentWeekday+": "+Today);


        //get the previous 1 day
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(String.valueOf(currentDate)));
        }catch(ParseException e){
            e.printStackTrace();
        }
        //Decrementing the date by 1 day
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus1day = sdf.format(c.getTime());
        TodayMinus1Day = mPreferences.getInt(Tminus1day,0);
        Log.d(TAG, Tminus1day+": "+TodayMinus1Day);


        //Decrementing the date by 2 days
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus2days = sdf.format(c.getTime());
        TodayMinus2Days = mPreferences.getInt(Tminus2days,0);
        Log.d(TAG, Tminus2days+": "+TodayMinus2Days);


        //Decrementing the date by 3 days
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus3days = sdf.format(c.getTime());
        TodayMinus3Days = mPreferences.getInt(Tminus3days,0);
        Log.d(TAG, Tminus3days+": "+TodayMinus3Days);


        //Decrementing the date by 4 days
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus4days = sdf.format(c.getTime());
        TodayMinus4Days = mPreferences.getInt(Tminus4days,0);
        Log.d(TAG, Tminus4days+": "+TodayMinus4Days);

        //Decrementing the date by 5 days
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus5days = sdf.format(c.getTime());
        TodayMinus5Days = mPreferences.getInt(Tminus5days,0);
        Log.d(TAG, Tminus5days+": "+TodayMinus5Days);


        //Decrementing the date by 6 days
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus6days = sdf.format(c.getTime());
        TodayMinus6Days = mPreferences.getInt(Tminus6days,0);
        Log.d(TAG, Tminus6days+": "+TodayMinus6Days);

        //TODO: if there is a note, then retrieve the note and show the note


        // hook the data to the interface layout
        lltoday = (LinearLayout) findViewById(R.id.today);
        //lltoday.layout_constraintWidth_percent = Today/5; //".6";
        ConstraintLayout clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(clmoodchartlayout);
        constraintSet.constrainPercentWidth(R.id.today,0.2f);
        constraintSet.applyTo(clmoodchartlayout);

        //TODO: change the color of the background,
        //TODO: do to a for loop that applies changes to all items
        //TODO: see where can improve efficiency

        lltodayminus1day = (LinearLayout) findViewById(R.id.one_day_ago);
        lltodayminus1day = (LinearLayout) findViewById(R.id.one_day_ago);
        lltodayminus2days = (LinearLayout) findViewById(R.id.two_days_ago);
        lltodayminus3days = (LinearLayout) findViewById(R.id.three_days_ago);
        lltodayminus4days = (LinearLayout) findViewById(R.id.four_days_ago);
        lltodayminus5days = (LinearLayout) findViewById(R.id.five_days_ago);
        lltodayminus6days = (LinearLayout) findViewById(R.id.six_days_ago);
        //TODO how do you end an activity ... and ... return to the previous activity? (finish()); yes


    }

}
