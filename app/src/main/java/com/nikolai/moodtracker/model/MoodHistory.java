package com.nikolai.moodtracker.model;

import java.util.List;

public class MoodHistory {
    private List<Mood> MoodHistory;

    public MoodHistory() {
        List<Mood> initialMoodHistory = new List<Mood>;
        Mood mood1 = new Mood(0);
        Mood mood2 = new Mood(0);
        Mood mood3 = new Mood(0);
        Mood mood4 = new Mood(0);
        Mood mood5 = new Mood(0);
        Mood mood6 = new Mood(0);
        Mood mood7 = new Mood(0);
        initialMoodHistory.add(mood1);
        initialMoodHistory.add(mood2);
        initialMoodHistory.add(mood3);
        initialMoodHistory.add(mood4);
        initialMoodHistory.add(mood5);
        initialMoodHistory.add(mood6);
        initialMoodHistory.add(mood7);
        MoodHistory = initialMoodHistory;
    }
}
