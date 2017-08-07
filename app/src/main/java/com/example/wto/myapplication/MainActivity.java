package com.example.wto.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rockerview.RockerView;
import com.example.wto.myapplication.compoment.NoClickSeekBar;
import com.example.wto.myapplication.compoment.NoClickSeekBarVertical;
import com.example.wto.myapplication.connection.Connect2Px4;

public class MainActivity extends AppCompatActivity
{
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

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

//        NoClickSeekBar noClickSeekBar_left = (NoClickSeekBar) findViewById(R.id.no_click_seekbar_left);
//        noClickSeekBar_left.setTextView((TextView) findViewById(R.id.no_click_seekbar_process_left));

        NoClickSeekBarVertical noClickSeekBar_right = (NoClickSeekBarVertical) findViewById(R.id.no_click_seekbar_right);
        noClickSeekBar_right.setTextView((TextView) findViewById(R.id.no_click_seekbar_process_right));

        try
        {
            Thread thread = new Thread(new Connect2Px4("192.168.1.30", 11332));
            thread.start();
        }
        catch (Exception e)
        {
            Log.e("MainActivity", "created Connect2Px4 is fail, please check your network");
            Toast.makeText(this, "created Connect2Px4 is fail, please check your network", Toast.LENGTH_SHORT).show();
        }
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

    private String getDirection(RockerView.Direction direction) {
        String message = null;
        switch (direction) {
            case DIRECTION_LEFT:
                message = "左";
                break;
            case DIRECTION_RIGHT:
                message = "右";
                break;
            case DIRECTION_UP:
                message = "上";
                break;
            case DIRECTION_DOWN:
                message = "下";
                break;
            case DIRECTION_UP_LEFT:
                message = "左上";
                break;
            case DIRECTION_UP_RIGHT:
                message = "右上";
                break;
            case DIRECTION_DOWN_LEFT:
                message = "左下";
                break;
            case DIRECTION_DOWN_RIGHT:
                message = "右下";
                break;
            default:
                break;
        }
        return message;
    }

}
