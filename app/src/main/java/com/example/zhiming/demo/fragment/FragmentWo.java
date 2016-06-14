package com.example.zhiming.demo.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import com.example.zhiming.demo.R;

import java.util.ArrayList;
import java.util.List;




/**
 * Created by zhiming on 2016/3/30.
 */
public class FragmentWo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*return super.onCreateView(inflater, container, savedInstanceState);*/
        return inflater.inflate(R.layout.wo,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_sacle= (Button) view.findViewById(R.id.btn_scale);
        final ImageView iv_showsacle = (ImageView) view.findViewById(R.id.iv_showscale);
        btn_sacle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //方法一
                ObjectAnimator scaleXAnimator=ObjectAnimator.ofFloat(iv_showsacle,View.SCALE_X,0.0f,1.0f,2.0f);
                scaleXAnimator.setDuration(3000);
                scaleXAnimator.setRepeatCount(2);
                ObjectAnimator scaleYAnimator=ObjectAnimator.ofFloat(iv_showsacle,View.SCALE_Y,0.0f,1.0f,2.0f);
                scaleYAnimator.setDuration(3000);
                scaleYAnimator.setRepeatCount(2);
                AnimatorSet animatorSet = new AnimatorSet();
                List<Animator> list=new ArrayList<Animator>();
                list.add(scaleXAnimator);
                list.add(scaleYAnimator);
                animatorSet.playTogether(list);
                animatorSet.start();

                /*方法二
                Animation scaleAnimation= AnimationUtils.loadAnimation(getContext(),R.anim.scale_home3_2);
                iv_showsacle.startAnimation(scaleAnimation);*/

                /*iv_showsacle.setImageResource(R.drawable.scale_home3);
                AnimationDrawable animationDrawable= (AnimationDrawable) iv_showsacle.getDrawable();
                animationDrawable.start();*/
            }
        });
    }
}
