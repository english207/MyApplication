package com.example.wto.myapplication.compoment;


import android.content.Context;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.example.wto.myapplication.data.Passage;
import com.example.wto.myapplication.data.SendData;

/**
 * Created by WTO on 2017/10/5 0005.
 *
 */
public class ModelOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener
{
    private static final int max = 100;
    private static final int min = 0;
    private Context context;
    private Passage passage;

    public ModelOnCheckedChangeListener(Context context, Passage passage)
    {
        this.context = context;
        this.passage = passage;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
    {
        if (!isChecked)
        {
            SendData.mapping[passage.getNum()] = min;
        }
        else
        {
            SendData.mapping[passage.getNum()] = max;
        }

        Toast.makeText(context, "model - " + (isChecked? "2" : "1"), Toast.LENGTH_SHORT).show();
    }
}
