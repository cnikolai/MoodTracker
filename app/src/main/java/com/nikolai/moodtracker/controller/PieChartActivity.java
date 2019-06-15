package com.nikolai.moodtracker.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;

import com.nikolai.moodtracker.R;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    public static final String TAG = "PieChartActivity";

    private int percenSupertHappy = 0;
    private int percentHappy = 0;
    private int percentNormal = 0;
    private int percentDisappointed = 0;
    private int percentSad = 0;
    private ArrayList<String> days = new ArrayList<String>();
    private ArrayList<Integer> mood = new ArrayList<Integer>();
    private ArrayList<Integer> resource_name = new ArrayList<Integer>();

    private int sum_super_happy = 0;
    private int sum_happy = 0;
    private int sum_normal = 0;
    private int sum_disappointed = 0;
    private int sum_sad = 0;

    // Shared preferences object
    private SharedPreferences mPreferences;
    // Name of shared preferences file
    private String sharedPrefFile =
            "com.nikolai.moodtracker.moodsharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        days.add("Mon");
        days.add("Tue");
        days.add("Wed");
        days.add("Thu");
        days.add("Fri");
        days.add("Sat");
        days.add("Sun");

        resource_name.add(R.id.stats_progressbar_sad);
        resource_name.add(R.id.stats_progressbar_disappointed);
        resource_name.add(R.id.stats_progressbar_normal);
        resource_name.add(R.id.stats_progressbar_happy);
        resource_name.add(R.id.stats_progressbar_super_happy);

        calculatePercents();
        updateChart();
    }

    private void calculatePercents() {
        int Today;
        for (int i = 0; i < 7; i++) {
            Today = mPreferences.getInt(days.get(i), 0);
            Log.d(TAG, "calculatePercents: "+days.get(i)+": "+Today);
            switch (Today) {
                case 0:
                    break;
                case 1:
                    sum_super_happy+=1;
                    Log.d(TAG, "calculatePercents: sum_super_happy: "+sum_super_happy);
                    break;
                case 2:
                    sum_happy+=1;
                    Log.d(TAG, "calculatePercents: sum_happy: "+sum_happy);
                    break;
                case 3:
                    sum_normal+=1;
                    Log.d(TAG, "calculatePercents: sum_normal: "+sum_normal);
                    break;
                case 4:
                    sum_disappointed+=1;
                    Log.d(TAG, "calculatePercents: sum_disappointed: "+sum_disappointed);
                    break;
                case 5:
                    sum_sad+=1;
                    Log.d(TAG, "calculatePercents: sum_sad: "+sum_sad);
                    break;
            }
        }

        mood.add(sum_sad);
        mood.add(sum_disappointed);
        mood.add(sum_normal);
        mood.add(sum_happy);
        mood.add(sum_super_happy);
    }

    private void updateChart(){
        // Calculate the slice size and update the pie chart:
        int sum = 0;
        for (int i=4; i >= 0; i--) {
            ProgressBar pieChart = findViewById(resource_name.get(i));
            Log.d(TAG, "updateChart: mood.get(i)sums:" + mood.get(i));
            float temp = (float) mood.get(i) / 7;
            Log.d(TAG, "updateChart: temp: " + temp);
            int progress = (int) (temp * 100);
            Log.d(TAG, "updateChart: progress: " + progress);
            sum+=progress;
            if (progress == 0)
                pieChart.setProgress(0);
            else
                pieChart.setProgress(sum);
        }
    }
}
