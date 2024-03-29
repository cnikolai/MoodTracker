package com.nikolai.moodtracker.controller;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String currentWeekday;
    //private SharedPreferences.Editor preferencesEditor;
    private PendingIntent pendingIntent;
    private AlarmManager manager;

    //the number of views to display
    private static final int NUMBER_OF_PAGES = 5;

    private MyAdapter mAdapter;
    private VerticalViewPager mPager;

    private Calendar calendar;
    private DataStorage dataStorage;

    /**
     * inserts demo data for each day
     */
    protected void demoData() {
        //put the moods for each day
        dataStorage.storeIntData("Mon", 2);
        dataStorage.storeIntData("Tue", 1);
        dataStorage.storeIntData("Wed", 3);
        dataStorage.storeIntData("Thu", 4);
        dataStorage.storeIntData("Fri", 5);
        dataStorage.storeIntData("Sat", 1);
        dataStorage.storeIntData("Sun", 2);

        //put mood notes for each day desired
        dataStorage.storeStringData("Tuemoodnote", "I'm very very happy today");
        dataStorage.storeStringData("Thumoodnote", "I'm very very sad today");
        dataStorage.storeStringData("Satmoodnote", "I'm doing well today");
    }

    /**
     * resets demo data for each day
     */
    protected void resetDemoData() {
        //removes the moods for each day
        dataStorage.removeData("Mon");
        dataStorage.removeData("Tue");
        dataStorage.removeData("Wed");
        dataStorage.removeData("Thu");
        dataStorage.removeData("Fri");
        dataStorage.removeData("Sat");
        dataStorage.removeData("Sun");

        //removes mood notes for each day desired
        dataStorage.removeData("Monmoodnote");
        dataStorage.removeData("Tuemoodnote");
        dataStorage.removeData("Wedmoodnote");
        dataStorage.removeData("Thumoodnote");
        dataStorage.removeData("Frimoodnote");
        dataStorage.removeData("Satmoodnote");
        dataStorage.removeData("Sunmoodnote");
    }

    /**
     * called when this activity is created.
     *
     * @param savedInstanceState state of view before when it was killed by system
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //Log.d(TAG, "instance #:" + this);

        dataStorage = new DataStorage(this);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE", Locale.US);
        Date currentDate = new Date();
        currentWeekday = sdf.format(currentDate);
        Log.d(TAG, currentWeekday);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(1);
        dataStorage.storeIntData(currentWeekday, 2);
        mPager.addOnPageChangeListener(pageChangeListener);

        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //number of milliseconds in a day - to repeat every 24 hours
        int interval = (int) TimeUnit.HOURS.toMillis(24);

        calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0); //24 hour calendar
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, /*System.currentTimeMillis()*/calendar.getTimeInMillis(), interval, pendingIntent);

        //creates demo data
        //demoData();
        //resets demo data
        //resetDemoData();
    }

    /**
     *
     */
    private final ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        int currentPosition = 0;

        /**
         * when a new page is selected, then it updates the shared preferences to the current mood
         * @param newPosition the position of the screen that correlates to the current mood
         */
        @Override
        public void onPageSelected(int newPosition) {
            currentPosition = newPosition;
            Log.d(TAG, "onPageSelected: currentposition: " + currentPosition);
            dataStorage.storeIntData(currentWeekday, currentPosition + 1);
            mPager.setCurrentItem(currentPosition);

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }

    };

    /**
     * gets the arguments that have been passed to this intent.  In this case a value of 0 for if it is midnight at the moment.  If it is midnight, then resets the mood day.
     *
     * @param intent the intent that is being called
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "Inside new intent of main activity");
        int midnight = intent.getIntExtra("midnight", 1);
        Log.d(TAG, "onNewIntent: midnight: " + midnight);
        if (midnight == 0) {
            //set to the default screen and default values for the current day
            resetDay();
        }
    }

    /**
     * at midnight, resets the day to green and the mood to happy.  Initializes the mood note invisible for the day and removes the mood note from the 7th day ago (today) from shared preferences.
     */
    private void resetDay() {
        //for the mood history - resets color to green
        dataStorage.storeIntData(currentWeekday, 2);
        //sets the current screen to position 1 (green happy mood)
        mPager.setCurrentItem(1);
        //for the mood history, removes mood note from current day
        if (dataStorage.contains(currentWeekday + "moodnote")) {
            //remove the note from shared preferences
            dataStorage.removeData(currentWeekday + "moodnote");
            setContentView(R.layout.activity_mood_chart);
            ImageView ivToday = findViewById(R.id.today_mood_note);
            ivToday.setVisibility(View.INVISIBLE);
            setContentView(R.layout.activity_main);

        }
    }

    /**
     *
     */
    private class MyAdapter extends FragmentPagerAdapter {
        private MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * @return the number of user view pages that are created
         */
        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }

        /**
         * creates a new mood fragment for each different mood layout screen
         *
         * @param position the position of the mood fragment in the user view
         * @return a fragment
         */
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Log.d(TAG, "changing sharedpreferences to yellow for the day");
                    return MoodFragment.newInstance(4, ContextCompat.getColor(MainActivity.this, R.color.colorYellow), R.drawable.ic_smiley_super_happy);
                case 1:
                    Log.d(TAG, "changing sharedpreferences to green for the day");
                    return MoodFragment.newInstance(3, ContextCompat.getColor(MainActivity.this, R.color.colorGreen), R.drawable.ic_smiley_happy);
                case 2:
                    Log.d(TAG, "changing sharedpreferences to blue for the day");
                    return MoodFragment.newInstance(2, ContextCompat.getColor(MainActivity.this, R.color.colorBlue), R.drawable.ic_smiley_normal);
                case 3:
                    Log.d(TAG, "changing sharedpreferences to grey for the day");
                    return MoodFragment.newInstance(2, ContextCompat.getColor(MainActivity.this, R.color.colorGrey), R.drawable.ic_smiley_disappointed);
                case 4:
                    Log.d(TAG, "changing sharedpreferences to red for the day");
                    return MoodFragment.newInstance(1, ContextCompat.getColor(MainActivity.this, R.color.colorRed), R.drawable.ic_smiley_sad);
                default:
                    return null;
            }
        }
    }
}
