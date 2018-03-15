package com.learn.broadcastevent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnDownload;
    private TextView tvIsi;
    public static final String ACTION_DOWNLOAD_STATUS = "download_status";
    private BroadcastReceiver mBroadcastReceiver;
    private String isiDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Incoming Message");

        initView();
        setupReceiver();
    }

    private void setupReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Download Selesai" , Toast.LENGTH_SHORT).show();
                isiDownload = intent.getStringExtra(DownloadService.ACTION_DOWNLOAD_STATUS_DONE);
                tvIsi.setText(isiDownload);
            }
        };

        IntentFilter mIntentFilter = new IntentFilter(ACTION_DOWNLOAD_STATUS);
        registerReceiver(mBroadcastReceiver, mIntentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isiDownload != null){
            tvIsi.setText(isiDownload);
        }
    }

    private void initView() {
        tvIsi = findViewById(R.id.tv_isi);
        btnDownload = findViewById(R.id.btn_download);
        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_download:
                Intent downloadService = new Intent(this, DownloadService.class);
                startService(downloadService);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null){
            unregisterReceiver(mBroadcastReceiver);
        }
    }
}
