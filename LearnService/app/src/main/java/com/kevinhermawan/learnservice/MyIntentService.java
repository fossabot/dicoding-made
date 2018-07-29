package com.kevinhermawan.learnservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class MyIntentService extends IntentService {
    public static String EXTRA_DURATION = "extra_duration";
    public static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("My Intent Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "on_handle_intent");
        if (intent != null) {
            int duration = intent.getIntExtra(EXTRA_DURATION, 0);
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "on_destroy");
    }
}