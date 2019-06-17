package com.nikolai.moodtracker.controller;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;

public class DataStorageTest {
    DataStorage dataStorage;
    private SharedPreferences mPreferences;

    @Test
    public void storeData() {
        //arrange
        //act - call method
        dataStorage.storeIntData("Mon",2);
        //assert
        //assertThat(mPreferences)
    }

    @Before
    public void Before() {
        //dataStorage = new DataStorage(mock);
        //initialize context
        //act - call method on test class
        //verify interactions (assert)
    }
}