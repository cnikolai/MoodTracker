package com.nikolai.moodtracker.controller;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class DataStorage {

    private final SharedPreferences mPreferences;
    // Name of shared preferences file
    private final String sharedPrefFile =
            "com.nikolai.moodtracker.moodsharedprefs";

    public DataStorage(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    public DataStorage(Context context) {
        mPreferences = context.getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
    }


    public void storeIntData(String currentWeekday, int currentPosition) {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(currentWeekday, currentPosition);
        preferencesEditor.apply();
    }
    public void storeStringData(String currentWeekdayMoodNote, String moodnote) {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(currentWeekdayMoodNote, moodnote);
        preferencesEditor.apply();
    }

    public int retrieveIntData(String currentWeekday, int defaultValue) {
        int mPreferencesInt = mPreferences.getInt(currentWeekday, defaultValue);
        return mPreferencesInt;
    }

    public String retrieveStringData(String currentWeekday, String defaultValue) {
        String mPreferencesString = mPreferences.getString(currentWeekday, defaultValue);
        return mPreferencesString;
    }

    public void removeData(String data) {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.remove(data);
        preferencesEditor.apply();
    }

    public boolean contains(String data) {
        return mPreferences.contains(data);
    }

}
