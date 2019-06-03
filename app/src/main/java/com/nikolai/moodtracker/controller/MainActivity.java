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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.nikolai.moodtracker.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements Serializable {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "instance #:" + this);


        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date currentDate = new Date();
        currentWeekday = sdf.format(currentDate);
        Log.d(TAG, currentWeekday);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(1);//number want

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;

        calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 7); //24 hour calendar
        calendar.set(Calendar.MINUTE, 24);
        calendar.set(Calendar.SECOND, 00);

//        Intent myIntent = new Intent(getApplicationContext(),
//                AlarmReceiver.class);
//        Bundle mbundle = new Bundle();
//        mbundle.putSerializable("VerticalViewPager", (Serializable) mPager);
//        pendingIntent.putExtras("bundle", mbundle);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, /*System.currentTimeMillis()*/calendar.getTimeInMillis(), interval, pendingIntent);

        //pendingIntent.putExtra("VerticalViewPager", mPager);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    //TODO: split into smaller methods of oncreate

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "Inside new intent of main activity", Toast.LENGTH_SHORT).show();
        mPager.setCurrentItem(1);
    }

    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }

        //TODO: put in current weekday note to sharedprefs
        //TODO: fire weekday 0 at time (https://stackoverflow.com/questions/8034127/how-to-remove-some-key-value-pair-from-sharedpreferences)
        //TODO: ??? unit testing (expresso)
        //TODO: silence all toast messages, except in the mood history
        //TODO: change alarm clock interval to be 24 hours later
        //TODO: ??? how to pass an object to a pending intent, or get object from current context (basically fire an event at midnight)

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    //ContextCompat.getColor(MainActivity.this, R.color.colorYellow)
                    //Color.parseColor("#00ff00")
                    mPager.setCurrentItem(0);
                    preferencesEditor = mPreferences.edit();
                    preferencesEditor.putInt(currentWeekday, 1);
                    preferencesEditor.apply();
                    return MoodFragment.newInstance(0, ContextCompat.getColor(MainActivity.this, R.color.colorYellow),R.drawable.ic_smiley_super_happy);
                case 1:
                    // return a different Fragment class here
                    // if you want want a completely different layout
                    mPager.setCurrentItem(1);
                    preferencesEditor = mPreferences.edit();
                    preferencesEditor.putInt(currentWeekday, 2);
                    preferencesEditor.apply();
                    return MoodFragment.newInstance(1, ContextCompat.getColor(MainActivity.this, R.color.colorGreen),R.drawable.ic_smiley_happy);
                case 2:
                    mPager.setCurrentItem(2);
                    preferencesEditor = mPreferences.edit();
                    preferencesEditor.putInt(currentWeekday, 3);
                    preferencesEditor.apply();
                    return MoodFragment.newInstance(2, ContextCompat.getColor(MainActivity.this, R.color.colorBlue),R.drawable.ic_smiley_normal);
                case 3:
                    mPager.setCurrentItem(3);
                    preferencesEditor = mPreferences.edit();
                    preferencesEditor.putInt(currentWeekday, 4);
                    preferencesEditor.apply();
                    return MoodFragment.newInstance(3, ContextCompat.getColor(MainActivity.this, R.color.colorGrey),R.drawable.ic_smiley_disappointed);
                case 4:
                    mPager.setCurrentItem(4);
                    preferencesEditor = mPreferences.edit();
                    preferencesEditor.putInt(currentWeekday, 5);
                    preferencesEditor.apply();
                    return MoodFragment.newInstance(4, ContextCompat.getColor(MainActivity.this, R.color.colorRed),R.drawable.ic_smiley_sad);
                default:
                    return null;
            }
        }
    }
}
