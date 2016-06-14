package com.example.zhiming.demo;

import android.app.Activity;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by zhiming on 2016/5/5.
 */
public class ProgressBarActivity extends Activity {
    ProgressBar pb_load;
    Button btn_load;
    Handler handler;
    //int progress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);

        pb_load= (ProgressBar) findViewById(R.id.pb_load);
        btn_load= (Button) findViewById(R.id.btn_pbload);

        /*handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x001)
                    pb_load.setProgress(progress);
                if(msg.what==0x002)
                    Toast.makeText(ProgressBarActivity.this,"加载完成",Toast.LENGTH_LONG).show();
            }
        };*/
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ProgressAsyncTask().execute();
                /*pb_load.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            int random =(int)(Math.random()*10);
                            progress+=random;
                            handler.sendEmptyMessage(0x001);
                            if(progress>=100)
                                break;
                            SystemClock.sleep(500);
                        }
                        handler.sendEmptyMessage(0x002);
                    }
                }).start();*/
            }
        });
    }

    class ProgressAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb_load.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("success"))
                Toast.makeText(ProgressBarActivity.this,"加载完成",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb_load.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(String... params) {
            int progress=0;
            while (true){
                int random =(int)(Math.random()*10);
                progress+=random;
                publishProgress(progress);
                if(progress>=100)
                    break;
                SystemClock.sleep(500);
            }
            return "success";
        }
    }
}
