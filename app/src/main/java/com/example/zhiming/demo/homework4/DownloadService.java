package com.example.zhiming.demo.homework4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.zhiming.demo.R;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by zhiming on 2016/6/7.
 */
public class DownloadService extends Service {
    NotificationManager manager;
    Notification.Builder builder;
    byte [] bitmapData;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        final String imgUrl="https://i.imgur.com/tGbaZCY.jpg";
        new NetworkAsync().execute(imgUrl);
        return super.onStartCommand(intent, flags, startId);
    }

    class NetworkAsync extends AsyncTask<String,Integer,Bitmap>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            builder = new Notification.Builder(getApplicationContext());
            builder.setSmallIcon(R.drawable.ic_looks_6_black_48dp);
            builder.setContentTitle("下载图片");
            PendingIntent pi=PendingIntent.getActivities(getApplicationContext(),0x110,new Intent[]{new Intent(getApplicationContext(),DownloadActivity.class)},PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pi);
            builder.setProgress(100,0,false);
            manager.notify(1,builder.build());
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(builder!=null){
                builder.setContentText("下载完成！");
                builder.setProgress(100,100,true);
                Notification notification =builder.build();
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                notification.defaults = Notification.DEFAULT_SOUND;
                manager.notify(1,notification);
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Intent intent =new Intent();
            intent.setAction("android.intent.action.MY_RECEIVER");
            intent.putExtra("progress",values[0]);
            if(values[0]>=100)
                intent.putExtra("bitmap",bitmapData);

            sendBroadcast(intent);
            if(builder!=null){
                builder.setContentText(values[0]+"%");
                builder.setProgress(100,values[0],false);
                Notification notification=builder.build();
                notification.flags=Notification.FLAG_NO_CLEAR;
                manager.notify(1,notification);

            }
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap=null;
            InputStream is=null;
            OutputStream os=null;
            try {
                URL url=new URL(params[0]);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                is=connection.getInputStream();

                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                os=new BufferedOutputStream(bos);
                int total=connection.getContentLength();
                int current=0;
                int read=0;
                byte[]data=new byte[1024];
                while((read=is.read(data))!=-1){
                    os.write(data,0,read);
                    current+=read;
                    int progress=(int)((float)current/total*100);
                    publishProgress(progress);
                }
                os.flush();
                bitmapData=bos.toByteArray();

                bitmap= BitmapFactory.decodeByteArray(bitmapData,0,bitmapData.length);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(os!=null){
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return bitmap;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
