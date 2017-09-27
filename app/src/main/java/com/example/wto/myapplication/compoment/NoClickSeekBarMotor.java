package com.example.wto.myapplication.compoment;

import android.content.Context;
import android.util.AttributeSet;
import com.example.wto.myapplication.data.Passage;
import com.example.wto.myapplication.data.SendData;

/**
 * Created by WTO on 2017/9/6 0006.
 *
 */
public class NoClickSeekBarMotor extends NoClickSeekBarVertical
{
    private static final String TAG = "NoClickSeekBarMotor";

    private Passage passage = Passage.FOUR;

    public NoClickSeekBarMotor(Context context) {
        super(context);
    }

    public NoClickSeekBarMotor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public NoClickSeekBarMotor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void changeData(int progress)
    {
        super.changeData(progress);
        SendData.mapping[passage.getNum()] = progress;
    }

    @Override
    public void reset() {
        setProgress(passage.getInit());
    }


}
