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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private String currentWeekday;
    private SharedPreferences.Editor preferencesEditor;
    private PendingIntent pendingIntent;
    private AlarmManager manager;

    // Whether the Log Fragment is currently shown
    //private boolean mLogShown;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if (savedInstanceState == null) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            SlidingTabsBasicFragment fragment = new SlidingTabsBasicFragment();
//            transaction.replace(R.id.content_fragment, fragment);
//            transaction.commit();
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
////        MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);
////        logToggle.setVisible(findViewById(R.id.sample_output) instanceof ViewAnimator);
////        logToggle.setTitle(mLogShown ? R.string.sample_hide_log : R.string.sample_show_log);
////
////        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.menu_toggle_log:
//                mLogShown = !mLogShown;
//                ViewAnimator output = (ViewAnimator) findViewById(R.id.sample_output);
//                if (mLogShown) {
//                    output.setDisplayedChild(1);
//                } else {
//                    output.setDisplayedChild(0);
//                }
//                supportInvalidateOptionsMenu();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * Create a chain of targets that will receive log data
     */
//    @Override
//    public void initializeLogging() {
//        // Wraps Android's native log framework.
//        LogWrapper logWrapper = new LogWrapper();
//        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
//        Log.setLogNode(logWrapper);
//
//        // Filter strips out everything except the message text.
//        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
//        logWrapper.setNext(msgFilter);
//
//        // On screen logging via a fragment with a TextView.
//        LogFragment logFragment = (LogFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.log_fragment);
//        msgFilter.setNext(logFragment.getLogView());
//
//        Log.i(TAG, "Ready");
//    }

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

        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        currentWeekday = sdf.format(new Date());
        Log.d(TAG, currentWeekday);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(1);//number want

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;

        calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 1); //24 hour calendar
        calendar.set(Calendar.MINUTE, 01);
        calendar.set(Calendar.SECOND, 00);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, /*System.currentTimeMillis()*/calendar.getTimeInMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }


    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }

        //TODO: in fragment, do inflate a new layout or start a new intent or fragment for the mood chart?
        //TODO: put in current weekday note to sharedprefs
        //TODO: get mood chart items from file
        //TODO: fire weekday 0 at time (https://stackoverflow.com/questions/8034127/how-to-remove-some-key-value-pair-from-sharedpreferences)
        //TODO: add notes to mood_chart.xml
        //TODO: unit testing (expresso)

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
                    return FragmentOne.newInstance(0, ContextCompat.getColor(MainActivity.this, R.color.colorYellow),R.drawable.ic_smiley_super_happy);
                case 1:
                    // return a different Fragment class here
                    // if you want want a completely different layout
                    mPager.setCurrentItem(1);
                    preferencesEditor = mPreferences.edit();
                    preferencesEditor.putInt(currentWeekday, 2);
                    preferencesEditor.apply();
                    return FragmentOne.newInstance(1, ContextCompat.getColor(MainActivity.this, R.color.colorGreen),R.drawable.ic_smiley_happy);
                case 2:
                    mPager.setCurrentItem(2);
                    preferencesEditor = mPreferences.edit();
                    preferencesEditor.putInt(currentWeekday, 3);
                    preferencesEditor.apply();
                    return FragmentOne.newInstance(2, ContextCompat.getColor(MainActivity.this, R.color.colorBlue),R.drawable.ic_smiley_normal);
                case 3:
                    mPager.setCurrentItem(3);
                    preferencesEditor = mPreferences.edit();
                    preferencesEditor.putInt(currentWeekday, 4);
                    preferencesEditor.apply();
                    return FragmentOne.newInstance(3, ContextCompat.getColor(MainActivity.this, R.color.colorGrey),R.drawable.ic_smiley_disappointed);
                case 4:
                    mPager.setCurrentItem(4);
                    preferencesEditor = mPreferences.edit();
                    preferencesEditor.putInt(currentWeekday, 5);
                    preferencesEditor.apply();
                    return FragmentOne.newInstance(4, ContextCompat.getColor(MainActivity.this, R.color.colorRed),R.drawable.ic_smiley_sad);
                default:
                    return null;
            }
        }
    }
}
