package com.example.wto.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rockerview.RockerView;
import com.example.wto.myapplication.compoment.NoClickSeekBarVertical;
import com.example.wto.myapplication.connection.Connect2Px4;

public class MainActivity extends AppCompatActivity
{
    private TextView textView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        init();
        initService();
    }

    private void init()
    {
        textView = (TextView) findViewById(R.id.no_click_seekbar_process_left);
        RockerView rockerViewLeft = (RockerView) findViewById(R.id.no_click_seekbar_left);
        if (rockerViewLeft != null)
        {
            rockerViewLeft.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE);
            rockerViewLeft.setOnAngleChangeListener(new RockerView.OnAngleChangeListener() {
                @Override
                public void onStart() {
                    textView.setText(null);
                }

                @Override
                public void angle(double angle) {
                    textView.setText(String.valueOf(angle));
                }

                @Override
                public void onFinish() {
                    textView.setText(null);
                }
            });
        }

        NoClickSeekBarVertical noClickSeekBar_right = (NoClickSeekBarVertical) findViewById(R.id.no_click_seekbar_right);
        noClickSeekBar_right.setTextView((TextView) findViewById(R.id.no_click_seekbar_process_right));

    }

    private void initService()
    {
        try
        {
            Connect2Px4 connect2Px4 = new Connect2Px4("192.168.1.29", 8000);
            new Thread(connect2Px4).start();
        }
        catch (Exception e) { Log.e(TAG, "create service is fail", e); }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
         getMenuInflater().inflate(R.menu.navigation, menu);
         return true;
    }

    //响应菜单项(MenuItem)的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.calibration_memu:
                Toast.makeText(this, "开始校准", Toast.LENGTH_SHORT).show();

                try
                {
                    Thread.sleep(500);
                }
                catch (Exception e) { e.printStackTrace(); }

                startActivity(new Intent(this, Calibration.class));
                break;
            default:
                break;
        }
      return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onDestroy()
//    {
//        super.onDestroy();
//        unbindService(conn);
//    }

//    private ServiceConnection conn = new ServiceConnection()
//    {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service)
//        {
//            ConnectService.ConnectBingder binder = (ConnectService.ConnectBingder) service;
//            binder.getService();
//
//            Log.v(TAG,"bind service ConnectService");
//        }
//
//        //client 和service连接意外丢失时，会调用该方法
//        @Override
//        public void onServiceDisconnected(ComponentName name)
//        {
//            Log.v("hjz","onServiceDisconnected  A");
//        }
//    };

}
