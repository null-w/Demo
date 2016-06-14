package com.example.zhiming.demo.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.adapters.UserAdapter;
import com.example.zhiming.demo.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiming on 2016/5/16.
 */
public class InitAdapter<T> {
    public void initUserData(Context context, ListView lv_net_user, String result) {
        List<User> list = (List<User>) getList(result,User.class);

        ArrayAdapter userAdapter=new UserAdapter(context, R.layout.net_user_list_item,list);
        lv_net_user.setAdapter(userAdapter);
    }

    private List<T> getList(String result,Type type) {
        Gson gson=new Gson();
        JsonArray jsonArray=new JsonArray();
        JsonParser jsonParser=new JsonParser();
        jsonArray=jsonParser.parse(result).getAsJsonArray();

        List<T> list=new ArrayList<>();
        for(JsonElement element:jsonArray){
            T myT=gson.fromJson(element,type);
            //Log.v("strR",user.getUid());
            list.add(myT);
        }
        return list;
    }
}
