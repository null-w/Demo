package com.example.zhiming.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.zhiming.demo.fragment.FragmentFaxian;
import com.example.zhiming.demo.fragment.FragmentTongxunlu;
import com.example.zhiming.demo.fragment.FragmentWeixin;
import com.example.zhiming.demo.fragment.FragmentWo;

/**
 * Created by zhiming on 2016/4/23.
 */
public class WeChat extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener  {
    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    FragmentWeixin weixin=null;
    FragmentTongxunlu tongxunlu=null;
    FragmentFaxian faxian=null;
    FragmentWo wo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weixin_main);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_menu_start_conversation,"微信").setActiveColor(R.color.colorWeiXin))
                .addItem(new BottomNavigationItem(R.mipmap.ic_menu_friendslist,"通讯录").setActiveColor(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.ic_explore_black_36dp, "发现").setActiveColor(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_account_circle_black_36dp,"我").setActiveColor(R.color.colorPrimaryDark))
                .setFirstSelectedPosition(lastSelectedPosition)
                .setMode(BottomNavigationBar.MODE_SHIFTING)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }
    private void setDefaultFragment() {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        weixin = new FragmentWeixin();
        ft.replace(R.id.fl_container,weixin).commit();
    }

    @Override
    public void onTabSelected(int position) {
        Log.d("position", "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
        switch (position)
        {
            case 0:
                if(weixin==null)
                    weixin=new FragmentWeixin();
                lastSelectedPosition=position;
                ft.replace(R.id.fl_container,weixin).commit();

                break;
            case 1:
                if(tongxunlu==null)
                    tongxunlu=new FragmentTongxunlu();
                lastSelectedPosition=position;
                ft.replace(R.id.fl_container,tongxunlu).commit();

                break;
            case 2:
                if(faxian==null)
                    faxian=new FragmentFaxian();
                bottomNavigationBar.setActiveColor(R.color.colorWeiXin);
                ft.replace(R.id.fl_container,faxian).commit();

                break;
            case 3:
                if(wo==null)
                    wo=new FragmentWo();
                ft.replace(R.id.fl_container,wo).commit();
                break;

        }

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
