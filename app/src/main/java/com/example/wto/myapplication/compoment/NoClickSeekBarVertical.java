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
public class NoClickSeekBarVertical extends android.support.v7.widget.AppCompatSeekBar {

    private Context context;
    private int oldsign;

    private TextView textView;

    public NoClickSeekBarVertical(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public NoClickSeekBarVertical(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public NoClickSeekBarVertical(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init()
    {
        setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                // TODO 自动生成的方法存根
                if( progress>oldsign + 20 || progress<oldsign - 20)
                {
                    seekBar.setProgress(oldsign);
                    return;
                }
                seekBar.setProgress(progress);
                oldsign = progress;

                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO 自动生成的方法存根
                seekBar.setProgress(oldsign);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO 自动生成的方法存根

            }
        });


    }

    public void setTextView(TextView textView)
    {
        this.textView = textView;
        oldsign = 50;
        setProgress(50);        // 初始化50
    }
}
