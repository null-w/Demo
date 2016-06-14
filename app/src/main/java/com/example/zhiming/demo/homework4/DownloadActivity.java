package com.example.zhiming.demo.homework4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.util.DialogUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhiming on 2016/5/9.
 */
public class DownloadActivity extends Activity {
    ImageView iv_net_img;
    ProgressBar pb_load_img;
    Button btn_load;
    MyReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_img);
        iv_net_img= (ImageView) findViewById(R.id.iv_net_img);
        pb_load_img= (ProgressBar) findViewById(R.id.pb_load_img);
        btn_load= (Button) findViewById(R.id.btn_load);

        receiver = new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("android.intent.action.MY_RECEIVER");
        registerReceiver(receiver,filter);

        final String url="https://i.imgur.com/tGbaZCY.jpg";
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pb_load_img.setVisibility(View.VISIBLE);
               startService(new Intent(getApplicationContext(),DownloadService.class));
            }
        });

    }

    private class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();
            int progress=bundle.getInt("progress");
            pb_load_img.setProgress(progress);
            //Log.v("fuck","warp");
            if(progress>=100){
                byte[]bitmapData=bundle.getByteArray("bitmap");
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapData,0,bitmapData.length);
                iv_net_img.setImageBitmap(bitmap);
                iv_net_img.setVisibility(View.VISIBLE);
                pb_load_img.setVisibility(View.INVISIBLE);
                //Log.v("fuck","in");
            }
        }
    }

}
