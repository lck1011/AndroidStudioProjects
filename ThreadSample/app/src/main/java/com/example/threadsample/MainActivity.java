package com.example.threadsample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String TAG = "ThreadSample";
    private TextView mLabel;
    private Button mButton;
    private Thread mClockThread;
    private boolean isRunning = true;
    private int mTimer = 0;
    private Handler mHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLabel = findViewById(R.id.textview);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        });
        mClockThread = new Thread() {
            @Override
            public void run() {
                while (isRunning) {
                    try {
                        Thread.currentThread().sleep(1000);
                        mTimer++;
                        //mLabel.setText("逝去了"+mTimer+"秒");
                        Message msg = new Message();
                        msg.obj = mTimer;
                        msg.what = 0;
                        mHandle.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mClockThread.start();
        mHandle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        mLabel.setText("逝去了" + mTimer + "秒");
                }
            }
        };
    }
}
