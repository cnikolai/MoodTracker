package com.nikolai.moodtracker.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
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
    private int Today;
    private int TodayMinus1Day;
    private int TodayMinus2Days;
    private int TodayMinus3Days;
    private int TodayMinus4Days;
    private int TodayMinus5Days;
    private int TodayMinus6Days;
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

        //Log.d(TAG, "onCreate: today: "+Today);
        lltoday = (LinearLayout) findViewById(R.id.today);
        switch(Today) {
            case 1:
                percent = (float) Today/5;
                lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                Log.d(TAG, "onCreate: setting background color to yellow");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.today, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 2:
                percent = (float) Today/5;
                lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.today, percent);
                constraintSet.applyTo(clmoodchartlayout);
                Log.d(TAG, "onCreate: setting background color to green");
                break;
            case 3:
                percent = (float) Today/5;
                lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                Log.d(TAG, "onCreate: setting background color to blue");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.today, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 4:
                percent = (float) Today/5;
                lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey));
                Log.d(TAG, "onCreate: setting background color to grey");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.today, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 5:
                percent = .9999f;
                lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                Log.d(TAG, "onCreate: setting background color to red");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.today, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            default:
                //there is no data for this day
                percent = .9999f;
                lltoday.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
                Log.d(TAG, "onCreate: setting background color to green inside default");
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.today, percent);
                constraintSet.applyTo(clmoodchartlayout);
        }
        //TODO: do to a for loop that applies changes to all items
        //TODO: see where can improve efficiency

        lltodayminus1day = (LinearLayout) findViewById(R.id.one_day_ago);
        switch(TodayMinus1Day) {
            case 1:
                percent = (float) TodayMinus1Day/5;
                lltodayminus1day.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                Log.d(TAG, "onCreate: setting background color to yellow");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.one_day_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 2:
                percent = (float) TodayMinus1Day/5;
                lltodayminus1day.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.one_day_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                Log.d(TAG, "onCreate: setting background color to green");
                break;
            case 3:
                percent = (float) TodayMinus1Day/5;
                lltodayminus1day.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                Log.d(TAG, "onCreate: setting background color to blue");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.one_day_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 4:
                percent = (float) TodayMinus1Day/5;
                lltodayminus1day.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey));
                Log.d(TAG, "onCreate: setting background color to grey");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.one_day_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 5:
                percent = .9999f;
                lltodayminus1day.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                Log.d(TAG, "onCreate: setting background color to red");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.one_day_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            default:
                //there is no data for this day
                percent = .9999f;
                lltodayminus1day.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
                Log.d(TAG, "onCreate: setting background color to green inside default");
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.one_day_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
        }


        lltodayminus2days = (LinearLayout) findViewById(R.id.two_days_ago);
        switch(TodayMinus2Days) {
            case 1:
                percent = (float) TodayMinus2Days/5;
                lltodayminus2days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                Log.d(TAG, "onCreate: setting background color to yellow");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.two_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 2:
                percent = (float) TodayMinus2Days/5;
                lltodayminus2days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.two_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                Log.d(TAG, "onCreate: setting background color to green");
                break;
            case 3:
                percent = (float) TodayMinus2Days/5;
                lltodayminus2days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                Log.d(TAG, "onCreate: setting background color to blue");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.two_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 4:
                percent = (float) TodayMinus2Days/5;
                lltodayminus2days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey));
                Log.d(TAG, "onCreate: setting background color to grey");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.two_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 5:
                percent = .9999f;
                lltodayminus2days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                Log.d(TAG, "onCreate: setting background color to red");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.two_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            default:
                //there is no data for this day
                percent = .9999f;
                lltodayminus2days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
                Log.d(TAG, "onCreate: setting background color to green inside default");
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.two_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
        }

        lltodayminus3days = (LinearLayout) findViewById(R.id.three_days_ago);
        switch(TodayMinus3Days) {
            case 1:
                percent = (float) TodayMinus3Days/5;
                lltodayminus3days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                Log.d(TAG, "onCreate: setting background color to yellow");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.three_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 2:
                percent = (float) TodayMinus3Days/5;
                lltodayminus3days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.three_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                Log.d(TAG, "onCreate: setting background color to green");
                break;
            case 3:
                percent = (float) TodayMinus3Days/5;
                lltodayminus3days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                Log.d(TAG, "onCreate: setting background color to blue");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.three_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 4:
                percent = (float) TodayMinus3Days/5;
                lltodayminus3days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey));
                Log.d(TAG, "onCreate: setting background color to grey");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.three_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 5:
                percent = .9999f;
                lltodayminus3days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                Log.d(TAG, "onCreate: setting background color to red");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.three_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            default:
                //there is no data for this day
                percent = .9999f;
                lltodayminus3days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
                Log.d(TAG, "onCreate: setting background color to green inside default");
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.three_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
        }

        lltodayminus4days = (LinearLayout) findViewById(R.id.four_days_ago);
        switch(TodayMinus4Days) {
            case 1:
                percent = (float) TodayMinus4Days/5;
                lltodayminus4days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                Log.d(TAG, "onCreate: setting background color to yellow");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.four_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 2:
                percent = (float) TodayMinus4Days/5;
                lltodayminus4days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.four_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                Log.d(TAG, "onCreate: setting background color to green");
                break;
            case 3:
                percent = (float) TodayMinus4Days/5;
                lltodayminus4days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                Log.d(TAG, "onCreate: setting background color to blue");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.four_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 4:
                percent = (float) TodayMinus4Days/5;
                lltodayminus4days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey));
                Log.d(TAG, "onCreate: setting background color to grey");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.four_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 5:
                percent = .9999f;
                lltodayminus4days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                Log.d(TAG, "onCreate: setting background color to red");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.four_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            default:
                //there is no data for this day
                percent = .9999f;
                lltodayminus4days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
                Log.d(TAG, "onCreate: setting background color to green inside default");
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.four_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
        }

        lltodayminus5days = (LinearLayout) findViewById(R.id.five_days_ago);
        switch(TodayMinus5Days) {
            case 1:
                percent = (float) TodayMinus5Days/5;
                lltodayminus5days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                Log.d(TAG, "onCreate: setting background color to yellow");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.five_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 2:
                percent = (float) TodayMinus5Days/5;
                lltodayminus5days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.five_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                Log.d(TAG, "onCreate: setting background color to green");
                break;
            case 3:
                percent = (float) TodayMinus5Days/5;
                lltodayminus5days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                Log.d(TAG, "onCreate: setting background color to blue");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.five_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 4:
                percent = (float) TodayMinus5Days/5;
                lltodayminus5days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey));
                Log.d(TAG, "onCreate: setting background color to grey");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.five_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 5:
                percent = .9999f;
                lltodayminus5days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                Log.d(TAG, "onCreate: setting background color to red");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.five_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            default:
                //there is no data for this day
                percent = .9999f;
                lltodayminus5days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
                Log.d(TAG, "onCreate: setting background color to green inside default");
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.five_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
        }

        lltodayminus6days = (LinearLayout) findViewById(R.id.six_days_ago);
        switch(TodayMinus6Days) {
            case 1:
                percent = (float) TodayMinus6Days/5;
                lltodayminus6days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                Log.d(TAG, "onCreate: setting background color to yellow");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.six_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 2:
                percent = (float) TodayMinus6Days/5;
                lltodayminus6days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.six_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                Log.d(TAG, "onCreate: setting background color to green");
                break;
            case 3:
                percent = (float) TodayMinus6Days/5;
                lltodayminus6days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                Log.d(TAG, "onCreate: setting background color to blue");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.six_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 4:
                percent = (float) TodayMinus6Days/5;
                lltodayminus6days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey));
                Log.d(TAG, "onCreate: setting background color to grey");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.six_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            case 5:
                percent = .9999f;
                lltodayminus6days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                Log.d(TAG, "onCreate: setting background color to red");
                // set the constraint width
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.six_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
                break;
            default:
                //there is no data for this day
                percent = .9999f;
                lltodayminus6days.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
                Log.d(TAG, "onCreate: setting background color to green inside default");
                clmoodchartlayout = (ConstraintLayout) findViewById(R.id.mood_chart_parent);
                constraintSet = new ConstraintSet();
                constraintSet.clone(clmoodchartlayout);
                constraintSet.constrainPercentWidth(R.id.six_days_ago, percent);
                constraintSet.applyTo(clmoodchartlayout);
        }

    }

}
