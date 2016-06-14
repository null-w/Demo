package com.example.zhiming.demo.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.models.Temp;

import java.util.List;
import java.util.Map;

import static com.example.zhiming.demo.R.id.img_item;
import static com.example.zhiming.demo.R.id.tv_item1;
import static com.example.zhiming.demo.R.id.tv_item2;

/**
 * Created by zhiming on 2016/4/11.
 */
public class TempSimpleAdapter extends SimpleAdapter {
    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    Context context;
    public TempSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context=context;
    }
    private static class ViewHolder{
        ImageView img_item;
        TextView tv_item1;
        TextView tv_item2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Map map= (Map) getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.show_item,parent,false);
            viewHolder.img_item= (ImageView) convertView.findViewById(img_item);
            viewHolder.tv_item1= (TextView) convertView.findViewById(tv_item1);
            viewHolder.tv_item2= (TextView) convertView.findViewById(tv_item2);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.img_item.setImageResource(Integer.parseInt(map.get("img_item").toString()));
        viewHolder.tv_item1.setText(map.get("item1").toString());
        viewHolder.tv_item2.setText(map.get("item2").toString());

        return convertView;
    }
}
