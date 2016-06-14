package com.example.zhiming.demo.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.util.HttpUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhiming on 2016/5/23.
 */
public class OkHttpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myservicetest_view);
        Button btn_start_service= (Button) findViewById(R.id.btn_start_service);
        //final String url="http://52.38.232.240:8080/jfinal_gradle/sendget1?uuid=1001&userPwd=1234";
        final String url="http://52.38.232.240:8080/jfinal_gradle/sendpost";
        final String uuid="1001";
        final String userPwd="1234";
        final Map<String,String> map=new HashMap<>();
        map.put("uuid","1001");
        map.put("userPwd","1234");
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtils.postData(url,map);

            }
        });
    }



}
