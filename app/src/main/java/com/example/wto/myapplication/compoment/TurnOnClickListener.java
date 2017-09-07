package com.example.wto.myapplication.compoment;

import android.view.View;
import com.example.wto.myapplication.data.Passage;
import com.example.wto.myapplication.data.SendData;

/**
 * Created by WTO on 2017/9/7 0007.
 *
 */
public class TurnOnClickListener implements View.OnClickListener
{
    private static final int max = 100;
    private static final int min = 0;

    private int type;
    private Passage passage;

    public TurnOnClickListener(Passage passage, int type)
    {
        this.type = type;
        this.passage = passage;
    }

    @Override
    public void onClick(View view)
    {
        int size = SendData.mapping[passage.getNum()] + type;
        size = size > max ? max : size < min ? min : size;
        SendData.mapping[passage.getNum()] = size;
    }
}
