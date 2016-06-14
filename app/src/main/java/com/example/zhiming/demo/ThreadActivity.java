package com.example.zhiming.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhiming on 2016/5/3.
 */
public class ThreadActivity extends Activity {
    Thread thread=null;
    int count=10;
    boolean flag;
    TextView tv_count;
    Handler handler;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        Button btn_start= (Button) findViewById(R.id.btn_thread_start);
        Button btn_stop= (Button) findViewById(R.id.btn_thread_stop);
        tv_count= (TextView) findViewById(R.id.tv_count);
        final ImageView iv_djs= (ImageView) findViewById(R.id.iv_djs);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x110){
                   // tv_count.setText(String.valueOf(count));
                    tv_count.setText(String.valueOf(msg.arg1));
                }
            }
        };

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_djs.setImageResource(R.drawable.num_animotion);
                animationDrawable= (AnimationDrawable) iv_djs.getDrawable();

                animationDrawable.start();
                int duration=0;
                for(int i=0;i<animationDrawable.getNumberOfFrames();i++){
                    duration+=animationDrawable.getDuration(i);
                }
                Handler handler1=new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(ThreadActivity.this,MainActivity.class);
                        startActivity(intent );
                    }
                },duration);
                /*flag=true;
                thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(flag){
                            //Log.v("str",String.valueOf(count++));
                            try {
                                while(count>0){
                                    //handler.sendEmptyMessage(0x110);

                                    Message msg = new Message();
                                    msg.what=0x110;
                                    msg.arg1=count;
                                    handler.sendMessage(msg);
                                    Thread.sleep(1000);
                                    count--;
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
                thread.start();*/
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
            }
        });

    }
}
