package com.nikolai.moodtracker.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.nikolai.moodtracker.R;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    private static final String TAG = "PieChartActivity";

    private final List<String> days = new ArrayList<>();
    private final List<Integer> mood = new ArrayList<>();
    private final List<String> mood_name = new ArrayList<>();

    private int sum_super_happy = 0;
    private int sum_happy = 0;
    private int sum_normal = 0;
    private int sum_disappointed = 0;
    private int sum_sad = 0;
    DataStorage dataStorage;

    /**
     * Starts when the activity is created.  Sets up instance variables.
     *
     * @param savedInstanceState state of view before when it was killed by system
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mood Pie Chart");
        toolbar.setTitleTextColor(Color.BLACK);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataStorage = new DataStorage(this);

        days.add("Mon");
        days.add("Tue");
        days.add("Wed");
        days.add("Thu");
        days.add("Fri");
        days.add("Sat");
        days.add("Sun");

        mood_name.add("Sad");
        mood_name.add("Disappointed");
        mood_name.add("Normal");
        mood_name.add("Happy");
        mood_name.add("Super Happy");

        calculatePercents();
        drawChart();
    }

    /**
     * Uses PhilJay MPAndroid Charts (https://github.com/PhilJay/MPAndroidChart) to create a pie chart
     */
    private void drawChart() {
        PieChart pieChart = findViewById(R.id.pieChart);
        //pieChart.setUsePercentValues(true);

        List<PieEntry> yvalues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Log.d(TAG, "updateChart: mood.get(i)sums:" + mood.get(i));
            float temp = (float) mood.get(i) / 7;
            Log.d(TAG, "updateChart: temp: " + temp);
            float progress = temp * 100;
            Log.d(TAG, "updateChart: progress: " + progress);
            if (progress == 0) {
                yvalues.add(new PieEntry(progress, i));
            } else {
                yvalues.add(new PieEntry(progress, mood_name.get(i), i));
            }
        }

        PieDataSet dataSet = new PieDataSet(yvalues, "");
        PieData data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);

        pieChart.setTransparentCircleRadius(0f);
        pieChart.setHoleRadius(0f);

        Legend legend = pieChart.getLegend();
        legend.setTextSize(16);

        dataSet.setColors(new int[]{Color.parseColor("#DE3C50"),
                Color.parseColor("#9B9B9B"),
                Color.parseColor("#468AD9"),
                Color.parseColor("#B8E986"),
                Color.parseColor("#F8EC50"),
        });

        data.setValueTextSize(20f);
        data.setValueTextColor(Color.DKGRAY);
        int colorBlack = Color.parseColor("#000000");
        pieChart.setEntryLabelColor(colorBlack);

        pieChart.getDescription().setEnabled(false);
    }

    /**
     * calculates the percentage of days that each mood takes up during the last 7 days
     */
    private void calculatePercents() {
        int Today;
        for (int i = 0; i < 7; i++) {
            Today = dataStorage.retrieveIntData(days.get(i), 0);
            Log.d(TAG, "calculatePercents: " + days.get(i) + ": " + Today);
            switch (Today) {
                case 0:
                    break;
                case 1:
                    sum_super_happy += 1;
                    Log.d(TAG, "calculatePercents: sum_super_happy: " + sum_super_happy);
                    break;
                case 2:
                    sum_happy += 1;
                    Log.d(TAG, "calculatePercents: sum_happy: " + sum_happy);
                    break;
                case 3:
                    sum_normal += 1;
                    Log.d(TAG, "calculatePercents: sum_normal: " + sum_normal);
                    break;
                case 4:
                    sum_disappointed += 1;
                    Log.d(TAG, "calculatePercents: sum_disappointed: " + sum_disappointed);
                    break;
                case 5:
                    sum_sad += 1;
                    Log.d(TAG, "calculatePercents: sum_sad: " + sum_sad);
                    break;
            }
        }

        mood.add(sum_sad);
        mood.add(sum_disappointed);
        mood.add(sum_normal);
        mood.add(sum_happy);
        mood.add(sum_super_happy);
    }
}
