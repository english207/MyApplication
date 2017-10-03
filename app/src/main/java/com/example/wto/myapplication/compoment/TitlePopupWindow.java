package com.example.wto.myapplication.compoment;

import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by WTO on 2017/9/6 0006.
 *
 */
public class TitlePopupWindow extends PopupWindow
{
    private Button change_ip_sure;
    private Button change_ip_cancle;
    private TextView change_ip;

    public TitlePopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public void setCompoment(Button change_ip_sure, Button change_ip_cancle, TextView change_ip)
    {
        this.change_ip_sure = change_ip_sure;
        this.change_ip_cancle = change_ip_cancle;
        this.change_ip = change_ip;
    }

    
}
