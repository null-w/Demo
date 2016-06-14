package com.example.zhiming.demo.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.adapters.UserAdapter;
import com.example.zhiming.demo.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiming on 2016/5/12.
 */
public class InitTask extends AsyncTask<String,String,String> {
    ListView lv_net_user;
    ProgressBar pb_load_user;
    Context context;
    public InitTask(Context context,ListView lv_net_user, ProgressBar pb_load_user) {
        this.context=context;
        this.lv_net_user = lv_net_user;
        this.pb_load_user = pb_load_user;
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpUtils.getString(params[0]);
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pb_load_user.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        InitAdapter<User> userInitAdapter=new InitAdapter<>();
        userInitAdapter.initUserData(context,lv_net_user,s);
        pb_load_user.setVisibility(View.INVISIBLE);
    }

    public void initUserData(String s) {
        Gson gson=new Gson();
        JsonArray jsonArray=new JsonArray();
        JsonParser jsonParser=new JsonParser();
        jsonArray=jsonParser.parse(s).getAsJsonArray();

        List<User> list=new ArrayList<>();
        for(JsonElement element:jsonArray){
            User user=gson.fromJson(element,User.class);
            //Log.v("strR",user.getUid());
            list.add(user);
        }

        ArrayAdapter userAdapter=new UserAdapter(context, R.layout.net_user_list_item,list);
        lv_net_user.setAdapter(userAdapter);
    }
}
