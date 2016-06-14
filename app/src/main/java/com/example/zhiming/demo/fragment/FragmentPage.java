package com.example.zhiming.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.zhiming.demo.R;

/**
 * Created by zhiming on 2016/4/23.
 */
public class FragmentPage extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage=1;

    public static FragmentPage newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,page);
        FragmentPage fragmentPage=new FragmentPage();
        fragmentPage.setArguments(args);
        return fragmentPage;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_page,container,false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #"+mPage);
        return view;
    }
}
