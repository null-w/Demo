package com.example.zhiming.demo.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.example.zhiming.demo.util.HttpUtils;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by zhiming on 2016/5/18.
 */
public class MyTestServices extends IntentService {

    public static final String ACTION="com.zhiming";

    public MyTestServices(){
        super("");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyTestServices(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String json= HttpUtils.getString(intent.getStringExtra("url"));
        Log.v("services",json);
        Intent i=new Intent(ACTION);
        i.putExtra("myjson",json);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(i);

        /*ResultReceiver receiver= (ResultReceiver) intent.getExtras().get("myreceiver");
        Bundle bundle=new Bundle();
        bundle.putString("myjson",json);
        if(receiver!=null)
            receiver.send(Activity.RESULT_OK,bundle);*/

        /*OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(intent.getStringExtra("url")).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("error","error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("json",response.body().string());
            }
        });*/
    }

}
