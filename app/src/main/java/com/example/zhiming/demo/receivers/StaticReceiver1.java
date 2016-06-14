package com.example.zhiming.demo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by zhiming on 2016/5/23.
 */
public class StaticReceiver1 extends BroadcastReceiver {
    private static String TAG="静态注册1";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG,"这是Receiver1");
    }
}
