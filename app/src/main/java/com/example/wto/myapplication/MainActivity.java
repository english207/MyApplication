package com.example.wto.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rockerview.RockerView;
import com.example.wto.myapplication.compoment.NoClickSeekBar;
import com.example.wto.myapplication.compoment.NoClickSeekBarVertical;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
         // Inflate the menu; this adds items to the action bar if it is present.
         //填充选项菜单（读取XML文件、解析、加载到Menu组件上）
         getMenuInflater().inflate(R.menu.navigation, menu);
         return true;
    }

    //重写OptionsItemSelected(MenuItem item)来响应菜单项(MenuItem)的点击事件（根据id来区分是哪个item）
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
