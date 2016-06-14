package com.example.zhiming.demo.util;

import android.content.ContentValues;

import android.content.Intent;
import android.util.Log;
import com.example.zhiming.demo.WeChat;
import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhiming on 2016/5/12.
 */
public class HttpUtils {
    public static String getString(String param) {
        StringBuffer resultStr=new StringBuffer();
        InputStream is=null;
        BufferedReader br=null;
        try{
            URL myurl=new URL(param);
            HttpURLConnection connection= (HttpURLConnection) myurl.openConnection();
            is=connection.getInputStream();
            br=new BufferedReader(new InputStreamReader(is));

            String strTemp="";

            while((strTemp=br.readLine())!=null){
                resultStr.append(strTemp);
            }
            Log.v("strR",resultStr.toString());
        }
        catch (Exception e){

        }finally {
            if(is!=null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(br!=null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return resultStr.toString();
    }
    public static final MediaType JSON=MediaType.parse("application/json;charset=utf-8");

    public static String postData(String url, Map<String,String> map){

        String uuid="uuid";
        String userPwd="userPwd";



        OkHttpClient client=new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add(uuid,map.get(uuid))
                .add(userPwd,map.get(userPwd))
                .build();


        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();

        /*try {
            Response response=client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return "登录失败";
        }
*/
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Toast.makeText(getApplicationContext(),"获取数据失败",Toast.LENGTH_LONG).show();
                Log.v("failure","获取数据失败");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                Log.v("success",response.body().string());

            }
        });
        return "";


    }
}
