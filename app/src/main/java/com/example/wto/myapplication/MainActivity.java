package com.example.wto.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.*;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rockerview.RockerView;
import com.example.wto.myapplication.compoment.*;
import com.example.wto.myapplication.connection.Connect2Px4;
import com.example.wto.myapplication.data.Passage;
import com.example.wto.myapplication.data.SendData;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setThemeView();
        setContentView(R.layout.activity_main);

        init();
        initService();
    }

    private void setCustomActionBar()
    {
        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View mActionBarView = LayoutInflater.from(this).inflate(R.layout.title, null);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setCustomView(mActionBarView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);

            Toolbar parent =(Toolbar) mActionBarView.getParent();
            parent.setContentInsetsAbsolute(0,0);

            TextView textView = (TextView) findViewById(R.id.text_title);
            textView.setOnClickListener(new TitleOnClickListener(this));
            textView.setText(SendData.host);
        }
    }

    private void setThemeView()
    {
        if(this.getResources().getConfiguration().orientation ==Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setCustomActionBar();
    }

    private void init()
    {
        TextView textView = (TextView) findViewById(R.id.no_click_seekbar_process_left);
        RockerView rockerViewLeft = (RockerView) findViewById(R.id.no_click_seekbar_left);
        if (rockerViewLeft != null)
        {
            rockerViewLeft.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE);
            rockerViewLeft.setOnDirectionChangeListener(new RpiDirectionChangeListener(textView));
        }

        NoClickSeekBarMotor noClickSeekBar_right = (NoClickSeekBarMotor) findViewById(R.id.no_click_seekbar_right);
        noClickSeekBar_right.setTextView((TextView) findViewById(R.id.no_click_seekbar_process_right));
        noClickSeekBar_right.reset();

        Button button_left = (Button) findViewById(R.id.turn_left);
        Button button_right = (Button) findViewById(R.id.turn_right);

        button_left.setOnClickListener(new TurnOnClickListener(Passage.THREE, -5));
        button_right.setOnClickListener(new TurnOnClickListener(Passage.THREE, 5));

        Switch passage_model = (Switch) findViewById(R.id.passage_model);
        passage_model.setOnCheckedChangeListener(new ModelOnCheckedChangeListener(this, Passage.FIVE));

        Switch passage_switch = (Switch) findViewById(R.id.passage_switch);
        passage_switch.setOnCheckedChangeListener(new KillSwitchOnCheckedChangeListener(this, Passage.SIX));

    }

    private void initService()
    {
        try
        {
            ToastHandler toastHandler = new ToastHandler(this);
            Connect2Px4 connect2Px4 = new Connect2Px4(toastHandler);
            new Thread(connect2Px4).start();
        }
        catch (Exception e) { Log.e(TAG, "create service is fail", e); }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation, menu);
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
