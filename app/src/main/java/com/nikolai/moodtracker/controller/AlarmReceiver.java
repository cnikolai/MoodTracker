package com.nikolai.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // For our recurring task, we'll just display a message
        Toast.makeText(arg0, "I'm running", Toast.LENGTH_SHORT).show();
        //arg0.getMpager();
//        //sends screen 0 to the new
        //implements Serializable
//        arg1.putExtra("screen0", 0);
//        Bundle bundle = arg1.getBundleExtra("bundle");
//        if (bundle != null) {
//            VerticalViewPager mpager = (VerticalViewPager)bundle.getSerializable("VerticalViewPager");
            //mpager.setCurrentItem(0);
            Intent intent = new Intent(arg0, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            arg0.startActivity(intent); //if doesn't work, check logs and add flag new task
            //onNewIntent();
            //start activity, pass intent, and pass flag that says it's midnight
//        }
    }
}
