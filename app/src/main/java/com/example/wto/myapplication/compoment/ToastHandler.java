package com.example.wto.myapplication.compoment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by WTO on 2017/8/9 0009.
 *
 */
public class ToastHandler extends Handler
{
    private Context context;
    public ToastHandler(Context context)
    {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg)
    {
        super.handleMessage(msg);
        Toast.makeText(context, msg.getData().getString("connect2Px4"), Toast.LENGTH_SHORT).show();
    }
}
