package com.example.zhiming.demo.activities;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import com.example.zhiming.demo.NetworkActivity;
import com.example.zhiming.demo.R;

/**
 * Created by zhiming on 2016/5/25.
 */
public class NotificationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myservicetest_view);
        Button btn_send = (Button) findViewById(R.id.btn_start_service);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent[] intent = new Intent[]{new Intent(getApplicationContext(), NetworkActivity.class)};
                PendingIntent pendingIntent = PendingIntent.getActivities(getApplicationContext(), 0x100, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                NotificationCompat.Builder notiBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.ic_looks_3_black_48dp)
                                .setContentTitle("通知标题")
                                .setContentText("通知内容")
                                .setVibrate(new long[]{1000,1000,1000,1000,1000})

                                .setTicker("ddd")
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true);
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_view);
                remoteViews.setImageViewResource(R.id.img_noti, R.drawable.ic_looks_3_black_48dp);
                remoteViews.setTextViewText(R.id.tv_noti1, "hahahaha");
                remoteViews.setTextViewText(R.id.tv_noti2, "xixixixi");
                notiBuilder.setContent(remoteViews);
                NotificationManager notificationManager =
                        (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, notiBuilder.build());

            }
        });
    }
}
