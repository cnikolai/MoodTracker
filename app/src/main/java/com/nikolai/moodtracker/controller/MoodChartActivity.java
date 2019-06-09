package com.nikolai.moodtracker.controller;

        import android.content.DialogInterface;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.constraint.ConstraintLayout;
        import android.support.constraint.ConstraintSet;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageView;
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
    private ImageView ivToday;
    private ImageView ivTodayMinus1Day;
    private ImageView ivTodayMinus2Days;
    private ImageView ivTodayMinus3Days;
    private ImageView ivTodayMinus4Days;
    private ImageView ivTodayMinus5Days;
    private ImageView ivTodayMinus6Days;
    private String TodayMoodNote;
    private String TodayMinus1DayMoodNote;
    private String TodayMinus2DaysMoodNote;
    private String TodayMinus3DaysMoodNote;
    private String TodayMinus4DaysMoodNote;
    private String TodayMinus5DaysMoodNote;
    private String TodayMinus6DaysMoodNote;
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

        // retrieve shared preferences for last 7 days
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date currentDate = new Date();
        currentWeekday = sdf.format(currentDate);
        Today = mPreferences.getInt(currentWeekday,0);
        TodayMoodNote = mPreferences.getString(currentWeekday + "moodnote","000");
        Log.d(TAG, currentWeekday+": "+Today);
        Log.d(TAG, currentWeekday+" moodnote: "+TodayMoodNote);


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
        TodayMinus1DayMoodNote = mPreferences.getString(Tminus1day + "moodnote","000");
        Log.d(TAG, Tminus1day+": "+TodayMinus1Day);
        Log.d(TAG, Tminus1day+" moodnote: "+TodayMinus1DayMoodNote);


        //Decrementing the date by 2 days
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus2days = sdf.format(c.getTime());
        TodayMinus2Days = mPreferences.getInt(Tminus2days,0);
        TodayMinus2DaysMoodNote = mPreferences.getString(Tminus2days + "moodnote","000");
        Log.d(TAG, Tminus2days+": "+TodayMinus2Days);
        Log.d(TAG, Tminus2days+" moodnote: "+TodayMinus2DaysMoodNote);

        //Decrementing the date by 3 days
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus3days = sdf.format(c.getTime());
        TodayMinus3Days = mPreferences.getInt(Tminus3days,0);
        TodayMinus3DaysMoodNote = mPreferences.getString(Tminus3days + "moodnote","000");
        Log.d(TAG, Tminus3days+": "+TodayMinus3Days);
        Log.d(TAG, Tminus3days+" moodnote: "+TodayMinus3DaysMoodNote);


        //Decrementing the date by 4 days
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus4days = sdf.format(c.getTime());
        TodayMinus4Days = mPreferences.getInt(Tminus4days,0);
        TodayMinus4DaysMoodNote = mPreferences.getString(Tminus4days + "moodnote","000");
        Log.d(TAG, Tminus4days+": "+TodayMinus4Days);
        Log.d(TAG, Tminus4days+" moodnote: "+TodayMinus4DaysMoodNote);

        //Decrementing the date by 5 days
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus5days = sdf.format(c.getTime());
        TodayMinus5Days = mPreferences.getInt(Tminus5days,0);
        TodayMinus5DaysMoodNote = mPreferences.getString(Tminus5days + "moodnote","000");
        Log.d(TAG, Tminus5days+": "+TodayMinus5Days);
        Log.d(TAG, Tminus5days+" moodnote: "+TodayMinus5DaysMoodNote);


        //Decrementing the date by 6 days
        c.add(Calendar.DAY_OF_MONTH, -1);
        Tminus6days = sdf.format(c.getTime());
        TodayMinus6Days = mPreferences.getInt(Tminus6days,0);
        //TODO: if it exists...
        TodayMinus6DaysMoodNote = mPreferences.getString(Tminus6days + "moodnote","000");
        Log.d(TAG, Tminus6days+": "+TodayMinus6Days);
        Log.d(TAG, Tminus6days+" moodnote: "+TodayMinus6DaysMoodNote);

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
        //display the 000 for this day, if it exists
        ivToday = (ImageView) findViewById(R.id.today_mood_note);
        if (TodayMoodNote != "000") {
            //display the 000 on the screen and add a clickable event to it.
            ivToday.setVisibility(View.VISIBLE);
            ivToday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final String moodnote = mPreferences.getString(currentWeekday + "moodnote", "000");
                    AlertDialog alertDialog = new AlertDialog.Builder(arg0.getContext())
                            //Read Update
                            .setTitle("Mood Log")
                            .setMessage(moodnote)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }
            });
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
        //display the 000 for this day, if it exists
        ivTodayMinus1Day = (ImageView) findViewById(R.id.one_day_ago_mood_note);
        if (TodayMinus1DayMoodNote != "000") {
            //display the 000 on the screen and add a clickable event to it.
            ivTodayMinus1Day.setVisibility(View.VISIBLE);
            ivTodayMinus1Day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final String moodnote = mPreferences.getString(Tminus1day + "moodnote", "000");
                    AlertDialog alertDialog = new AlertDialog.Builder(arg0.getContext())
                            //Read Update
                            .setTitle("Mood Log")
                            .setMessage(moodnote)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }
            });
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
        //display the 000 for this day, if it exists
        ivTodayMinus2Days = (ImageView) findViewById(R.id.two_days_ago_mood_note);
        if (TodayMinus2DaysMoodNote != "000") {
            //display the 000 on the screen and add a clickable event to it.
            ivTodayMinus2Days.setVisibility(View.VISIBLE);
            ivTodayMinus2Days.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final String moodnote = mPreferences.getString(Tminus2days + "moodnote", "000");
                    AlertDialog alertDialog = new AlertDialog.Builder(arg0.getContext())
                            //Read Update
                            .setTitle("Mood Log")
                            .setMessage(moodnote)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }
            });
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
        //display the 000 for this day, if it exists
        ivTodayMinus3Days = (ImageView) findViewById(R.id.three_days_ago_mood_note);
        if (TodayMinus3DaysMoodNote != "000") {
            //display the 000 on the screen and add a clickable event to it.
            ivTodayMinus3Days.setVisibility(View.VISIBLE);
            ivTodayMinus3Days.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final String moodnote = mPreferences.getString(Tminus3days + "moodnote", "000");
                    AlertDialog alertDialog = new AlertDialog.Builder(arg0.getContext())
                            //Read Update
                            .setTitle("Mood Log")
                            .setMessage(moodnote)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }
            });
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
        //display the 000 for this day, if it exists
        ivTodayMinus4Days = (ImageView) findViewById(R.id.four_days_ago_mood_note);
        if (TodayMinus4DaysMoodNote != "000") {
            //display the 000 on the screen and add a clickable event to it.
            ivTodayMinus4Days.setVisibility(View.VISIBLE);
            ivTodayMinus4Days.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final String moodnote = mPreferences.getString(Tminus4days + "moodnote", "000");
                    AlertDialog alertDialog = new AlertDialog.Builder(arg0.getContext())
                            //Read Update
                            .setTitle("Mood Log")
                            .setMessage(moodnote)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }
            });
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
        //display the 000 for this day, if it exists
        ivTodayMinus5Days = (ImageView) findViewById(R.id.five_days_ago_mood_note);
        if (TodayMinus5DaysMoodNote != "000") {
            //display the 000 on the screen and add a clickable event to it.
            ivTodayMinus5Days.setVisibility(View.VISIBLE);
            ivTodayMinus5Days.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final String moodnote = mPreferences.getString(Tminus5days + "moodnote", "000");
                    AlertDialog alertDialog = new AlertDialog.Builder(arg0.getContext())
                            //Read Update
                            .setTitle("Mood Log")
                            .setMessage(moodnote)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }
            });
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
        //display the 000 for this day, if it exists
        ivTodayMinus6Days = (ImageView) findViewById(R.id.six_days_ago_mood_note);
        if (TodayMinus6DaysMoodNote != "000") {
            //display the 000 on the screen and add a clickable event to it.
            ivTodayMinus6Days.setVisibility(View.VISIBLE);
            ivTodayMinus6Days.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final String moodnote = mPreferences.getString(Tminus6days + "moodnote", "000");
                    AlertDialog alertDialog = new AlertDialog.Builder(arg0.getContext())
                            //Read Update
                            .setTitle("Mood Log")
                            .setMessage(moodnote)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }
            });
        }

    }

}

