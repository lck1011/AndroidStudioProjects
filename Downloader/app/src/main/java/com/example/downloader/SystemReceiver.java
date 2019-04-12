package com.example.downloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SystemReceiver extends BroadcastReceiver {
    private final static String TAG = "SystemReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"SystemBootReceiver...");
        Intent it = new Intent(context,DownloadService.class);
        context.startService(it);
    }
}
