package com.nikolai.moodtracker.controller;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nikolai.moodtracker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private String currentWeekday;
    private SharedPreferences.Editor preferencesEditor;
    private PendingIntent pendingIntent;
    private AlarmManager manager;

    static final int NUMBER_OF_PAGES = 5;

    MyAdapter mAdapter;
    VerticalViewPager mPager;

    // Shared preferences object
    private SharedPreferences mPreferences;

    // Name of shared preferences file
    private String sharedPrefFile =
            "com.nikolai.moodtracker.moodsharedprefs";

    Calendar calendar;

    /**
     * called when this activity is created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //Log.d(TAG, "instance #:" + this);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date currentDate = new Date();
        currentWeekday = sdf.format(currentDate);
        Log.d(TAG, currentWeekday);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(1);
        preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(currentWeekday, 2);
        preferencesEditor.apply();
        mPager.addOnPageChangeListener(pageChangeListener);

        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //number of milliseconds in a day - to repeat every 24 hours
        int interval = 86400000;

        calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 00); //24 hour calendar
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, /*System.currentTimeMillis()*/calendar.getTimeInMillis(), interval, pendingIntent);

        //Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

//    private boolean isAlarmSet() {
//        Intent intent = AlarmReceiver.getTargetIntent(context);
//        PendingIntent service = PendingIntent.getService(
//                context,
//                0,
//                intent,
//                PendingIntent.FLAG_NO_CREATE
//        );
//        return service != null;
//    }

    /**
     *
     */
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        int currentPosition = 0;

        /**
         * when a new page is selected, then it updates the shared preferences to the current mood
         * @param newPosition the position of the screen that correlates to the current mood
         */
        @Override
        public void onPageSelected(int newPosition) {
            currentPosition = newPosition;
            Log.d(TAG, "onPageSelected: currentposition: "+currentPosition);
            preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(currentWeekday, currentPosition+1);
            preferencesEditor.apply();
            mPager.setCurrentItem(currentPosition);

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) { }

        public void onPageScrollStateChanged(int arg0) { }

    };

    /**
     * gets the arguments that have been passed to this intent.  In this case a value of 0 for if it is midnight at the moment.  If it is midnight, then resets the mood day.
     * @param intent the intent that is being called
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "Inside new intent of main activity");
        int midnight = intent.getIntExtra("midnight",1);
        Log.d(TAG, "onNewIntent: midnight: "+ midnight);
        if (midnight == 0) {
            //set to the default screen and default values for the current day
            resetDay();
        }
    }

    /**
     * at midnight, resets the day to green and the mood to happy.  Initializes the mood note invisible for the day and removes the mood note from the 7th day ago (today) from shared preferences.
     */
    private void resetDay() {
        //set the task to run in the background, if not already running in foreground
        //moveTaskToBack(true);
        //for the mood history - resets color to green
        preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(currentWeekday, 2);
        preferencesEditor.apply();
        //sets the current screen to position 1 (green happy mood)
        mPager.setCurrentItem(1);
        //for the mood history, removes mood note from current day
        if (mPreferences.contains(currentWeekday+"moodnote")) {
            //remove the note from shared preferences
            preferencesEditor = mPreferences.edit();
            preferencesEditor.remove(currentWeekday + "moodnote");
            preferencesEditor.apply();
            setContentView(R.layout.activity_mood_chart);
            ImageView ivToday = (ImageView) findViewById(R.id.today_mood_note);
            ivToday.setVisibility(View.INVISIBLE);
            setContentView(R.layout.activity_main);

        }
    }

    /**
     *
     */
    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         *
         * @return the number of user view pages that are created
         */
        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }

        /**
         * creates a new mood fragment for each different mood layout screen
         * @param position the position of the mood fragment in the user view
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Log.d(TAG, "changing sharedpreferences to yellow for the day");
                    return MoodFragment.newInstance(4, ContextCompat.getColor(MainActivity.this, R.color.colorYellow),R.drawable.ic_smiley_super_happy);
                case 1:
                    Log.d(TAG, "changing sharedpreferences to green for the day");
                    return MoodFragment.newInstance(3, ContextCompat.getColor(MainActivity.this, R.color.colorGreen),R.drawable.ic_smiley_happy);
                case 2:
                    Log.d(TAG, "changing sharedpreferences to blue for the day");
                    return MoodFragment.newInstance(2, ContextCompat.getColor(MainActivity.this, R.color.colorBlue),R.drawable.ic_smiley_normal);
                case 3:
                    Log.d(TAG, "changing sharedpreferences to grey for the day");
                    return MoodFragment.newInstance(2, ContextCompat.getColor(MainActivity.this, R.color.colorGrey),R.drawable.ic_smiley_disappointed);
                case 4:
                    Log.d(TAG, "changing sharedpreferences to red for the day");
                    return MoodFragment.newInstance(1, ContextCompat.getColor(MainActivity.this, R.color.colorRed),R.drawable.ic_smiley_sad);
                default:
                    return null;
            }
        }
    }
}
