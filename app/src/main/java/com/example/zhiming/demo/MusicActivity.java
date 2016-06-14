package com.example.zhiming.demo;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.zhiming.demo.adapters.MusicAdapter;
import com.example.zhiming.demo.models.Music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhiming on 2016/5/7.
 */
public class MusicActivity extends Activity {
    ImageButton play,stop,next;
    ListView lv_music;

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String outputFile=null;
    private  String palyfile=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_paly);
        play = (ImageButton) findViewById(R.id.ib_play);
        stop = (ImageButton) findViewById(R.id.ib_stop);
        next = (ImageButton) findViewById(R.id.ib_next);
        lv_music=(ListView)findViewById(R.id.lv_music);

        ContentResolver contentResolver=getApplication().getContentResolver();
        if(contentResolver==null)
            ;
        Cursor cursor= contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if(cursor==null)
            ;
        //Map<String,String> map=new HashMap<>();
        List<Music> list =new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String title= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String url=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                Music music=new Music(title,url);
                list.add(music);
            }while(cursor.moveToNext());
        }

        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.musiclist_item,R.id.tv_musictitle,list);
        MusicAdapter musicAdapter=new MusicAdapter(list,getApplicationContext());


        lv_music.setAdapter(musicAdapter );
        lv_music.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Music music= (Music) parent.getItemAtPosition(position);
                Log.v("a",music.getUrl());
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                    mediaPlayer=null;
                }

                mediaPlayer=new MediaPlayer();
                palyfile=music.getUrl();
                try {
                    mediaPlayer.setDataSource(palyfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //outputFile= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Dance for Me Wallis.mp3";


        /*mediaRecorder= new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(outputFile);*/

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                /*try {
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                }

                catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"Autio started",Toast.LENGTH_LONG).show();*/
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                /*mediaRecorder.stop();
                mediaRecorder=null;
                Toast.makeText(getApplicationContext(),"Autio recored",Toast.LENGTH_LONG).show();*/
            }
        });
    }
}
