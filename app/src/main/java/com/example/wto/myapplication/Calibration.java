package com.example.wto.myapplication;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.example.wto.myapplication.compoment.NoClickSeekBarCalibration;
import com.example.wto.myapplication.data.Passage;

/**
 * Created by WTO on 2017/8/4 0004.
 *
 */

public class Calibration extends AppCompatActivity
{
    private NoClickSeekBarCalibration noClickSeekBarCalibration;
    private TextView whatAction;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.calibration);
        init();
    }

    private void init()
    {
        noClickSeekBarCalibration = (NoClickSeekBarCalibration) findViewById(R.id.no_click_seekbar_calibration);
        noClickSeekBarCalibration.setTextView((TextView) findViewById(R.id.no_click_seekbar_process_calibration));

        whatAction = (TextView) findViewById(R.id.whatAction);
    }

    public void passageListener(View v)
    {
        int id = v.getId();
        switch (id)
        {
            case R.id.calibration_1:
                changePassage(Passage.ONE);
                break;
            case R.id.calibration_2:
                changePassage(Passage.TWO);
                break;
            case R.id.calibration_3:
                changePassage(Passage.THREE);
                break;
            case R.id.calibration_4:
                changePassage(Passage.FOUR);
                break;
        }
    }

    private void changePassage(Passage passage)
    {
        Toast.makeText(this, "开始校准 " + passage.getName(), Toast.LENGTH_SHORT).show();
        noClickSeekBarCalibration.reset();
        noClickSeekBarCalibration.setPassage(passage);
        whatAction.setText(passage.getName());
    }

}
