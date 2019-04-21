package com.example.notificationsample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFY_ME_ID = 12345;
    //Timer是一个定时器
    private Timer timer = new Timer();
    //通知管理器
    private NotificationManager mNotificationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.notify);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TimerTask task = new TimerTask() {
                    public void run() {
                        notifyMe();
                    }
                };
                //延时3秒发送
                timer.schedule(task, 3000);
            }
        });

        Button button2 = findViewById(R.id.cancel);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // 取消显示在通知列表中的指定通知
                mNotificationManager.cancel(NOTIFY_ME_ID);
            }
        });

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private void notifyMe() {

        //设置单击通知后所打开的详细界面
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, NotificationActivity.class), 0);

        //获得res对于的资源对象
        Resources res = this.getResources();
        //创建通知对象
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setContentTitle("通知发送人")
                .setContentText("我是详细的通知")
                .setContentIntent(pendingIntent).build();
//        notification.ledARGB = Color.RED;
//        notification.ledOffMS = 0;
//        notification.ledOnMS = 1;
//        notification.flags = notification.flags | Notification.FLAG_SHOW_LIGHTS;
//LED灯与震动
//        long[] vibrate = new long[]{500,1000,500,1000,500,1000};
//        notification.vibrate = vibrate;
        //发送通知
        mNotificationManager.notify(NOTIFY_ME_ID, notification);
    }
}
