package com.example.zhiming.demo.homework4;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.example.zhiming.demo.NetworkActivity;
import com.example.zhiming.demo.R;

import java.util.logging.Logger;

/**
 * Created by zhiming on 2016/5/26.
 */
public class Home4Activities extends Activity {
    Button btn_down_home4;
    ProgressBar pb_down_home4;
    RemoteViews remoteViews;
    NotificationManager notificationManager;
    NotificationCompat.Builder notiBuilder;
    BroadcastReceiver receiver;
    boolean flag=true;
    boolean flagwarp=true;
    static final String ACTION="My_ACTION_BTN";
    static final String INTENT_BTN_ACTION="My_INTENT_ACTION_BTN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4_view);
        btn_down_home4= (Button) findViewById(R.id.btn_down_home4);
        pb_down_home4= (ProgressBar) findViewById(R.id.pb_down_home4);

        btn_down_home4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ProgressAsyncTask().execute();

            }
        });

    }
    private void sendNoti(){

        notiBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_get_app_black_18dp)

                        .setAutoCancel(true);

        remoteViews= new RemoteViews(getPackageName(),R.layout.home4_notification_view);
        remoteViews.setImageViewResource(R.id.img_noti_home4,R.drawable.ic_get_app_black_48dp);
        remoteViews.setTextViewText(R.id.tv_titel_home4,"模拟下载home4");
        remoteViews.setProgressBar(R.id.pb_noti_home4,100,0,false);

        notiBuilder.setContent(remoteViews);
        notificationManager=
                (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(4,notiBuilder.build());

        //接收广播来实现通知栏按钮点击事件
        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                String str=intent.getExtras().getString(INTENT_BTN_ACTION);
                if(str.equals("BTN_STOP")){
                    Log.v("what","暂停按钮");
                    flag=false;
                }
                else if(str.equals("BTN_REGO")){
                    Log.v("what","继续按钮");
                    flag=true;
                }
                else if(str.equals("BTN_CENCAL")){
                    Log.v("what","取消按钮");
                    flagwarp=false;
                }
            }
        };
        //拦截指定广播
        IntentFilter filter=new IntentFilter();
        filter.addAction(ACTION);
        //注册
        registerReceiver(receiver,filter);

        //给按钮添加OnClickPendingIntent
        Intent intentBtn=new Intent(ACTION);

        //暂停按钮
        intentBtn.putExtra(INTENT_BTN_ACTION,"BTN_STOP");
        PendingIntent pendingIntentBtnSTOP=PendingIntent.getBroadcast(getApplicationContext(),0x101,intentBtn,PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_stop_home4,pendingIntentBtnSTOP);
        //继续按钮
        intentBtn.putExtra(INTENT_BTN_ACTION,"BTN_REGO");
        PendingIntent pendingIntentBtnREGO=PendingIntent.getBroadcast(getApplicationContext(),0x102,intentBtn,PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_rego_home4,pendingIntentBtnREGO);
        //取消按钮
        intentBtn.putExtra(INTENT_BTN_ACTION,"BTN_CENCAL");
        PendingIntent pendingIntentBtnCENCAL=PendingIntent.getBroadcast(getApplicationContext(),0x103,intentBtn,PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_cancel_home4,pendingIntentBtnCENCAL);
    }
    class ProgressAsyncTask extends AsyncTask<String,Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sendNoti();
            //为能模拟第二次下载，还原变量
            flag=true;
            flagwarp=true;
            pb_down_home4.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("success"))
            {
                //隐藏部分控件
                setVisbilityInvisible();
                //提示声音
                notiBuilder.setDefaults(Notification.DEFAULT_SOUND);
                //成功提示
                remoteViews.setTextViewText(R.id.tv_titel_home4,"模拟下载成功");

                //添加事件，使点击状态栏跳能转到下载页面
                Intent[] intent=new Intent[]{new Intent(getApplicationContext(),Home4Activities.class)};
                PendingIntent pendingIntent=PendingIntent.getActivities(getApplicationContext(),0x004,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                notiBuilder.setContentIntent(pendingIntent);

                notificationManager.notify(4,notiBuilder.build());
            }
            else if(s.equals("cencal"))
            {
                remoteViews.setTextViewText(R.id.tv_titel_home4,"模拟下载已取消");

                setVisbilityInvisible();

                notificationManager.notify(4,notiBuilder.build());

            }
            unregisterReceiver(receiver);
        }
        protected void setVisbilityInvisible(){
            pb_down_home4.setVisibility(View.INVISIBLE);
            remoteViews.setViewVisibility(R.id.pb_noti_home4,View.INVISIBLE);
            remoteViews.setViewVisibility(R.id.btn_cancel_home4,View.INVISIBLE);
            remoteViews.setViewVisibility(R.id.btn_rego_home4,View.INVISIBLE);
            remoteViews.setViewVisibility(R.id.btn_stop_home4,View.INVISIBLE);
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb_down_home4.setProgress(values[0]);
            remoteViews.setProgressBar(R.id.pb_noti_home4,100,values[0],false);
            notificationManager.notify(4,notiBuilder.build());
        }

        @Override
        protected String doInBackground(String... params) {
            int progress=0;
            warp:while (flagwarp) {
                while (flag) {
                    int random = (int) (Math.random() * 10);
                    progress += random;
                    publishProgress(progress);
                    if (progress >= 100)
                        break warp;
                    if(!flagwarp)
                        return "cencal";
                    SystemClock.sleep(800);
                }

            }

            return "success";
        }
    }
}
