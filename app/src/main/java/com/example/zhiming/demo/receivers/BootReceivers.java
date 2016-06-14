package com.example.zhiming.demo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.example.zhiming.demo.MainActivity;

/**
 * Created by zhiming on 2016/5/24.
 */
public class BootReceivers extends BroadcastReceiver {
    private static String TAG="静态注册";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG,"wifi改变");
        Intent intent1=new Intent(context,MainActivity.class);
        context.startActivity(intent1);
    }
}
