package com.example.zhiming.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.daimajia.numberprogressbar.NumberProgressBar;
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
public class NetworkActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_img);
        final ImageView iv_net_img= (ImageView) findViewById(R.id.iv_net_img);
        final ProgressBar pb_load_img= (ProgressBar) findViewById(R.id.pb_load_img);
        final NumberProgressBar numpb_load= (NumberProgressBar) findViewById(R.id.numpb_load);
        Button btn_load= (Button) findViewById(R.id.btn_load);
        if(!isNetworkConnected(getApplicationContext())){
            Dialog dialog=new AlertDialog.Builder(NetworkActivity.this)
                    .setTitle("网络异常")
                    .setMessage("是否打开网络设置")
                    .setCancelable(false)
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        }
                    }).create();
            dialog.show();

        }
        final Dialog dialog= DialogUtils.getLoadingDialog(NetworkActivity.this);
        final String url="https://i.imgur.com/tGbaZCY.jpg";
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pb_load_img.setVisibility(View.VISIBLE);

               // new NetworkAsync(iv_net_img,pb_load_img,numpb_load).execute(url);
                if(dialog!=null)
                    dialog.show();

                //用Picasso加载图片
                Picasso.with(getApplicationContext()).load(url).into(iv_net_img, new Callback() {
                    @Override
                    public void onSuccess() {
                        pb_load_img.setVisibility(View.INVISIBLE);
                        dialog.dismiss();
                    }

                    @Override
                    public void onError() {

                    }
                });

            }
        });

    }
    public static boolean isNetworkConnected(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info=cm.getActiveNetworkInfo();
        if(info!=null)
            return info.isAvailable();
        return false;
    }

    class NetworkAsync extends AsyncTask<String,Integer,Bitmap>{
        ImageView iv_net;
        ProgressBar pb_load;
        NumberProgressBar numpb_load;

        public NetworkAsync(ImageView iv_net, ProgressBar pb_load, NumberProgressBar numpb_load) {
            this.iv_net = iv_net;
            this.pb_load = pb_load;
            this.numpb_load = numpb_load;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb_load.setVisibility(View.VISIBLE);
            numpb_load.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            iv_net.setImageBitmap(bitmap);
            pb_load.setVisibility(View.INVISIBLE);
            numpb_load.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb_load.setProgress(values[0]);
            numpb_load.setProgress(values[0]);
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
                byte[]bitmapData=bos.toByteArray();

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
}
