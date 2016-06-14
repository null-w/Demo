package com.example.zhiming.demo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.models.Temp;
import com.example.zhiming.demo.models.User;

import java.util.List;

import static com.example.zhiming.demo.R.id.tv_item1;
import static com.example.zhiming.demo.R.id.tv_item2;

/**
 * Created by zhiming on 2016/4/9.
 */
public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
    }
    private static class ViewHolder{
        TextView tv_user_id;
        TextView tv_user_name;
        TextView tv_user_pwd;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user=getItem(position);

        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.net_user_list_item,parent,false);
            viewHolder.tv_user_id= (TextView) convertView.findViewById(R.id.tv_user_id);
            viewHolder.tv_user_name= (TextView) convertView.findViewById(R.id.tv_user_name);
            viewHolder.tv_user_pwd= (TextView) convertView.findViewById(R.id.tv_user_pwd);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)convertView.getTag();

        }
        viewHolder.tv_user_id.setText(user.getUid());
        viewHolder.tv_user_name.setText(user.getUname());
        viewHolder.tv_user_pwd.setText(user.getUpwd());


        return convertView;
    }

}
