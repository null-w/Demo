package com.example.zhiming.demo.util;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Window;
import android.widget.ImageView;
import com.example.zhiming.demo.R;

/**
 * Created by zhiming on 2016/5/17.
 */
public class DialogUtils {

    public static Dialog getLoadingDialog(Context context){
        Dialog dialog=new Dialog(context, R.style.myLoadingStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setCancelable(false);

        return dialog;
    }
}
