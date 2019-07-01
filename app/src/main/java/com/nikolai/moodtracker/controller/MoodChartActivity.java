package com.nikolai.moodtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.List;
import java.util.Locale;

public class MoodChartActivity extends AppCompatActivity {

    private DataStorage dataStorage;

    private String currentWeekday;
    private int Today;
    private String TodayMoodNote;
    private String Tminus1day;
    private LinearLayout lltoday;
    private float percent;
    private ConstraintLayout clmoodchartlayout;
    private ConstraintSet constraintSet;

    private static final String TAG = "MoodChartActivity";
    private LinearLayout ll;

    /**
     * when the pie chart icon on the toolbar is selected, this method creates a new intent for the PieChartActivity and starts it.
     *
     * @param item the menu item to be selected (menu item is from menu resource).
     * @return a boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_mood_chart:
                //create new activity
                Intent newIntent = new Intent(this, PieChartActivity.class);
                //start new activity
                startActivity(newIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * called to inflate the main menu for the toolbar
     *
     * @param menu menu items from menu_main (there is only one item in this case)
     * @return a boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * called when this activity is created
     *
     * @param savedInstanceState state of view before when it was killed by system
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_chart);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.inflateMenu(R.menu.menu_main);

        List<Integer> days = new ArrayList<>();
        days.add(R.id.today);
        days.add(R.id.one_day_ago);
        days.add(R.id.two_days_ago);
        days.add(R.id.three_days_ago);
        days.add(R.id.four_days_ago);
        days.add(R.id.five_days_ago);
        days.add(R.id.six_days_ago);

        List<Integer> mood_charts = new ArrayList<>();
        mood_charts.add(R.id.today_mood_note);
        mood_charts.add(R.id.one_day_ago_mood_note);
        mood_charts.add(R.id.two_days_ago_mood_note);
        mood_charts.add(R.id.three_days_ago_mood_note);
        mood_charts.add(R.id.four_days_ago_mood_note);
        mood_charts.add(R.id.five_days_ago_mood_note);
        mood_charts.add(R.id.six_days_ago_mood_note);

        dataStorage = new DataStorage(this);

        // retrieve shared preferences for last 7 days
        SimpleDateFormat sdf = new SimpleDateFormat("EEE", Locale.US);
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(String.valueOf(currentDate)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //for each of the last 7 weekdays:
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                currentWeekday = sdf.format(currentDate);
                Today = dataStorage.retrieveIntData(currentWeekday, 0);
                TodayMoodNote = dataStorage.retrieveStringData(currentWeekday + "moodnote", "000");
                Log.d(TAG, currentWeekday + ": " + Today);
                Log.d(TAG, currentWeekday + " moodnote: " + TodayMoodNote);
            } else {
                //Decrementing the date by 1 day
                c.add(Calendar.DAY_OF_MONTH, -1);
                Tminus1day = sdf.format(c.getTime());
                currentWeekday = Tminus1day;
                Today = dataStorage.retrieveIntData(Tminus1day, 0);
                TodayMoodNote = dataStorage.retrieveStringData(Tminus1day + "moodnote", "000");
                Log.d(TAG, Tminus1day + ": " + Today);
                Log.d(TAG, Tminus1day + " moodnote: " + TodayMoodNote);
            }

            // hook the data to the interface layout
            lltoday = findViewById(days.get(i));
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
                    Log.d(TAG, "onCreate: setting background color to white inside default");
            }

            //set the constraint width (width of the layout for the day)
            clmoodchartlayout = findViewById(R.id.mood_chart_parent);
            constraintSet = new ConstraintSet();
            constraintSet.clone(clmoodchartlayout);
            constraintSet.constrainPercentWidth(days.get(i), percent);
            constraintSet.applyTo(clmoodchartlayout);

            //display the the mood note for this day, if it exists
            ImageView ivToday = findViewById(mood_charts.get(i));
            if (!TodayMoodNote.equals("000")) {
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