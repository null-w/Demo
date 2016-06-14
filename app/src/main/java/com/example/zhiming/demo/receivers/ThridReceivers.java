package com.example.zhiming.demo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by zhiming on 2016/5/23.
 */
public class ThridReceivers extends BroadcastReceiver {
    private static String TAG="thrid_receivers";
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=getResultExtras(true).getString("msg");
        Log.v(TAG,msg+" "+TAG);
//        abortBroadcast();
    }
}
