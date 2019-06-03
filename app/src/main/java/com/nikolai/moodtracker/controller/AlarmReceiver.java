package com.nikolai.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        Toast.makeText(arg0, "inside alarm receiver", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(arg0, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("midnight", 0);
            arg0.startActivity(intent);
    }
}
