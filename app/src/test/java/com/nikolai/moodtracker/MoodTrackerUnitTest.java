package com.nikolai.moodtracker;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MoodTrackerUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }
//
    //TODO: install expresso
    //TODO: create unit tests
    @Test
    public void leaving_and_returning_to_app() {
        //the app should return to the same spot that it left off at
        //the app should reset at midnight
    }

    @Test
    public void entering_mood() {
        //should keep the most current mood and mood note while in current day
        //should reset to screen 1 and no notes for current day at midnight
    }

    @Test
    public void swipes() {
        //swipe up should go to the previous screen, unless at beginning
        //swipe down should go to the next screen, unless at end
        //side to side shouldn't do anything
    }

    @Test
    public void add_mood_note() {
        //should add a mood note and make it visible in the mood history screen and save to shared preferences file
    }

}