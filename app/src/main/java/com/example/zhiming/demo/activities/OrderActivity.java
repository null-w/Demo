package com.example.zhiming.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.services.MyOrderService;
import com.example.zhiming.demo.services.MyStaticService;


/**
 * Created by zhiming on 2016/5/23.
 */
public class OrderActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myservicetest_view);
        Button btn_start_service= (Button) findViewById(R.id.btn_start_service);
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             launchMyService();

            }
        });
    }

    protected void launchMyService(){
        Intent intent=new Intent(this, MyOrderService.class);
        intent.putExtra("msg","有序广播内容");
        //intent.putExtra("myreceiver",receiver);
        startService(intent);
    }
}
