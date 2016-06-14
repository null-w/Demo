package com.example.zhiming.demo.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.services.MyTestServices;

/**
 * Created by zhiming on 2016/5/18.
 */
public class TestServiceActivies extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myservicetest_view);
        final Button btn_start_service= (Button) findViewById(R.id.btn_start_service);
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMyService();
                btn_start_service.setText("Start service");
            }
        });
    }

    protected void launchMyService(){
        Intent intent=new Intent(this, MyTestServices.class);
        intent.putExtra("url","http://52.38.232.240:8080/jfinal_gradle/users");
        //intent.putExtra("myreceiver",receiver);
        startService(intent);
    }
    //广播
    BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("ac_json",intent.getStringExtra("myjson"));
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("ac","onResume...");
        IntentFilter filter=new IntentFilter(MyTestServices.ACTION);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("ac","onPause...");
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
    }
    /*
    private static Handler handler=new Handler();
    private ResultReceiver receiver=new ResultReceiver(handler){
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if(resultCode==RESULT_OK){
                Log.v("activity",resultData.getString("myjson","error"));
            }
        }
    };*/

}
