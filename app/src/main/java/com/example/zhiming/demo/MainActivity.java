package com.example.zhiming.demo;

import android.animation.*;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.*;
import android.widget.ListView;
import com.example.zhiming.demo.activities.*;
import com.example.zhiming.demo.adapters.ContactAdapter;
import com.example.zhiming.demo.adapters.TempSimpleAdapter;
import com.example.zhiming.demo.homework4.DownloadActivity;
import com.example.zhiming.demo.homework4.Home4Activities;
import com.example.zhiming.demo.models.Contact;
import com.example.zhiming.demo.util.MyComparator;
import com.example.zhiming.demo.views.SideIndex;
import okhttp3.OkHttpClient;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lv_show = (ListView) findViewById(R.id.lv_show);
        SideIndex sideIndex = (SideIndex) findViewById(R.id.sideIndex);

        Random random = new Random();
        String str = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Contact> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            char ch = str.charAt(random.nextInt(26));

            Contact contact = new Contact("" + ch + i);

            list.add(contact);
        }
        Contact []c=new Contact[]{new Contact("啊啊"),new Contact("不了"),new Contact("传是"),new Contact("点点"),new Contact("恶人"),new Contact("分分"),
                new Contact("谷歌"),new Contact("呵呵"),new Contact("机2"),new Contact("空3"),new Contact("浏览"),new Contact("每秒"),new Contact("那你"),
                new Contact("哦哦"),new Contact("匹配"),new Contact("全球"),new Contact("人人"),new Contact("身上"),new Contact("天天"),new Contact("学习"),
                new Contact("英语"),new Contact("政治")};
        for(int i=0 ;i<c.length;i++){
            list.add(c[i]);
        }
        Collections.sort(list, new MyComparator());
        ContactAdapter adapter = new ContactAdapter(list, getApplicationContext());
        /*int []imgarr=new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,
                R.drawable.img4,R.drawable.img5,R.drawable.img7,
                R.drawable.img8,R.drawable.img9,R.drawable.img10,
                R.drawable.img11,R.drawable.img12,R.drawable.img13
        };
        List<Map<String,String>> list=new ArrayList<>();
        for(int i=0;i<12;i++){
            Map<String,String> map=new Hashtable<>();
            map.put("img_item",String.valueOf(imgarr[i]));
            map.put("item1","name"+i);
            map.put("item2","tel"+i);
            list.add(map);
        }


        String []from=new String[]{"img_item","item1","item2"};
        int []to=new int[]{R.id.img_item,R.id.tv_item1,R.id.tv_item2};

        //SimpleAdapter adapter=new SimpleAdapter(getApplicationContext(),list,R.layout.show_item,from,to);
        TempSimpleAdapter adapter=new TempSimpleAdapter(getApplicationContext(),list,R.layout.show_item,from,to);*/
/*
        //SimpleAdapter的使用


        //创建List<Map<String,String>>
        List<Map<String,String>> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            Map<String,String> map=new Hashtable<>();
            map.put("item1","item1-"+i);
            map.put("item2","item2-"+i);
            list.add(map);
        }


        String []from=new String[]{"item1","item2"};
        int []to=new int[]{R.id.tv_item1,R.id.tv_item2};

        SimpleAdapter adapter=new SimpleAdapter(getApplicationContext(),list,R.layout.show_item,from,to);
        lv_show.setAdapter(adapter);
*/
        //ArrayAdapter
        /*List<Temp>list=new ArrayList<>();
        for(int i=0;i<20;i++){
            Temp temp=new Temp();
            temp.setTid("id"+i);
            temp.setTname("name"+i);
            list.add(temp);
        }
        ArrayAdapter<Temp> adapter=new TempAdapter(getApplicationContext(),R.layout.show_item,list);
        */

        lv_show.setAdapter(adapter);
        sideIndex.setListView(lv_show);

        //final Animation alphaAnimation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha_animotion);
        final AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        //重复动画次数
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                //属性动画
                /*ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(fab,View.ALPHA,0.0f,1.0f);
                alphaAnimator.setRepeatCount(5);
                alphaAnimator.setRepeatMode(ValueAnimator.REVERSE);
                alphaAnimator.setDuration(5000);
                alphaAnimator.start();*/

                /*ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(fab,View.SCALE_X,0.0f,2.0f,0.5f,1.0f);
                scaleXAnimator.setDuration(5000);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(fab,View.SCALE_Y,0.0f,2.0f,0.5f,1.0f);
                scaleYAnimator.setDuration(5000);
                //scaleYAnimator.start();
                //scaleXAnimator.start();
                AnimatorSet animatorSet = new AnimatorSet();
                Collection<Animator> collection=new ArrayList<Animator>();
                collection.add(scaleXAnimator);
                collection.add(scaleYAnimator);
                animatorSet.playTogether(collection);
                animatorSet.start();
*/

                /*ObjectAnimator rotateAnimator= ObjectAnimator.ofFloat(fab,View.ROTATION_Y,0.0f,360.0f);
                rotateAnimator.setDuration(5000);
                rotateAnimator.start();*/

                /*ObjectAnimator tanslateAnimator = ObjectAnimator.ofFloat(fab,View.TRANSLATION_X,0.0f,-100f,-50.0f,-200.0f);
                tanslateAnimator.setDuration(5000);
                tanslateAnimator.start();*/

                /*ObjectAnimator rgbAnimator =ObjectAnimator.ofArgb(fab,"BackgroundColor(",0xffabcaaa,0xffffff00,0xffff00ff,0xff00ffff);
                rgbAnimator.setDuration(5000);
                rgbAnimator.start();*/
                /*ObjectAnimator rgbAnimator =ObjectAnimator.ofInt(fab,"BackgroundTintList", Color.BLUE,Color.GREEN,Color.LTGRAY,Color.BLACK);
                rgbAnimator.setEvaluator(new ArgbEvaluator());
                rgbAnimator.setDuration(5000);
                rgbAnimator.start();*/

                //视图动画
                //fab.startAnimation(alphaAnimation);
//                Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_animotion);
//                Animation rotateAnimation =  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_animotion);
//                Animation translateAnimation =  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_animotion);
                //fab.startAnimation(scaleAnimation);
                //fab.startAnimation(rotateAnimation);
                //fab.startAnimation(translateAnimation);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //Intent intent=new Intent(MainActivity.this,ThreadActivity.class);
                //Intent intent=new Intent(MainActivity.this,ProgressBarActivity.class);
                //Intent intent=new Intent(MainActivity.this,MusicActivity.class);
                //Intent intent=new Intent(MainActivity.this,NetworkActivity.class);
                //Intent intent=new Intent(MainActivity.this,UserListActivity.class);
                //Intent intent=new Intent(MainActivity.this,TestServiceActivies.class);
                //Intent intent=new Intent(MainActivity.this,MyStaticActivity.class);
                //Intent intent=new Intent(MainActivity.this,OrderActivity.class);
                //Intent intent=new Intent(MainActivity.this,Home4Activities.class);
                //Intent intent=new Intent(MainActivity.this,RequestActivity.class);
                //Intent intent=new Intent(MainActivity.this,OkHttpActivity.class);
                //Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                Intent intent=new Intent(MainActivity.this,DownloadActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
