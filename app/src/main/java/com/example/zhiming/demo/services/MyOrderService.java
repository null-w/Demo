package com.example.zhiming.demo.services;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by zhiming on 2016/5/23.
 */
public class MyOrderService extends IntentService{
    static final String ACTION="android.intent.action.MY_ORGER_ACTION";


    public MyOrderService(){
        super("service-test");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyOrderService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String msg=intent.getStringExtra("msg");
        Intent i=new Intent(ACTION);
        i.putExtra("msg",msg);
        //用了自定义permission
        sendOrderedBroadcast(i,"com.my.order.receiver.permission");
    }
}
