package com.example.zhiming.demo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.adapters.UserAdapter;
import com.example.zhiming.demo.models.User;
import com.example.zhiming.demo.util.HttpUtils;
import com.example.zhiming.demo.util.InitTask;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiming on 2016/5/11.
 */
public class UserListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_user);

        ListView lv_net_user= (ListView) findViewById(R.id.lv_net_user);
        ProgressBar pb_load_user= (ProgressBar) findViewById(R.id.pb_load_user);

        String strUrl="http://52.38.232.240:8080/jfinal_gradle/users";
        //new InitTask(getApplicationContext(),lv_net_user,pb_load_user).execute(strUrl);
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(strUrl).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("error","error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("json",response.body().string());
            }
        });
        /*try {
            Response response=okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }


}
