package com.example.wto.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import com.example.wto.myapplication.data.SendData;

/**
 * Created by WTO on 2017/9/2 0002.
 *
 */
public class LoadActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setView();
        setContentView(R.layout.load);
        init();
    }

    private void setView()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //全屏
    }

    private void init()
    {
        SharedPreferences read  = getSharedPreferences("data", MODE_PRIVATE);
        SendData.host = read.getString("host", "192.168.1.30");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(LoadActivity.this, MainActivity.class));
                finish();
            }
        }, 700);
    }
}
