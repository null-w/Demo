package com.example.zhiming.demo.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by zhiming on 2016/5/23.
 */
public class MyStaticService extends IntentService {
    static final String ACTION="android.intent.action.My_Static_Action";


    public MyStaticService(){
            super("service-test");
        }
        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
    public MyStaticService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent i=new Intent(ACTION);
        sendBroadcast(i);
    }
}