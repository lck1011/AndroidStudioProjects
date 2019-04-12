package com.example.downloader;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

public class DownloadService extends IntentService {
    private final static String TAG = "DownloadService";
    private boolean isRunning = true;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (isRunning) {
            try {
                Thread.sleep(1000 * 5);
                if (isConnected(ConnectivityManager.TYPE_WIFI)) {
                    Log.i(TAG, "Download thread start...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isConnected(int type) {
        ConnectivityManager connMgr = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        Network[] networks = connMgr.getAllNetworks();
        NetworkInfo networkInfo;

        for (Network mNetwork : networks) {
            networkInfo = connMgr.getNetworkInfo(mNetwork);
            if (networkInfo != null && networkInfo.getType() == type && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
