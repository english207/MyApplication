package com.example.wto.myapplication.compoment;

import android.graphics.Point;
import android.widget.TextView;
import com.example.rockerview.RockerView;
import com.example.wto.myapplication.data.Passage;
import com.example.wto.myapplication.data.SendData;

/**
 * Created by WTO on 2017/9/2 0002.
 *
 */
public class RpiDirectionChangeListener implements RockerView.OnDirectionChangeListener
{
    private TextView textView;

    public RpiDirectionChangeListener(TextView textView)
    {
        this.textView = textView;
    }

    @Override
    public void onStart()
    {
        textView.setText(null);
    }

    @Override
    public void direction(float lenX, float lenY, Point centerPoint, float regionRadius, float rockerRadius)
    {
        int max = 2 * Float.valueOf(regionRadius).intValue();
        int min = 0;
        float last = max - 2 * rockerRadius ;

        int X = Float.valueOf(lenX + regionRadius).intValue();
        X = X < min ? min : X;
        X = X > max ? max : X;
        int Y = Float.valueOf(lenY + regionRadius).intValue();
        Y = Y < min ? min : Y;
        Y = Y > max ? max : Y;

        int turnX = Float.valueOf((X - rockerRadius) / last * 100).intValue();
        int turnY = Float.valueOf((Y - rockerRadius) / last * 100).intValue();

        textView.setText("X = " + X + " Y = " + Y + " turnX = " + turnX + " turnY = " + turnY + " last = " + last);
        SendData.mapping[Passage.ONE.getNum()] = turnX;
        SendData.mapping[Passage.TWO.getNum()] = turnY;
    }

    @Override
    public void onFinish()
    {
        textView.setText(null);
    }
}
