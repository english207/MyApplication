package com.example.wto.myapplication.compoment;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.wto.myapplication.data.Passage;
import com.example.wto.myapplication.data.SendData;

/**
 * Created by WTO on 2017/8/3 0003.
 *
 */
public class NoClickSeekBarCalibration extends NoClickSeekBarVertical {

    private static final String TAG = "calibration";

    private Passage passage;

    public NoClickSeekBarCalibration(Context context) {
        super(context);
    }

    public NoClickSeekBarCalibration(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public NoClickSeekBarCalibration(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPassage(Passage passage) {
        this.passage = passage;
        Log.i(TAG, "当前切换至 通道 - " + passage.getNum() + " " + passage.getName());
    }

    @Override
    public void changeData(int progress)
    {
        super.changeData(progress);
        SendData.mapping[passage.getNum()] = progress;
    }
}
