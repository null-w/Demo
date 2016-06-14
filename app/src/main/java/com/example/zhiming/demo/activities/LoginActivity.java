package com.example.zhiming.demo.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.WeChat;
import com.example.zhiming.demo.models.ResultLogin;
import com.example.zhiming.demo.util.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhiming on 2016/6/1.
 */
public class LoginActivity extends AppCompatActivity {
    Button btn_login,btn_reg;
    EditText et_uuid,et_userPwd;
    String strre="";
    Handler handler;
    private static final int REQUESTCODE=0x110;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar=getActionBar();


        btn_login= (Button) findViewById(R.id.btn_login);
        btn_reg= (Button) findViewById(R.id.btn_reg);
        et_uuid= (EditText) findViewById(R.id.et_uuid);
        et_userPwd= (EditText) findViewById(R.id.et_userPwd);
        final String url="http://52.38.232.240:8080/jfinal_gradle/apkusers/login";
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uuid= et_uuid.getText().toString();
                String userPwd=et_userPwd.getText().toString();
                Map<String ,String> map = new HashMap<String, String>();
                map.put("userId",uuid);
                map.put("userPwd",userPwd);
                OkHttpClient client=new OkHttpClient();
                RequestBody body=new FormBody.Builder()
                        .add("userId",uuid)
                        .add("userPwd",userPwd)
                        .build();


                Request request=new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //Toast.makeText(getApplicationContext(),"获取数据失败",Toast.LENGTH_LONG).show();
                        Log.v("failure","获取数据失败");

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();

                        String json=response.body().string();
                        Log.v("success",json);
                        Gson gson= new GsonBuilder().create();
                        ResultLogin resultLogin=gson.fromJson(json, ResultLogin.class);
                        if(resultLogin.getSuccess()){
                            Log.v("Login","登录成功");
                            Intent intent=new Intent(LoginActivity.this, WeChat.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Log.v("Login","登录失败");
                        }

                    }
                });

            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(LoginActivity.this,RegActivity.class),REQUESTCODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUESTCODE&&resultCode==0x111){
            if(data!=null){
                Bundle bundle=data.getExtras();
                if(bundle!=null){
                    String userId=bundle.getString("userId","1001");
                    String userPwd=bundle.getString("userPwd","1234");
                    et_uuid.setText(userId);
                    et_userPwd.setText(userPwd);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
