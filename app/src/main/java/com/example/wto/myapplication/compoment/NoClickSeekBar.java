package com.example.wto.myapplication.compoment;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by WTO on 2017/8/3 0003.
 *
 */
public abstract class NoClickSeekBar extends android.support.v7.widget.AppCompatSeekBar {

    private Context context;
    private int oldsign;

    public TextView textView;

    public NoClickSeekBar(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public NoClickSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public NoClickSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public abstract void changeData(int progress);

    private void init()
    {
        setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if( progress>oldsign + 20 || progress<oldsign - 20)
                {
                    seekBar.setProgress(oldsign);
                    return;
                }
                seekBar.setProgress(progress);
                oldsign = progress;
                changeData(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setProgress(oldsign);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void setTextView(TextView textView)
    {
        this.textView = textView;
        reset();
    }

    public void reset() {
        oldsign = 50;
        setProgress(50);        // 初始化50
    }
}
