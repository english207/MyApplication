package com.example.wto.myapplication;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;
import com.example.wto.myapplication.compoment.NoClickSeekBarCalibration;
import com.example.wto.myapplication.compoment.NoClickSeekBarVertical;

/**
 * Created by WTO on 2017/8/4 0004.
 *
 */

public class Calibration extends AppCompatActivity
{
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
        NoClickSeekBarCalibration noClickSeekBarCalibration = (NoClickSeekBarCalibration) findViewById(R.id.no_click_seekbar_calibration);
        noClickSeekBarCalibration.setTextView((TextView) findViewById(R.id.no_click_seekbar_process_calibration));
    }

}
