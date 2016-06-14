package com.example.zhiming.demo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by zhiming on 2016/5/23.
 */
public class FristReceivers extends BroadcastReceiver {
    private static String TAG="frist_receivers";
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getStringExtra("msg");
        Log.v(TAG,msg);
        //忽略广播，后面的receiver不再接接收
        abortBroadcast();
        Bundle bundle=new Bundle();
        bundle.putString("msg",msg+TAG);
        setResultExtras(bundle);
    }
}
