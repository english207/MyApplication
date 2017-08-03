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
public class NoClickSeekBar extends android.support.v7.widget.AppCompatSeekBar {

    private Context context;
    private int oldsign;

    private TextView textView;

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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override
    protected synchronized void onDraw(Canvas canvas)
    {
        canvas.rotate(-90);
        canvas.translate(-getHeight(), 0);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (!isEnabled())
        {
            return false;
        }

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                setProgress(getMax() - (int) (getMax() * event.getY() / getHeight()));
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;
    }

}
