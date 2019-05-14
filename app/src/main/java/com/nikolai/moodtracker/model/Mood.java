package com.nikolai.moodtracker.model;

public class Mood {
    private int mood;

    public static final int NO_MOOD = 0;
    public static final int GREAT_MOOD = 1;
    public static final int GOOD_MOOD = 2;
    public static final int DECENT_MOOD = 3;
    public static final int BAD_MOOD = 4;
    public static final int REALLY_BAD_MOOD = 5;

    public Mood(int mood) {
        this.mood = mood;
    }
}
