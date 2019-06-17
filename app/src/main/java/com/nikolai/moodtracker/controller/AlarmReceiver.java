package com.nikolai.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    /**
     * Sets up a new intent, passes a parameter to it, and then starts the new intent.  In this case, MainActivity.
     * @param arg0 the context that we are receiving
     * @param arg1 the intent that we are using
     */
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        //Toast.makeText(arg0, "inside alarm receiver", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(arg0, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("midnight", 0);
            arg0.startActivity(intent);
    }

}
