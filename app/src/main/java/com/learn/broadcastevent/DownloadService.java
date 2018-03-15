package com.learn.broadcastevent;

import android.app.IntentService;
import android.content.Intent;

public class DownloadService extends IntentService {

    public static final String ACTION_DOWNLOAD_STATUS_DONE = "download_status_done";

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent notifyFinishIntent = new Intent(MainActivity.ACTION_DOWNLOAD_STATUS);
            notifyFinishIntent.putExtra(ACTION_DOWNLOAD_STATUS_DONE, "SELESAI BRO");
            sendBroadcast(notifyFinishIntent);
        }
    }
}
