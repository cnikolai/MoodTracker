package com.nikolai.moodtracker.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.nikolai.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(0);//number want

    }


    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    //ContextCompat.getColor(MainActivity.this, R.color.colorYellow)
                    //Color.parseColor("#00ff00")
                    return FragmentOne.newInstance(0, ContextCompat.getColor(MainActivity.this, R.color.colorYellow),R.drawable.ic_smiley_super_happy);
                case 1:
                    // return a different Fragment class here
                    // if you want want a completely different layout
                    return FragmentOne.newInstance(1, ContextCompat.getColor(MainActivity.this, R.color.colorGreen),R.drawable.ic_smiley_happy);
                    //return null;
                case 2:
                    return FragmentOne.newInstance(2, ContextCompat.getColor(MainActivity.this, R.color.colorBlue),R.drawable.ic_smiley_normal);
                    //return null;
                case 3:
                    return FragmentOne.newInstance(3, ContextCompat.getColor(MainActivity.this, R.color.colorGrey),R.drawable.ic_smiley_disappointed);
                    //return null;
                case 4:
                    return FragmentOne.newInstance(4, ContextCompat.getColor(MainActivity.this, R.color.colorRed),R.drawable.ic_smiley_sad);
                    //return null;
                default:
                    return null;
            }
        }
    }
}
