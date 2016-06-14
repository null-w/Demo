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

import java.util.List;
import java.util.Map;

/**
 * Created by zhiming on 2016/4/13.
 */
public class ContactAdapter extends BaseAdapter implements SectionIndexer{
    private List<Contact> list;
    private Context context;

    public ContactAdapter(List<Contact> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        Contact contact;

        char firstletter;
        for(int i = 0; i < list.size(); i++){
            contact=list.get(i);
            firstletter = contact.getLetter();
            if(sectionIndex == firstletter)
                return i;
        }

        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    final static class ViewHolder{
        TextView tv_name;
        TextView tv_catalog;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.tongxulu_item,parent,false);
            viewHolder.tv_catalog = (TextView) convertView.findViewById(R.id.tv_catalog);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //处理字母是否显示
        Contact contact=list.get(position);
        if(position==0){
            viewHolder.tv_catalog.setVisibility(View.VISIBLE);
            viewHolder.tv_catalog.setText(String.valueOf(contact.getLetter()));
        }else{
            char lastCatalog=list.get(position-1).getLetter();
            if(contact.getLetter()==(lastCatalog)){
                viewHolder.tv_catalog.setVisibility(View.GONE);
            }else{
                viewHolder.tv_catalog.setVisibility(View.VISIBLE);
                viewHolder.tv_catalog.setText(String.valueOf(contact.getLetter()));
            }
        }
        viewHolder.tv_name.setText(contact.getName());

        return convertView;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Contact getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
