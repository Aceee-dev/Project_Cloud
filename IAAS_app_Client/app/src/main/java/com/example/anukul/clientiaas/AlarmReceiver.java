package com.example.anukul.clientiaas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "com.example.anukul.cliantiaas.alarm";

    @Override
    public void onReceive(Context context, Intent intent) {

        context.startService(new Intent(context, WorkerService.class));

    }
}
