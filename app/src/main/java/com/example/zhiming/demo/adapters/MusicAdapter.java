package com.example.zhiming.demo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.models.Contact;
import com.example.zhiming.demo.models.Music;

import java.util.List;

/**
 * Created by zhiming on 2016/4/13.
 */
public class MusicAdapter extends BaseAdapter{
    private List<Music> list;
    private Context context;

    public MusicAdapter(List<Music> list, Context context) {
        this.list = list;
        this.context = context;
    }



    final static class ViewHolder{
        TextView tv_title;
        TextView tv_url;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.musiclist_item,parent,false);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_musictitle);
            viewHolder.tv_url = (TextView) convertView.findViewById(R.id.tv_musicurl);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Music music=list.get(position);


        viewHolder.tv_title.setText(String.valueOf(music.getTitle()));
        viewHolder.tv_url.setText(String.valueOf(music.getUrl()));


        return convertView;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Music getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
