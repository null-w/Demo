package com.example.zhiming.demo.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.zhiming.demo.R;
import com.example.zhiming.demo.util.HttpUtils;


/**
 * Created by zhiming on 2016/5/23.
 */
public class PostActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myservicetest_view);
        Button btn_start_service= (Button) findViewById(R.id.btn_start_service);
        final String url="http://52.38.232.240:8080/jfinal_gradle/sendpost";
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostTask().execute(url);
            }
        });
    }

    class PostTask extends AsyncTask<String ,Integer,String>{
        @Override
        protected String doInBackground(String... params) {
            return HttpUtils.getString(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }
    }

}
