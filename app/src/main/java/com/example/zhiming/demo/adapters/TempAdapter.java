package com.example.zhiming.demo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.models.Temp;

import java.util.List;

import static com.example.zhiming.demo.R.id.tv_item1;
import static com.example.zhiming.demo.R.id.tv_item2;

/**
 * Created by zhiming on 2016/4/9.
 */
public class TempAdapter extends ArrayAdapter<Temp> {

    public TempAdapter(Context context, int resource, List<Temp> objects) {
        super(context, resource, objects);
    }
    private static class ViewHolder{
        TextView tv_item1;
        TextView tv_item2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Temp temp=getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.show_item,parent,false);
            viewHolder.tv_item1= (TextView) convertView.findViewById(tv_item1);
            viewHolder.tv_item2= (TextView) convertView.findViewById(tv_item2);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)convertView.getTag();

        }

        viewHolder.tv_item1.setText(temp.getTid());
        viewHolder.tv_item2.setText(temp.getTname());

        return convertView;
    }
}
